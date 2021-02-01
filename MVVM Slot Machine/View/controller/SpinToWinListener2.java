package slotmachine.controller;

import java.awt.event.ActionEvent;

/**
 * Specific listener spin button in the player panel
 * 
 * 
 * 
 * @author Paul McKercher
 */
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.model.ViewModelSettings;

public class SpinToWinListener2 implements ActionListener {

	private SlotMachineImpl model;
	private ViewModelSettings viewModel;

	public SpinToWinListener2(SlotMachineImpl model, ViewModelSettings viewModel) {

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