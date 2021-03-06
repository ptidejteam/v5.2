// Copyright (c) 2006-2008 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#include <math.h>

#include "chrome/browser/views/tabs/dragged_tab_controller.h"

#include "chrome/browser/browser_window.h"
#include "chrome/browser/frame_util.h"
#include "chrome/browser/tab_contents.h"
#include "chrome/browser/views/tabs/dragged_tab_view.h"
#include "chrome/browser/views/tabs/hwnd_photobooth.h"
#include "chrome/browser/views/tabs/tab.h"
#include "chrome/browser/views/tabs/tab_strip.h"
#include "chrome/browser/web_contents.h"
#include "chrome/views/event.h"
#include "chrome/views/root_view.h"
#include "skia/include/SkBitmap.h"

static const int kHorizontalMoveThreshold = 16; // pixels

namespace {

///////////////////////////////////////////////////////////////////////////////
// WindowFinder
//  A WindowForPoint facility that can ignore 2 provided window HWNDs.
//
class WindowFinder {
 public:
  static HWND WindowForPoint(const gfx::Point& screen_point, HWND ignore1) {
    WindowFinder instance(screen_point, ignore1);
    return instance.GetResult();
  }
 private:
  WindowFinder(const gfx::Point& screen_point, HWND ignore1)
      : screen_point_(screen_point.ToPOINT()),
    ignore1_(ignore1),
    result_(NULL) {
  }

  static BOOL CALLBACK WindowEnumProc(HWND hwnd, LPARAM lParam) {
    WindowFinder* wf = reinterpret_cast<WindowFinder*>(lParam);
    if (hwnd == wf->ignore1_)
      return true;

    if (::IsWindowVisible(hwnd)) {
      CRect r;
      ::GetWindowRect(hwnd, &r);
      if (r.PtInRect(wf->screen_point_)) {
        // We always deal with the root HWND.
        wf->result_ = GetAncestor(hwnd, GA_ROOT);
        return FALSE;
      }
    }
    return TRUE;
  }

  HWND GetResult() {
    EnumThreadWindows(GetCurrentThreadId(), WindowEnumProc,
                      reinterpret_cast<LPARAM>(this));
    return result_;
  }

  POINT screen_point_;
  HWND ignore1_;
  HWND result_;

  DISALLOW_EVIL_CONSTRUCTORS(WindowFinder);
};

gfx::Point ConvertScreenPointToTabStripPoint(TabStrip* tabstrip,
                                             const gfx::Point& screen_point) {
  gfx::Point tabstrip_topleft;
  views::View::ConvertPointToScreen(tabstrip, &tabstrip_topleft);
  return gfx::Point(screen_point.x() - tabstrip_topleft.x(),
                    screen_point.y() - tabstrip_topleft.y());
}

}

///////////////////////////////////////////////////////////////////////////////
// DraggedTabController, public:

DraggedTabController::DraggedTabController(Tab* source_tab,
                                           TabStrip* source_tabstrip)
    : dragged_contents_(NULL),
      original_delegate_(NULL),
      source_tab_(source_tab),
      source_tabstrip_(source_tabstrip),
      source_model_index_(source_tabstrip->GetIndexOfTab(source_tab)),
      attached_tabstrip_(source_tabstrip),
      old_focused_view_(NULL),
      in_destructor_(false),
      last_move_screen_x_(0) {
  ChangeDraggedContents(
      source_tabstrip_->model()->GetTabContentsAt(source_model_index_));
  // Listen for Esc key presses.
  MessageLoopForUI::current()->AddObserver(this);
}

DraggedTabController::~DraggedTabController() {
  in_destructor_ = true;
  CleanUpSourceTab();
  MessageLoopForUI::current()->RemoveObserver(this);
  // Need to delete the view here manually _before_ we reset the dragged
  // contents to NULL, otherwise if the view is animating to its destination
  // bounds, it won't be able to clean up properly since its cleanup routine
  // uses GetIndexForDraggedContents, which will be invalid.
  view_.reset(NULL);
  ChangeDraggedContents(NULL); // This removes our observer.
}

