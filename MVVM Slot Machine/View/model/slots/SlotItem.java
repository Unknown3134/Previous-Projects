package slotmachine.model.slots;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Supporting enum used in the <b>Further Programming Assignment</b>
 * <p>
 * The values in this enum are use to represent a single slot item on a wheel.
 * <p>
 * Each {@link Wheel} is made up of 100 {@link SlotItem} with {@link #count}
 * number of each {@link SlotItem} value. e.g. There are two {@link #GOLD} 
 * and 7 {@link #LIME} on each wheel
 * <p>
 * <b>Note:</b> You are <b>not</b> permitted to change this enum in any way
 * (aside from during your own testing). The submitted enum must exactly match
 * the start up code.
 * 
 *
 * 
 * @author Ross Nye
 */
public enum SlotItem
{
	ONE("1", 22,"images/Number-1-icon.png"),
	THREE("3", 18,"images/3-icon.png"),
	FIVE("5", 14,"images/Five-Fingers-icon.png"),
	SEVEN("7", 11, "images/seven-icon.png"),
	LEMON("Lemon", 8, "images/lemon-icon.png"),
	LIME("Lime", 7, "images/lime-icon.png"),	
	BERRY("Berry",6, "images/Plants-Berry-icon.png"),
	CHERRY("Cherry", 5, "images/cherry.png"),
	MELON("Melon", 4, "images/Melon-icon.png"),
	SILVER("Silver Bar", 3, "images/Trophy-silver-icon.png"),
	GOLD("Gold Bar", 2, "images/coin-icon.png");

	//https://iconarchive.com/show/pretty-office-7-icons-by-custom-icon-design/Trophy-silver-icon.html
	
	private String label;
	private int count;
	private ImageIcon icon;
	
	private SlotItem(String label, int count, String path)
	{
		this.label = label;
		this.count = count;
		this.icon = new ImageIcon(path, label);
	}
	
	@Override
	public String toString()
	{
		return label;
	}
	
	public int getCount()
	{
		return count;
	}
	public ImageIcon getIcon() {
		return icon;
	}
}
