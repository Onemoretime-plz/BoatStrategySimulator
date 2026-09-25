package model;

public class RaceResult {

	private String date;
	private String venue;
	private int venueCode;
	private int raceNo;

	private int first;
	private int second;
	private int third;

	private int winBoat;
	private int winPayout;

	private int place1Boat;
	private int place1Payout;
	private int place2Boat;
	private int place2Payout;
	private int place3Boat;
	private int place3Payout;

	private String exactaCombination;
	private int exactaPayout;

	private String trifectaCombination;
	private int trifectaPayout;

	private String quinellaCombination;
	private int quinellaPayout;

	private String trioCombination;
	private int trioPayout;

	public RaceResult(
			String date,
			String venue,
			int venueCode,
			int raceNo,
			int first,
			int second,
			int third,
			int winBoat,
			int winPayout,
			int place1Boat,
			int place1Payout,
			int place2Boat,
			int place2Payout,
			int place3Boat,
			int place3Payout,
			String exactaCombination,
			int exactaPayout,
			String trifectaCombination,
			int trifectaPayout,
			String quinellaCombination,
			int quinellaPayout,
			String trioCombination,
			int trioPayout) {

		this.date = date;
		this.venue = venue;
		this.venueCode = venueCode;
		this.raceNo = raceNo;
		this.first = first;
		this.second = second;
		this.third = third;
		this.winBoat = winBoat;
		this.winPayout = winPayout;
		this.place1Boat = place1Boat;
		this.place1Payout = place1Payout;
		this.place2Boat = place2Boat;
		this.place2Payout = place2Payout;
		this.place3Boat = place3Boat;
		this.place3Payout = place3Payout;
		this.exactaCombination = exactaCombination;
		this.exactaPayout = exactaPayout;
		this.trifectaCombination = trifectaCombination;
		this.trifectaPayout = trifectaPayout;
		this.quinellaCombination = quinellaCombination;
		this.quinellaPayout = quinellaPayout;
		this.trioCombination = trioCombination;
		this.trioPayout = trioPayout;
	}

	public String getDate() {
		return date;
	}

	public String getVenue() {
		return venue;
	}

	public int getVenueCode() {
		return venueCode;
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

	public int getWinBoat() {
		return winBoat;
	}

	public int getWinPayout() {
		return winPayout;
	}

	public int getPlace1Boat() {
		return place1Boat;
	}

	public int getPlace1Payout() {
		return place1Payout;
	}

	public int getPlace2Boat() {
		return place2Boat;
	}

	public int getPlace2Payout() {
		return place2Payout;
	}

	public int getPlace3Boat() {
		return place3Boat;
	}

	public int getPlace3Payout() {
		return place3Payout;
	}

	public String getExactaCombination() {
		return exactaCombination;
	}

	public int getExactaPayout() {
		return exactaPayout;
	}

	public String getTrifectaCombination() {
		return trifectaCombination;
	}

	public int getTrifectaPayout() {
		return trifectaPayout;
	}

	public String getQuinellaCombination() {
		return quinellaCombination;
	}

	public int getQuinellaPayout() {
		return quinellaPayout;
	}

	public String getTrioCombination() {
		return trioCombination;
	}

	public int getTrioPayout() {
		return trioPayout;
	}
}