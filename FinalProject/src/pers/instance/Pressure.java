package pers.instance;

public class Pressure {
	int region;
	int year;
	int month;
	int day;
	int ave_pressure;
	int high_pressure;
	int low_pressure;
	
	
	
	public Pressure(int region, int year, int month, int day, int ave_pressure, int high_pressure, int low_pressure) {
		super();
		this.region = region;
		this.year = year;
		this.month = month;
		this.day = day;
		this.ave_pressure = ave_pressure;
		this.high_pressure = high_pressure;
		this.low_pressure = low_pressure;
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
	public int getAve_pressure() {
		return ave_pressure;
	}
	public void setAve_pressure(int ave_pressure) {
		this.ave_pressure = ave_pressure;
	}
	public int getHigh_pressure() {
		return high_pressure;
	}
	public void setHigh_pressure(int high_pressure) {
		this.high_pressure = high_pressure;
	}
	public int getLow_pressure() {
		return low_pressure;
	}
	public void setLow_pressure(int low_pressure) {
		this.low_pressure = low_pressure;
	}
	
}
