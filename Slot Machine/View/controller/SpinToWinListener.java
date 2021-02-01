package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.GuiCallback;
import slotmachine.view.SlotMachineFrame;
import slotmachine.view.model.ViewModelSettings;

/**
 * Specific listener for spin button in the Tool bar
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class SpinToWinListener implements ActionListener {

	private SlotMachineImpl model;
	private ViewModelSettings viewModel;;

	public SpinToWinListener(SlotMachineImpl model, SlotMachineFrame frame,
			GuiCallback callback, ViewModelSettings viewModel) {

		this.model = model;
		this.viewModel = viewModel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		new Thread() {
			@Override
			public void run() {
				try {
					model.spinToWin(viewModel.getSelectedTurn().getNum(),
							viewModel.getSelectedDelay().getNum());
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}.start();

	}

}