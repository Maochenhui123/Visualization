package pers.instance;

public class Sunlightdur {
	int region;
	int year;
	int month;
	int day;
	int sunlightdur;
	
	
	
	public Sunlightdur(int region, int year, int month, int day, int sunlightdur) {
		super();
		this.region = region;
		this.year = year;
		this.month = month;
		this.day = day;
		this.sunlightdur = sunlightdur;
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
	public int getSunlightdur() {
		return sunlightdur;
	}
	public void setSunlightdur(int sunlightdur) {
		this.sunlightdur = sunlightdur;
	}

	@Override
	public String toString() {
		return "Sunlightdur [year=" + year + ", month=" + month + ", day=" + day + ", sunlightdur=" + sunlightdur
				+"]";
	}
	
}
