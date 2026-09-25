package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.BetStyle;
import model.BetType;
import model.RaceResult;
import model.SimulationSummary;
import model.Strategy;
import service.SimulationService;
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

		while (true) {

			showMainMenu();

			int menuChoice = scanner.nextInt();

			if (menuChoice == 1) {

				while (true) {
					showStrategyMenu();

					int strategyMenuChoice = scanner.nextInt();

					if (strategyMenuChoice == 1) {

						System.out.println("===== 戦略一覧 =====");
						System.out.println();
						for (Strategy strategy : strategyService01.getAllStrategies()) {
							System.out.println("ID:" + strategy.getStrategyId());
							System.out.println("戦略名:" + strategy.getStrategyName());
							System.out.println("券種:" + strategy.getBetType());
							System.out.println("買い方:" + strategy.getBetStyle());
							System.out.println("賭け金:" + strategy.getStake());
							System.out.println();
						}

						System.out.println("----------------------------");
						System.out.println();
						System.out.println("詳細を表示する戦略IDを入力してください。");
						System.out.println("0. 戦略管理メニューへ戻る");
						System.out.print("> ");

						int strategyChoice = scanner.nextInt();

						if (strategyChoice == 0) {
							returnStrategyMenu();
							continue;
						}

						Strategy foundstrategy = strategyService01.findById(strategyChoice);

						if (foundstrategy != null) {

							handleStrategyDetail(
									foundstrategy,
									scanner,
									strategyService01);

						} else {

							System.out.println("戦略が見つかりません。");
							returnStrategyMenu();
							continue;
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

							handleStrategyDetail(foundStrategy,
									scanner,
									strategyService01);

						} else {
							System.out.println("戦略が見つかりません。");
							System.out.println();

							returnStrategyMenu();
							continue;
						}

					} else if (strategyMenuChoice == 3) {

						createStrategy(
								scanner,
								strategyService01);

					} else if (strategyMenuChoice == 0) {

						returnMainMenu();
						break;

					} else {

						System.out.println("0から3の整数を入力してください。");
					}
				}

			} else if (menuChoice == 2) {

				System.out.println("===== シミュレーション =====");
				System.out.println();

				System.out.println("使用する戦略IDを入力してください。");
				System.out.print("> ");

				int strategyId = scanner.nextInt();

				Strategy selectedStrategy = strategyService01.findById(strategyId);

				if (selectedStrategy == null) {

					System.out.println("戦略が見つかりません。");
					System.out.println();
					continue;
				}

				// 仮レースデータ
				List<RaceResult> raceResults = new ArrayList<>();

				raceResults.add(
						new RaceResult(
								"2026-09-25",
								"平和島",
								1,
								1,
								3,
								2,
								180));

				raceResults.add(
						new RaceResult(
								"2026-09-25",
								"平和島",
								2,
								2,
								1,
								4,
								320));

				raceResults.add(
						new RaceResult(
								"2026-09-25",
								"平和島",
								3,
								1,
								4,
								3,
								150));

				raceResults.add(
						new RaceResult(
								"2026-09-25",
								"平和島",
								4,
								3,
								5,
								1,
								470));

				raceResults.add(
						new RaceResult(
								"2026-09-25",
								"平和島",
								5,
								1,
								2,
								6,
								130));

				SimulationService simulationService = new SimulationService();

				SimulationSummary summary = simulationService.simulateWinAll(
						selectedStrategy,
						raceResults);

				if (summary == null) {

					System.out.println(
							"現在は単勝戦略のみシミュレーションできます。");

					System.out.println();
					continue;
				}

				System.out.println();
				System.out.println(
						"===== シミュレーション結果 =====");

				System.out.println();

				System.out.println(
						"対象レース数: "
								+ summary.getRaceCount());

				System.out.println(
						"的中数: "
								+ summary.getHitCount());

				System.out.println(
						"的中率: "
								+ String.format(
										"%.1f",
										summary.getHitRate())
								+ "%");

				System.out.println(
						"総投資額: "
								+ summary.getTotalBetAmount()
								+ "円");

				System.out.println(
						"総払戻額: "
								+ summary.getTotalPayout()
								+ "円");

				System.out.println(
						"総損益: "
								+ summary.getTotalProfit()
								+ "円");

				System.out.println(
						"回収率: "
								+ String.format(
										"%.1f",
										summary.getReturnRate())
								+ "%");

				System.out.println();

			} else if (menuChoice == 3) {

			} else if (menuChoice == 0) {

				System.out.println("終了します。");
				break;

			} else {
				System.out.println("0から3の整数を入力してください。");
			}
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
		System.out.print("> ");

	}

	public static void showStrategyMenu() {
		System.out.println("===== 戦略管理 =====");
		System.out.println();
		System.out.println("1. 戦略一覧");
		System.out.println("2. 戦略ID検索");
		System.out.println("3. 新規戦略登録");
		System.out.println("0. メニューへ戻る");
		System.out.println();
		System.out.println("選択してください：");
		System.out.print("> ");
	}

	public static void showStrategyDetail(Strategy strategy) {
		System.out.println("===== 戦略詳細 =====");
		System.out.println();
		System.out.println("ID          ：" + strategy.getStrategyId());
		System.out.println("戦略名      ：" + strategy.getStrategyName());
		System.out.println("券種        ：" + strategy.getBetType());
		System.out.println("買い方      ：" + strategy.getBetStyle());
		System.out.println("1着買い目      ：" + strategy.getFirstChoices());
		if (!strategy.getSecondChoices().isEmpty()) {
			System.out.println("2着買い目      ：" + strategy.getSecondChoices());
		}
		if (!strategy.getThirdChoices().isEmpty()) {
			System.out.println("3着買い目      ：" + strategy.getThirdChoices());
		}
		System.out.println("1点賭け金   ：" + strategy.getStake() + "円");
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
		System.out.println("1. 編集");
		System.out.println("2. 削除");
		System.out.println("0. 戦略管理メニューへ戻る");
		System.out.println();
		System.out.println("選択してください：");
		System.out.print("> ");
	}

	public static void returnMainMenu() {
		System.out.println("メインメニューへ戻ります");
		System.out.println();
	}

	public static void returnStrategyMenu() {
		System.out.println("戦略管理メニューへ戻ります");
		System.out.println();
	}

	public static void handleStrategyDetail(
			Strategy strategy,
			Scanner scanner,
			StrategyService strategyService01) {
		while (true) {
			showStrategyDetail(strategy);

			int detailChoice = scanner.nextInt();

			// 編集
			if (detailChoice == 1) {

				System.out.println("===== 戦略編集 =====");
				System.out.println();
				System.out.println("新しい戦略名を入力してください。");
				System.out.print("> ");
				scanner.nextLine();

				String newName = scanner.nextLine();

				System.out.println();
				System.out.println("新しい1点賭け金を入力してください。");
				System.out.print("> ");

				int newStake = scanner.nextInt();

				System.out.println();
				if (strategyService01.updateStrategy(strategy.getStrategyId(), newName,
						newStake)) {
					System.out.println("戦略を更新しました。");
					System.out.println("戦略詳細画面に戻ります。");
					System.out.println();
					continue;

				} else {
					System.out.println("戦略の更新に失敗しました。");
					returnStrategyMenu();
					break;
				}

				// 削除
			} else if (detailChoice == 2) {

				System.out.println("この戦略を削除しますか？");
				System.out.println();
				System.out.println("1. 削除する");
				System.out.println("0. キャンセル");
				System.out.println();
				System.out.println("選択してください：");
				System.out.print("> ");

				int deleteChoice = scanner.nextInt();

				if (deleteChoice == 1) {

					System.out.println();

					if (strategyService01.deleteStrategy(strategy.getStrategyId())) {

						System.out.println("戦略を削除しました。");

					} else {

						System.out.println("削除対象がありません。");
					}
					return;

				} else if (deleteChoice == 0) {

					System.out.println();
					System.out.println("削除をキャンセルしました。");
					System.out.println("戦略詳細画面に戻ります。");
					System.out.println();
					continue;

				} else {
					System.out.println("0または1を入力してください。");
					System.out.println();
				}

				// メニューへ戻る
			} else if (detailChoice == 0) {

				returnStrategyMenu();
				System.out.println();

				return;

			} else {

				System.out.println("0から2の整数を入力してください。");
				System.out.println();
			}
		}

	}

	public static void createStrategy(
			Scanner scanner,
			StrategyService strategyService01) {

		System.out.println("===== 新規戦略登録 =====");
		System.out.println();

		int newId = strategyService01.getNextId();

		scanner.nextLine();

		// 戦略名
		System.out.println("戦略名を入力してください。");
		System.out.print("> ");

		String strategyName = scanner.nextLine();

		System.out.println();

		// 券種
		System.out.println("券種を選択してください。");
		System.out.println();
		System.out.println("1. 単勝");
		System.out.println("2. 複勝");
		System.out.println("3. 2連単");
		System.out.println("4. 3連単");
		System.out.println("5. 2連複");
		System.out.println("6. 3連複");
		System.out.println();
		System.out.print("> ");

		int betTypeChoice = scanner.nextInt();

		BetType betType;

		switch (betTypeChoice) {

		case 1:
			betType = BetType.WIN;
			break;

		case 2:
			betType = BetType.PLACE;
			break;

		case 3:
			betType = BetType.EXACTA;
			break;

		case 4:
			betType = BetType.TRIFECTA;
			break;

		case 5:
			betType = BetType.QUINELLA;
			break;

		case 6:
			betType = BetType.TRIO;
			break;

		default:
			System.out.println("1から6を入力してください。");
			return;
		}

		System.out.println();

		// 買い方
		System.out.println("買い方を選択してください。");
		System.out.println();
		System.out.println("1. 通常");
		System.out.println("2. フォーメーション");
		System.out.println("3. BOX");
		System.out.println();
		System.out.print("> ");

		int betStyleChoice = scanner.nextInt();

		BetStyle betStyle;

		if (betStyleChoice == 1) {

			betStyle = BetStyle.NORMAL;

		} else if (betStyleChoice == 2) {

			System.out.println();
			System.out.println("フォーメーション登録は後に実装します。");
			return;

		} else if (betStyleChoice == 3) {

			System.out.println();
			System.out.println("BOX登録は後に実装します。");
			return;

		} else {

			System.out.println("1から3を入力してください。");
			return;
		}

		// 買い目格納用List
		List<Integer> firstChoices = new ArrayList<>();
		List<Integer> secondChoices = new ArrayList<>();
		List<Integer> thirdChoices = new ArrayList<>();

		System.out.println();

		// 券種によって買い目入力を変更
		if (betType == BetType.WIN || betType == BetType.PLACE) {

			System.out.println("艇番を入力してください。");
			System.out.print("> ");

			int boat = scanner.nextInt();

			firstChoices.add(boat);

		} else if (betType == BetType.EXACTA) {

			System.out.println("1着艇を入力してください。");
			System.out.print("> ");

			int first = scanner.nextInt();

			System.out.println("2着艇を入力してください。");
			System.out.print("> ");

			int second = scanner.nextInt();

			firstChoices.add(first);
			secondChoices.add(second);

		} else if (betType == BetType.TRIFECTA) {

			System.out.println("1着艇を入力してください。");
			System.out.print("> ");

			int first = scanner.nextInt();

			System.out.println("2着艇を入力してください。");
			System.out.print("> ");

			int second = scanner.nextInt();

			System.out.println("3着艇を入力してください。");
			System.out.print("> ");

			int third = scanner.nextInt();

			firstChoices.add(first);
			secondChoices.add(second);
			thirdChoices.add(third);

		} else if (betType == BetType.QUINELLA) {

			System.out.println("1艇目を入力してください。");
			System.out.print("> ");

			int first = scanner.nextInt();

			System.out.println("2艇目を入力してください。");
			System.out.print("> ");

			int second = scanner.nextInt();

			firstChoices.add(first);
			secondChoices.add(second);

		} else if (betType == BetType.TRIO) {

			System.out.println("1艇目を入力してください。");
			System.out.print("> ");

			int first = scanner.nextInt();

			System.out.println("2艇目を入力してください。");
			System.out.print("> ");

			int second = scanner.nextInt();

			System.out.println("3艇目を入力してください。");
			System.out.print("> ");

			int third = scanner.nextInt();

			firstChoices.add(first);
			secondChoices.add(second);
			thirdChoices.add(third);
		}

		System.out.println();

		// 賭け金
		System.out.println("1点あたりの賭け金を入力してください。");
		System.out.print("> ");

		int stake = scanner.nextInt();

		// Strategy生成
		Strategy newStrategy = new Strategy(
				newId,
				strategyName,
				betType,
				betStyle,
				firstChoices,
				secondChoices,
				thirdChoices,
				stake);

		// 登録
		strategyService01.addStrategy(newStrategy);

		System.out.println();
		System.out.println("戦略を登録しました。");
		System.out.println();
		System.out.println("ID: " + newStrategy.getStrategyId());
		System.out.println("戦略名: " + newStrategy.getStrategyName());
		System.out.println();

	}
}
