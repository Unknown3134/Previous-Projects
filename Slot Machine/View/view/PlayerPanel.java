package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import slotmachine.controller.LaunchDialogListener;
import slotmachine.controller.SpinToWinListener2;
import slotmachine.model.Player;
import slotmachine.model.SlotMachineImpl;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.model.ViewModelSettings;

/**
 * Along with the Bet panel, this panel is also added to the SideInfoPanel,
 * currently placed EAST on the Frame.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements PropertyChangeListener, GuiUtilities {
		
	private JLabel playerName,credits,availCredits,bets,ID,icon, initCredits;
	private JButton spinButton;
	private JButton placeBet;
	private ViewModelSettings viewModel;
	

	public PlayerPanel(SlotMachineImpl model, GuiCallback callback,
			 JDialog quickBetDialog, GuiToolBar tb, ViewModelSettings viewModel) {
		
		callback.addPropertyChangeListener(this);
		this.viewModel=viewModel;
		
		setLayout(new GridLayout(6, 2));
		JLabel playerLabel = new JLabel("Player:");
		JLabel IDLabel = new JLabel("ID:");
		JLabel betsLabel = new JLabel("Bets:");
		JLabel totalCreditsLabel = new JLabel("Credits:");
		JLabel availCreditsLabel = new JLabel("Available Credits:");
		JLabel initCreditsLabel = new JLabel("Starting Credits:");
 
		icon = new JLabel();
		
		playerName = new JLabel();
		ID = new JLabel();
		credits = new JLabel();
		availCredits = new JLabel();
		bets = new JLabel();
		initCredits = new JLabel();

		JPanel name = new JPanel();
		JPanel iconPanel = new JPanel();
		add(iconPanel);
		add(name);
		iconPanel.add(icon);
		
				
		playerLabel.setFont(new Font("Verdana", 2, FONT_SIZE));
		name.add(playerLabel);
		playerName.setFont(new Font("Verdana", 2, FONT_SIZE));
		name.add(playerName);

		IDLabel.setFont(new Font("Verdana", 2, FONT_SIZE));
		name.add(IDLabel);
		ID.setFont(new Font("Verdana", 2, FONT_SIZE));
		name.add(ID);
		name.setLayout(new GridLayout(2, 3));

		add(initCreditsLabel);
		add(initCredits);
		
		add(totalCreditsLabel);
		add(credits);

		add(availCreditsLabel);
		add(availCredits);

		add(betsLabel);
		add(bets);
		placeBet = new JButton("Quick Place Bet");
		add(placeBet);
		placeBet.addActionListener(new LaunchDialogListener(quickBetDialog));
		
		spinButton = new JButton("Spin to Win");
		spinButton.setBackground(new Color(4,119,6));
		spinButton.setForeground(Color.WHITE);
		add(spinButton);
		spinButton.addActionListener(new SpinToWinListener2(model, viewModel));
		
		spinButton.setDefaultCapable(true);
		spinButton.requestFocusInWindow();
		
		setBackground(new Color(198, 198, 198));
		Border border = BorderFactory.createRaisedSoftBevelBorder();

		setBorder(border);
	}

	// Spin button and player panel updated based on the state of the model
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName() == PLAYER) {
			Player player = (Player) evt.getNewValue();
			if(player !=null) {
				placeBet.setEnabled(true);
				spinButton.setEnabled(true);
				spinButton.setDefaultCapable(true);
				spinButton.requestFocusInWindow();
			}
			
			playerName.setText(player.getName());
			credits.setText(Integer.toString(player.getCredits()));
			availCredits.setText(Integer.toString(player.getAvailableCredits()));
			bets.setText(Integer.toString(player.getBet()));
			ID.setText(player.getId()); 
			icon.setIcon(viewModel.getAvatar());
			initCredits.setText(Integer.toString(player.getInitalCredits()));
			
			
		}
		
		if(evt.getPropertyName() == WHEEL) {
			placeBet.setEnabled(false);
			spinButton.setEnabled(false);
		}
		
		if (evt.getPropertyName() == CASH_OUT) {
			
			playerName.setText("No Player");
			credits.setText(BLANK);
			availCredits.setText(BLANK);	
			bets.setText(BLANK);
			ID.setText(BLANK); 
			initCredits.setText(BLANK);
			icon.setIcon(null);
			placeBet.setEnabled(false);
			spinButton.setEnabled(false);
		}
	}


}
