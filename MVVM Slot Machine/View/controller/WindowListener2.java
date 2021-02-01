package slotmachine.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Specific listener for when the user clicks X on a dialog
 * 
 * This one sets panels to clear
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class WindowListener2 extends WindowAdapter {

	private JDialog dialog;
	private JPanel panel;

	public WindowListener2(JDialog dialog, JPanel panel) {
		this.panel = panel;
		this.dialog = dialog;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dialog.setVisible(false);
		panel.removeAll();
	}
}
