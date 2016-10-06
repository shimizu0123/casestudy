
public class longitude {
	//String dataO = "1000110101000000011000100001110101011000110000111000011001000011010111001100010000010010011010010010101011010110";
	String dataO ="";
	//String dataE = "1000110101000000011000100001110101011000110000111000001011010110100100001100100010101100001010000110001110100111";
	String dataE ="0001000000000010000000010000000000000100000100000001000010001101100011001000011001110101010101100011100101011111001001100100100010100000010000011011111000000000000000000000000000010000000000110100100101101100";
	double pi =Math.PI;
	int NZ = 15;

	public String binDataE() {
		return dataE;
	}

	public String binDataO() {
		return dataO;
	}

	public double mod(double x,double y) {
		return x-y*Math.floor(x/y);
	}

	public double calc_longi() {
		int T_odd;
		int T_even;
		double ni;


		double Lat_CPR_E;
		double Lat_CPR_O;
		double Lat_E;
		double Lat_O;
		double DLat_E;
		double DLat_O;
		double DLon;
		double m;
		double LonE;
		double LonO;
		double Lon=0;



		Lat_CPR_E = (double)Integer.parseInt(dataE.substring(54,54+17), 2)/131072;//109
		Lat_CPR_O = (double)Integer.parseInt(dataO.substring(54,54+17), 2)/131072;

		double j = Math.floor(59.0 * Lat_CPR_E - 60.0 * Lat_CPR_O + 0.5);

		DLat_E = (double)360 / (4 * NZ);
		DLat_O = (double)360 / (4 * (NZ - 1));

		Lat_E = DLat_E * (mod(j, 60) + Lat_CPR_E);
		Lat_O = DLat_O * (mod(j, 59) + Lat_CPR_O);

		T_even = 1;
		T_odd = 0;

		LonE = (double)Integer.parseInt(dataE.substring(71,71+17), 2) /131072;//
		LonO = (double)Integer.parseInt(dataO.substring(71,71+17), 2) /131072;

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
