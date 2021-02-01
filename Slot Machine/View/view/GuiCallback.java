package slotmachine.view;

import java.beans.PropertyChangeListener;

/**
 * Callback / Observer for informing the view of changes from the model
 * 
 * 
 * 
 * @author Paul McKercher
 */
import java.beans.PropertyChangeSupport;
import java.util.Collection;

import javax.swing.SwingUtilities;

import slotmachine.model.Player;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.Wheel;
import slotmachine.utilities.GuiUtilities;

@SuppressWarnings("unused")
public class GuiCallback implements GameCallback, GuiUtilities {

	private boolean spinState;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void registerPlayer(Player player) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				pcs.firePropertyChange(PLAYER, null, player);
			}
		});

	}

	@Override
	public void cashOutPlayer(Player player) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange(CASH_OUT, null, player);
			}
		});

	}

	@Override
	public void addCredits(Player player, int credits) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange(PLAYER, null, player);
			}
		});

	}

	@Override
	public void betUpdated(Player player, int amount, LineNum line) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				pcs.firePropertyChange(PLAYER, null, player);
				// System.out.println(line.toString());
				pcs.firePropertyChange(line.toString(), null, amount);
			}
		});

	}

	@Override
	public void betUpdated(Player player, int amount, Collection<LineNum> lines) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange(PLAYER, null, player);
				pcs.firePropertyChange(BET_UPDATE, null, null);
				for (LineNum line : lines) {
					pcs.firePropertyChange(line.toString(), null, amount);
				}

			}
		});

	}

	@Override
	public void turnWheel(Wheel wheel, int turnNum) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				spinState = true;
				pcs.firePropertyChange(WHEEL, null, wheel);
			}
		});
	}

	@Override
	public void spinComplete(SpinResult spinResult) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				pcs.firePropertyChange(SPIN_RESULT, null, spinResult);
			}
		});
	}

	@Override
	public void lineResult(Player player, boolean hasBet, int lineOutcome,
			SlotLine slotLine) {
		SwingUtilities.invokeLater(new Runnable() {		
			@Override
			public void run() {
				
				int end = slotLine.toString().indexOf("|");

				String subString = "";
				if (end != -1) {
					subString = slotLine.toString().substring(0, end);
				}
				
				pcs.firePropertyChange(HAS_BET, null, hasBet);
				pcs.firePropertyChange(SLOT_LINE, lineOutcome, subString);
			}

		});

	}

	@Override
	public void betTotals(Player player, int total) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pcs.firePropertyChange(PLAYER, null, player);
				pcs.firePropertyChange(BET_TOTALS, null, total);
				pcs.firePropertyChange(COMPLETE, null, true);
			}
		});
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

}
