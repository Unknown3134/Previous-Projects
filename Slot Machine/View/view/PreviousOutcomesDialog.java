package slotmachine.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import slotmachine.model.slots.SlotLine;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.model.PreviousOutcome;
import slotmachine.view.model.ViewModelSettings;


/**
 * Shows the slots with line labels of the 3 last previous spins
 * 
 * 
 * 
 * @author Paul McKercher
 */
@SuppressWarnings("serial")
public class PreviousOutcomesDialog extends JDialog
		implements GuiUtilities, PropertyChangeListener {

	private ViewModelSettings viewModel;
	private JPanel icons;
	private int count;

	public PreviousOutcomesDialog(JFrame parent, GuiCallback callback,
			ViewModelSettings viewModel) {
		super(parent, true);
		setLocationRelativeTo(parent);
		setTitle("Previous Outcomes");
		callback.addPropertyChangeListener(this);
		this.viewModel = viewModel;

		setLayout(new GridLayout(1, 1));

		setSize(new Dimension(500, 600));
		icons = new JPanel();
		count = -1;

		icons.setLayout(new GridLayout(6, 5));

		setLocationRelativeTo(parent);
			
		add(icons);

	

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Count is used to determine the size of the JDialog box
		if (evt.getPropertyName() == SPIN_RESULT) {
			if (count >= 2) {
				count = 2;
			} else {
				count++;
			}
			icons.removeAll();
		}
		// Accesses the previous spins stored in view model
		if (evt.getPropertyName() == SPIN_RESULT) {
			for (int i = 0; i < viewModel.getOutcomeSize(); i++) {

				PreviousOutcome currentOutcome = viewModel.getPreviousOutcome(i);
				List<SlotLine> lines = currentOutcome.getAllSlotLines();

				for (SlotLine line : lines) {

					ImageIcon imageIcon = line.getSlot1().getIcon();
					Image image = imageIcon.getImage();
					Image newImg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newImg);
					icons.add(new JLabel(imageIcon));

					imageIcon = line.getSlot2().getIcon();
					image = imageIcon.getImage();
					newImg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newImg);
					icons.add(new JLabel(imageIcon));

					imageIcon = line.getSlot3().getIcon();
					image = imageIcon.getImage();
					newImg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newImg);
					icons.add(new JLabel(imageIcon));
					icons.add(new JLabel(line.getLineNum().toString()));
					

				}
				JLabel temp = new JLabel(String.format("%s %d", "Previous Spin", i + 1));
				temp.setVerticalAlignment(SwingConstants.NORTH);
				icons.add(temp);
				icons.add(new JSeparator());
				icons.add(new JSeparator());
				icons.add(new JSeparator());

			}
			if (count == 1) {
				setSize(new Dimension(800, 800));
				icons.setLayout(new GridLayout(12, 5));
			}
			if (count >= 2) {
				icons.setLayout(new GridLayout(18, 5));

			}

		}

	}

}
