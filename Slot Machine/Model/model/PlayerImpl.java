package slotmachine.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerImpl implements Player {

	private String id;
	private String name;
	private int initialCredits;

	private int credits;
	private int bet;

	/*
	 * Contructor with three separate checks and three Exception messages I
	 * considered a helper method/s here such as checkName() but it works out to be
	 * more writing more code, cause the constructor still needs IF statements and
	 * throwing exceptions
	 */
	public PlayerImpl(String id, String name, int initialCredits)
			throws IllegalArgumentException {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					String.format("%s","Player Name is invalid", name));
		} else {
			this.name = name;

			Pattern p = Pattern.compile("\\d{5}[A-Za-z]{1}$");
			Matcher m = p.matcher(id);
			if (m.matches()) {
				this.id = id;

			} else {
				throw new IllegalArgumentException(
						String.format("%s","Player id must conform to (Example: 12345X)", id));
			}
			if (initialCredits < 0) {
				throw new IllegalArgumentException(
						"Initial credits can not be a negative number");
			} else
				this.initialCredits = this.credits = initialCredits;
		}

	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getInitalCredits() {
		return this.initialCredits;
	}

	@Override
	public int getCredits() {
		return this.credits;
	}

	@Override
	public int getAvailableCredits() {
		return this.bet <= 0 ? this.credits : this.credits - this.bet;

	}

	@Override
	public void addCredits(int credits) {
		this.credits += credits;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void setBet(int bet) {
		this.bet += bet;
		// System.out.println(currentBet);
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public void applyWin(int winAmount) {

		this.credits -= this.bet;
		
		if (winAmount > 0)
			this.credits += winAmount;
	}

	@Override
	public String toString() {
		return String.format("Player %s, %s, credits %d, bet %d, available %d", this.id,
				this.name, this.credits, this.bet, getAvailableCredits());

	}
}
