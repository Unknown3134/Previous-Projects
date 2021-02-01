package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import slotmachine.model.SlotMachineImpl;
import slotmachine.utilities.GuiUtilities;
/**
 * Specific listener for the field in the quick bet dialog
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class QuickBetFieldListener implements ActionListener {

	private SlotMachineImpl model;
	private JDialog dialog;
	private JTextField bet;
	private JLabel errorLabel;

	public QuickBetFieldListener(JDialog dialog, SlotMachineImpl model, JTextField bet,
			JLabel errorLabel) {
		this.dialog = dialog;
		this.model = model;
		this.bet = bet;
		this.errorLabel = errorLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (bet.getText().isEmpty() || !bet.getText().matches("\\d+")) {
			errorLabel.setText("Please enter a number");
			errorLabel.setVisible(true);
		} else {

			try {
				model.placeBet(Integer.parseInt(bet.getText()));
				dialog.setVisible(false);
				errorLabel.setVisible(false);
				bet.setText(GuiUtilities.BLANK);
			} catch (Exception ex) {
				errorLabel.setText(ex.getMessage());
				errorLabel.setVisible(true);

			}
		}
	}

}
