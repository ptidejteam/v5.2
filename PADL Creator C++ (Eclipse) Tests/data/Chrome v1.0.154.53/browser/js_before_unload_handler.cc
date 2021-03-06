// Copyright (c) 2006-2008 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#include "chrome/browser/js_before_unload_handler.h"

#include "chrome/browser/app_modal_dialog_queue.h"
#include "chrome/common/l10n_util.h"
#include "chrome/views/message_box_view.h"

#include "generated_resources.h"

///////////////////////////////////////////////////////////////////////////////
// JavascriptBeforeUnloadHandler, public:

// static
void JavascriptBeforeUnloadHandler::RunBeforeUnloadDialog(
    WebContents* web_contents,
    const std::wstring& message_text,
    IPC::Message* reply_msg) {
  std::wstring full_message =
    message_text + L"\n\n" +
    l10n_util::GetString(IDS_BEFOREUNLOAD_MESSAGEBOX_FOOTER);
  JavascriptBeforeUnloadHandler* handler =
      new JavascriptBeforeUnloadHandler(web_contents, full_message, reply_msg);
  AppModalDialogQueue::AddDialog(handler);
}

//////////////////////////////////////////////////////////////////////////////
// JavascriptBeforeUnloadHandler, views::DialogDelegate implementation:

std::wstring JavascriptBeforeUnloadHandler::GetWindowTitle() const {
  return l10n_util::GetString(IDS_BEFOREUNLOAD_MESSAGEBOX_TITLE);
}

std::wstring JavascriptBeforeUnloadHandler::GetDialogButtonLabel(
    DialogButton button) const {
  if (button == DialogDelegate::DIALOGBUTTON_OK) {
    return l10n_util::GetString(IDS_BEFOREUNLOAD_MESSAGEBOX_OK_BUTTON_LABEL);
  } else if (button == DialogDelegate::DIALOGBUTTON_CANCEL) {
    return l10n_util::GetString(
        IDS_BEFOREUNLOAD_MESSAGEBOX_CANCEL_BUTTON_LABEL);
  }
  return L"";
}

JavascriptBeforeUnloadHandler::JavascriptBeforeUnloadHandler(
    WebContents* web_contents,
    const std::wstring& message_text,
    IPC::Message* reply_msg)
        : JavascriptMessageBoxHandler(web_contents,
                                      MessageBoxView::kIsJavascriptConfirm,
                                      message_text,
                                      L"",
                                      false,
                                      reply_msg) {}

