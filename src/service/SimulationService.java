package service;

import model.BetType;
import model.RaceResult;
import model.SimulationResult;
import model.Strategy;

public class SimulationService {

	public SimulationResult simulateWin(
			Strategy strategy,
			RaceResult raceResult) {

		// 単勝戦略以外は処理しない
		if (strategy.getBetType() != BetType.WIN) {
			return null;
		}

		// 買い目が存在しない場合
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
}