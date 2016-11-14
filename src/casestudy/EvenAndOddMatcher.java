package casestudy;

import java.util.ArrayList;
import java.util.Collections;
/**
 * ADSBからのデータを解析
 */
public class EvenAndOddMatcher {

	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();
	static StringBuilder sb = new StringBuilder();

	public static String analyzeData(String rawData){
		rawData = HexToBinary.hexToBinary(rawData);

		if(judgedADS_B_Data(rawData)){
			int typeCode = ADS_B_Analyzer.tc_analys(rawData);
			printRawData_TypeCode_modeSAddress(rawData, typeCode);

			if(1 <= typeCode && typeCode <= 4){
				printCallSign(rawData);
			}else if(typeCode == 19){
				AnalyticalMethod.velocity(rawData);
			}else if(9 <= typeCode && typeCode <= 18){
				print_Attitude_Nicnum(rawData, typeCode);

				pairDataMatch(rawData);

			}
		}
		System.out.print(sb.toString());
		sb = new StringBuilder();
		return null;
	}

	private static void pairDataMatch(String rawData) {
		String dataOdd;
		ArrayList<Data>dataList1 = evenDataList;
		ArrayList<Data>dataList2 = oddDataList;

		if(judgeEven(rawData)){
			dataList1 = oddDataList;
			dataList2 = evenDataList;
		}
		//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
		for(Data pairData : dataList1){
			if(pairData.timeModeSEquals(rawData)){
					dataOdd = pairData.getData();
					//dataOよりdataの方が新しいデータなので、タイムスタンプを1,0とする
					sb.append(AnalyticalMethod.calc_Position(rawData, dataOdd, 1, 0));
					sb.append("\n");
					break;
			}
		}
		//ArrayListへ追加
	    dataList2.add(new Data(rawData));
		//タイムスタンプの新しい順にソート
		Collections.sort(dataList2, new DataListComparator());

	}

	private static boolean judgeOdd(String rawData) {
		return Integer.parseInt(rawData.substring(109, 109+1), 2) == 1;
	}

	private static boolean judgeEven(String rawData) {
		return !(judgeOdd(rawData));
	}

	private static void print_Attitude_Nicnum(String rawData, int typeCode) {
		sb.append("Altitude = ");
		sb.append(AnalyticalMethod.alt_calc(rawData));
		sb.append("ft");
		sb.append('\n');

		sb.append("Nicnum = ");
		sb.append(AnalyticalMethod.nic_analyz(rawData, typeCode));
		sb.append('\n');
	}

	private static void printCallSign(String rawData) {
		sb.append("Callsign = ");
		sb.append(AnalyticalMethod.callSign(rawData));
		sb.append('\n');
	}

	private static void printRawData_TypeCode_modeSAddress(String rawData,int typeCode) {
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
		return createDownLinkFormatNo(rawData) == 17;
	}

	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}
}