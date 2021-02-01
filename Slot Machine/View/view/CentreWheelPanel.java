package slotmachine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import slotmachine.model.slots.Wheel;
import slotmachine.utilities.GuiUtilities;

/**
 * This is centre wheel panel, which takes up most of the space. Overriding
 * painComponent to get the grid like layout of slots.
 * 
 * @author Paul McKercher
 */

@SuppressWarnings("serial")
public class CentreWheelPanel extends JPanel
		implements PropertyChangeListener, GuiUtilities {

	private static final int ROWS = 3;
	private static final int COLS = 3;

	private List<Wheel> wheels;
	private boolean line1,line2,line3,line4,line5;
	

	public CentreWheelPanel(GuiCallback callback, Wheel wheel1, Wheel wheel2,
			Wheel wheel3) {

		wheels = new ArrayList<Wheel>();
		wheels.add(wheel1);
		wheels.add(wheel2);
		wheels.add(wheel3);
		callback.addPropertyChangeListener(this);
		
		line1 = false;
		line2 = false;
		line3 = false;
		line4 = false;
		line5 = false;
		
		setBackground(new Color(126,161,175));

	}

	/*
	 * Simple method for scaling and placing all the icons in a grid and scalable to
	 * the panel size
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int xPos = getWidth() / COLS;
		int yPos = getHeight() / ROWS;
		int xScale = xPos;
		int yScale = yPos;
		int index = 0;
		
		setForeground(Color.RED);

		if (wheels.get(WHEEL1_NUM - 1) != null && wheels.get(WHEEL2_NUM - 1) != null
				&& wheels.get(WHEEL3_NUM - 1) != null) {

			for (Wheel wheel : wheels) {

				

				g.setColor(getForeground());
				g.drawImage(wheel.getTopSlot().getIcon().getImage(), xPos * index,
						yPos - yPos, xScale, yScale, this);
				g.drawImage(wheel.getCentreSlot().getIcon().getImage(), xPos * index,
						yPos, xScale, yScale, this);
			
				g.drawImage(wheel.getBottomSlot().getIcon().getImage(), xPos * index,
						yPos + yPos, xScale, yScale, this);
				index++;

			}
			
			// The following 5 checks will allow to highlight any winning lines

			if (line2) {
				index = 0;
				g.setColor(HIGHLIGHT);
				g.drawRect(xPos - xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos - xPos, yPos * index, xScale, yScale);
				g.drawRect(xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos, yPos * index, xScale, yScale);
				g.drawRect(xPos + xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos + xPos, yPos * index, xScale, yScale);
			}

			if (line1) {
				index = 1;
				g.setColor(HIGHLIGHT);
				g.drawRect(xPos - xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos - xPos, yPos * index, xScale, yScale);
				g.drawRect(xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos, yPos * index, xScale, yScale);

				g.drawRect(xPos + xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos + xPos, yPos * index, xScale, yScale);
			}

			if (line3) {
				index = 2;
				g.setColor(HIGHLIGHT);
				g.drawRect(xPos - xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos - xPos, yPos * index, xScale, yScale);
				g.drawRect(xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos, yPos * index, xScale, yScale);
				g.drawRect(xPos + xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos + xPos, yPos * index, xScale, yScale);
			}
			if (line4) {
				index=0;
				g.setColor(HIGHLIGHT);
				g.drawRect(xPos - xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos - xPos, yPos * index, xScale, yScale);
				index++;
				g.drawRect(xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos, yPos * index, xScale, yScale);
				index++;
				g.drawRect(xPos + xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos + xPos, yPos * index, xScale, yScale);
				
			}
			if (line5) {
				index = 2;
				g.setColor(HIGHLIGHT);
				g.drawRect(xPos - xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos - xPos, yPos * index, xScale, yScale);
				index--;
				g.drawRect(xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos, yPos * index, xScale, yScale);
				index--;
				g.drawRect(xPos + xPos, yPos * index, xScale, yScale);
				g.fillRect(xPos + xPos, yPos * index, xScale, yScale);

				
			}

		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName() == WHEEL) {
			repaint();
		}

		int amount = 0;
		if (evt.getPropertyName() == SLOT_LINE) {
			amount = (int) evt.getOldValue();
			
			String line = (String) evt.getNewValue();
			if (line.contains("#1") && amount > 0) {
				line1 = true;
				repaint();
			}
			if (line.contains("#2") && amount > 0) {
				line2 = true;
				repaint();

			}
			if (line.contains("#3") && amount > 0) {
				line3 = true;
				repaint();
			}
			if (line.contains("#4") && amount > 0) {
				line4 = true;
				repaint();
			}
			if (line.contains("#5") && amount > 0) {
				line5 = true;
				repaint();
			}

		}
		if (evt.getPropertyName() == WHEEL || evt.getPropertyName() == BET_UPDATE) {
			line1 = false;
			line2 = false;
			line3 = false;
			line4 = false;
			line5 = false;
			repaint();

		}

// None of this code required, now I removed wheel getter and instantiated wheels in main

//			WheelImpl wheel = (WheelImpl) evt.getNewValue();

//			if (wheel.getWheelNum() == WHEEL1_NUM) {
//				wheels.set(WHEEL1_NUM - 1, wheel);
//				
//			}
//			if (wheel.getWheelNum() == WHEEL2_NUM) {
//				wheels.set(WHEEL2_NUM - 1, wheel);
//				
//			}
//			if (wheel.getWheelNum() == WHEEL3_NUM) {
//				wheels.set(WHEEL3_NUM - 1, wheel);
//				
//			}

	}

}
