package pers.instance;

public class Temperature {
	int region;
	int year;
	int month;
	int day;
	int ave_temp;
	int high_temp;
	int low_temp;
	
	

	public Temperature(int region, int year, int month, int day, int ave_temp, int high_temp, int low_temp) {
		super();
		this.region = region;
		this.year = year;
		this.month = month;
		this.day = day;
		this.ave_temp = ave_temp;
		this.high_temp = high_temp;
		this.low_temp = low_temp;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getAve_temp() {
		return ave_temp;
	}

	public void setAve_temp(int ave_temp) {
		this.ave_temp = ave_temp;
	}

	public int getHigh_temp() {
		return high_temp;
	}

	public void setHigh_temp(int high_temp) {
		this.high_temp = high_temp;
	}

	public int getLow_temp() {
		return low_temp;
	}

	public void setLow_temp(int low_temp) {
		this.low_temp = low_temp;
	}
}
