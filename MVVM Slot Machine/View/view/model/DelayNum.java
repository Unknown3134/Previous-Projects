package slotmachine.view.model;

/**
 * A very helpful enum to store the Delay values
 * 
 * 
 * @author Paul McKercher
 */

public enum DelayNum {
	DEFAULT_LABEL("Select Delay", 100), FAST("Fast", 50), DEFAULT("Default", 100),
	SLOW("Slow", 250), VERY_SLOW("Very Slow", 1000);

	private int num;
	private String string;

	private DelayNum(String string, int i) {
		this.num = i;
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

	public int getNum() {
		return num;
	}
}
