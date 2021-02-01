package slotmachine.view.model;

import java.util.ArrayList;
import java.util.List;

import slotmachine.model.slots.SlotLine;

/**
 * A Previous outcome object. Stores the 5 slot lines for that outcome.
 * 
 * Originally also stored amounts and bet booleans. I had no end of trouble with 
 * this and decided to cut my losses.
 * 
 * 
 * @author Paul McKercher
 */
public class PreviousOutcome {

	private List<SlotLine> slotLines;
	

	public PreviousOutcome() {
		slotLines = new ArrayList<SlotLine>();
		

	}

	public void addSlotLine(SlotLine line) {
		// List not allowed to have more than 5 lines
		if (slotLines.size() < 5)
			slotLines.add(line);

	}



	public List<SlotLine> getAllSlotLines() {
		return slotLines;
	}
	





}
