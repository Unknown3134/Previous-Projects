package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * This Listener is for closing any opened dialog box.
 * 
 * It does this by making it non-visible.
 * 
 * @author Paul McKercher
 */
public class CloseDialogListener implements ActionListener {

	// stores the JDialog object, allowing access to it's methods
	private JDialog dialog;

	public CloseDialogListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		dialog.setVisible(false);

	}
}
