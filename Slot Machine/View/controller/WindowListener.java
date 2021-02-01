package slotmachine.controller;

/**
 * Specific listener for when the user clicks X on a dialog
 * 
 * This one sets error Labels to non visible
 * 
 * 
 * 
 * @author Paul McKercher
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class WindowListener extends WindowAdapter {

	private JDialog dialog;
	private JLabel label;

	public WindowListener(JDialog dialog, JLabel label) {
		this.label = label;
		this.dialog = dialog;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dialog.setVisible(false);
		label.setVisible(false);
	}
}
