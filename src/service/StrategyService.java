package service;

import java.util.ArrayList;
import java.util.List;

import model.Strategy;

public class StrategyService {

	private List<Strategy> strategies = new ArrayList<>();

	public void addStrategy(Strategy strategy) {
		strategies.add(strategy);
	}

	public List<Strategy> getAllStrategies() {
		return strategies;
	}

	public Strategy findById(int id) {

		for (Strategy strategy : strategies) {

			if (strategy.getStrategyId() == id) {

				return strategy;

			}
		}
		return null;
	}

	public boolean deleteStrategy(int id) {

		Strategy strategy = findById(id);

		if (strategy == null) {
			return false;
		}

		strategies.remove(strategy);

		return true;
	}

	public boolean updateStrategy(int id, String newName, int newStake) {

		Strategy strategy = findById(id);

		if (strategy == null) {
			return false;
		}

		strategy.setStrategyName(newName);
		strategy.setStake(newStake);
		return true;

	}
}
