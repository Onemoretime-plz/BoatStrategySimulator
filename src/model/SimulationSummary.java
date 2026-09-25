package model;

public class SimulationSummary {

	private int raceCount;
	private int hitCount;
	private int totalBetAmount;
	private int totalPayout;
	private int totalProfit;

	public SimulationSummary(
			int raceCount,
			int hitCount,
			int totalBetAmount,
			int totalPayout,
			int totalProfit) {

		this.raceCount = raceCount;
		this.hitCount = hitCount;
		this.totalBetAmount = totalBetAmount;
		this.totalPayout = totalPayout;
		this.totalProfit = totalProfit;
	}

	public int getRaceCount() {
		return raceCount;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getTotalBetAmount() {
		return totalBetAmount;
	}

	public int getTotalPayout() {
		return totalPayout;
	}

	public int getTotalProfit() {
		return totalProfit;
	}

	public double getHitRate() {

		if (raceCount == 0) {
			return 0.0;
		}

		return (double) hitCount / raceCount * 100;
	}

	public double getReturnRate() {

		if (totalBetAmount == 0) {
			return 0.0;
		}

		return (double) totalPayout / totalBetAmount * 100;
	}
}