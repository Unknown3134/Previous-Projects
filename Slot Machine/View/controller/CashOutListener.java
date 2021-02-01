package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.GuiCallback;
import slotmachine.view.SlotMachineFrame;
import slotmachine.view.model.ViewModelSettings;

/**
 * This Listener is for when the user clicks Cash Out
 * 
 * @author Paul McKercher
 */
public class CashOutListener implements ActionListener {

	private SlotMachineImpl model;
	private SlotMachineFrame frame;
	private ViewModelSettings viewModel;

	public CashOutListener(SlotMachineImpl model, SlotMachineFrame frame,
			GuiCallback callback, ViewModelSettings viewModel) {
		this.model = model;
		this.frame = frame;
		this.viewModel = viewModel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (JOptionPane.showConfirmDialog(frame,
				String.format("%s %d", "Confirm Cash out? Credits to be refunded: ",
						viewModel.getPlayerState().getCredits(), "WARNING",
						JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {

			try {
				model.cashOut();

			} catch (Exception ex) {

			}
		}

	}

}
