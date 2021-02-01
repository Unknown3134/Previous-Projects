package slotmachine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import slotmachine.utilities.GuiUtilities;

/**
 * Bet panel shares it's space with Player Panel and is added to the Side Info
 * Panel JPanel
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class BetPanel extends JPanel implements PropertyChangeListener, GuiUtilities {

	private JLabel line1Value,line2Value,line3Value,line4Value,line5Value;
	
	private JLabel line1,line2,line3,line4,line5;
	
	public BetPanel(GuiCallback callback) {

		callback.addPropertyChangeListener(this);
		setLayout(new GridLayout(5, 2));

		line1 = new JLabel("Line 1");
		line1.setFont(new Font("Verdana", 2, FONT_SIZE));
		add(line1);
		line1Value = new JLabel("No Bet");
		add(line1Value);

		line2 = new JLabel("Line 2");
		line2.setFont(new Font("Verdana", 2, FONT_SIZE));
		add(line2);
		line2Value = new JLabel("No Bet");
		add(line2Value);

		line3 = new JLabel("Line 3");
		line3.setFont(new Font("Verdana", 2, FONT_SIZE));
		add(line3);
		line3Value = new JLabel("No Bet");
		add(line3Value);

		line4 = new JLabel("Line 4");
		line4.setFont(new Font("Verdana", 2, FONT_SIZE));
		add(line4);
		line4Value = new JLabel("No Bet");
		add(line4Value);

		line5 = new JLabel("Line 5");
		line5.setFont(new Font("Verdana", 2, FONT_SIZE));
		add(line5);
		line5Value = new JLabel("No Bet");
		add(line5Value);

		setBackground(new Color(198, 198, 198));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(border);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName() == "Line #1") {
			int amount = (int) evt.getNewValue();
			if (amount > 0) {
				line1Value.setText(Integer.toString(amount));
			} else {
				line1Value.setText("No Bet");
			}
		}
		if (evt.getPropertyName() == "Line #2") {
			int amount = (int) evt.getNewValue();
			if (amount > 0) {
				line2Value.setText(Integer.toString(amount));
			} else {
				line2Value.setText("No Bet");
			}
		}
		if (evt.getPropertyName() == "Line #3") {
			int amount = (int) evt.getNewValue();
			if (amount > 0) {
				line3Value.setText(Integer.toString(amount));
			} else {
				line3Value.setText("No Bet");
			}
		}
		if (evt.getPropertyName() == "Line #4") {
			int amount = (int) evt.getNewValue();
			if (amount > 0) {
				line4Value.setText(Integer.toString(amount));
			} else {
				line4Value.setText("No Bet");
			}

		}
		if (evt.getPropertyName() == "Line #5") {
			int amount = (int) evt.getNewValue();
			if (amount > 0) {
				line5Value.setText(Integer.toString(amount));
			} else {
				line5Value.setText("No Bet");
			}
		}
		int amount = 0;
		if (evt.getPropertyName() == SLOT_LINE) {
			amount = (int) evt.getOldValue();

			String line = (String) evt.getNewValue();
			if (line.contains("#1") && amount > 0) {

				line1Value.setForeground(Color.RED);
				line1.setForeground(Color.RED);

			}
			if (line.contains("#2") && amount > 0) {

				line2Value.setForeground(Color.RED);
				line2.setForeground(Color.RED);
			}
			if (line.contains("#3") && amount > 0) {

				line3Value.setForeground(Color.RED);
				line3.setForeground(Color.RED);

			}
			if (line.contains("#4") && amount > 0) {

				line4Value.setForeground(Color.RED);
				line4.setForeground(Color.RED);

			}
			if (line.contains("#5") && amount > 0) {

				line5Value.setForeground(Color.RED);
				line5.setForeground(Color.RED);

			}

		}

		if (evt.getPropertyName() == WHEEL || evt.getPropertyName() == BET_UPDATE) {
			line1Value.setForeground(Color.BLACK);
			line1.setForeground(Color.BLACK);
			line2Value.setForeground(Color.BLACK);
			line2.setForeground(Color.BLACK);
			line3Value.setForeground(Color.BLACK);
			line3.setForeground(Color.BLACK);
			line4Value.setForeground(Color.BLACK);
			line4.setForeground(Color.BLACK);
			line5Value.setForeground(Color.BLACK);
			line5.setForeground(Color.BLACK);
		}
	}
}