void DraggedTabController::CaptureDragInfo(const gfx::Point& mouse_offset) {
  start_screen_point_ = GetCursorScreenPoint();
  mouse_offset_ = mouse_offset;
}

void DraggedTabController::Drag() {
  // Before we get to dragging anywhere, ensure that we consider ourselves
  // attached to the source tabstrip.
  if (source_tab_->IsVisible() && CanStartDrag())
    Attach(source_tabstrip_, gfx::Point());

  if (!source_tab_->IsVisible()) {
    SaveFocus();
    ContinueDragging();
  }
}

void DraggedTabController::EndDrag(bool canceled) {
  EndDragImpl(canceled ? CANCELED : NORMAL);
}

Tab* DraggedTabController::GetDragSourceTabForContents(
    TabContents* contents) const {
  if (attached_tabstrip_ == source_tabstrip_)
    return contents == dragged_contents_ ? source_tab_ : NULL;
  return NULL;
}

bool DraggedTabController::IsDragSourceTab(Tab* tab) const {
  return source_tab_ == tab;
}

///////////////////////////////////////////////////////////////////////////////
// DraggedTabController, PageNavigator implementation:

void DraggedTabController::OpenURLFromTab(TabContents* source,
                                          const GURL& url,
                                          const GURL& referrer,
                                          WindowOpenDisposition disposition,
                                          PageTransition::Type transition) {
  if (original_delegate_) {
    if (disposition == CURRENT_TAB)
      disposition = NEW_WINDOW;

    original_delegate_->OpenURLFromTab(source, url, referrer,
                                       disposition, transition);
  }
}

///////////////////////////////////////////////////////////////////////////////
// DraggedTabController, TabContentsDelegate implementation:

void DraggedTabController::NavigationStateChanged(const TabContents* source,
                                                  unsigned changed_flags) {
  if (view_.get())
    view_->Update();
}

void DraggedTabController::ReplaceContents(TabContents* source,
                                           TabContents* new_contents) {
  DCHECK(dragged_contents_ == source);
  source->set_delegate(NULL);
  new_contents->set_delegate(this);

  // If we're attached to a TabStrip, we need to tell the TabStrip that this
  // TabContents was replaced.
  if (attached_tabstrip_ && attached_tabstrip_->model() && dragged_contents_) {
    int index =
        attached_tabstrip_->model()->GetIndexOfTabContents(dragged_contents_);
    if (index != TabStripModel::kNoTab)
      attached_tabstrip_->model()->ReplaceTabContentsAt(index, new_contents);
  }

  // Update our internal state.
  ChangeDraggedContents(new_contents);

  if (view_.get())
    view_->Update();
}

void DraggedTabController::AddNewContents(TabContents* source,
                                          TabContents* new_contents,
                                          WindowOpenDisposition disposition,
                                          const gfx::Rect& initial_pos,
                                          bool user_gesture) {
  // Theoretically could be called while dragging if the page tries to
  // spawn a window. Route this message back to the browser in most cases.
  if (disposition == CURRENT_TAB) {
    ReplaceContents(source, new_contents);
  } else if (original_delegate_) {
    original_delegate_->AddNewContents(source, new_contents, disposition,
                                       initial_pos, user_gesture);
  }
}

void DraggedTabController::ActivateContents(TabContents* contents) {
  // Ignored.
}

void DraggedTabController::LoadingStateChanged(TabContents* source) {
  // It would be nice to respond to this message by changing the
  // screen shot in the dragged tab.
  if (view_.get())
    view_->Update();
}

void DraggedTabController::CloseContents(TabContents* source) {
  // Theoretically could be called by a window. Should be ignored
  // because window.close() is ignored (usually, even though this
  // method gets called.)
}

void DraggedTabController::MoveContents(TabContents* source,
                                        const gfx::Rect& pos) {
  // Theoretically could be called by a web page trying to move its
  // own window. Should be ignored since we're moving the window...
}

