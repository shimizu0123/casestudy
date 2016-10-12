
public class Position_Dec {



	static double pi =Math.PI;
	static int NZ = 15;

	static int T_even = 1;
	static int T_odd = 0;

	public static PlanePosition calc_Position(String dataO, String dataE){



		double Lat_CPR_E = (double)Integer.parseInt(dataE.substring(110,110+17), 2)/131072;
		double Lat_CPR_O = (double)Integer.parseInt(dataO.substring(110,110+17), 2)/131072;
		double j= Math.floor(59.0 * Lat_CPR_E - 60.0 * Lat_CPR_O + 0.5);;
		double DLat_E = (double)360 / (4 * NZ);
		double DLat_O= (double)360 / (4 * (NZ - 1));

		double Lat_E = DLat_E * (mod(j, 60) + Lat_CPR_E);
		double Lat_O = DLat_O * (mod(j, 59) + Lat_CPR_O);;

		double Lat;


		double DLon;
		double m;
		double LonE = (double)Integer.parseInt(dataE.substring(127,127+17), 2) /131072;
		double LonO = (double)Integer.parseInt(dataO.substring(127,127+17), 2) /131072;
		double Lon=0;

		if(Lat_E >= 270) Lat_E -= 360;
		if(Lat_O >= 270) Lat_O -= 360;

		if(T_even>T_odd)Lat=Lat_E;
		else Lat=Lat_O;


		if(T_even > T_odd){
			double ni = Math.max(NL(Lat_E), 1.0);
			DLon = 360/ni;

			m = Math.floor(LonE*(NL(Lat_E)-1)-LonO*NL(Lat_E)+0.5);

			Lon = DLon*(mod(m,ni)+LonE);
		}

		if(T_odd > T_even){
			double ni = Math.max(NL(Lat_O)-1, 1.0);
			DLon = 360/ni;

			m = Math.floor(LonE*(NL(Lat_O)-1)-LonO*NL(Lat_O)+0.5);

			Lon = DLon*(mod(m,ni)+LonO);
		}

		if(Lon >= 180.0){
			Lon = Lon - 360;
		}

		PlanePosition position = new PlanePosition(Lon, Lat);

		return position;

	}


	static class PlanePosition{

		double Lon;
		double Lat;

		PlanePosition(double Lon, double Lat){
			this.Lon = Lon;
			this.Lat = Lat;
		}
	}


	static double NL(double lat) {
		return Math.floor(2*pi/Math.acos(1-(1-Math.cos(pi/(2*NZ)))/Math.pow(Math.cos(pi/180*lat),2.0)));
	}


	static double mod(double x,double y) {
		return x-y*Math.floor(x/y);
	}

}
