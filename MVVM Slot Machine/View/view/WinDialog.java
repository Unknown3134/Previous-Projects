package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import slotmachine.controller.CloseDialogListener3;
import slotmachine.controller.KeyListener;
import slotmachine.controller.WindowListener2;
import slotmachine.utilities.GuiUtilities;

/*
 * A quick dialog made visible and re-validated after every Spin
 * Showing the wins for each Slot line
 *  
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class WinDialog extends JDialog implements PropertyChangeListener, GuiUtilities {

	private boolean firstRun;

	private JPanel textPanel;

	public WinDialog(JFrame parent, GuiCallback callback) {
		super(parent, true);
		setTitle("Results");
		callback.addPropertyChangeListener(this);

		setLayout(new GridLayout(3, 1));

		setSize(new Dimension(400, 250));
		setLocationRelativeTo(parent);
		JLabel title = new JLabel("Results from spin");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", 2, FONT_SIZE));
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("OK");
		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(5, 1));
		add(title, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(ok, BorderLayout.CENTER);

		ok.addActionListener(new CloseDialogListener3(this, textPanel));
		ok.addKeyListener(new KeyListener(this));
		addWindowListener(new WindowListener2(this, textPanel));

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		

		if (evt.getPropertyName() == SPIN_RESULT && firstRun) {
			setVisible(true);
		} else if (evt.getPropertyName() == SPIN_RESULT) {
			firstRun = true;
		}


		if (evt.getPropertyName() == SLOT_LINE) {					
			if ((int) evt.getOldValue() > 0) {
				JLabel temp =new JLabel(
						String.format("%50s:  %2s %d %s", (String) evt.getNewValue(),
								"WON", (int) evt.getOldValue(), "Credits"));
				temp.setForeground(Color.RED);
				textPanel.add(temp);
				
			} else {
				textPanel.add(new JLabel(String.format("%50s:  %2s ",
						(String) evt.getNewValue(), "No Outcome")));
			}		
			validate();

		}
	}

}