bool DraggedTabController::IsPopup(TabContents* source) {
  return false;
}

void DraggedTabController::ToolbarSizeChanged(TabContents* source,
                                            bool finished) {
  // Dragged tabs don't care about this.
}

void DraggedTabController::URLStarredChanged(TabContents* source,
                                             bool starred) {
  // Ignored.
}

void DraggedTabController::UpdateTargetURL(TabContents* source,
                                           const GURL& url) {
  // Ignored.
}

///////////////////////////////////////////////////////////////////////////////
// DraggedTabController, NotificationObserver implementation:

void DraggedTabController::Observe(NotificationType type,
                                   const NotificationSource& source,
                                   const NotificationDetails& details) {
  DCHECK(type == NOTIFY_TAB_CONTENTS_DESTROYED);
  DCHECK(Source<TabContents>(source).ptr() == dragged_contents_);
  EndDragImpl(TAB_DESTROYED);
}

///////////////////////////////////////////////////////////////////////////////
// DraggedTabController, MessageLoop::Observer implementation:

void DraggedTabController::WillProcessMessage(const MSG& msg) {
}

void DraggedTabController::DidProcessMessage(const MSG& msg) {
  // If the user presses ESC during a drag, we need to abort and revert things
  // to the way they were. This is the most reliable way to do this since no
  // single view or window reliably receives events throughout all the various
  // kinds of tab dragging.
  if (msg.message == WM_KEYDOWN && msg.wParam == VK_ESCAPE)
    EndDrag(true);
}

///////////////////////////////////////////////////////////////////////////////
// DraggedTabController, private:

void DraggedTabController::InitWindowCreatePoint() {
  window_create_point_.SetPoint(mouse_offset_.x(), mouse_offset_.y());
  Tab* first_tab = attached_tabstrip_->GetTabAt(0);
  views::View::ConvertPointToContainer(first_tab, &window_create_point_);
}

gfx::Point DraggedTabController::GetWindowCreatePoint() const {
  POINT pt;
  GetCursorPos(&pt);
  return gfx::Point(pt.x - window_create_point_.x(),
                    pt.y - window_create_point_.y());
}

void DraggedTabController::ChangeDraggedContents(TabContents* new_contents) {
  if (dragged_contents_) {
    NotificationService::current()->RemoveObserver(this,
      NOTIFY_TAB_CONTENTS_DESTROYED,
      Source<TabContents>(dragged_contents_));
  }
  dragged_contents_ = new_contents;
  if (dragged_contents_) {
    NotificationService::current()->AddObserver(this,
      NOTIFY_TAB_CONTENTS_DESTROYED,
      Source<TabContents>(dragged_contents_));
  }
}

void DraggedTabController::SaveFocus() {
  if (!old_focused_view_) {
    old_focused_view_ = source_tab_->GetRootView()->GetFocusedView();
    source_tab_->GetRootView()->FocusView(source_tab_);
  }
}

void DraggedTabController::RestoreFocus() {
  if (old_focused_view_ && attached_tabstrip_ == source_tabstrip_)
    old_focused_view_->GetRootView()->FocusView(old_focused_view_);
  old_focused_view_ = NULL;
}

bool DraggedTabController::CanStartDrag() const {
  // Determine if the mouse has moved beyond a minimum elasticity distance in
  // any direction from the starting point.
  static const int kMinimumDragDistance = 10;
  gfx::Point screen_point = GetCursorScreenPoint();
  int x_offset = abs(screen_point.x() - start_screen_point_.x());
  int y_offset = abs(screen_point.y() - start_screen_point_.y());
  return sqrt(pow(static_cast<float>(x_offset), 2) +
              pow(static_cast<float>(y_offset), 2)) > kMinimumDragDistance;
}

