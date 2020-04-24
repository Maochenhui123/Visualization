package pers.pre.change;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LIST {
	
	public static List<Integer> evaporation = new ArrayList<>();
	
	public static List<Integer> precipitation = new ArrayList<>();
	
	public static List<Integer> sundur = new ArrayList<>();
	
	public static List<Integer> windspeed = new ArrayList<>();
	
	public static List<Integer> winddirection = new ArrayList<>();
	
	public static List<Integer> humidity = new ArrayList<>();
	
	public static List<Integer> pressure = new ArrayList<>();
	
	public static List<Integer> temperature = new ArrayList<>();
	
	public static List<Double> precipitation_normal = new ArrayList<>();
	
	public static List<Double> evaporation_normal = new ArrayList<>();
	
	public static List<Double> sundur_normal = new ArrayList<>();
	
	public static List<Double> windspeed_normal = new ArrayList<>();
	
	public static List<Double> winddirection_normal = new ArrayList<>();
	
	public static List<Double> humidity_normal = new ArrayList<>();
	
	public static List<Double> pressure_normal = new ArrayList<>();
	
	public static List<Double> temperature_normal = new ArrayList<>();
	
	public static List<List<Double>> Norm = new ArrayList<>();
	
	public static List<List<Integer>> Level = new ArrayList<>();
	
	public static List<Integer> integration = new ArrayList<>();
	
	public static List<Integer> index = new ArrayList<>();
	
	public static List<Double> association = new ArrayList<>();
	
	public static List<List<String>> rest = new ArrayList<>();
	
	public static List<String> date = new ArrayList<>();

	public static Map<String, Map<List<String>,Double>> all = new HashMap<>();

	public static List<String> relation = new ArrayList<>();
	
	public static void clear() {
		evaporation.clear();
		precipitation.clear();
		sundur.clear();
		windspeed.clear();
		winddirection.clear();
		humidity.clear();
		pressure.clear();
		temperature.clear();
		precipitation_normal.clear();
		evaporation_normal.clear();
		sundur_normal.clear();
		windspeed_normal.clear();
		winddirection_normal.clear();
		humidity_normal.clear();
		pressure_normal.clear();
		temperature_normal.clear();
		integration.clear();
		index.clear();
		association.clear();
		rest.clear();
		all.clear();
		date.clear();
		relation.clear();
		Norm.clear();
		Level.clear();
	}
	
	public static void match(String attribute,int index, List<String> integration) {
		
		switch(attribute) {
		case "Precipitation":
			integration.add("P"+LIST.precipitation.get(index));
			break;
		case "Evaporation":
			integration.add("E"+LIST.evaporation.get(index));
			break;
		case "Humidity":
			integration.add("H"+LIST.humidity.get(index));
			break;
		case "Pressure":
			integration.add("PR"+LIST.pressure.get(index));
			break;	
		case "Sunlightdur":
			integration.add("S"+LIST.sundur.get(index));
			break;
		case "Temperature":
			integration.add("T"+LIST.temperature.get(index));
			break;	
		case "WindSpeed":
			integration.add("WS"+LIST.windspeed.get(index));
			break;
		case "WindDirection":
			integration.add("WD"+LIST.winddirection.get(index));
			break;
		default:
			break;
		}
	}
	
	public static double match(String attribute, int index) {
		switch(attribute) {
		case "Precipitation":
			return LIST.precipitation_normal.get(index);
		case "Evaporation":
			return LIST.evaporation_normal.get(index);
		case "Humidity":
			return LIST.humidity_normal.get(index);
		case "Pressure":
			return LIST.pressure_normal.get(index);	
		case "Sunlightdur":
			return LIST.sundur_normal.get(index);
		case "Temperature":
			return LIST.temperature_normal.get(index);
		case "WindSpeed":
			return LIST.windspeed_normal.get(index);
		case "WindDirection":
			return LIST.winddirection_normal.get(index);
		default:
			return -1;
		}
	}
	
	public static List<Integer> matchList(String attribute){
		switch(attribute) {
		case "Precipitation":
			return LIST.precipitation;
		case "Evaporation":
			return LIST.evaporation;
		case "Humidity":
			return LIST.humidity;
		case "Pressure":
			return LIST.humidity;	
		case "Sunlightdur":
			return LIST.sundur;
		case "Temperature":
			return LIST.temperature;
		case "WindSpeed":
			return LIST.windspeed;
		case "WindDirection":
			return LIST.winddirection;
		default:
			return null;
		}
	}
	
	public static List<Double> matchNormal(String attribute){
		switch(attribute) {
		case "Precipitation":
			return LIST.precipitation_normal;
		case "Evaporation":
			return LIST.evaporation_normal;
		case "Humidity":
			return LIST.humidity_normal;
		case "Pressure":
			return LIST.humidity_normal;	
		case "Sunlightdur":
			return LIST.sundur_normal;
		case "Temperature":
			return LIST.temperature_normal;
		case "WindSpeed":
			return LIST.windspeed_normal;
		case "WindDirection":
			return LIST.winddirection_normal;
		default:
			return null;
		}
	}
	
	public static String match(String attribute) {
		switch(attribute) {
		case "Precipitation":
			return "P";
		case "Evaporation":
			return "E";
		case "Humidity":
			return "H";
		case "Pressure":
			return "PR";	
		case "Sunlightdur":
			return "S";
		case "Temperature":
			return "T";
		case "WindSpeed":
			return "WS";
		case "WindDirection":
			return "WD";
		default:
			return null;
		}
	}

}
