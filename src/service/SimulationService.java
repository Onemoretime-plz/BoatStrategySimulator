package service;

import java.util.List;

import model.BetType;
import model.RaceResult;
import model.SimulationResult;
import model.SimulationSummary;
import model.Strategy;

public class SimulationService {

	public SimulationResult simulateWin(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() != BetType.WIN) {
			return null;
		}

		if (strategy.getFirstChoices().isEmpty()) {
			return null;
		}

		int selectedBoat = strategy.getFirstChoices().get(0);

		int betAmount = strategy.getStake();

		boolean hit = selectedBoat == raceResult.getFirst();

		int payout = 0;

		if (hit) {

			payout = raceResult.getWinPayout()
					* betAmount
					/ 100;
		}

		int profit = payout - betAmount;

		return new SimulationResult(
				hit,
				betAmount,
				payout,
				profit);
	}

	public SimulationSummary simulateWinAll(
			Strategy strategy,
			List<RaceResult> raceResults) {

		if (strategy.getBetType() != BetType.WIN) {
			return null;
		}

		int hitCount = 0;
		int totalBetAmount = 0;
		int totalPayout = 0;

		for (RaceResult raceResult : raceResults) {

			SimulationResult result = simulateWin(
					strategy,
					raceResult);

			if (result.isHit()) {
				hitCount++;
			}

			totalBetAmount += result.getBetAmount();

			totalPayout += result.getPayout();
		}

		int totalProfit = totalPayout - totalBetAmount;

		return new SimulationSummary(
				raceResults.size(),
				hitCount,
				totalBetAmount,
				totalPayout,
				totalProfit);
	}
}