void DraggedTabController::ContinueDragging() {
  EnsureDraggedView();

  // Note that the coordinates given to us by |drag_event| are basically
  // useless, since they're in source_tab_ coordinates. On the surface, you'd
  // think we could just convert them to screen coordinates, however in the
  // situation where we're dragging the last tab in a window when multiple
  // windows are open, the coordinates of |source_tab_| are way off in
  // hyperspace since the window was moved there instead of being closed so
  // that we'd keep receiving events. And our ConvertPointToScreen methods
  // aren't really multi-screen aware. So really it's just safer to get the
  // actual position of the mouse cursor directly from Windows here, which is
  // guaranteed to be correct regardless of monitor config.
  gfx::Point screen_point = GetCursorScreenPoint();

  // Determine whether or not we have dragged over a compatible TabStrip in
  // another browser window. If we have, we should attach to it and start
  // dragging within it.
  TabStrip* target_tabstrip = GetTabStripForPoint(screen_point);
  if (target_tabstrip != attached_tabstrip_) {
    // Make sure we're fully detached from whatever TabStrip we're attached to
    // (if any).
    if (attached_tabstrip_)
      Detach();
    if (target_tabstrip)
      Attach(target_tabstrip, screen_point);
  }
  MoveTab(screen_point);
}

void DraggedTabController::MoveTab(const gfx::Point& screen_point) {
  gfx::Point dragged_view_point = GetDraggedViewPoint(screen_point);

  if (attached_tabstrip_) {
    // Determine the horizontal move threshold. This is dependent on the width
    // of tabs. The smaller the tabs compared to the standard size, the smaller
    // the threshold.
    double unselected, selected;
    attached_tabstrip_->GetCurrentTabWidths(&unselected, &selected);
    double ratio = unselected / Tab::GetStandardSize().width();
    int threshold = static_cast<int>(ratio * kHorizontalMoveThreshold);

    // Update the model, moving the TabContents from one index to another. Do
    // this only if we have moved a minimum distance since the last reorder (to
    // prevent jitter).
    if (abs(screen_point.x() - last_move_screen_x_) > threshold) {
      TabStripModel* attached_model = attached_tabstrip_->model();
      int from_index =
          attached_model->GetIndexOfTabContents(dragged_contents_);
      gfx::Rect bounds = GetDraggedViewTabStripBounds(dragged_view_point);
      int to_index = GetInsertionIndexForDraggedBounds(bounds);
      to_index = NormalizeIndexToAttachedTabStrip(to_index);
      if (from_index != to_index) {
        last_move_screen_x_ = screen_point.x();
        attached_model->MoveTabContentsAt(from_index, to_index);
      }
    }
  }
  // Move the View. There are no changes to the model if we're detached.
  view_->MoveTo(dragged_view_point);
}

TabStrip* DraggedTabController::GetTabStripForPoint(
    const gfx::Point& screen_point) const {
  HWND dragged_hwnd = view_->GetContainer()->GetHWND();
  HWND other_hwnd = WindowFinder::WindowForPoint(screen_point, dragged_hwnd);
  if (!other_hwnd)
    return NULL;

  BrowserWindow* other_frame = FrameUtil::GetBrowserWindowForHWND(other_hwnd);
  if (other_frame) {
    TabStrip* other_tabstrip = other_frame->GetTabStrip();
    if (!other_tabstrip->IsCompatibleWith(source_tabstrip_))
      return NULL;
    return GetTabStripIfItContains(other_tabstrip, screen_point);
  }
  return NULL;
}

TabStrip* DraggedTabController::GetTabStripIfItContains(
    TabStrip* tabstrip, const gfx::Point& screen_point) const {
  static const int kVerticalDetachMagnetism = 15;
  // Make sure the specified screen point is actually within the bounds of the
  // specified tabstrip...
  gfx::Rect tabstrip_bounds = GetViewScreenBounds(tabstrip);
  if (screen_point.x() < tabstrip_bounds.right() &&
      screen_point.x() >= tabstrip_bounds.x()) {
    // TODO(beng): make this be relative to the start position of the mouse for
    // the source TabStrip.
    int upper_threshold = tabstrip_bounds.bottom() + kVerticalDetachMagnetism;
    int lower_threshold = tabstrip_bounds.y() - kVerticalDetachMagnetism;
    if (screen_point.y() >= lower_threshold &&
        screen_point.y() <= upper_threshold) {
      return tabstrip;
    }
  }
  return NULL;
}

