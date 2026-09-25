package service;

import java.util.ArrayList;
import java.util.List;

import model.BetStyle;
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

		if (strategy.getBetStyle() == BetStyle.BOX) {

			return simulateExactaBox(
					strategy,
					raceResult);
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()) {
			return null;
		}

		int ticketCount = 0;
		boolean hit = false;

		for (int first : strategy.getFirstChoices()) {

			for (int second : strategy.getSecondChoices()) {

				// 同じ艇同士の買い目は除外
				if (first == second) {
					continue;
				}

				ticketCount++;

				if (first == raceResult.getFirst()
						&& second == raceResult.getSecond()) {

					hit = true;
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getExactaPayout()
					* strategy.getStake()
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

		if (strategy.getBetStyle() == BetStyle.BOX) {

			return simulateTrifectaBox(
					strategy,
					raceResult);
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()
				|| strategy.getThirdChoices().isEmpty()) {
			return null;
		}

		int ticketCount = 0;
		boolean hit = false;

		for (int first : strategy.getFirstChoices()) {

			for (int second : strategy.getSecondChoices()) {

				for (int third : strategy.getThirdChoices()) {

					// 同じ艇を複数着に指定した買い目は除外
					if (first == second
							|| first == third
							|| second == third) {

						continue;
					}

					ticketCount++;

					if (first == raceResult.getFirst()
							&& second == raceResult.getSecond()
							&& third == raceResult.getThird()) {

						hit = true;
					}
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getTrifectaPayout()
					* strategy.getStake()
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

		if (strategy.getBetStyle() == BetStyle.BOX) {

			return simulateQuinellaBox(
					strategy,
					raceResult);
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()) {
			return null;
		}

		int ticketCount = 0;
		boolean hit = false;

		List<String> generatedTickets = new ArrayList<>();

		for (int boat1 : strategy.getFirstChoices()) {

			for (int boat2 : strategy.getSecondChoices()) {

				// 同じ艇同士は成立しない
				if (boat1 == boat2) {
					continue;
				}

				// 2連複は順不同なので小さい艇番を先にする
				int minBoat = Math.min(boat1, boat2);
				int maxBoat = Math.max(boat1, boat2);

				String ticket = minBoat + "-" + maxBoat;

				// 同じ買い目の重複を防ぐ
				if (generatedTickets.contains(ticket)) {
					continue;
				}

				generatedTickets.add(ticket);
				ticketCount++;

				boolean ticketHit = (minBoat == raceResult.getFirst()
						&& maxBoat == raceResult.getSecond())
						||
						(minBoat == raceResult.getSecond()
								&& maxBoat == raceResult.getFirst());

				if (ticketHit) {
					hit = true;
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getQuinellaPayout()
					* strategy.getStake()
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

		if (strategy.getBetStyle() == BetStyle.BOX) {

			return simulateTrioBox(
					strategy,
					raceResult);
		}

		if (strategy.getFirstChoices().isEmpty()
				|| strategy.getSecondChoices().isEmpty()
				|| strategy.getThirdChoices().isEmpty()) {
			return null;
		}

		int ticketCount = 0;
		boolean hit = false;

		List<String> generatedTickets = new ArrayList<>();

		for (int boat1 : strategy.getFirstChoices()) {

			for (int boat2 : strategy.getSecondChoices()) {

				for (int boat3 : strategy.getThirdChoices()) {

					// 同じ艇を含む組み合わせは除外
					if (boat1 == boat2
							|| boat1 == boat3
							|| boat2 == boat3) {

						continue;
					}

					// 3艇を小さい順に並べる
					List<Integer> boats = new ArrayList<>();

					boats.add(boat1);
					boats.add(boat2);
					boats.add(boat3);

					boats.sort(null);

					String ticket = boats.get(0)
							+ "-"
							+ boats.get(1)
							+ "-"
							+ boats.get(2);

					// 同じ組み合わせを二重に数えない
					if (generatedTickets.contains(ticket)) {
						continue;
					}

					generatedTickets.add(ticket);
					ticketCount++;

					boolean firstHit = boats.contains(
							raceResult.getFirst());

					boolean secondHit = boats.contains(
							raceResult.getSecond());

					boolean thirdHit = boats.contains(
							raceResult.getThird());

					if (firstHit
							&& secondHit
							&& thirdHit) {

						hit = true;
					}
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getTrioPayout()
					* strategy.getStake()
					/ 100;
		}

		int profit = payout - betAmount;

		return new SimulationResult(
				hit,
				betAmount,
				payout,
				profit);
	}

	public SimulationResult simulateExactaBox(
			Strategy strategy,
			RaceResult raceResult) {

		List<Integer> boats = strategy.getFirstChoices();

		int ticketCount = 0;
		boolean hit = false;

		for (int first : boats) {

			for (int second : boats) {

				if (first == second) {
					continue;
				}

				ticketCount++;

				if (first == raceResult.getFirst()
						&& second == raceResult.getSecond()) {

					hit = true;
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getExactaPayout()
					* strategy.getStake()
					/ 100;
		}

		return new SimulationResult(
				hit,
				betAmount,
				payout,
				payout - betAmount);
	}

	public SimulationResult simulateTrifectaBox(
			Strategy strategy,
			RaceResult raceResult) {

		List<Integer> boats = strategy.getFirstChoices();

		int ticketCount = 0;
		boolean hit = false;

		for (int first : boats) {

			for (int second : boats) {

				for (int third : boats) {

					if (first == second
							|| first == third
							|| second == third) {

						continue;
					}

					ticketCount++;

					if (first == raceResult.getFirst()
							&& second == raceResult.getSecond()
							&& third == raceResult.getThird()) {

						hit = true;
					}
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getTrifectaPayout()
					* strategy.getStake()
					/ 100;
		}

		return new SimulationResult(
				hit,
				betAmount,
				payout,
				payout - betAmount);
	}

	public SimulationResult simulateQuinellaBox(
			Strategy strategy,
			RaceResult raceResult) {

		List<Integer> boats = strategy.getFirstChoices();

		int ticketCount = 0;
		boolean hit = false;

		for (int i = 0; i < boats.size(); i++) {

			for (int j = i + 1; j < boats.size(); j++) {

				int boat1 = boats.get(i);
				int boat2 = boats.get(j);

				ticketCount++;

				if ((boat1 == raceResult.getFirst()
						&& boat2 == raceResult.getSecond())
						||
						(boat1 == raceResult.getSecond()
								&& boat2 == raceResult.getFirst())) {

					hit = true;
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getQuinellaPayout()
					* strategy.getStake()
					/ 100;
		}

		return new SimulationResult(
				hit,
				betAmount,
				payout,
				payout - betAmount);
	}

	public SimulationResult simulateTrioBox(
			Strategy strategy,
			RaceResult raceResult) {

		List<Integer> boats = strategy.getFirstChoices();

		int ticketCount = 0;
		boolean hit = false;

		for (int i = 0; i < boats.size(); i++) {

			for (int j = i + 1; j < boats.size(); j++) {

				for (int k = j + 1; k < boats.size(); k++) {

					int boat1 = boats.get(i);
					int boat2 = boats.get(j);
					int boat3 = boats.get(k);

					ticketCount++;

					boolean firstHit = raceResult.getFirst() == boat1
							|| raceResult.getFirst() == boat2
							|| raceResult.getFirst() == boat3;

					boolean secondHit = raceResult.getSecond() == boat1
							|| raceResult.getSecond() == boat2
							|| raceResult.getSecond() == boat3;

					boolean thirdHit = raceResult.getThird() == boat1
							|| raceResult.getThird() == boat2
							|| raceResult.getThird() == boat3;

					if (firstHit
							&& secondHit
							&& thirdHit) {

						hit = true;
					}
				}
			}
		}

		int betAmount = strategy.getStake() * ticketCount;

		int payout = 0;

		if (hit) {

			payout = raceResult.getTrioPayout()
					* strategy.getStake()
					/ 100;
		}

		return new SimulationResult(
				hit,
				betAmount,
				payout,
				payout - betAmount);
	}
}