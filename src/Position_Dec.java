import static java.lang.Math.*;

import casestudy.PlanePosition;

public class Position_Dec {

	static int NZ = 15;
	static int T_even = 1;
	static int T_odd = 0;

	public static PlanePosition calc_Position( String dataE, String dataO){

		double Lat_CPR_E = bin_to_dec_Lat_CPR(dataE);
		double Lat_CPR_O = bin_to_dec_Lat_CPR(dataO);

		double j= floor(59.0 * Lat_CPR_E - 60.0 * Lat_CPR_O + 0.5);

		double DLat_E = (double)360 / (4 * NZ);
		double DLat_O= (double)360 / (4 * (NZ - 1));

		double Lat_E = DLat_E * (mod(j, 60) + Lat_CPR_E);
		double Lat_O = DLat_O * (mod(j, 59) + Lat_CPR_O);

		double LonE = bin_to_dec_Lon_CPR(dataE);
		double LonO = bin_to_dec_Lon_CPR(dataO);

		if(Lat_E >= 270) Lat_E -= 360;
		if(Lat_O >= 270) Lat_O -= 360;

		double Lat;
		if(T_even>T_odd) Lat = Lat_E;
		else Lat = Lat_O;

		double Lon;
		if(T_even > T_odd){
			double ni = max(NL(Lat_E), 1.0);

			double DLon = 360/ni;
			double m = floor(LonE*(NL(Lat_E)-1)-LonO*NL(Lat_E)+0.5);
			Lon = DLon*(mod(m,ni)+LonE);
		}else{
			double ni = max(NL(Lat_O)-1, 1.0);

			double DLon = 360/ni;
			double m = floor(LonE*(NL(Lat_O)-1)-LonO*NL(Lat_O)+0.5);
			Lon = DLon*(mod(m,ni)+LonO);
		}
		if(Lon >= 180.0){
			Lon = Lon - 360;
		}

		return  new PlanePosition(Lon, Lat);

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
