package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.BetStyle;
import model.BetType;
import model.Strategy;
import service.StrategyService;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		StrategyService strategyService01 = new StrategyService();

		// サンプル戦略01インスタンス化
		Strategy strategy01 = new Strategy(
				1,
				"1号艇単勝",
				BetType.WIN,
				BetStyle.NORMAL,
				new ArrayList<>(List.of(1)),
				new ArrayList<>(),
				new ArrayList<>(),
				500);

		// サンプル戦略02インスタンス化
		Strategy strategy02 = new Strategy(
				2,
				"1号艇軸3連単",
				BetType.TRIFECTA,
				BetStyle.FORMATION,
				new ArrayList<>(List.of(1)),
				new ArrayList<>(List.of(2, 3)),
				new ArrayList<>(List.of(2, 3, 4)),
				100);

		// サンプル戦略インスタンス登録
		strategyService01.addStrategy(strategy01);
		strategyService01.addStrategy(strategy02);

		showMainMenu();

		int menuChoice = scanner.nextInt();

		if (menuChoice == 1) {
			showStrategyMenu();

			int strategyMenuChoice = scanner.nextInt();

			if (strategyMenuChoice == 1) {

				for (Strategy strategy : strategyService01.getAllStrategies()) {
					System.out.println("ID:" + strategy.getStrategyId());
					System.out.println("戦略名:" + strategy.getStrategyName());
					System.out.println("券種:" + strategy.getBetType());
					System.out.println("買い方:" + strategy.getBetStyle());
					System.out.println("賭け金:" + strategy.getStake());
					System.out.println();
				}

			} else if (strategyMenuChoice == 2) {

				System.out.println("検索するIDを入力してください。");
				int searchID = scanner.nextInt();

				Strategy foundStrategy = strategyService01.findById(searchID);

				if (foundStrategy != null) {

					System.out.println("===== 検索結果 =====");
					System.out.println();

					System.out.println("ID: " + foundStrategy.getStrategyId());
					System.out.println("戦略名: " + foundStrategy.getStrategyName());

					System.out.println();
					System.out.println("----------------------------");
					System.out.println();
					System.out.println("詳細画面へ進む場合は 『" + searchID + "』 を入力してください。");
					System.out.print(">");

					int iDChoice = scanner.nextInt();

					if (iDChoice == searchID) {
						showStrategyDetail(strategyService01.findById(searchID));

						int detailChoice = scanner.nextInt();

						// 編集
						if (detailChoice == 1) {

							// 削除
						} else if (detailChoice == 2) {

							// メニューへ戻る
						} else if (detailChoice == 0) {

						}

					} else {

						System.out.println("戦略が見つかりません。");
						System.out.println();
					}
				}

			} else if (strategyMenuChoice == 3) {

				System.out.println("新規戦略登録");

			} else if (strategyMenuChoice == 0) {

				System.out.println("メインメニューへ戻ります。");

			} else {

				System.out.println("0から3の整数を入力してください。");
			}

		} else if (menuChoice == 2) {

		} else if (menuChoice == 3) {

		} else if (menuChoice == 0) {

			System.out.println("終了します。");

		} else {
			System.out.println("0から3の整数を入力してください。");
		}

		scanner.close();
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
		System.out.println("===== 戦略管理 =====");
		System.out.println();
		System.out.println("1. 戦略一覧");
		System.out.println("2. 戦略ID検索");
		System.out.println("3. 新規戦略登録");
		System.out.println("0. メニューへ戻る");
	}

	public static void showStrategyDetail(Strategy strategy) {
		System.out.println("===== 戦略詳細 =====");
		System.out.println();
		System.out.println("ID          ：" + strategy.getStrategyId());
		System.out.println("戦略名      ：" + strategy.getStrategyName());
		System.out.println("券種        ：" + strategy.getBetType());
		System.out.println("買い方      ：" + strategy.getBetStyle());
		System.out.println("買い目      ：" + strategy.getFirstChoices());
		System.out.println("1点賭け金   ：" + strategy.getStake() + "円");
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
		System.out.println("1. 編集");
		System.out.println("2. 削除");
		System.out.println("0. 戦略一覧へ戻る");
		System.out.println();
		System.out.println("選択してください：");
		System.out.print(">");
	}
}
