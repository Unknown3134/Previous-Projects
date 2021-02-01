package slotmachine.controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import slotmachine.view.model.ViewModelSettings;

/**
 * Specific listener for the Avatar select, JToggle button
 * 
 * 
 * 
 * @author Paul McKercher
 */

public class IconSelectListener implements ActionListener {

	private JToggleButton button;
	private ViewModelSettings viewModel;

	public IconSelectListener(JToggleButton icon1, ViewModelSettings viewModel) {
		this.button = icon1;
		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		viewModel.setAvatar(button.getIcon());

	}

}
