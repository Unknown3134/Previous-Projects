package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import slotmachine.model.SlotMachineImpl;
import slotmachine.utilities.GuiUtilities;

/**
 * Specific listener for all the fields in the register dialog
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class RegisterFieldListener implements ActionListener, GuiUtilities {

	private JTextField name;
	private JTextField ID;
	private JTextField credits;
	private JDialog dialog;
	private SlotMachineImpl model;
	private JLabel errorLabel;
	private int tempCredits;

	public RegisterFieldListener(JDialog dialog, JTextField name, JTextField ID,
			JTextField credits, SlotMachineImpl model, JLabel errorLabel) {
		this.dialog = dialog;
		this.name = name;
		this.ID = ID;
		this.credits = credits;
		this.model = model;
		this.errorLabel = errorLabel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (credits.getText().isEmpty() || !credits.getText().matches("\\d+")) {
			errorLabel.setText("Please enter a positive number in credits");
			errorLabel.setVisible(true);
		} else {
			errorLabel.setVisible(false);
			try {
				tempCredits = Integer.parseInt(credits.getText());

			} catch (Exception num) {
				errorLabel.setText("Please enter a positive number in credits");
				errorLabel.setVisible(true);
			}
			try {

				model.registerPlayer(ID.getText(), name.getText(), tempCredits);
				dialog.setVisible(false);
				errorLabel.setVisible(false);

				name.setText(BLANK);
				ID.setText(BLANK);
				credits.setText(BLANK);
			} catch (Exception ex) {
				errorLabel.setText(ex.getMessage());
				errorLabel.setVisible(true);
			}

		}
	}
}
