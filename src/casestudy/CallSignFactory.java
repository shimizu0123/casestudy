package casestudy;

public class CallSignFactory {

	private static final String MOJI_CODE = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ#####_###############0123456789######";
	/**
	 * dataからコールサイン解析
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return コールサイン
	 */
	public static String calcCallSign(String data){

		StringBuilder callSignBuilder = new StringBuilder();

		for(int i = 96; i < 143; i += 6){
			callSignBuilder.append(MOJI_CODE.charAt(Integer.parseInt(data.substring(i,i+6), 2)));
		}

		return callSignBuilder.toString();
	}
}
