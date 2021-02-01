package slotmachine.controller;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * This Listener is for closing any certain dialogs that have error labels
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class CloseDialogListener2 implements ActionListener {

	
	private JDialog dialog;
	private JLabel label;

	public CloseDialogListener2(JDialog dialog, JLabel label) {
		this.dialog = dialog;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		dialog.setVisible(false);
		label.setVisible(false);

	}
}