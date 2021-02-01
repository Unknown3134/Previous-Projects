package slotmachine.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.WinSettingsImpl;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.model.ViewModelSettings;

/**
 *  A quick dialog to show the winning odds for some slot lines
 * 
 * 
 * 
 * @author Paul McKercher
 */
@SuppressWarnings("serial")
public class WinningOddsDialog extends JDialog implements GuiUtilities {

	

	public WinningOddsDialog(JFrame parent, GuiCallback callback, WinSettingsImpl ws,
			ViewModelSettings viewModel) {
		super(parent,true);
		setTitle("Winning Odds");

		setLayout(new GridLayout(1, 1));

		setSize(new Dimension(500, 600));
		JPanel icons = new JPanel();
		icons.setLayout(new GridLayout(6, 4));
		/* Gets pre-populated slot lines from view model, I chose which ones I 
		wanted to show for the odds
		*/
		for (SlotLine line : viewModel.getSlotLines()) {

			ImageIcon imageIcon = line.getSlot1().getIcon();
			Image image = imageIcon.getImage();
			Image newImg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newImg);
			icons.add(new JLabel(imageIcon));

			imageIcon = line.getSlot2().getIcon();
			image = imageIcon.getImage();
			newImg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newImg);
			icons.add(new JLabel(imageIcon));

			imageIcon = line.getSlot3().getIcon();
			image = imageIcon.getImage();
			newImg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newImg);
			icons.add(new JLabel(imageIcon));
			int odds = ws.getWinOdds(line);
			icons.add(new JLabel(String.format("Winning Odds: %d", odds)));

		}
		setLocationRelativeTo(parent);
		
		add(icons);
		
		
	}

}