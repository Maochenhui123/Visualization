package pers.instance;

public class Evaporation {
	int region;
	int year;
	int month;
	int day;
	int evaporation;
	
		
	public Evaporation(int region, int year, int month, int day, int evaporation) {
		super();
		this.region = region;
		this.year = year;
		this.month = month;
		this.day = day;
		this.evaporation = evaporation;
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
	public int getEvaporation() {
		return evaporation;
	}
	public void setEvaporation(int evaporation) {
		this.evaporation = evaporation;
	}
	
	@Override
	public String toString() {
		return "Evaporation [year=" + year + ", month=" + month + ", day=" + day + ", evaporation=" + evaporation + "]";
	}
	
}
