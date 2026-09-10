package model;

import java.util.List;

public class Strategy {

	private int strategyID;
	private String strategyName;

	private BetType betType;
	private BetStyle betStyle;

	private List<Integer> firstChoices;
	private List<Integer> secondChoices;
	private List<Integer> thirdChoices;

	private int stake;

}