void DraggedTabController::Attach(TabStrip* attached_tabstrip,
                                  const gfx::Point& screen_point) {
  attached_tabstrip_ = attached_tabstrip;
  InitWindowCreatePoint();
  attached_tabstrip_->GenerateIdealBounds();

  // We don't need the photo-booth while we're attached.
  photobooth_.reset(NULL);

  Tab* tab = GetTabMatchingDraggedContents(attached_tabstrip_);

  // Update the View first, so we can ask it for its bounds and determine
  // where to insert the hidden Tab.

  // If this is the first time Attach is called for this drag, we're attaching
  // to the source TabStrip, and we should assume the tab count already
  // includes this Tab since we haven't been detached yet. If we don't do this,
  // the dragged representation will be a different size to others in the
  // TabStrip.
  int tab_count = attached_tabstrip_->GetTabCount();
  if (!tab)
    ++tab_count;
  double unselected_width, selected_width = 0;
  attached_tabstrip_->GetDesiredTabWidths(tab_count, &unselected_width,
                                          &selected_width);
  EnsureDraggedView();
  view_->Attach(static_cast<int>(selected_width));

  if (!tab) {
    // There is no Tab in |attached_tabstrip| that corresponds to the dragged
    // TabContents. We must now create one.

    // Remove ourselves as the delegate now that the dragged TabContents is
    // being inserted back into a Browser.
    dragged_contents_->set_delegate(NULL);
    original_delegate_ = NULL;

    // Return the TabContents' to normalcy.
    dragged_contents_->set_capturing_contents(false);

    // We need to ask the TabStrip we're attached to to ensure that the ideal
    // bounds for all its tabs are correctly generated, because the calculation
    // in GetInsertionIndexForDraggedBounds needs them to be to figure out the
    // appropriate insertion index.
    attached_tabstrip_->GenerateIdealBounds();

    // Inserting counts as a move. We don't want the tabs to jitter when the
    // user moves the tab immediately after attaching it.
    last_move_screen_x_ = screen_point.x();

    // Figure out where to insert the tab based on the bounds of the dragged
    // representation and the ideal bounds of the other Tabs already in the
    // strip. ("ideal bounds" are stable even if the Tabs' actual bounds are
    // changing due to animation).
    gfx::Rect bounds = GetDraggedViewTabStripBounds(screen_point);
    int index = GetInsertionIndexForDraggedBounds(bounds);
    index = std::max(std::min(index, attached_tabstrip_->model()->count()), 0);
    attached_tabstrip_->model()->InsertTabContentsAt(index, dragged_contents_,
        true, false);

    tab = GetTabMatchingDraggedContents(attached_tabstrip_);
  }
  DCHECK(tab); // We should now have a tab.
  tab->SetVisible(false);

  // Move the corresponding window to the front.
  attached_tabstrip_->GetContainer()->MoveToFront(true);
}

void DraggedTabController::Detach() {
  // Prevent the TabContents' HWND from being hidden by any of the model
  // operations performed during the drag.
  dragged_contents_->set_capturing_contents(true);

  // Update the Model.
  TabStripModel* attached_model = attached_tabstrip_->model();
  int index = attached_model->GetIndexOfTabContents(dragged_contents_);
  if (index >= 0 && index < attached_model->count()) {
    // Sometimes, DetachTabContentsAt has consequences that result in
    // attached_tabstrip_ being set to NULL, so we need to save it first.
    TabStrip* attached_tabstrip = attached_tabstrip_;
    attached_model->DetachTabContentsAt(index);
    attached_tabstrip->SchedulePaint();
  }

  // If we've removed the last Tab from the TabStrip, hide the frame now.
  if (attached_model->empty())
    HideFrame();

  // Set up the photo booth to start capturing the contents of the dragged
  // TabContents.
  if (!photobooth_.get())
    photobooth_.reset(new HWNDPhotobooth(dragged_contents_->GetContainerHWND()));

  // Update the View. This NULL check is necessary apparently in some
  // conditions during automation where the view_ is destroyed inside a
  // function call preceding this point but after it is created.
  if (view_.get())
    view_->Detach(photobooth_.get());

  // We need to be the delegate so we receive messages about stuff,
  // otherwise our dragged_contents() may be replaced and subsequently
  // collected/destroyed while the drag is in process, leading to
  // nasty crashes.
  original_delegate_ = dragged_contents_->delegate();
  dragged_contents_->set_delegate(this);

  attached_tabstrip_ = NULL;
}

