package slotmachine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import slotmachine.utilities.GuiUtilities;

/**
 * Simple HCI helper panel which shows clearly to the user which line number is
 * which. Added to the main frame.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class LinePanel extends JPanel implements PropertyChangeListener, GuiUtilities {

	private JLabel center,bottom,top,topLine4,botLine5;

	public LinePanel(GuiCallback callback) {
		callback.addPropertyChangeListener(this);

		setLayout(new GridLayout(3, 1));
		JPanel topArea = new JPanel();
		topArea.setLayout(new GridLayout(2, 1));
		topLine4 = new JLabel(" 4");
		topLine4.setFont(new Font("Verdana", 1, 30));
		topArea.add(topLine4);
		top = new JLabel(" 2");
		top.setFont(new Font("Verdana", 1, 30));
		topArea.add(top);
		add(topArea);

		center = new JLabel(" 1");
		center.setFont(new Font("Verdana", 1, 30));
		add(center);

		JPanel bottomArea = new JPanel();
		bottomArea.setLayout(new GridLayout(2, 1));
		bottom = new JLabel(" 3");
		bottom.setFont(new Font("Verdana", 1, 30));
		bottomArea.add(bottom);
		add(bottomArea);

		botLine5 = new JLabel(" 5");
		botLine5.setFont(new Font("Verdana", 1, 30));
		bottomArea.add(botLine5);

		Border border = BorderFactory.createTitledBorder("Line");
		topLine4.setBorder(border);
		top.setBorder(border);
		center.setBorder(border);
		bottom.setBorder(border);
		botLine5.setBorder(border);

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		int amount = 0;
		if (evt.getPropertyName() == SLOT_LINE) {
			amount = (int) evt.getOldValue();
			
			String line = (String) evt.getNewValue();
			if (line.contains("#1") && amount > 0) {
				center.setForeground(Color.RED);
				center.setBackground(Color.RED);
			}
			if (line.contains("#2") && amount > 0) {
				top.setForeground(Color.RED);
			}
			if (line.contains("#3") && amount > 0) {
				bottom.setForeground(Color.RED);
			}
			if (line.contains("#4") && amount > 0) {
				topLine4.setForeground(Color.RED);
			}
			if (line.contains("#5") && amount > 0) {
				botLine5.setForeground(Color.RED);
			}

		}
		if (evt.getPropertyName() == WHEEL || evt.getPropertyName() == BET_UPDATE) {
			topLine4.setForeground(Color.BLACK);
			top.setForeground(Color.BLACK);
			center.setForeground(Color.BLACK);
			bottom.setForeground(Color.BLACK);
			botLine5.setForeground(Color.BLACK);
		}
	}
}
