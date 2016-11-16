package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.AnalyticalMethod.*;
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
	static StringBuilder sb = new StringBuilder();

	public static String analyzeData(String hexRawData){
		String binaryRawData = HexToBinary.hexToBinary(hexRawData);
		PlanePosition planePosition = null;

		if(parityCheck(binaryRawData)){
			if(judgedADS_B_Data(binaryRawData)){
				printRawData_TypeCode_modeSAddress(binaryRawData);

				if(			createTypeCode(binaryRawData) == CALL_SIGN){
					printCallSign(binaryRawData);


				}else if(	createTypeCode(binaryRawData) == VELOCITY){
					calc_velocity(binaryRawData);


				}else if(	createTypeCode(binaryRawData) == PLANE_POSITION){

					print_Attitude_Nicnum(binaryRawData);

					planePosition = rawDataToPlanePosition(binaryRawData);
					if(!(planePosition == null)){
						sb.append(planePosition.toString());
					}
				}
			}
			System.out.print(sb.toString());
			sb = new StringBuilder();
		}
		return null;
	}

	/**
	 * rawDataを対象Listに追加し、整理する
	 * @param rawData
	 */
	public static void listAdd(String rawData,ArrayList<Data> addDataList){

		addDataList.add(new Data(rawData));
		Collections.sort(addDataList, new DataListComparator());
		oldListDelete(addDataList);

		/*
		 * テスト用　データリストサイズ表示
		 */
		if(judgeEven(rawData)){
			System.out.println("******evenDataListサイズ = " + addDataList.size() + "******");
		}else{
			System.out.println("******oddDataListサイズ = " + addDataList.size() + "******");
		}

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
	 * @param rawData
	 * @returnb PlanePositon 航空機の緯度経度高度情報
	 */
	public static PlanePosition planePositonCreator(String rawData,ArrayList<Data> pairDataList) {

		String listData;
		PlanePosition planePosition = null;


		//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
		for(Data pairData : pairDataList){
			if(pairData.timeAndModeSEquals(rawData)){
				listData = pairData.getData();
				if(judgeEven(rawData)){
					planePosition = calc_Position(rawData, listData, 1, 0);
				}else{
					planePosition = calc_Position(listData, rawData, 0, 1);
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

	private static void print_Attitude_Nicnum(String rawData) {
		sb.append("Altitude = ");
		sb.append(calc_alt(rawData));
		sb.append("ft");
		sb.append('\n');

		sb.append("Nicnum = ");
		sb.append(calc_nic(rawData,tc_Analyze(rawData)));
		sb.append('\n');
	}

	private static void printCallSign(String rawData) {
		sb.append("Callsign = ");

		sb.append(calc_callSign(rawData));
		sb.append('\n');
	}

	private static void printRawData_TypeCode_modeSAddress(String rawData) {
		sb.append(rawData);
		sb.append('\n');
		sb.append("TC = ");
		sb.append(tc_Analyze(rawData));
		sb.append('\n');
		sb.append("modeS_Address = ");
		sb.append(modeS_Analyze(rawData));
		sb.append('\n');
	}

	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}

}