int DraggedTabController::GetInsertionIndexForDraggedBounds(
    const gfx::Rect& dragged_bounds) const {
  int right_tab_x = 0;

  // If the UI layout of the tab strip is right-to-left, we need to mirror the
  // bounds of the dragged tab before performing the drag/drop related
  // calculations. We mirror the dragged bounds because we determine the
  // position of each tab on the tab strip by calling GetBounds() (without the
  // mirroring transformation flag) which effectively means that even though
  // the tabs are rendered from right to left, the code performs the
  // calculation as if the tabs are laid out from left to right. Mirroring the
  // dragged bounds adjusts the coordinates of the tab we are dragging so that
  // it uses the same orientation used by the tabs on the tab strip.
  gfx::Rect adjusted_bounds(dragged_bounds);
  adjusted_bounds.set_x(
      attached_tabstrip_->MirroredLeftPointForRect(adjusted_bounds));

  for (int i = 0; i < attached_tabstrip_->GetTabCount(); ++i) {
    gfx::Rect ideal_bounds = attached_tabstrip_->GetIdealBounds(i);
    gfx::Rect left_half = ideal_bounds;
    left_half.set_width(left_half.width() / 2);
    gfx::Rect right_half = ideal_bounds;
    right_half.set_width(ideal_bounds.width() - left_half.width());
    right_half.set_x(left_half.right());
    right_tab_x = right_half.right();
    if (adjusted_bounds.x() >= right_half.x() &&
        adjusted_bounds.x() < right_half.right()) {
      return i + 1;
    } else if (adjusted_bounds.x() >= left_half.x() &&
               adjusted_bounds.x() < left_half.right()) {
      return i;
    }
  }
  if (adjusted_bounds.right() > right_tab_x)
    return attached_tabstrip_->model()->count();
  return TabStripModel::kNoTab;
}

gfx::Rect DraggedTabController::GetDraggedViewTabStripBounds(
    const gfx::Point& screen_point) {
  gfx::Point client_point =
      ConvertScreenPointToTabStripPoint(attached_tabstrip_, screen_point);
  gfx::Size view_size = view_->attached_tab_size();
  return gfx::Rect(client_point.x(), client_point.y(),
                   view_size.width(), view_size.height());
}

gfx::Point DraggedTabController::GetDraggedViewPoint(
    const gfx::Point& screen_point) {
  int x = screen_point.x() - mouse_offset_.x();
  int y = screen_point.y() - mouse_offset_.y();

  // If we're not attached, we just use x and y from above.
  if (attached_tabstrip_) {
    gfx::Rect tabstrip_bounds = GetViewScreenBounds(attached_tabstrip_);
    // Snap the dragged Tab to the TabStrip if we are attached, detaching
    // only when the mouse position (screen_point) exceeds the screen bounds
    // of the TabStrip.
    if (x < tabstrip_bounds.x() && screen_point.x() >= tabstrip_bounds.x())
      x = tabstrip_bounds.x();

    gfx::Size tab_size = view_->attached_tab_size();
    int vertical_drag_magnetism = tab_size.height() * 2;
    int vertical_detach_point = tabstrip_bounds.y() - vertical_drag_magnetism;
    if (y < tabstrip_bounds.y() && screen_point.y() >= vertical_detach_point)
      y = tabstrip_bounds.y();

    // Make sure the Tab can't be dragged off the right side of the TabStrip
    // unless the mouse pointer passes outside the bounds of the strip by
    // clamping the position of the dragged window to the tabstrip width less
    // the width of one tab until the mouse pointer (screen_point) exceeds the
    // screen bounds of the TabStrip.
    int max_x = tabstrip_bounds.right() - tab_size.width();
    int max_y = tabstrip_bounds.bottom() - tab_size.height();
    if (x > max_x && screen_point.x() <= tabstrip_bounds.right())
      x = max_x;
    if (y > max_y && screen_point.y() <=
        (tabstrip_bounds.bottom() + vertical_drag_magnetism)) {
      y = max_y;
    }
  }
  return gfx::Point(x, y);
}


