package pers.pre.clean;

import java.util.ArrayList;
import java.util.List;

import pers.instance.Wind;
import pres.dao.Winddao;

public class WindClean {
	
	/**
	 * Clean the wind speed
	 * @param region
	 * @return ws: the list that has been clustered
	 */
	public static List<Integer> clean_speed(int region) {
		
		List<Wind> windList = Winddao.query(region);
		List<Wind> ud = new ArrayList<>();
		for(Wind wind:windList) {
			 if(wind.getWindspeed()==32766) {
				ud.add(wind);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Wind> w = Winddao.query_unknown(ud.get(i),region);
			int sum = 0;
			int count = 0;
			
			for(Wind wx:w) {
				if(wx.getWindspeed()==32766)
					continue;
				sum += wx.getWindspeed();
				count++;
			}
			
			ud.get(i).setWindspeed(sum/count);
		}
		List<Integer> ws = new ArrayList<>();
		
		for(int i = 0;i<windList.size();i++) {
			ws.add(windList.get(i).getWindspeed());
		}
		
		return ws;
	}
	
	/**
	 * Clean the wind direction
	 * @param region
	 * @return wd: list that has been clustered
	 */
	public static List<Integer> clean_direction(int region) {
		List<Wind> windList = Winddao.query(region);
		List<Wind> ud = new ArrayList<>();
		for(Wind wind:windList) {
			 if(wind.getWinddirection()==32766) {
				ud.add(wind);
			}
		}
		
		for(int i=0;i<ud.size();i++) {

			List<Wind> w = Winddao.query_unknown(ud.get(i),region);
			int sum = 0;
			int count = 0;
			
			for(Wind wx:w) {
				if(wx.getWinddirection()==32766)
					continue;
				sum += wx.getWinddirection();
				count++;
			}
			
			ud.get(i).setWinddirection(sum/count);
		}
		
		List<Integer> ws = new ArrayList<>();
		for(int i = 0;i<windList.size();i++) {
			ws.add(windList.get(i).getWinddirection());
		}
		
		return ws;
		
	}
	
}
