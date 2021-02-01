package slotmachine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.SpinResultImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WheelImpl;
import slotmachine.model.slots.WinSettings;
import slotmachine.model.slots.WinSettingsImpl;
import slotmachine.view.GameCallback;

public class SlotMachineImpl implements SlotMachine {

	private Wheel[] wheels;
	private WinSettings winSettings;
	private Collection<GameCallback> callbacks;
	private Map<LineNum, Integer> bets;
	private Player player, lastPlayer;

	private static final int MIN_CREDITS = 0;
	private static final double WHEEL2_RATE = 0.66;
	private static final double WHEEL3_RATE = 0.33;
	private static final int WHEEL1_NUM = 1;
	private static final int WHEEL2_NUM = 2;
	private static final int WHEEL3_NUM = 3;

	public SlotMachineImpl() {
		this(WheelImpl.createWheel(WHEEL1_NUM), WheelImpl.createWheel(WHEEL2_NUM),
				WheelImpl.createWheel(WHEEL3_NUM), new WinSettingsImpl());

	}

	/*
	 * Second constructor for Tutor marking. Has same function as the other
	 * constructor except takes provided objects
	 */
	public SlotMachineImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3,
			WinSettings winSettings) {
		this.wheels = new Wheel[3];
		this.wheels[0] = wheel1;
		this.wheels[1] = wheel2;
		this.wheels[2] = wheel3;
		this.winSettings = winSettings;
		this.callbacks = new ArrayList<GameCallback>();
		this.bets = new HashMap<LineNum, Integer>();
		// forcing null on player, to be sure a player is registered before change of
		// state
		this.player = null;
		// To avoid null problems, I created a 0 bet for each Line. Logically, a bet
		// exists if the value is > 0
		bets.put(LineNum.LINE1, 0);
		bets.put(LineNum.LINE2, 0);
		bets.put(LineNum.LINE3, 0);
		bets.put(LineNum.LINE4, 0);
		bets.put(LineNum.LINE5, 0);
	}

	@Override
	public int addCallback(GameCallback callback) {
		this.callbacks.add(callback);
		return this.callbacks.size();
	}

	@Override
	public int removeCallback(GameCallback callback) {
		callbacks.remove(callback);
		return this.callbacks.size();
	}

	@Override
	public Player registerPlayer(String id, String name, int initialCredits)
			throws NullPointerException, IllegalArgumentException, IllegalStateException {
		if (nullOrEmpty(id))
			throw new IllegalArgumentException("The Player ID cannot be empty!");

		if (!Objects.isNull(this.player))
			throw new IllegalStateException(String.format("%s   %s",
					"Please cash out the current player first:", player.getName()));

		this.player = new PlayerImpl(id, name, initialCredits);

		for (GameCallback g : callbacks) {
			g.registerPlayer(this.player);
		}
		return this.player;
	}

	@Override
	public Player cashOut() throws IllegalStateException {
		checkPlayer();

		resetBets();
		this.lastPlayer = this.player;
		this.player = null;
		for (GameCallback g : callbacks)
			g.cashOutPlayer(this.lastPlayer);

		return this.lastPlayer;

	}

	@Override
	public void addCredits(int credits)
			throws IllegalStateException, IllegalArgumentException {
		checkPlayer();
		if (credits < MIN_CREDITS)
			throw new IllegalArgumentException(
					"Must supply a positive amount of credits!");
		this.player.addCredits(credits);
		for (GameCallback g : callbacks) {
			g.addCredits(this.player, credits);
		}
	}

	@Override
	public void resetBets() {
		checkPlayer();
		this.player.resetBet();
		// Local List for sorting the Lines in Ascending order
		List<LineNum> orderedLines = new ArrayList<>(bets.keySet());
		Collections.sort(orderedLines);
		for (LineNum key : bets.keySet())
			bets.replace(key, 0);

		for (GameCallback g : callbacks)
			g.betUpdated(this.player, 0, orderedLines);

	}

	@Override
	public SpinResult spinToWin(int turns, int delay)
			throws IllegalArgumentException, IllegalStateException {
		checkPlayer();
		if (this.player.getBet() == 0)
			throw new IllegalArgumentException("Must have a current Bet");
		SpinResult spin = spin(turns, delay);
		applySpinResult(spin);
		return spin;

	}

	@Override
	public SpinResult spin(int turns, int delay) throws IllegalArgumentException {
		if (turns < 1 || delay < 0 || delay > 2000)
			throw new IllegalArgumentException(
					"Turns must be at Least 1 and Delay must be a positive number less that 2000");

		// Wheels will always spin at least once, before For loop begins
		wheels[0].nextSlot();
		turnWheelCallback(wheels[0], 1);
		wheels[1].nextSlot();
		turnWheelCallback(wheels[1], 1);
		wheels[2].nextSlot();
		turnWheelCallback(wheels[2], 1);
		runDelay(delay);

		for (int i = 1; i < turns; i++) {

			wheels[0].nextSlot();
			turnWheelCallback(wheels[0], i + 1);
			if (i < Math.ceil(turns * WHEEL2_RATE) - 1) {
				wheels[1].nextSlot();
				turnWheelCallback(wheels[1], i + 1);
			}

			if (i < Math.ceil(turns * WHEEL3_RATE) - 1) {
				wheels[2].nextSlot();
				turnWheelCallback(wheels[2], i + 2);
			}
			if (i < turns - 1)
				runDelay(delay);
		}

		SpinResult spin = new SpinResultImpl(wheels[0], wheels[1], wheels[2]);
		
		for (GameCallback g : callbacks)
			g.spinComplete(spin);

		return spin;

	}

	@Override
	public int applySpinResult(SpinResult spinResult) {

		boolean hasBet = false;
		int resultTotal = 0;

		for (SlotLine line : spinResult) {
			LineNum currentLine = line.getLineNum();
			hasBet = bets.get(currentLine) > 0 ? true : false;
			resultTotal += this.winSettings.getWinOdds(line) * bets.get(currentLine);
			for (GameCallback g : callbacks)
				g.lineResult(player, hasBet,
						this.winSettings.getWinOdds(line) * bets.get(currentLine), line);

		}
		player.applyWin(resultTotal);
		if (player.getBet() >= player.getCredits())
			resetBets();
		for (GameCallback g : callbacks)
			g.betTotals(player, resultTotal);

		return resultTotal;
	}

	@Override
	public void placeBet(int amount)
			throws IllegalArgumentException, IllegalStateException {
		// calls next method which will put LINE1 into a Set. Will propagate any
		// exceptions from called method
		
		placeBet(amount, LineNum.LINE1);

	}

	@Override
	public void placeBet(int amount, LineNum line)
			throws IllegalArgumentException, IllegalStateException {

		/*
		 * Null check required before adding to set, could have done a helper method for
		 * creating a set and therefore the null check would only be written once. But
		 * come on, this is just one line of code, this saves me writing a helper
		 * method.
		 */
		if (line == null)
			throw new IllegalArgumentException("A Line number must be supplied!");

		// creates a local set of one LineNum, to allow for using the one placeBet
		// method that takes a set
		Set<LineNum> singleLineSet = new HashSet<LineNum>();
		singleLineSet.add(line);
		// calls main method
		placeBet(amount, singleLineSet);

	}

	// This is the main placeBet method which all other placeBets call (there can be
	// only one)
	@Override
	public void placeBet(int amount, Set<LineNum> lines)
			throws IllegalArgumentException, IllegalStateException {
		checkPlayer();
		if (amount < 0 || amount * lines.size() > player.getAvailableCredits())
			throw new IllegalArgumentException(
					"Not enough available credits or a negative bet supplied!");
		// Local List for sorting the Lines in Ascending order
		List<LineNum> sortedLines = new ArrayList<LineNum>(lines);
		Collections.sort(sortedLines);
		if (sortedLines.size() > 1) {
			for (LineNum line : sortedLines) {
				// this null check is repeated on the odd chance this method is called
				// only (a set is passed in from the application).
				if (line == null) {

					restoreBets(amount, lines);
					throw new IllegalArgumentException("A Line number must be supplied!");
				}
				// System.out.println(amount);
				// System.out.println(bets.get(line));
				if (amount <= bets.get(line)) {
					restoreBets(amount, lines);
					throw new IllegalArgumentException(
							"There is already a bet on this Line with a higher or Equal amount! ");
				}

				int previousAmount = bets.get(line);
				player.setBet(amount - previousAmount);
				bets.replace(line, amount);

				// System.out.println(amount);
				// System.out.println(bets.get(line));
			}
		} else {
			// System.out.println(amount + " amount passed in before");

			// System.out
			// .println(bets.get(sortedLines.get(0)) + " amount inside bet array");
			if (sortedLines.get(0) == null) {
				throw new IllegalArgumentException("A Line number must be supplied!");
			}
			if (amount <= bets.get(sortedLines.get(0))) {

				throw new IllegalArgumentException(
						"There is already a bet on this Line with a higher or Equal amount! ");
			}
			player.setBet(amount - player.getBet());
			bets.replace(sortedLines.get(0), amount);
			// System.out.println(amount + " amount passed in before");
			// System.out.println(
			// bets.get(sortedLines.get(0)) + " amount inside bet array AFTER");
			// System.out.println(player.getBet());
		}
		for (GameCallback g : callbacks) {
			g.betUpdated(this.player, amount, sortedLines);
		}

	}
	/*
	 * This method takes an array of LineNum, adds it to a local Set and then calls
	 * the other placeBet that takes a Set.
	 */

	@Override
	public void placeBet(int amount, LineNum... lines)
			throws IllegalArgumentException, IllegalStateException {
		Set<LineNum> localLines = new HashSet<LineNum>();

		for (LineNum line : lines) {
			if (line == null) {
				restoreBets(amount, lines);
				throw new IllegalArgumentException("A Line number must be supplied!");
			} else {
				localLines.add(line);

			}

		}
		placeBet(amount, localLines);
	}

	/**
	 * Calls thread.sleep for the supplied amount of delay
	 * <p>
	 * Catches an interupted exception which is Thrown when a thread is waiting,
	 * sleeping, or otherwise occupied,and the thread is interrupted, either before
	 * or during the activity
	 * 
	 * @param delay the amount of delay
	 * 
	 */
	private void runDelay(int delay) {

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/**
	 * This method replaces the need for 9 x for loops in spin, it is called for
	 * every wheel spin.
	 * <p>
	 * Catches an interupted exception which is Thrown when a thread is waiting,
	 * sleeping, or otherwise occupied,and the thread is interrupted, either before
	 * or during the activity
	 * 
	 * @param wheel the supplied wheel to use
	 * @param turns the current turn number
	 */
	private void turnWheelCallback(Wheel wheel, int turns) {
		for (GameCallback gc : callbacks)
			gc.turnWheel(wheel, turns);
	}

	/**
	 * This method is for the atomic requirement. It will reset the bets back to a
	 * previous state after the failure of placing a bet on multiple lines
	 * 
	 * <p>
	 * The amount is reduced back to the state prior to this instance of running
	 * placeBet()
	 * <p>
	 * This method is called when a null line is supplied or the current line had a
	 * higher bet on it already.
	 * 
	 * @param amount supplied amount
	 * @param lines  array of lines
	 */

	private void restoreBets(int amount, LineNum... lines) {
		for (LineNum line : lines) {

			player.setBet(-amount);
			bets.replace(line, -amount);

		}
	}

	/**
	 * This method is for the atomic requirement. It will reset the bets back to a
	 * previous state after the failure of placing a bet on multiple lines
	 * <p>
	 * Overloaded method in case a Set of Lines has a null value. This is code
	 * duplication, but we don't know if in the future a Collection would be
	 * supplied instead of a LineNum[] by the application.
	 * <p>
	 * The amount is reduced back to the state prior to this instance of running
	 * placeBet()
	 * <p>
	 * This method is called when a null line is supplied or the current line had a
	 * higher bet on it already.
	 * 
	 * @param amount supplied amount
	 * @param lines  set (collection) of lines
	 */

	private void restoreBets(int amount, Set<LineNum> lines) {
		for (LineNum line : lines) {

			player.setBet(-amount);
			bets.replace(line, -amount);
		}
	}

	/**
	 * This method is a simple null/empty checker on a string. Used for checking
	 * player ID
	 * <p>
	 * It should be said Player name is checked inside the player constructor, but
	 * player ID is checked here before instantiating a Player.
	 * 
	 * @param string is supplied for null check
	 * @return true is String is empty
	 */
	private boolean nullOrEmpty(String string) {
		if (string != null && !string.isEmpty())
			return false;
		return true;
	}

	/**
	 * This method is checks there is a registered player and throws an exception if
	 * there is no registerd player.
	 * <p>
	 * This uses the Objects class, which has an isNull method.
	 * 
	 * @throws IllegalStateException if there is no registered player.
	 */
	private void checkPlayer() throws IllegalStateException {
		if (Objects.isNull(this.player))
			throw new IllegalStateException("Must register a player");
	}

	/*
	 * I left my old Spin Result method here for your pleasure Ross, but later in
	 * the game I figured out how to do checking for < 3 without a check for each
	 * ==1 ==2 ==3. Improved logic in the current implemented method above.
	 * 
	 * @Override public SpinResult spin(int turns, int delay) throws
	 * IllegalArgumentException { if (turns < 1 || delay < 0 || delay > 2000) throw
	 * new IllegalArgumentException(
	 * "Turns must be at Least 1 and Delay must be a positive number less that 2000"
	 * );
	 * 
	 * // if turns == 1 or == 2/==3 , they are handled differently to meet the //
	 * requirements if (turns == 1) { wheels[0].nextSlot();
	 * turnWheelCallback(wheels[0], turns); wheels[1].nextSlot();
	 * turnWheelCallback(wheels[1], turns); wheels[2].nextSlot();
	 * turnWheelCallback(wheels[2], turns); } else if (turns == 2 || turns == 3) {
	 * for (int i = 0; i < turns; i++) { wheels[0].nextSlot();
	 * turnWheelCallback(wheels[0], i + 1); if (i == 0) { wheels[1].nextSlot();
	 * turnWheelCallback(wheels[1], i + 1); wheels[2].nextSlot();
	 * turnWheelCallback(wheels[2], i + 1); runDelay(delay); } } } else { //
	 * Otherwise the .66% and .33% rule applies for anything > 3 for (int i = 0; i <
	 * turns; i++) {
	 * 
	 * wheels[0].nextSlot(); turnWheelCallback(wheels[0], i + 1); if (i <
	 * Math.ceil(turns * 0.66) - 1) { wheels[1].nextSlot();
	 * turnWheelCallback(wheels[1], i + 1); }
	 * 
	 * if (i < Math.ceil(turns * 0.33) - 1) { wheels[2].nextSlot();
	 * turnWheelCallback(wheels[2], i + 2); } if (i < turns - 1) runDelay(delay); }
	 * } SpinResult spin = new SpinResultImpl(wheels[0], wheels[1], wheels[2]); for
	 * (GameCallback g : callbacks) g.spinComplete(spin);
	 * 
	 * applySpinResult(spin); return spin;
	 * 
	 * }
	 */

}
