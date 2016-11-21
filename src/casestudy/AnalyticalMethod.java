package casestudy;

import static java.lang.Math.*;

/**
 * 解析手法クラス
 */
public class AnalyticalMethod {


	/**
	 * 地理緯度の区切り数
	 */
	private static final int NUMUBER_OF_LON_ZONE = 15;
	/**
	 * Even(偶数)における南北方向における1緯度帯の大きさ
	 */
	private static final double D_LAT_E = (double)360 / (4 * NUMUBER_OF_LON_ZONE);
	/**
	 * Odd(奇数)における南北方向における1緯度帯の大きさ
	 */
	private static final double D_LAT_O= (double)360 / (4 * (NUMUBER_OF_LON_ZONE - 1));

	/**
	 * モードSアドレスが一致したEven(偶数)、Odd(奇数)データから緯度経度高度を保存したPlanePositionを返す
	 * @param dataE(偶数) SBS-3受信したevenデータ(バイナリ形式)
	 * @param dataO(奇数) SBS-3受信したoddデータ(バイナリ形式)
	 * @param evenNewThanOdd EvenOddよりタイムスタンプが新しい場合、true、それ以外はfalse
	 * @return PlanePosition 緯度経度高度を格納したオブジェクト
	 */
	public static PlanePosition calc_Position(String dataE, String dataO, boolean evenNewThanOdd){

		double lat_E = D_LAT_E * (mod(latIndexJ(dataE, dataO), 60) + binToDecLatCPR(dataE));
		double lat_O = D_LAT_O * (mod(latIndexJ(dataE, dataO), 59) + binToDecLatCPR(dataO));

		double lonE = binToDecLonCPR(dataE);
		double lonO = binToDecLonCPR(dataO);

		if(lat_E >= 270) lat_E -= 360;
		if(lat_O >= 270) lat_O -= 360;

		double lat;
		if(evenNewThanOdd) lat = lat_E;
		else lat = lat_O;

		double lon;
		int alt;

		if(evenNewThanOdd){
			double ni = max(numberOfLatZone(lat_E), 1.0);
			double dLon = 360/ni;
			double m = floor(lonE*(numberOfLatZone(lat_E)-1)-lonO*numberOfLatZone(lat_E)+0.5);
			lon = dLon*(mod(m,ni)+lonE);
			alt = calc_alt(dataE);

		}else{
			double ni = max(numberOfLatZone(lat_O)-1, 1.0);

			double dLon = 360/ni;
			double m = floor(lonE*(numberOfLatZone(lat_O)-1)-lonO*numberOfLatZone(lat_O)+0.5);
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
	 * 緯度ゾーン番号jの算出
	 * 参考:ADS-Bフォーマットp5
	 * 	j = floor(59 × Lat_cprE - 60 Lat_cprO + 1/2)
	 * @param dataE
	 * @param dataO
	 * @return 緯度ゾーン番号を返す
	 */
	public static double latIndexJ(String dataE, String dataO) {
		return floor(59.0 * binToDecLatCPR(dataE) - 60.0 * binToDecLatCPR(dataO) + 0.5);
	}
	/**
	 * 緯度における経度ゾーン数
	 * 参考:ADS-Bフォーマットp4
	 *	極地に近い緯度では、東西方向に分割したゾーン数は少なく
	 *		Lat > +87	or Lat < -87	⇒	NL=1
	 *	赤道に近い緯度では、東西方向に分割したゾーン数は少なく
	 *		Lat = 0						⇒	NL=59
	 * @param lat 緯度
	 * @return NumberLat 緯度における経度ゾーンの数
	 */
	public static double numberOfLatZone(double lat) {
		return floor(2*PI/acos(1-(1-cos(PI/(2*NUMUBER_OF_LON_ZONE)))/pow(cos(PI/180*lat),2.0)));
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


	private static double binToDecLonCPR(String data) {
		return (double)Integer.parseInt(data.substring(127,127+17), 2) /131072;
	}

	private static double binToDecLatCPR(String data) {
		return (double)Integer.parseInt(data.substring(110,110+17), 2)/131072;
	}


	static double mod(double x,double y) {
		return x-y*floor(x/y);
	}

}
