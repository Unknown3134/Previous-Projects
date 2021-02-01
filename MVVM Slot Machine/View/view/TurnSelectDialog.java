package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import slotmachine.controller.CloseDialogListener;
import slotmachine.controller.ComboListener;
import slotmachine.controller.ComboListener2;
import slotmachine.model.SlotMachineImpl;
import slotmachine.view.model.DelayNum;
import slotmachine.view.model.TurnNum;
import slotmachine.view.model.ViewModelSettings;

/**
 * Dialog populated with the two JCombo boxes for Turn and Delay amount
 * 
 * 
 * @author Paul McKercher
 */
@SuppressWarnings("serial")
public class TurnSelectDialog extends JDialog {

	private JComboBox<TurnNum> turnSelect;
	private JComboBox<DelayNum> delaySelect;

	public TurnSelectDialog(JFrame parent, SlotMachineImpl model,
			ViewModelSettings viewModel) {
		super(parent,true);
		setLayout(new GridLayout(4, 4));
		setTitle("Turn/Delay Select");
		setSize(new Dimension(600, 200));
		setLocationRelativeTo(parent);
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("OK");

		JPanel playerTextPanel = new JPanel();

		turnSelect = new JComboBox<TurnNum>(TurnNum.values());
		add(turnSelect);
		turnSelect.setSelectedItem(viewModel.getSelectedTurn());

		delaySelect = new JComboBox<DelayNum>(DelayNum.values());
		add(delaySelect);
		delaySelect.setSelectedItem(viewModel.getSelectedTurn());

		delaySelect.addActionListener(new ComboListener(delaySelect, viewModel));
		turnSelect.addActionListener(new ComboListener2(turnSelect, viewModel));

		add(playerTextPanel);

		add(buttonPanel);
		buttonPanel.add(ok, BorderLayout.WEST);

		playerTextPanel.add(new JPanel());
		playerTextPanel.setLayout(new FlowLayout());

		playerTextPanel.add(new JPanel());

		playerTextPanel.add(new JPanel());

		ok.addActionListener(new CloseDialogListener(this));
		delaySelect.setFocusable(false);
		turnSelect.setFocusable(false);
		ok.setDefaultCapable(true);
		ok.requestFocusInWindow();

	}


}
