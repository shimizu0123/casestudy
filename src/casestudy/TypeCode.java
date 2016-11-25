package casestudy;

import static casestudy.ADS_B_Analyzer.*;

/**
 * タイプコードを判別するクラス
 */
public enum TypeCode {

	CALL_SIGN,
	VELOCITY,
	PLANE_POSITION,
	OTHER;

	/**
	 * タイプコードを判別し、結果を返す
	 * @param rawData
	 * @return CALL_SIGN
	 * @return VELOCITY
	 * @return PLANE_POSITION
	 * @return OTHER
	 */
	public static TypeCode createTypeCode(String rawData){
		if(judgeCallSign(rawData)) return CALL_SIGN;
		else if(judgeVelocity(rawData)) return VELOCITY;
		else if(judgePosition(rawData)) return PLANE_POSITION;
		else return OTHER;
	}

	/**
	 * タイプコード判別(Position)
	 * @param rawData
	 * @return タイプコードが9～18のときtrue
	 */
	private static boolean judgePosition(String rawData) {
		return 9 <= tc_Analyze(rawData) && tc_Analyze(rawData) <= 18;
	}

	/**
	 * タイプコード判別(Velocity)
	 * @param rawData
	 * @return タイプコードが9～18のときtrue
	 */
	private static boolean judgeVelocity(String rawData) {
		return tc_Analyze(rawData) == 19;
	}

	/**
	 * タイプコード判別(CallSign)
	 * @param rawData
	 * @return タイプコードが9～18のときtrue
	 */
	private static boolean judgeCallSign(String rawData) {
		return 1 <= tc_Analyze(rawData) && tc_Analyze(rawData) <= 4;
	}

}
