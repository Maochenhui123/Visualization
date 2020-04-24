package pers.instance;

public class Wind {
	int region;
	int year;
	int month;
	int day;
	int winddirection;
	int windspeed;
	
	
	public Wind(int region, int year, int month, int day, int windspeed, int winddirection) {
		super();
		this.region = region;
		this.year = year;
		this.month = month;
		this.day = day;
		this.winddirection = winddirection;
		this.windspeed = windspeed;
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
	public int getWinddirection() {
		return winddirection;
	}
	public void setWinddirection(int winddirection) {
		this.winddirection = winddirection;
	}
	public int getWindspeed() {
		return windspeed;
	}
	public void setWindspeed(int windspeed) {
		this.windspeed = windspeed;
	}
	
	
}
