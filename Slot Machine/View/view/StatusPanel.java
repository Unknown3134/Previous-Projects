package slotmachine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import slotmachine.model.Player;
import slotmachine.utilities.GuiUtilities;

/**
 * Simple StatusPanel that extends JPanel Two sections for displaying contextual
 * information.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class StatusPanel extends JPanel implements PropertyChangeListener {

	private JLabel s1, s2;

	public StatusPanel(GuiCallback callback) {

		setLayout(new GridLayout());
		callback.addPropertyChangeListener(this);
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		s1 = new JLabel("1", SwingConstants.CENTER);
		s1.setBorder(border);
		s1.setFont(new Font("Verdana", 2, 14));
		add(s1);

		s2 = new JLabel("", SwingConstants.CENTER);
		s2.setBorder(border);
		s2.setFont(new Font("Verdana", 2, 14));
		add(s2);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == GuiUtilities.WHEEL) {
			s1.setText("Spinning - -  Please Wait");
			s1.setForeground(Color.RED);
		} else {
			s1.setText("Ready - - Taking Bets");
			s1.setForeground(Color.BLACK);
		}

		if (evt.getPropertyName() == GuiUtilities.PLAYER) {
			Player player = (Player) evt.getNewValue();
			s2.setText(String.format("%s   %d", "Total Bets:", player.getBet()));

		}
		if (evt.getPropertyName() == GuiUtilities.CASH_OUT) {
			s1.setText("Please Register a Player");

		}

	}
}
