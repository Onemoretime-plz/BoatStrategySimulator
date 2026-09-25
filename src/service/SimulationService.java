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

	public SimulationResult simulatePlace(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() != BetType.PLACE) {
			return null;
		}

		if (strategy.getFirstChoices().isEmpty()) {
			return null;
		}

		int selectedBoat = strategy.getFirstChoices().get(0);

		int betAmount = strategy.getStake();

		int payout = 0;

		boolean hit = false;

		if (selectedBoat == raceResult.getPlace1Boat()) {

			hit = true;

			payout = raceResult.getPlace1Payout()
					* betAmount
					/ 100;

		} else if (selectedBoat == raceResult.getPlace2Boat()) {

			hit = true;

			payout = raceResult.getPlace2Payout()
					* betAmount
					/ 100;

		} else if (selectedBoat == raceResult.getPlace3Boat()) {

			hit = true;

			payout = raceResult.getPlace3Payout()
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

	public SimulationResult simulate(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() == BetType.WIN) {

			return simulateWin(
					strategy,
					raceResult);

		} else if (strategy.getBetType() == BetType.PLACE) {

			return simulatePlace(
					strategy,
					raceResult);

		}

		return null;
	}

	public SimulationSummary simulateAll(
			Strategy strategy,
			List<RaceResult> raceResults) {

		int hitCount = 0;
		int totalBetAmount = 0;
		int totalPayout = 0;

		for (RaceResult raceResult : raceResults) {

			SimulationResult result = simulate(
					strategy,
					raceResult);

			if (result == null) {
				return null;
			}

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