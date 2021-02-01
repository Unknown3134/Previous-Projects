package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import slotmachine.model.SlotMachineImpl;

/**
 * Specific listener for when the user selects Reset bets
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class ResetBetListener implements ActionListener {

	private SlotMachineImpl model;

	public ResetBetListener(SlotMachineImpl model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			model.resetBets();
		} catch (Exception ex) {

		}
	}

}
