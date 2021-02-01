package slotmachine.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JToolBar;

import slotmachine.controller.CashOutListener;
import slotmachine.controller.ComboListener;
import slotmachine.controller.ComboListener2;
import slotmachine.controller.LaunchDialogListener;
import slotmachine.controller.ResetBetListener;
import slotmachine.controller.SpinToWinListener;
import slotmachine.model.SlotMachineImpl;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.model.DelayNum;
import slotmachine.view.model.TurnNum;
import slotmachine.view.model.ViewModelSettings;

/**
 * The Gui tool bar contains JButtons for quick actions. Is currently added to
 * the NORTH of the JFrame
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class GuiToolBar extends JToolBar implements PropertyChangeListener {

	private JComboBox<TurnNum> turnSelect;
	private JComboBox<DelayNum> delaySelect;
	private ArrayList<JButton> buttons;
	private JButton register;

	public GuiToolBar(SlotMachineImpl model, SlotMachineFrame frame, GuiCallback callback,
			JDialog quickBetDialog, JDialog betDialog, JDialog registerDialog,
			ViewModelSettings viewModel) {

		setLayout(new GridLayout(1, 8));

		callback.addPropertyChangeListener(this);
		JButton spinButton = new JButton("Spin to Win");
		JButton resetBetButton = new JButton("Reset Bets");
		JButton cashOutButton = new JButton("Cash Out");
		JButton placeBet = new JButton("Quick Place Bet");
		JButton placeBetChoose = new JButton("Place Bet");
		register = new JButton("Register Player");
		add(register);
		add(resetBetButton);
		add(cashOutButton);
		add(placeBetChoose);
		add(placeBet);

		register.setBackground(new Color(198, 198, 198));
		resetBetButton.setBackground(new Color(198, 198, 198));
		cashOutButton.setBackground(new Color(198, 198, 198));
		placeBetChoose.setBackground(new Color(198, 198, 198));
		placeBet.setBackground(new Color(198, 198, 198));

		turnSelect = new JComboBox<TurnNum>(TurnNum.values());
		add(turnSelect);
		turnSelect.setSelectedItem(viewModel.getSelectedTurn());
		delaySelect = new JComboBox<DelayNum>(DelayNum.values());
		add(delaySelect);
		delaySelect.setSelectedItem(viewModel.getSelectedDelay());

		delaySelect.addActionListener(new ComboListener(delaySelect, viewModel));
		turnSelect.addActionListener(new ComboListener2(turnSelect, viewModel));

		spinButton.setBackground(new Color(4, 119, 6));
		spinButton.setForeground(Color.WHITE);
		add(spinButton);

		setFloatable(false);

		buttons = new ArrayList<JButton>();
		buttons.add(spinButton);
		buttons.add(cashOutButton);
		buttons.add(resetBetButton);
		buttons.add(placeBet);
		buttons.add(placeBetChoose);

		cashOutButton.addActionListener(
				new CashOutListener(model, frame, callback, viewModel));

		spinButton.addActionListener(
				new SpinToWinListener(model, frame, callback, viewModel));
		resetBetButton.addActionListener(new ResetBetListener(model));
		placeBet.addActionListener(new LaunchDialogListener(quickBetDialog));
		placeBetChoose.addActionListener(new LaunchDialogListener(betDialog));
		register.addActionListener(new LaunchDialogListener(registerDialog));

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == GuiUtilities.WHEEL) {
			for (JButton button : buttons) {
				button.setEnabled(false);
			}
		}
		if (evt.getPropertyName() == GuiUtilities.CASH_OUT) {
			register.setEnabled(true);
			for (JButton button : buttons) {
				button.setEnabled(false);
			}

		}
		if (evt.getPropertyName() == GuiUtilities.PLAYER) {
			register.setEnabled(false);
			for (JButton button : buttons) {
				button.setEnabled(true);
			}
		}

	}


}
