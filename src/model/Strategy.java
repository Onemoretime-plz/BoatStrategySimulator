package model;

import java.util.List;

public class Strategy {

	private int strategyId;
	private String strategyName;

	private BetType betType;
	private BetStyle betStyle;

	private List<Integer> firstChoices;
	private List<Integer> secondChoices;
	private List<Integer> thirdChoices;

	private int stake;

	public Strategy(
			int strategyId,
			String strategyName,
			BetType betType,
			BetStyle betStyle,
			List<Integer> firstChoices,
			List<Integer> secondChoices,
			List<Integer> thirdChoices,
			int stake) {

		this.strategyId = strategyId;
		this.strategyName = strategyName;
		this.betType = betType;
		this.betStyle = betStyle;
		this.firstChoices = firstChoices;
		this.secondChoices = secondChoices;
		this.thirdChoices = thirdChoices;
	}

	public int getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public BetStyle getBetStyle() {
		return betStyle;
	}

	public void setBetStyle(BetStyle betStyle) {
		this.betStyle = betStyle;
	}

	public List<Integer> getFirstChoices() {
		return firstChoices;
	}

	public void setFirstChoices(List<Integer> firstChoices) {
		this.firstChoices = firstChoices;
	}

	public List<Integer> getSecondChoices() {
		return secondChoices;
	}

	public void setSecondChoices(List<Integer> secondChoices) {
		this.secondChoices = secondChoices;
	}

	public List<Integer> getThirdChoices() {
		return thirdChoices;
	}

	public void setThirdChoices(List<Integer> thirdChoices) {
		this.thirdChoices = thirdChoices;
	}

	public int getStake() {
		return stake;
	}

	public void setStake(int stake) {
		this.stake = stake;
	}
}