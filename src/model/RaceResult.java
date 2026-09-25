package model;

public class RaceResult {

	private String date;
	private String venue;
	private int raceNo;

	private int first;
	private int second;
	private int third;

	private int winPayout;

	public RaceResult(
			String date,
			String venue,
			int raceNo,
			int first,
			int second,
			int third,
			int winPayout) {

		this.date = date;
		this.venue = venue;
		this.raceNo = raceNo;
		this.first = first;
		this.second = second;
		this.third = third;
		this.winPayout = winPayout;
	}

	public String getDate() {
		return date;
	}

	public String getVenue() {
		return venue;
	}

	public int getRaceNo() {
		return raceNo;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public int getThird() {
		return third;
	}

	public int getWinPayout() {
		return winPayout;
	}
}