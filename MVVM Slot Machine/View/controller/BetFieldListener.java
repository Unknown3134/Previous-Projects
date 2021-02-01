package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.LineNum;
import slotmachine.utilities.GuiUtilities;


/**
 * This Listener is for the fields in Place Bet
 * 
 * @author Paul McKercher
 */

public class BetFieldListener implements ActionListener {

	private SlotMachineImpl model;
	private JDialog dialog;
	private JTextField bet;
	private JLabel errorLabel;
	private Set<LineNum> lineSet;

	private JCheckBox line1,line2,line3,line4,line5;
	
	public BetFieldListener(JDialog dialog, SlotMachineImpl model, JTextField bet,
			JLabel errorLabel, JCheckBox cb1, JCheckBox cb2, JCheckBox cb3, JCheckBox cb4,
			JCheckBox cb5) {
		this.lineSet = new HashSet<LineNum>();
		this.dialog = dialog;
		this.model = model;
		this.bet = bet;
		this.errorLabel = errorLabel;

		this.line1 = cb1;
		this.line2 = cb2;
		this.line3 = cb3;
		this.line4 = cb4;
		this.line5 = cb5;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (bet.getText().isEmpty() || !bet.getText().matches("\\d+")) {
			errorLabel.setText("Please enter a number");
			errorLabel.setVisible(true);
		} else {
			errorLabel.setVisible(false);
			if (line1.isSelected()) {
				lineSet.add(LineNum.LINE1);
			}
			if (line2.isSelected()) {
				lineSet.add(LineNum.LINE2);
			}
			if (line3.isSelected()) {
				lineSet.add(LineNum.LINE3);
			}
			if (line4.isSelected()) {
				lineSet.add(LineNum.LINE4);
			}
			if (line5.isSelected()) {
				lineSet.add(LineNum.LINE5);
			}

			if (!line1.isSelected() &&!line2.isSelected() && !line3.isSelected() && !line4.isSelected() &&!line5.isSelected()) {
				errorLabel.setText("Please select a line");
				errorLabel.setVisible(true);
			} else {
				try {
					model.placeBet(Integer.parseInt(bet.getText()), lineSet);
					dialog.setVisible(false);
					errorLabel.setVisible(false);
					bet.setText(GuiUtilities.BLANK);
					line1.setSelected(false);
					line2.setSelected(false);
					line3.setSelected(false);
					line4.setSelected(false);
					line5.setSelected(false);
					lineSet.clear();

				} catch (Exception ex) {
					errorLabel.setText(ex.getMessage());
					errorLabel.setVisible(true);

				}
			}
		}
	}

}