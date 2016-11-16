package casestudy;

/*
 * DF17データ解析クラス
 */
public class ADS_B_Analyzer {

	/*
	 * TypeCcodeを解析
	 */
	public static int tc_Analyze(String data) {
		int tcnum;
		tcnum = Integer.parseInt(data.substring(87,87+5), 2);
		return tcnum;
	}

	/*
	 * モードSアドレスを解析
	 */
	public static String modeS_Analyze(String data) {
		 String modeS_Address_hex = Integer.toHexString(Integer.parseInt(data.substring(63,63+24), 2));
		 return modeS_Address_hex;
	}

}
