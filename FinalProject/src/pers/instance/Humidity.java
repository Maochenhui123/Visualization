package pers.instance;

public class Humidity {
	int region;
	int year;
	int month;
	int day;
	int humidity;
	
	
	public Humidity(int region, int year, int month, int day, int humidity) {
		super();
		this.region = region;
		this.year = year;
		this.month = month;
		this.day = day;
		this.humidity = humidity;
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
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	
}
