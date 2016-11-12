package casestudy;

import java.util.ArrayList;
import java.util.Collections;
/**
 * ADSBからのデータを解析
 */
public class SBS3DataAnalyzer {

	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();
	static StringBuilder sb = new StringBuilder();

	public static String analyzeData(String rawData){
		rawData = HexToBinary.hexToBinary(rawData);

		if(judgedADS_B_Data(rawData)){
			int typeCode = ADS_B_Analyzer.tc_analys(rawData);
			addStringRawDataAndTypeCodeAndModeSAndAddress(rawData, typeCode);

			if(1 <= typeCode && typeCode <= 4){
				addStringCallSign(rawData);
			}else if(typeCode == 19){
				AnalyticalMethod.velocity(rawData);
			}else if(9 <= typeCode && typeCode <= 18){
				addStringAttitudeAndNicnum(rawData, typeCode);

				findSamePairData(rawData);

			}
		}
		System.out.print(sb.toString());
		return null;
	}

	private static void findSamePairData(String rawData) {
		String dataOdd;
		int timeStamp1;
		int timeStamp2;
		ArrayList<Data> dataList1;
		ArrayList<Data> dataList2;

		if(judgeEven(rawData)){
			timeStamp1 = 1;
			timeStamp2 = 0;
			dataList1 = oddDataList;
			dataList2 = evenDataList;
		}else{
			timeStamp1 = 0;
			timeStamp2 = 1;
			dataList1 = evenDataList;
			dataList2 = oddDataList;
		}
		for(Data oddData : dataList1){
			if(oddData.timeModeSEquals(rawData)){
					dataOdd = oddData.getData();
					//dataOよりdataの方が新しいデータなので、タイムスタンプを変更する
					AnalyticalMethod.calc_Position(rawData, dataOdd, timeStamp1, timeStamp2);
					break;
			}
		}
			listAddRawData(rawData,dataList2);
		}

	private static void listAddRawData(String rawData,ArrayList<Data> dataList) {
		//ArrayListへ追加
		Data data = new Data(rawData);
		dataList.add(data);
		//タイムスタンプの新しい順にソート
		Collections.sort(dataList, new DataListComparator());
	}

	private static boolean judgeOdd(String rawData) {
		return Integer.parseInt(rawData.substring(109, 109+1), 2) == 1;
	}

	private static boolean judgeEven(String rawData) {
		return !(judgeOdd(rawData));
	}

	private static void addStringAttitudeAndNicnum(String rawData, int typeCode) {
		sb.append("Altitude = ");
		sb.append(AnalyticalMethod.alt_calc(rawData));
		sb.append("ft");
		sb.append('\n');

		sb.append("Nicnum = ");
		sb.append(AnalyticalMethod.nic_analyz(rawData, typeCode));
		sb.append('\n');
	}

	private static void addStringCallSign(String rawData) {
		sb.append("Callsign = ");
		sb.append(AnalyticalMethod.callSign(rawData));
		sb.append('\n');
	}

	private static void addStringRawDataAndTypeCodeAndModeSAndAddress(String rawData,int typeCode) {
		sb.append(rawData);
		sb.append('\n');
		sb.append("TC = ");
		sb.append(typeCode);
		sb.append('\n');
		sb.append("modeS_Address = ");
		sb.append(ADS_B_Analyzer.modoS_analys(rawData));
		sb.append('\n');
	}

	private static boolean judgedADS_B_Data(String rawData) {
		return downLinkFormatNo(rawData) == 17;
	}

	private static int downLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}
}