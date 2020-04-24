package data;
/**
 * 
 * @author mch
 * Region class that contains all the collected region, each province is represented as 5 digits
 */
public class Region {
	public static final int Heilongjiang = 50136;
	
	public static final int Innermongolia = 50425;
	
	public static final int JiLin = 50936;
	
	public static final int Sinkiang = 51053;
	
	public static final int Gansu = 52323;
	
	public static final int Qinghai = 52707;
	
	public static final int Hebei = 53399;
	
	public static final int Shanxi = 53478;
	
	public static final int Shaanxi = 53646;
	
	public static final int Liaoning = 54236;
	
	public static final int Beijing = 54406;
	
	public static final int Tianjing = 54525;
	
	public static final int Shandong = 54715;
	
	public static final int Sichuan = 56144;
	
	public static final int Yunnan = 56533;
	
	public static final int Guizhou = 56792;
	
	public static final int HeNan =  57071;
	
	public static final int Hubei = 57251;
	
	public static final int Jiangxi = 57789;
	
	public static final int Guangxi = 57947;
	
	public static final int Anhui = 58015;
	
	public static final int Jiangsu = 58026;
	
	public static final int Shanghai = 58362;
	
	public static final int Fujian = 58725;
	
	public static final int Guangdong = 59072;
	
	public static final int Hainan = 59758;
	
	public static String getRegion(int region) {
		switch(region) {
		case 50136:
			return "Beijing";
		case 50425:
			return "Innermongolia";
		case 50936:
			return "JiLin";
		case 51053:
			return "Sinkiang";
		case 52323:
			return "Gansu";
		case 52707:
			return "Qinghai";
		case 53399:
			return "Hebei";
		case 53478:
			return "Shanxi";
		case 53646:
			return "Shannxi";
		case 54236:
			return "Liaoning";
		case 54406:
			return "Beijing";
		case 54525:
			return "Tianjing";
		case 54715:
			return "Shandong";
		case 56144:
			return "Sichuan";
		case 56533:
			return "Yunnan";
		case 56792:
			return "Guizhou";
		case 57071:
			return "HeNan";
		case 57251:
			return "Hubei";
		case 57789:
			return "Jiangxi";
		case 57947:
			return "Guangxi";
		case 58015:
			return "Anhui";
		case 58026:
			return "Jiangsu";
		case 58362:
			return "Shanghai";
		case 58725:
			return "Fujian";
		case 59072:
			return "Guangdong";
		case 59758:
			return "HaiNan";
		default:
			return "";
				
		}
	}
}
