package slotmachine.view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import slotmachine.controller.CashOutListener;
import slotmachine.controller.ExitListener;
import slotmachine.controller.LaunchDialogListener;
import slotmachine.controller.ResetBetListener;
import slotmachine.controller.SpinToWinListener;
import slotmachine.model.SlotMachineImpl;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.model.ViewModelSettings;

/**
 * Simple JMenu bar which allows for actions like quit and register.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar implements PropertyChangeListener, GuiUtilities  {

	private JMenuItem registerItem;
	private JMenu optionMenu;

	public MenuBar(JDialog registerDialog, JDialog aboutDialog, JDialog quickBetDialog,
			JDialog betDialog, GuiCallback callback, SlotMachineImpl model,
			SlotMachineFrame frame, JDialog selectTurnDialog, JDialog winningOddsDialog,
			JDialog previousOutcomesDialog, ViewModelSettings viewModel) {

		callback.addPropertyChangeListener(this);
		JMenu fileMenu = new JMenu("File");
		add(fileMenu);
		registerItem = new JMenuItem("Register Player");

		fileMenu.add(registerItem);
		LaunchDialogListener registerListener = new LaunchDialogListener(registerDialog);
		registerItem.addActionListener(registerListener);
		JMenuItem quitItem = new JMenuItem("Quit");

		optionMenu = new JMenu("Options");
		add(optionMenu);
		JMenuItem helpMenu = new JMenu("Help");
		add(helpMenu);
		JMenuItem aboutItem = new JMenuItem("About");

		aboutItem.addActionListener(new LaunchDialogListener(aboutDialog));
		helpMenu.add(aboutItem);
		quitItem.addActionListener(new ExitListener());
		fileMenu.add(quitItem);

		JMenuItem cashOutItem = new JMenuItem("Cash Out");
		optionMenu.add(cashOutItem);

		JMenuItem resetBetItem = new JMenuItem("Reset Bets");
		optionMenu.add(resetBetItem);
		JMenuItem placeBetItem = new JMenuItem("Place Bets");
		optionMenu.add(placeBetItem);
		JMenuItem quickPlaceBetItem = new JMenuItem("Quick Place Bets");
		optionMenu.add(quickPlaceBetItem);
		JMenuItem spinToWinItem = new JMenuItem("Spin to win");
		optionMenu.add(spinToWinItem);

		JMenuItem turnSelectItem = new JMenuItem("Turn/Delay select");
		optionMenu.add(turnSelectItem);

		JMenuItem winningOddsItem = new JMenuItem("Winning Odds");
		optionMenu.add(winningOddsItem);
		winningOddsItem.addActionListener(new LaunchDialogListener(winningOddsDialog));

		JMenuItem previousOutcomeItem = new JMenuItem("Previous Outcomes");
		optionMenu.add(previousOutcomeItem);
		previousOutcomeItem
				.addActionListener(new LaunchDialogListener(previousOutcomesDialog));

		turnSelectItem.addActionListener(new LaunchDialogListener(selectTurnDialog));
		resetBetItem.addActionListener(new ResetBetListener(model));

		placeBetItem.addActionListener(new LaunchDialogListener(betDialog));

		quickPlaceBetItem.addActionListener(new LaunchDialogListener(quickBetDialog));

		cashOutItem.addActionListener(
				new CashOutListener(model, frame, callback, viewModel));

		spinToWinItem.addActionListener(
				new SpinToWinListener(model, frame, callback, viewModel));

		fileMenu.setMnemonic(KeyEvent.VK_F);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		quitItem.setMnemonic(KeyEvent.VK_X);
		aboutItem.setMnemonic(KeyEvent.VK_A);
		cashOutItem.setMnemonic(KeyEvent.VK_C);

		optionMenu.setMnemonic(KeyEvent.VK_O);
		placeBetItem.setMnemonic(KeyEvent.VK_B);
		quickPlaceBetItem.setMnemonic(KeyEvent.VK_Q);
		resetBetItem.setMnemonic(KeyEvent.VK_R);
		winningOddsItem.setMnemonic(KeyEvent.VK_W);
		turnSelectItem.setMnemonic(KeyEvent.VK_T);
		spinToWinItem.setMnemonic(KeyEvent.VK_S);
		previousOutcomeItem.setMnemonic(KeyEvent.VK_P);

		quitItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.ALT_DOWN_MASK));
		registerItem
				.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_DOWN_MASK));
		cashOutItem.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK));
		placeBetItem
				.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.ALT_DOWN_MASK));
		quickPlaceBetItem
				.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.ALT_DOWN_MASK));
		resetBetItem
				.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_DOWN_MASK));
		winningOddsItem
				.setAccelerator(KeyStroke.getKeyStroke('W', InputEvent.ALT_DOWN_MASK));
		turnSelectItem
				.setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.ALT_DOWN_MASK));
		spinToWinItem
				.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.ALT_DOWN_MASK));
		previousOutcomeItem
		.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.ALT_DOWN_MASK));
	}

	/** All this listener is doing is deactivating and reactivating
	 * options and register, depending on if a player is registered.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == CASH_OUT) {
			registerItem.setEnabled(true);
			optionMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == PLAYER) {
			registerItem.setEnabled(false);
			optionMenu.setEnabled(true);
		}

		if (evt.getPropertyName() == WHEEL) {
			optionMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == BET_TOTALS ) {
			optionMenu.setEnabled(true);
		}
	}

}
