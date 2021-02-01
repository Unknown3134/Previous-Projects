package slotmachine.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;

/**
 * Key listener for closing dialog boxes if ENTER hit on OK or REGISTER
 * 
 * @author Paul McKercher
 */

public class KeyListener extends KeyAdapter {

	private JDialog dialog;

	public KeyListener(JDialog dialog) {
		this.dialog = dialog;
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			dialog.setVisible(false);

	}
}