package slotmachine.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * Listener for launching About dialog box, stores the main frame
 * 
 * @author Paul McKercher
 */



public class LaunchDialogListener implements ActionListener {
	
	private JDialog dialog;
	
	public LaunchDialogListener(JDialog dialog) {
		this.dialog =dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// refers to main frame and makes about dialog (single instance) visible
		dialog.setVisible(true);
       
		
	}

}
