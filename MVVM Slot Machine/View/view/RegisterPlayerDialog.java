package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import slotmachine.controller.CloseDialogListener2;
import slotmachine.controller.IconSelectListener;
import slotmachine.controller.KeyListener;
import slotmachine.controller.KeyListener2;
import slotmachine.controller.RegisterFieldListener;
import slotmachine.controller.WindowListener;
import slotmachine.model.SlotMachineImpl;
import slotmachine.view.model.IconNum;
import slotmachine.view.model.ViewModelSettings;

/**
 * JDialog that allows a user to register as a player. Is instantiated on
 * program open. Made visible when called from MenuBar.
 * 
 * Takes name, ID and credits as fields for registration.
 * 
 * Choose the avatar/player Icon
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class RegisterPlayerDialog extends JDialog {

	public RegisterPlayerDialog(SlotMachineFrame parent, SlotMachineImpl model,
			JPanel sideInfo, ViewModelSettings viewModel) {
		super(parent,true);
		setLayout(new GridLayout(2, 3));
		setTitle("Register Player");
		setSize(new Dimension(750, 200));
		setLocationRelativeTo(parent);
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("Register");

		JButton cancel = new JButton("Cancel");
		JPanel playerTextPanel = new JPanel();

		add(playerTextPanel, BorderLayout.NORTH);
		JPanel iconPanel = new JPanel();
		JLabel errorLabel = new JLabel();
		JPanel errorPanel = new JPanel();
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);

		errorPanel.add(errorLabel, BorderLayout.CENTER);
		add(errorPanel);
		add(iconPanel);
		iconPanel.add(new JLabel("Select an Avatar"));

		add(buttonPanel, BorderLayout.EAST);

		buttonPanel.add(ok, BorderLayout.WEST);
		buttonPanel.add(cancel, BorderLayout.EAST);

		JTextField name = new JTextField(10);
		JTextField credits = new JTextField(10);
		JTextField ID = new JTextField(10);

		JLabel labelName = new JLabel("  Enter Player Name");
		JLabel labelCredits = new JLabel("  Enter starting credits");
		JLabel labelID = new JLabel("  Enter Player ID");

		labelName.setAlignmentX(RIGHT_ALIGNMENT);

		JToggleButton icon1 = new JToggleButton(IconNum.ICON1.getIcon());
		JToggleButton icon2 = new JToggleButton(IconNum.ICON2.getIcon());
		JToggleButton icon3 = new JToggleButton(IconNum.ICON3.getIcon());
		JToggleButton icon4 = new JToggleButton(IconNum.ICON4.getIcon());

		ButtonGroup bg = new ButtonGroup();
		bg.add(icon1);
		bg.add(icon2);
		bg.add(icon3);
		bg.add(icon4);
		icon1.setSelected(true);

		iconPanel.add(icon1);
		iconPanel.add(icon2);
		iconPanel.add(icon3);
		iconPanel.add(icon4);
		
		icon1.isSelected();
		icon1.addActionListener(new IconSelectListener(icon1, viewModel));
		icon2.addActionListener(new IconSelectListener(icon2, viewModel));
		icon3.addActionListener(new IconSelectListener(icon3, viewModel));
		icon4.addActionListener(new IconSelectListener(icon4, viewModel));

		playerTextPanel.setLayout(new GridLayout(3, 4));
		playerTextPanel.add(labelName);
		playerTextPanel.add(name);

		playerTextPanel.add(labelCredits);
		playerTextPanel.add(credits);

		playerTextPanel.add(labelID);
		playerTextPanel.add(ID);

		// All relevant listeners are added and instantiated here
		ok.addActionListener(
				new RegisterFieldListener(this, name, ID, credits, model, errorLabel));
		cancel.addActionListener(new CloseDialogListener2(this, errorLabel));
		ok.requestFocusInWindow();
		ok.addKeyListener(new KeyListener(this));
		cancel.addKeyListener(new KeyListener2(this, errorLabel));
		addWindowListener(new WindowListener(this, errorLabel));

	}

}
