package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import slotmachine.view.model.DelayNum;
import slotmachine.view.model.ViewModelSettings;

/**
 * Specific listener for the Delay amount Combobox
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class ComboListener implements ActionListener {
	private JComboBox<DelayNum> comboBox;
	private ViewModelSettings viewModel;

	public ComboListener(JComboBox<DelayNum> comboBox, ViewModelSettings viewModel) {
		this.comboBox = comboBox;
		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		viewModel.setSelectedDelay((DelayNum) comboBox.getSelectedItem());
		

	}

}
