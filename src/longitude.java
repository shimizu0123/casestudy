
public class longitude {
	String dataO = "000100000000001000000001000000001011111110011101011001011000110101000000011000100001110101011000110000111000011001000011010111001100010000010010011010010010101011010110";
	String dataE = "000100000000001000000001000000001011111110011101011001011000110101000000011000100001110101011000110000111000001011010110100100001100100010101100001010000110001110100111";

	double pi =Math.PI;
	int NZ = 15;


	int T_even = 1;
	int T_odd = 0;
	double ni;


	double Lat_CPR_E = (double)Integer.parseInt(dataE.substring(110,110+17), 2)/131072;;
	double Lat_CPR_O = (double)Integer.parseInt(dataO.substring(110,110+17), 2)/131072;
	double j= Math.floor(59.0 * Lat_CPR_E - 60.0 * Lat_CPR_O + 0.5);;
	double DLat_E = (double)360 / (4 * NZ);
	double DLat_O= (double)360 / (4 * (NZ - 1));

	double Lat_E = DLat_E * (mod(j, 60) + Lat_CPR_E);
	double Lat_O = DLat_O * (mod(j, 59) + Lat_CPR_O);;

	double Lat;

	double DLon;
	double m;
	double LonE = (double)Integer.parseInt(dataE.substring(127,127+17), 2) /131072;//
	double LonO = (double)Integer.parseInt(dataO.substring(127,127+17), 2) /131072;
	double Lon=0;
	
	
	public String binDataE() {
		return dataE;
	}

	public String binDataO() {
		return dataO;
	}

	public double mod(double x,double y) {
		return x-y*Math.floor(x/y);
	}

	public double calc_alt() {
		if(Lat_E >= 270) Lat_E -= 360;
		if(Lat_O >= 270) Lat_O -= 360;

		if(T_even>T_odd)Lat=Lat_E;
		else Lat=Lat_O;

		return Lat;
	}

	public double calc_longi() {

		if(T_even > T_odd){
			ni = Math.max(NL(Lat_E), 1.0);
			DLon = 360/ni;

			m = Math.floor(LonE*(NL(Lat_E)-1)-LonO*NL(Lat_E)+0.5);

			Lon = DLon*(mod(m,ni)+LonE);
		}

		if(T_odd > T_even){
			ni = Math.max(NL(Lat_O)-1, 1.0);
			DLon = 360/ni;

			m = Math.floor(LonE*(NL(Lat_O)-1)-LonO*NL(Lat_O)+0.5);

			Lon = DLon*(mod(m,ni)+LonO);
		}

		if(Lon >= 180.0){
			Lon = Lon - 360;
		}

		return Lon;
	}

	private double NL(double lat) {

		return Math.floor(2*pi/Math.acos(1-(1-Math.cos(pi/(2*NZ)))/Math.pow(Math.cos(pi/180*lat),2.0)));
	}
}
