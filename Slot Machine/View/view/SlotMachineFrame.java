package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WinSettingsImpl;
import slotmachine.view.model.ViewModelSettings;

@SuppressWarnings("serial")
public class SlotMachineFrame extends JFrame {

	private JDialog aboutDialog, winDialog, quickBetDialog, betDialog, registerDialog,
			selectTurnDialog, winningOddsDialog, previousOutcomesDialog;
	private JPanel sideInfo;
	private GuiToolBar tb;

	/**
	 * The main JFrame of the program. Adds the other panels.
	 * 
	 * Takes the SlotMachineImpl model as a parameter in the constructor.
	 * 
	 * @author Paul McKercher
	 */

	/**
	 *
	 *
	 * @param model takes the SlotMachineImpl Model as a parameter
	 */

	public SlotMachineFrame(SlotMachineImpl model, GuiCallback callback, Wheel wheel1,
			Wheel wheel2, Wheel wheel3, ViewModelSettings viewModel, WinSettingsImpl ws) {
		super("Slot Machine GUI");

		JPanel mainPanel = new CentreWheelPanel(callback, wheel1, wheel2, wheel3);

		add(mainPanel, BorderLayout.CENTER);
		JPanel lineLabels = new LinePanel(callback);
		add(lineLabels, BorderLayout.WEST);

		add(new StatusPanel(callback), BorderLayout.SOUTH);
		// Dialogs instantiated here. Set non-visible until opened in menubar
		registerDialog = new RegisterPlayerDialog(this, model, sideInfo, viewModel);
		registerDialog.setLocationRelativeTo(this);
		registerDialog.setVisible(false);

		aboutDialog = new AboutDialog(this);
		aboutDialog.setLocationRelativeTo(this);
		aboutDialog.setVisible(false);

		winDialog = new WinDialog(this, callback);
		winDialog.setVisible(false);
		winDialog.setLocationRelativeTo(this);

		quickBetDialog = new QuickBetDialog(this, model);
		quickBetDialog.setVisible(false);
		quickBetDialog.setLocationRelativeTo(this);

		betDialog = new BetDialog(this, model);
		betDialog.setVisible(false);
		betDialog.setLocationRelativeTo(this);

		sideInfo = new SideInfoPanel(model, callback, quickBetDialog, tb, viewModel);
		add(sideInfo, BorderLayout.EAST);

		tb = new GuiToolBar(model, this, callback, quickBetDialog, betDialog,
				registerDialog, viewModel);
		add(tb, BorderLayout.NORTH);

		selectTurnDialog = new TurnSelectDialog(this, model, viewModel);
		selectTurnDialog.setVisible(false);
		selectTurnDialog.setLocationRelativeTo(this);

		winningOddsDialog = new WinningOddsDialog(this, callback, ws, viewModel);
		winningOddsDialog.setVisible(false);
		winningOddsDialog.setLocationRelativeTo(this);

		previousOutcomesDialog = new PreviousOutcomesDialog(this, callback, viewModel);
		previousOutcomesDialog.setVisible(false);
		previousOutcomesDialog.setLocationRelativeTo(this);

		MenuBar mb = new MenuBar(registerDialog, aboutDialog, quickBetDialog, betDialog,
				callback, model, this, selectTurnDialog, winningOddsDialog,
				previousOutcomesDialog, viewModel);
		setJMenuBar(mb);

		// a good starting size
		setSize(900, 700);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// starting size is also the miniumum size
		setMinimumSize(new Dimension(900, 700));

		setVisible(true);

	}

}
