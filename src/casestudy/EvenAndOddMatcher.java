package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.CallSignFactory.*;
import static casestudy.PlanePositionFactory.*;
import static casestudy.TypeCode.*;

import java.util.ArrayList;
/**
 * ADSBからのデータを解析
 */
public class EvenAndOddMatcher {

	static final long DELETE_TIME = 1000 * 10;//削除のしきい値(ms)
	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	//受信した受信した生データ（16進数表記かつスペースで区切られている）を解析し、各DB-Itemを作る
	public static String analyzeData(String hexRawData){

		if(hexRawDataCheck(hexRawData)){

			String binaryRawData = HexToBinary.hexToBinary(hexRawData);
			PlanePosition planePosition = null;

			//受信データのパリティチェック
			if(parityCheck(binaryRawData)){

				//ADS-Bデータか判別
				if(judgedADS_B_Data(binaryRawData)){

					if(			createTypeCode(binaryRawData) == CALL_SIGN){
						DB_Item_Generator.dB_Item_CallSign_Generate(modeS_Analyze(binaryRawData), calcCallSign(binaryRawData));
					}else if(	createTypeCode(binaryRawData) == VELOCITY){
						DB_Item_Generator.dB_Item_Velocity_Generate(modeS_Analyze(binaryRawData), VelocityFactory.calc_velocity(binaryRawData));
					}else if(	createTypeCode(binaryRawData) == PLANE_POSITION){
						planePosition = rawDataToPlanePosition(binaryRawData);
						if(!(planePosition == null)){
							DB_Item_Generator.dB_Item_PlanePosition_Generate(modeS_Analyze(binaryRawData), planePosition);
						}
					}
				}
			}
		}
		return null;
	}

	//受信した生データ（16進数表記かつスペースで区切られている）がADS-Bデータのときtrue、それ以外はfalseを返す
	private static boolean hexRawDataCheck(String hexRawData){
		if(hexRawData.length() == 75){
			return hexRawData.substring(0,0+8).equals("10 02 01") && hexRawData.substring(63,63+5).equals("10 03");
		}
		return  false;
	}

	//バイナリ形式の受信データのDF(ダウンリンクフォーマット)をint型で返す
	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}

	//DF(ダウンリンクフォーマット)が17のときtrue、それ以外はfalseを返す
	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

}