Tab* DraggedTabController::GetTabMatchingDraggedContents(
    TabStrip* tabstrip) const {
  int index = tabstrip->model()->GetIndexOfTabContents(dragged_contents_);
  return index == TabStripModel::kNoTab ? NULL : tabstrip->GetTabAt(index);
}

void DraggedTabController::EndDragImpl(EndDragType type) {
  bool destroy_now = true;
  if (type != TAB_DESTROYED) {
    // We only finish up the drag if we were actually dragging. If we never
    // constructed a view, the user just clicked and released and didn't move the
    // mouse enough to trigger a drag.
    if (view_.get()) {
      RestoreFocus();
      if (type == CANCELED) {
        RevertDrag();
      } else {
        destroy_now = CompleteDrag();
      }
    }
  } else {
    // If we get here it means the NavigationController is going down. Don't
    // attempt to do any cleanup other than resetting the delegate (if we're
    // still the delegate).
    if (dragged_contents_ && dragged_contents_->delegate() == this)
      dragged_contents_->set_delegate(NULL);
    dragged_contents_ = NULL;
    attached_tabstrip_ = NULL;
  }
  // If we're not destroyed now, we'll be destroyed asynchronously later.
  if (destroy_now)
    source_tabstrip_->DestroyDragController();
}

void DraggedTabController::RevertDrag() {
  // We save this here because code below will modify |attached_tabstrip_|.
  bool restore_frame = attached_tabstrip_ != source_tabstrip_;
  if (attached_tabstrip_) {
    int index = attached_tabstrip_->model()->GetIndexOfTabContents(
        dragged_contents_);
    if (attached_tabstrip_ != source_tabstrip_) {
      // The Tab was inserted into another TabStrip. We need to put it back
      // into the original one.
      attached_tabstrip_->model()->DetachTabContentsAt(index);
      // TODO(beng): (Cleanup) seems like we should use Attach() for this
      //             somehow.
      attached_tabstrip_ = source_tabstrip_;
      source_tabstrip_->model()->InsertTabContentsAt(source_model_index_,
          dragged_contents_, true, false);
    } else {
      // The Tab was moved within the TabStrip where the drag was initiated.
      // Move it back to the starting location.
      source_tabstrip_->model()->MoveTabContentsAt(index, source_model_index_);
    }
  } else {
    // TODO(beng): (Cleanup) seems like we should use Attach() for this
    //             somehow.
    attached_tabstrip_ = source_tabstrip_;
    // The Tab was detached from the TabStrip where the drag began, and has not
    // been attached to any other TabStrip. We need to put it back into the
    // source TabStrip.
    source_tabstrip_->model()->InsertTabContentsAt(source_model_index_,
        dragged_contents_, true, false);
  }
  // If we're not attached to any TabStrip, or attached to some other TabStrip,
  // we need to restore the bounds of the original TabStrip's frame, in case
  // it has been hidden.
  if (restore_frame) {
    if (!restore_bounds_.IsEmpty()) {
      HWND frame_hwnd = source_tabstrip_->GetContainer()->GetHWND();
      MoveWindow(frame_hwnd, restore_bounds_.x(), restore_bounds_.y(),
                 restore_bounds_.width(), restore_bounds_.height(), TRUE);
    }
  }
  source_tab_->SetVisible(true);
}

