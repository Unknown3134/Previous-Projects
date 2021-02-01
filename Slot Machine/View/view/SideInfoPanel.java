package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.model.ViewModelSettings;

/**
 * A JPanel parent on the EAST that holds BetPanel and PlayerPanel.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class SideInfoPanel extends JPanel {

	private PlayerPanel playerPanel;

	// main side panel which contains child panels of player and Bets
	public SideInfoPanel(SlotMachineImpl model, GuiCallback callback,
			JDialog quickBetDialog, GuiToolBar tb, ViewModelSettings viewModel) {

		setLayout(new GridLayout(3, 1));
		JLabel heading = new JLabel(new ImageIcon("images/slotfun.png"));
		add(heading, BorderLayout.NORTH);

		add(new BetPanel(callback), BorderLayout.CENTER);
		playerPanel = new PlayerPanel(model, callback, quickBetDialog, tb, viewModel);

		add(playerPanel, BorderLayout.SOUTH);
		setBackground(new Color(126, 161, 175));

		setBorder(BorderFactory.createEtchedBorder());
	}


}
