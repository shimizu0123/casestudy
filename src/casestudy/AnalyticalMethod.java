package casestudy;

import static java.lang.Math.*;

/**
 * 解析手法クラス
 */
public class AnalyticalMethod {

	/**
	 * dataからコールサイン解析
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return コールサイン
	 */
	public static String calc_callSign(String data){

		String mojiCode = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ#####_###############0123456789######";
		StringBuilder callSignBuilder = new StringBuilder();

		for(int i = 96; i < 143; i += 6){
			callSignBuilder.append(mojiCode.charAt(Integer.parseInt(data.substring(i,i+6), 2)));
		}

		return callSignBuilder.toString();
	}

	/**
	 * 位置解析
	 */
	static int NZ = 15;

	public static PlanePosition calc_Position(String dataE, String dataO, int t_even, int t_odd){

		double lat_CPR_E = bin_to_dec_Lat_CPR(dataE);
		double lat_CPR_O = bin_to_dec_Lat_CPR(dataO);

		int t_Even = t_even;
		int t_Odd = t_odd;

		double j= floor(59.0 * lat_CPR_E - 60.0 * lat_CPR_O + 0.5);

		double dLat_E = (double)360 / (4 * NZ);
		double dLat_O= (double)360 / (4 * (NZ - 1));

		double lat_E = dLat_E * (mod(j, 60) + lat_CPR_E);
		double lat_O = dLat_O * (mod(j, 59) + lat_CPR_O);

		double lonE = bin_to_dec_Lon_CPR(dataE);
		double lonO = bin_to_dec_Lon_CPR(dataO);

		if(lat_E >= 270) lat_E -= 360;
		if(lat_O >= 270) lat_O -= 360;

		double lat;
		if(t_Even > t_Odd) lat = lat_E;
		else lat = lat_O;

		double lon;
		int alt;

		if(t_Even > t_Odd){
			double ni = max(NL(lat_E), 1.0);
			double dLon = 360/ni;
			double m = floor(lonE*(NL(lat_E)-1)-lonO*NL(lat_E)+0.5);
			lon = dLon*(mod(m,ni)+lonE);
			alt = calc_alt(dataE);

		}else{
			double ni = max(NL(lat_O)-1, 1.0);

			double dLon = 360/ni;
			double m = floor(lonE*(NL(lat_O)-1)-lonO*NL(lat_O)+0.5);
			lon = dLon*(mod(m,ni)+lonO);
			alt = calc_alt(dataO);
		}
		if(lon >= 180.0){
			lon = lon - 360;
		}

		return  new PlanePosition(lat, lon, alt);

	}


	/*
	 * 高度解析
	 */
	public static int calc_alt(String data) {
		int n=0;

		String frontbit=data.substring(96,96+7);
		String backbit=data.substring(103,103+4);

		String altbin = frontbit + backbit;
		int unitbit = Integer.parseInt(data.substring(103,104),2);

		if(unitbit==1)n=25;
		if(unitbit==0)n=100;

		int altitude = Integer.parseInt(altbin,2)*n-1000;


		return altitude;
	}

	/**
	 * Nic(精度)解析
	 * @param data
	 * @param tcnum
	 * @return
	 */
	public static double calc_nic(String data,int tcnum) {
		double nicNum=0;

		switch(tcnum){
		case 9:
			nicNum=7.5;
			break;
		case 10:
			nicNum=25;
			break;
		case 11:
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
				nicNum=74;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				nicNum=185.2;
			}
			break;
		case 12:
			nicNum=185.2*2;
			break;
		case 13:
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
					nicNum=185.2*3;
			}else if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				nicNum=185.2*5;
			}
			break;
		case 14:
			nicNum=1852;
			break;
		case 15:
			nicNum=1852*2;
			break;
		case 16:
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
				nicNum=1852*4;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				nicNum=1852*8;
			}
			break;
		case 17:
			nicNum=1852*20;
			break;
		case 18:
			nicNum=2852*2;
		}
		return nicNum;
	}


	private static double bin_to_dec_Lon_CPR(String data) {
		return (double)Integer.parseInt(data.substring(127,127+17), 2) /131072;
	}

	private static double bin_to_dec_Lat_CPR(String data) {
		return (double)Integer.parseInt(data.substring(110,110+17), 2)/131072;
	}

	static double NL(double lat) {
		return floor(2*PI/acos(1-(1-cos(PI/(2*NZ)))/pow(cos(PI/180*lat),2.0)));
	}

	static double mod(double x,double y) {
		return x-y*floor(x/y);
	}

}
