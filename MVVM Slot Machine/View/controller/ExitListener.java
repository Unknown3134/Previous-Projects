package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for closing application from Exit in menu
 * 
 * @author Paul McKercher
 */

public class ExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);

	}

}
