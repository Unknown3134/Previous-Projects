package slotmachine.model.slots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WheelImpl implements Wheel {

	private int currentSlot;
	private List<SlotItem> slots;
	private int wheelNumber;

	private WheelImpl(int wheelNum, List<SlotItem> slots) {

		this.slots = slots;
		this.wheelNumber = wheelNum;
		this.currentSlot = 0;

	}

	public static Wheel createWheel(int wheelNum) {
		
		return new WheelImpl(wheelNum, generateSlots());
	}

	public static List<SlotItem> generateSlots() {
		List<SlotItem> localList = new ArrayList<SlotItem>();
		for (SlotItem s : SlotItem.values()) {
			for (int i = 0; i < s.getCount(); i++) {
				localList.add(s);
			}

		}
		Collections.shuffle(localList);
		return localList;
	}

	@Override
	public SlotItem getTopSlot() {

		return this.currentSlot > 0 ? this.slots.get(currentSlot - 1)
				: this.slots.get(99);
	}

	@Override
	public SlotItem getCentreSlot() {

		return this.slots.get(currentSlot);
	}

	@Override
	public SlotItem getBottomSlot() {

		return this.currentSlot < slots.size() - 1 ? this.slots.get(currentSlot + 1)
				: this.slots.get(0);
	}

	@Override
	public Wheel nextSlot() {

		if (this.currentSlot > 0) {
			this.currentSlot--;
		} else {
			this.currentSlot = 99;
		}
		return this;

	}

	@Override
	public String toString() {
		return String.format("Wheel #%d %-10s / %-10s / %-20s", this.wheelNumber,
				getTopSlot(), getCentreSlot(), getBottomSlot());

	}


}
