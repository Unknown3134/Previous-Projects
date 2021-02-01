package slotmachine.view.model;

import javax.swing.ImageIcon;

/**
 * A very helpful enum to store Player Icons
 * 
 * 
 * @author Paul McKercher
 */
public enum IconNum {
	ICON1("images/capicon.png", "ICON1"), ICON2("images/widowicon.png", "ICON2"),
	ICON3("images/hulkicon.png", "ICON3"), ICON4("images/Ironicon.png", "ICON4");

	private String label;
	private ImageIcon icon;

	private IconNum(String path, String label) {
		this.icon = new ImageIcon(path, label);
		this.label = label;
	}

	public ImageIcon getIcon() {
		return this.icon;
	}

	public String getLabel() {
		return label;
	}
}
