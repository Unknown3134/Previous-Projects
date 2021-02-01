package slotmachine.view.model;

/**
 * A very helpful enum to store the Turn values
 * 
 * 
 * @author Paul McKercher
 */
public enum TurnNum {

	DEFAULT_LABEL("Select Turns", 20), QUICK("Quick", 5), SHORT("Short", 10),
	DEFAULT("Default", 20), LONG("Long", 50);

	private int num;
	private String string;

	private TurnNum(String name, int i) {
		this.num = i;
		this.string = name;
	}

	@Override
	public String toString() {
		return string;
	}

	public int getNum() {
		return num;
	}
}
