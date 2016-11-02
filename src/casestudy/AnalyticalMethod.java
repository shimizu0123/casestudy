package casestudy;

import static java.lang.Math.*;

/*
 * 解析手法クラス
 */
public class AnalyticalMethod {

	/*
	 * コールサイン解析
	 */
	public static String callSign(String data){

		String code = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ#####_###############0123456789######";
		StringBuilder sb = new StringBuilder();

		for(int i = 96; i < 143; i += 6){

			//System.out.println(data.substring(i,i+6));

			//System.out.println(Integer.parseInt(data.substring(i,i+6), 2));

			sb.append(code.charAt(Integer.parseInt(data.substring(i,i+6), 2)));
		}

		String CallSign = sb.toString();

		return CallSign;
	}


	/*
	 * 速度解析
	 */
	public static Velocity velocity(String data) {

		int S_EW;//東―西軸方向のどちらに向かって進むのか
		int S_NS;//北―南軸方向のどちらに向かって進むのか
		int S_Vr;//垂直方向の動き
		double V_EW=0;//東―西軸方向の速さ
		double V_NS=0;//北―南軸方向の速さ
		double Vel;//速さ
		double deg;//方位
		double Vr;//垂直方向の速さ

		S_EW = Integer.parseInt(data.substring(101,101+1), 2);
		if(S_EW==1){
			V_EW = (-1) * (Integer.parseInt(data.substring(102,102+10), 2) - 1);
		}
		if(S_EW==0){
			V_EW = Integer.parseInt(data.substring(102,102+10), 2) - 1;
		}

		S_NS = Integer.parseInt(data.substring(112,112+1), 2);
		if(S_NS==1){
			V_NS = (-1) * (Integer.parseInt(data.substring(102,102+10), 2) - 1);
		}
		if(S_NS==0){
			V_NS = Integer.parseInt(data.substring(102,102+10), 2) - 1;
		}

		Vel = Math.sqrt(Math.pow(V_EW,2.0) + Math.pow(V_NS, 2.0));

		deg = Math.atan(V_EW/V_NS) * 360.0 / (2 * Math.PI );

		if(deg < 0){
			deg += 360;
		}

		System.out.println("Velocity = "+ Vel + "kn");
		System.out.println("deg = "+ deg + "deg");

		Vr = Integer.parseInt(data.substring(125,125+8), 2);

		S_Vr = Integer.parseInt(data.substring(124,124+1), 2);
		if(S_Vr==1){
			System.out.println("Down");
		}
		if(S_Vr==0){
			System.out.println("UP");
		}

		System.out.println(Vr + "kn");

		return new Velocity(S_Vr, Vel, deg, Vr);
	}


	/*
	 * 位置解析
	 */
	static int NZ = 15;


	public static PlanePosition calc_Position(String dataE, String dataO, int t_even, int t_odd){

		double Lat_CPR_E = bin_to_dec_Lat_CPR(dataE);
		double Lat_CPR_O = bin_to_dec_Lat_CPR(dataO);

		int t_Even = t_even;
		int t_Odd = t_odd;

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
		if(t_Even > t_Odd) Lat = Lat_E;
		else Lat = Lat_O;

		double Lon;
		if(t_Even > t_Odd){
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

		/*
		 * テスト用ここから
		 */

		System.out.println("Lon = " + Lon + ", Lat = " + Lat);

		/*
		 * テスト用ここまで
		 */


		return  new PlanePosition(Lon, Lat);

	}


	/*
	 * 高度解析
	 */
	public static int alt_calc(String data) {
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


	/*
	 * NIC解析
	 */
	public static double nic_analyz(String data,int tcnum) {
		double Nicnum=0;

		if(tcnum==9){
			Nicnum=7.5;
		}
		if(tcnum==10){
			Nicnum=25;
		}
		if(tcnum==11){
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
				Nicnum=74;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				Nicnum=185.2;
			}
		}
		if(tcnum==12){
			Nicnum=185.2*2;
		}
		if(tcnum==13){
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
					Nicnum=185.2*3;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				Nicnum=185.2*5;
			}
		}
		if(tcnum==14){
			Nicnum=1852;
		}
		if(tcnum==15){
			Nicnum=1852*2;
		}
		if(tcnum==16){
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
				Nicnum=1852*4;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				Nicnum=1852*8;
			}
		}
		if(tcnum==17){
			Nicnum=1852*20;
		}
		if(tcnum==18){
			Nicnum=2852*2;

		}
		return Nicnum;
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
