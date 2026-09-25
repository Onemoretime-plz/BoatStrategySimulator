package model;

public class SimulationResult {

	private boolean hit;
	private int betAmount;
	private int payout;
	private int profit;

	public SimulationResult(
			boolean hit,
			int betAmount,
			int payout,
			int profit) {

		this.hit = hit;
		this.betAmount = betAmount;
		this.payout = payout;
		this.profit = profit;
	}

	public boolean isHit() {
		return hit;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public int getPayout() {
		return payout;
	}

	public int getProfit() {
		return profit;
	}
}