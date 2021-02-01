package slotmachine.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Key listener for closing dialog boxes if ENTER hit on OK or REGISTER
 * 
 * This one makes any error labels non-visible
 * 
 * @author Paul McKercher
 */

public class KeyListener2 extends KeyAdapter {

	private JDialog dialog;
	private JLabel label;

	public KeyListener2(JDialog dialog, JLabel label) {
		this.dialog = dialog;
		this.label = label;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			dialog.setVisible(false);
			label.setVisible(false);
		}

	}
}