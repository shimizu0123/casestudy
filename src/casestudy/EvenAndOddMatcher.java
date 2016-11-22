package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.CallSignFactory.*;
import static casestudy.LatLonAltAnalyzer.*;
import static casestudy.PlanePositionFactory.*;
import static casestudy.TypeCode.*;

import java.util.ArrayList;
import java.util.Collections;
/**
 * ADSBからのデータを解析
 */
public class EvenAndOddMatcher {

	static final long DELETE_TIME = 1000 * 10;//削除のしきい値(ms)
	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	public static String analyzeData(String hexRawData){

		if(hexRawDataCheck(hexRawData)){

			String binaryRawData = HexToBinary.hexToBinary(hexRawData);
			PlanePosition planePosition = null;
			if(parityCheck(binaryRawData)){
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

	/**
	 * rawDataを対象Listに追加し、整理する
	 * @param rawData SBS-3受信データ(バイナリ形式)
	 * @param addDataList 追加対象のリスト(EvenList又はOddList)
	 */
	public static void listAdd(String rawData,ArrayList<Data> addDataList){
		addDataList.add(new Data(rawData));
		Collections.sort(addDataList, new DataListComparator());
		oldListDelete(addDataList);
	}

	private static void oldListDelete(ArrayList<Data> dataList) {
		long now = System.currentTimeMillis();//現在時刻を取得
		for(int i = dataList.size() - 1; i >= 0; i--){
			if((now - dataList.get(i).getTimeStamp()) >=  DELETE_TIME) dataList.remove(i);
		}
	}

	/**
	 * 位置情報のrawDataと対になるデータを
	 * evenDataList又はOddDataListから探し、
	 * PlanePostionを返す。なければ、null値を返す
	 * @param rawData SBS-3受信データ(バイナリ形式)
	 * @param pairDataList rawDataがEvenならOddList。rawDataがOddならEvenList
	 * @return PlanePositon 航空機の緯度経度高度情報
	 */
	public static PlanePosition planePositonCreator(String rawData,ArrayList<Data> pairDataList) {

		String listData;
		PlanePosition planePosition = null;

		for(Data pairData : pairDataList){
			if(pairData.timeAndModeSEquals(rawData)){
				listData = pairData.getData();
				if(judgeEven(rawData)){
					planePosition = calc_Position(rawData, listData, true);
				}else{
					planePosition = calc_Position(listData, rawData, false);
				}

				break;
			}
		}
		return planePosition;
	}

	private static boolean judgeOdd(String rawData) {
		return Integer.parseInt(rawData.substring(109, 109+1), 2) == 1;
	}

	private static boolean judgeEven(String rawData) {
		return !(judgeOdd(rawData));
	}

	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}

	private static boolean hexRawDataCheck(String hexRawData){
		if(hexRawData.length() == 75){
			return hexRawData.substring(0,0+8).equals("10 02 01") && hexRawData.substring(63,63+5).equals("10 03");
		}
		return  false;
	}

}