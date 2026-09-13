package main;

import java.util.List;
import java.util.Scanner;

import model.Strategy;
import model.BetStyle;
import model.BetType;
import service.StrategyService;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		StrategyService strategyService01 = new StrategyService();

		Strategy strategy01 = new Strategy(
				1,
				"1号艇単勝",
				BetType.WIN,
				BetStyle.NORMAL,
				List.of(1),
				List.of(),
				List.of(),
				500);

		Strategy strategy02 = new Strategy(
				2,
				"1号艇軸3連単",
				BetType.TRIFECTA,
				BetStyle.FORMATION,
				List.of(1),
				List.of(2, 3),
				List.of(2, 3, 4),
				100);

		strategyService01.addStrategy(strategy01);

		strategyService01.addStrategy(strategy02);

		showMainMenu();

		int menuChoice = scanner.nextInt();

		if (menuChoice == 1) {

		} else if (menuChoice == 2) {

		} else if (menuChoice == 3) {

		} else if (menuChoice == 0) {

		} else {
			System.out.println("0から3の整数を入力してください。");
		}

	}

	public static void showMainMenu() {
		System.out.println("=================================");
		System.out.println(" Boat Strategy Simulator");
		System.out.println("=================================");
		System.out.println();
		System.out.println("1. 戦略管理");
		System.out.println("2. シミュレーション");
		System.out.println("3. レースデータCSVインポート");
		System.out.println("0. 終了");
		System.out.println();
		System.out.println("選択してください：");
		System.out.print(">");

	}

	public static void showStrategyMenu() {
		System.out.println("===== 戦略一覧 =====");
		System.out.println();
		System.out.println("ID  戦略名                    券種       買い方");
		System.out.println("------------------------------------------------");

	}

	public static void showStrategyDetail(Strategy strategy) {
		System.out.println("===== 戦略詳細 =====");
		System.out.println();
		System.out.println("ID          ：" + strategy.getStrategyId());
		System.out.println("戦略名      ：" + strategy.getStrategyName());
		System.out.println("券種        ：" + strategy.getBetType());
		System.out.println("買い方      ：" + strategy.getBetStyle());
		System.out.println("買い目      ：" + strategy.getFirstChoices());
		System.out.println("1点賭け金   ：" + strategy.getStake());
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
		System.out.println("1. 編集");
		System.out.println("2. 削除");
		System.out.println("0. 戦略一覧へ戻る");
		System.out.println();
		System.out.println("選択してください：");
		System.out.println(">");
	}
