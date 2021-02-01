package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * This Listener is for closing certain dialog boxes that have error panels
 * 
 * It does this by making it non-visible.
 * 
 * @author Paul McKercher
 */
public class CloseDialogListener3 implements ActionListener {
	private JDialog dialog;
	private JPanel panel;

	public CloseDialogListener3(JDialog dialog, JPanel panel) {
		this.dialog = dialog;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
		panel.removeAll();

	}

}