bool DraggedTabController::CompleteDrag() {
  bool destroy_immediately = true;
  if (attached_tabstrip_) {
    // We don't need to do anything other than make the Tab visible again,
    // since the dragged View is going away.
    Tab* tab = GetTabMatchingDraggedContents(attached_tabstrip_);
    view_->AnimateToBounds(
        GetViewScreenBounds(tab),
        NewCallback(this, &DraggedTabController::OnAnimateToBoundsComplete));
    destroy_immediately = false;
  } else {
    // Compel the model to construct a new window for the detached TabContents.
    source_tabstrip_->model()->TearOffTabContents(
        dragged_contents_,
        GetWindowCreatePoint());
    CleanUpHiddenFrame();
  }

  return destroy_immediately;
}

void DraggedTabController::EnsureDraggedView() {
  if (!view_.get()) {
    RECT wr;
    GetWindowRect(dragged_contents_->GetContainerHWND(), &wr);

    view_.reset(new DraggedTabView(dragged_contents_, mouse_offset_,
        gfx::Size(wr.right - wr.left, wr.bottom - wr.top)));
  }
}

gfx::Point DraggedTabController::GetCursorScreenPoint() const {
  POINT pt;
  GetCursorPos(&pt);
  return gfx::Point(pt);
}

gfx::Rect DraggedTabController::GetViewScreenBounds(views::View* view) const {
  gfx::Point view_topleft;
  views::View::ConvertPointToScreen(view, &view_topleft);
  gfx::Rect view_screen_bounds = view->GetLocalBounds(true);
  view_screen_bounds.Offset(view_topleft.x(), view_topleft.y());
  return view_screen_bounds;
}

int DraggedTabController::NormalizeIndexToAttachedTabStrip(int index) const {
  DCHECK(attached_tabstrip_) << "Can only be called when attached!";
  TabStripModel* attached_model = attached_tabstrip_->model();
  if (index >= attached_model->count())
    return attached_model->count() - 1;
  if (index == TabStripModel::kNoTab)
    return 0;
  return index;
}

void DraggedTabController::HideFrame() {
  // We don't actually hide the window, rather we just move it way off-screen.
  // If we actually hide it, we stop receiving drag events.
  HWND frame_hwnd = source_tabstrip_->GetContainer()->GetHWND();
  RECT wr;
  GetWindowRect(frame_hwnd, &wr);
  MoveWindow(frame_hwnd, 0xFFFF, 0xFFFF, wr.right - wr.left,
             wr.bottom - wr.top, TRUE);

  // We also save the bounds of the window prior to it being moved, so that if
  // the drag session is aborted we can restore them.
  restore_bounds_ = gfx::Rect(wr);
}

void DraggedTabController::CleanUpHiddenFrame() {
  // If the model we started dragging from is now empty, we must ask the
  // delegate to close the frame.
  if (source_tabstrip_->model()->empty())
    source_tabstrip_->model()->delegate()->CloseFrameAfterDragSession();
}

void DraggedTabController::CleanUpSourceTab() {
  // If we were attached to the source TabStrip, source Tab will be in use
  // as the Tab. If we were detached or attached to another TabStrip, we can
  // safely remove this item and delete it now.
  if (attached_tabstrip_ != source_tabstrip_) {
    source_tabstrip_->DestroyDraggedSourceTab(source_tab_);
    source_tab_ = NULL;
  }
}

void DraggedTabController::OnAnimateToBoundsComplete() {
  // Sometimes, for some reason, in automation we can be called back on a
  // detach even though we aren't attached to a TabStrip. Guard against that.
  if (attached_tabstrip_) {
    Tab* tab = GetTabMatchingDraggedContents(attached_tabstrip_);
    if (tab)
      tab->SetVisible(true);
  }
  CleanUpHiddenFrame();

  if (!in_destructor_)
    source_tabstrip_->DestroyDragController();
}

