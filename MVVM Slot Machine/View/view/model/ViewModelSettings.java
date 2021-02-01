package slotmachine.view.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import slotmachine.model.Player;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotItem;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SlotLineImpl;
import slotmachine.model.slots.SpinResult;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.GuiCallback;

/**
 * The view Model <BR>
 * <BR>
 * Far from perfect. <BR>
 * <BR>
 * If I had more time, there would be more state storage inside this class. This
 * view model was implmented long after a lot of my major logic had been
 * complete. Refactoring attempts were made, but after 80+ hours on this, I had
 * to lock in the final implementation. <BR>
 * <BR>
 * Stores Player state, previous outcome, current Icon, current Turn amount,
 * current delay amount
 * 
 * @author Paul McKercher
 */

public final class ViewModelSettings implements PropertyChangeListener, GuiUtilities {

	private ImageIcon avatar;
	private TurnNum selectedTurn;
	private DelayNum selectedDelay;
	private Player playerState;
	private List<SlotLine> slotLinesForOdds;

	private List<PreviousOutcome> outComes;
	private PreviousOutcome previous;

	public ViewModelSettings(GuiCallback callback) {
		callback.addPropertyChangeListener(this);
		selectedTurn = TurnNum.DEFAULT_LABEL;
		selectedDelay = DelayNum.DEFAULT_LABEL;
		avatar = IconNum.ICON1.getIcon();
		outComes = new ArrayList<PreviousOutcome>();

		popululateSlotLineForOdds();

	}

	/**
	 * Populates my chosen choice of lines for the win odds dialog
	 */
	private void popululateSlotLineForOdds() {
		slotLinesForOdds = new ArrayList<SlotLine>();
		slotLinesForOdds.add(new SlotLineImpl(LineNum.LINE1, SlotItem.CHERRY,
				SlotItem.CHERRY, SlotItem.CHERRY));
		slotLinesForOdds.add(new SlotLineImpl(LineNum.LINE2, SlotItem.GOLD, SlotItem.GOLD,
				SlotItem.GOLD));
		slotLinesForOdds.add(new SlotLineImpl(LineNum.LINE3, SlotItem.SEVEN,
				SlotItem.SEVEN, SlotItem.SEVEN));
		slotLinesForOdds.add(new SlotLineImpl(LineNum.LINE4, SlotItem.GOLD, SlotItem.GOLD,
				SlotItem.CHERRY));
		slotLinesForOdds.add(new SlotLineImpl(LineNum.LINE5, SlotItem.ONE, SlotItem.ONE,
				SlotItem.ONE));
		slotLinesForOdds.add(new SlotLineImpl(LineNum.LINE1, SlotItem.SILVER,
				SlotItem.SILVER, SlotItem.SILVER));

	}

	public List<SlotLine> getSlotLines() {
		return slotLinesForOdds;
	}

	public Player getPlayerState() {
		return playerState;
	}

	public void setPlayerState(Player player) {
		playerState = player;
	}

	public ImageIcon getAvatar() {
		return avatar;
	}

	public void setAvatar(Icon icon) {
		avatar = (ImageIcon) icon;
	}

	public TurnNum getSelectedTurn() {
		return selectedTurn;
	}

	public void setSelectedTurn(TurnNum selected) {
		selectedTurn = selected;
	}

	public DelayNum getSelectedDelay() {
		return selectedDelay;
	}

	public void setSelectedDelay(DelayNum selected) {
		selectedDelay = selected;
	}

	public PreviousOutcome getPreviousOutcome(int index) {
		return outComes.get(index);
	}

	public int getOutcomeSize() {
		return outComes.size();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		previous = new PreviousOutcome();
		if (evt.getPropertyName() == PLAYER) {
			setPlayerState((Player) evt.getNewValue());
		}

		if (evt.getPropertyName() == SPIN_RESULT) {
			SpinResult spinResult = (SpinResult) evt.getNewValue();

			for (SlotLine line : spinResult) {
				previous.addSlotLine(line);
			}

			if (outComes.size() < 3) {

				outComes.add(previous);
			} else {
				PreviousOutcome temp = outComes.get(1);
				outComes.set(0, temp);
				temp = outComes.get(2);
				outComes.set(1, temp);
				outComes.set(2, previous);

			}

		}

	}

}
