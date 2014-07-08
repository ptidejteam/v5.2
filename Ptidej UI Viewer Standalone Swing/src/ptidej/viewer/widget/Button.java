/*
 * (c) Copyright 2002 Yann-Ga�l Gu�h�neuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Ga�l Gu�h�neuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.viewer.widget;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import ptidej.viewer.event.ISourceModelListener;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.ui.DesktopFrame;
import ptidej.viewer.ui.rulecard.IRuleCardListener;
import ptidej.viewer.ui.rulecard.RuleCardEvent;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2006/07/17
 */
public final class Button extends JButton implements ISourceModelListener,
		IRuleCardListener {

	private static final long serialVersionUID = 1L;
	public Button(final String label) {
		super();
		this.setText(label);
	}
	protected void fireActionPerformed(final ActionEvent anEvent) {
		DesktopFrame.getInstance().setCursor(
			Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		super.fireActionPerformed(anEvent);

		DesktopFrame.getInstance().setCursor(Cursor.getDefaultCursor());
	}
	public void ruleCardAvailable(final RuleCardEvent aRuleCardEvent) {
		this.setEnabled(true);
	}
	public void ruleCardChanged(final RuleCardEvent aRuleCardEvent) {
		this.ruleCardAvailable(aRuleCardEvent);
	}
	public void ruleCardUnavailable() {
		this.setEnabled(false);
	}
	public void sourceModelAvailable(final SourceAndGraphModelEvent aViewerEvent) {
		this.setEnabled(true);
	}
	public void sourceModelChanged(final SourceAndGraphModelEvent aViewerEvent) {
		this.sourceModelAvailable(aViewerEvent);
	}
	public void sourceModelUnavailable() {
		this.setEnabled(false);
	}
}
