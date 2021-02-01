package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import slotmachine.view.model.TurnNum;
import slotmachine.view.model.ViewModelSettings;
/**
 * Specific listener for the Turn amount Combobox
 * 
 * 
 * 
 * @author Paul McKercher
 */
public class ComboListener2 implements ActionListener {
	private JComboBox<TurnNum> comboBox;
	private ViewModelSettings viewModel;

	public ComboListener2(JComboBox<TurnNum> comboBox, ViewModelSettings viewModel) {
		this.comboBox = comboBox;
		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		viewModel.setSelectedTurn((TurnNum) comboBox.getSelectedItem());

	}

}
