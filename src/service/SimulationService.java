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

		} else if (strategy.getBetType() == BetType.EXACTA) {

			return simulateExacta(
					strategy,
					raceResult);

		} else if (strategy.getBetType() == BetType.TRIFECTA) {

			return simulateTrifecta(
					strategy,
					raceResult);

		} else if (strategy.getBetType() == BetType.QUINELLA) {

			return simulateQuinella(
					strategy,
					raceResult);

		} else if (strategy.getBetType() == BetType.TRIO) {

			return simulateTrio(
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

	public SimulationResult simulateExacta(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() != BetType.EXACTA) {
			return null;
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()) {
			return null;
		}

		int selectedFirst = strategy.getFirstChoices().get(0);

		int selectedSecond = strategy.getSecondChoices().get(0);

		int betAmount = strategy.getStake();

		boolean hit = selectedFirst == raceResult.getFirst()
				&& selectedSecond == raceResult.getSecond();

		int payout = 0;

		if (hit) {

			payout = raceResult.getExactaPayout()
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

	public SimulationResult simulateTrifecta(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() != BetType.TRIFECTA) {
			return null;
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()
				|| strategy.getThirdChoices().isEmpty()) {
			return null;
		}

		int selectedFirst = strategy.getFirstChoices().get(0);

		int selectedSecond = strategy.getSecondChoices().get(0);

		int selectedThird = strategy.getThirdChoices().get(0);

		int betAmount = strategy.getStake();

		boolean hit = selectedFirst == raceResult.getFirst()
				&& selectedSecond == raceResult.getSecond()
				&& selectedThird == raceResult.getThird();

		int payout = 0;

		if (hit) {

			payout = raceResult.getTrifectaPayout()
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

	public SimulationResult simulateQuinella(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() != BetType.QUINELLA) {
			return null;
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()) {
			return null;
		}

		int selectedBoat1 = strategy.getFirstChoices().get(0);

		int selectedBoat2 = strategy.getSecondChoices().get(0);

		int betAmount = strategy.getStake();

		boolean hit = (selectedBoat1 == raceResult.getFirst()
				&& selectedBoat2 == raceResult.getSecond())
				||
				(selectedBoat1 == raceResult.getSecond()
						&& selectedBoat2 == raceResult.getFirst());

		int payout = 0;

		if (hit) {

			payout = raceResult.getQuinellaPayout()
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

	public SimulationResult simulateTrio(
			Strategy strategy,
			RaceResult raceResult) {

		if (strategy.getBetType() != BetType.TRIO) {
			return null;
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()
				|| strategy.getThirdChoices().isEmpty()) {
			return null;
		}

		int selectedBoat1 = strategy.getFirstChoices().get(0);

		int selectedBoat2 = strategy.getSecondChoices().get(0);

		int selectedBoat3 = strategy.getThirdChoices().get(0);

		int betAmount = strategy.getStake();

		boolean boat1Hit = selectedBoat1 == raceResult.getFirst()
				|| selectedBoat1 == raceResult.getSecond()
				|| selectedBoat1 == raceResult.getThird();

		boolean boat2Hit = selectedBoat2 == raceResult.getFirst()
				|| selectedBoat2 == raceResult.getSecond()
				|| selectedBoat2 == raceResult.getThird();

		boolean boat3Hit = selectedBoat3 == raceResult.getFirst()
				|| selectedBoat3 == raceResult.getSecond()
				|| selectedBoat3 == raceResult.getThird();

		boolean hit = boat1Hit
				&& boat2Hit
				&& boat3Hit;

		int payout = 0;

		if (hit) {

			payout = raceResult.getTrioPayout()
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
}