package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.RaceResult;

public class CsvLoader {

	public static List<RaceResult> loadRaceResults(String filePath)
			throws IOException {

		List<RaceResult> raceResults = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			String line;

			// 1行目のヘッダーを読み飛ばす
			reader.readLine();

			while ((line = reader.readLine()) != null) {

				String[] data = line.split(",", -1);

				RaceResult raceResult = new RaceResult(
						data[0],
						data[1],
						parseInt(data[2]),
						parseInt(data[3]),
						parseInt(data[4]),
						parseInt(data[5]),
						parseInt(data[6]),
						parseInt(data[7]),
						parseInt(data[8]),
						parseInt(data[9]),
						parseInt(data[10]),
						parseInt(data[11]),
						parseInt(data[12]),
						parseInt(data[13]),
						parseInt(data[14]),
						data[15],
						parseInt(data[16]),
						data[17],
						parseInt(data[18]),
						data[19],
						parseInt(data[20]),
						data[21],
						parseInt(data[22]));

				raceResults.add(raceResult);
			}
		}

		return raceResults;
	}

	private static int parseInt(String value) {

		if (value == null || value.isBlank()) {
			return 0;
		}

		return Integer.parseInt(value);
	}
}