package slotmachine.model.slots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SpinResultImpl implements SpinResult {

	private List<SlotLine> slotLines;


	public SpinResultImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3) {

		this.slotLines = new ArrayList<SlotLine>();

		slotLines.add(new SlotLineImpl(LineNum.LINE1, wheel1.getCentreSlot(),
				wheel2.getCentreSlot(), wheel3.getCentreSlot()));
		slotLines.add(new SlotLineImpl(LineNum.LINE2, wheel1.getTopSlot(),
				wheel2.getTopSlot(), wheel3.getTopSlot()));
		slotLines.add(new SlotLineImpl(LineNum.LINE3, wheel1.getBottomSlot(),
				wheel2.getBottomSlot(), wheel3.getBottomSlot()));
		slotLines.add(new SlotLineImpl(LineNum.LINE4, wheel1.getTopSlot(),
				wheel2.getCentreSlot(), wheel3.getBottomSlot()));
		slotLines.add(new SlotLineImpl(LineNum.LINE5, wheel1.getBottomSlot(),
				wheel2.getCentreSlot(), wheel3.getTopSlot()));

	}

	@Override
	public Iterator<SlotLine> iterator() {

		return slotLines.iterator();
	}

	@Override
	public String toString() {
		return String.format("%s\n%s\n%s", slotLines.get(1),
				slotLines.get(0), slotLines.get(2));
		

	}

}
