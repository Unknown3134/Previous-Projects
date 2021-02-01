package slotmachine.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import slotmachine.controller.CloseDialogListener2;
import slotmachine.controller.KeyListener2;
import slotmachine.controller.QuickBetFieldListener;
import slotmachine.controller.WindowListener;
import slotmachine.model.SlotMachineImpl;

/**
 * This Dialog takes a bet amount for only Line 1
 * 
 * 
 * 
 * @author Paul McKercher
 */
@SuppressWarnings("serial")
public class QuickBetDialog extends JDialog {

	public QuickBetDialog(JFrame parent, SlotMachineImpl model) {
		super(parent,true);
		setLayout(new GridLayout(3, 1));
		setTitle("Quick Bet");
		setSize(new Dimension(600, 200));
		setLocationRelativeTo(parent);
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("Place Bet");

		JButton cancel = new JButton("Cancel");
		JPanel playerTextPanel = new JPanel();
		JPanel errorPanel = new JPanel();

		JLabel errorLabel = new JLabel();
		errorLabel.setForeground(Color.RED);
		errorPanel.add(errorLabel);

		add(playerTextPanel);
		add(errorPanel);
		add(buttonPanel);
		buttonPanel.add(ok, BorderLayout.WEST);
		buttonPanel.add(cancel, BorderLayout.EAST);
		JTextField bet = new JTextField(10);

		JLabel labelID = new JLabel("Enter Bet for Line 1");

		playerTextPanel.add(new JPanel());
		playerTextPanel.setLayout(new FlowLayout());

		playerTextPanel.add(new JPanel());
		playerTextPanel.add(labelID);

		playerTextPanel.add(bet);

		errorLabel.setVisible(false);
		playerTextPanel.add(new JPanel());

		cancel.addActionListener(new CloseDialogListener2(this, errorLabel));
		ok.addActionListener(new QuickBetFieldListener(this, model, bet, errorLabel));
		cancel.addKeyListener(new KeyListener2(this, errorLabel));
		addWindowListener(new WindowListener(this, errorLabel));

	}
}
