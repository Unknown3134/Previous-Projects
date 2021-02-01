package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import slotmachine.controller.BetFieldListener;
import slotmachine.controller.CloseDialogListener2;
import slotmachine.controller.KeyListener2;
import slotmachine.controller.WindowListener;
import slotmachine.model.SlotMachineImpl;

/**
 * Takes selected lines and bet amounts. This is the Bet Dialog
 * 
 * 
 * 
 * @author Paul McKercher
 */
@SuppressWarnings("serial")
public class BetDialog extends JDialog {

	public BetDialog(JFrame parent, SlotMachineImpl model) {
		super(parent,true);
		setLayout(new GridLayout(4, 1));

		setTitle("Place a Bet");
		setSize(new Dimension(600, 200));
		setLocationRelativeTo(parent);
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("Place Bet");

		JButton cancel = new JButton("Cancel");
		JPanel betTextPanel = new JPanel();
		JPanel errorPanel = new JPanel();
		JPanel lineTextPanel = new JPanel();
		JLabel errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		errorPanel.add(errorLabel);

		add(betTextPanel);
		add(errorPanel);
		add(lineTextPanel);
		add(buttonPanel);
		JTextField bet = new JTextField(10);

		betTextPanel.add(bet);
		JLabel labelID = new JLabel("Enter Bet for Multiple Lines");
		betTextPanel.add(labelID);
		buttonPanel.add(ok, BorderLayout.WEST);
		buttonPanel.add(cancel, BorderLayout.EAST);

		JCheckBox cb1 = new JCheckBox("Line 1");
		cb1.setBounds(100, 100, 150, 20);

		JCheckBox cb2 = new JCheckBox("Line 2");
		cb2.setBounds(100, 100, 150, 20);
		JCheckBox cb3 = new JCheckBox("Line 3");
		cb3.setBounds(100, 100, 150, 20);
		JCheckBox cb4 = new JCheckBox("Line 4");
		cb4.setBounds(100, 100, 150, 20);
		JCheckBox cb5 = new JCheckBox("Line 5");
		cb5.setBounds(100, 100, 150, 20);

		betTextPanel.add(bet);
		lineTextPanel.add(cb1);
		lineTextPanel.add(cb2);
		lineTextPanel.add(cb3);
		lineTextPanel.add(cb4);
		lineTextPanel.add(cb5);

		errorLabel.setVisible(false);

		cancel.addActionListener(new CloseDialogListener2(this, errorLabel));

		ok.addActionListener(new BetFieldListener(this, model, bet, errorLabel, cb1, cb2,
				cb3, cb4, cb5));
		cancel.addKeyListener(new KeyListener2(this, errorLabel));
		addWindowListener(new WindowListener(this, errorLabel));

	}
}
