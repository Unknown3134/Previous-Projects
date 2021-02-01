package slotmachine.client;

import javax.swing.SwingUtilities;

import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WheelImpl;
import slotmachine.model.slots.WinSettingsImpl;
import slotmachine.utilities.GuiUtilities;
import slotmachine.view.ConsoleLoggerCallback;
import slotmachine.view.GuiCallback;
import slotmachine.view.SlotMachineFrame;
import slotmachine.view.model.ViewModelSettings;

/**
 * Client of GUI and Model.
 * 
 * Creates a second thread for Swing to run on.
 * 
 * @author Paul McKercher
 */
public class GuiSlotMachine implements GuiUtilities{
	private SlotMachineImpl sm;
	private GuiCallback callback;
	private Wheel wheel1;
	private Wheel wheel2;
	private Wheel wheel3;

	public GuiSlotMachine() {

		wheel1 = WheelImpl.createWheel(WHEEL1_NUM);
		wheel2 = WheelImpl.createWheel(WHEEL2_NUM);
		wheel3 = WheelImpl.createWheel(WHEEL3_NUM);
		
		WinSettingsImpl ws =new WinSettingsImpl();
		sm = new SlotMachineImpl(wheel1, wheel2, wheel3, ws);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				callback = new GuiCallback();
				new SlotMachineFrame(sm, callback, wheel1, wheel2, wheel3, new ViewModelSettings(callback),ws);

			}
		});
		// New thread for the model method calls
		new Thread() {
			@Override
			public void run() {
				sm.addCallback(new ConsoleLoggerCallback());

				sm.addCallback(callback);
				sm.spin(TURNS_ON_LOAD, DELAY_ON_LOAD);
				
			
			}
		}.start();

		

	}
	
	public static void main(String[] args) {
		
		new GuiSlotMachine();
	}

}