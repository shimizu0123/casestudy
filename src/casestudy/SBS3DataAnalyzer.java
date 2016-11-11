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

		String dataEven;
		String dataOdd;

		if(judgedADS_B_Data(rawData)){
			int typeCode = ADS_B_Analyzer.tc_analys(rawData);
			printRawData_TypeCode_modeSAddress(rawData, typeCode);

			if(1 <= typeCode && typeCode <= 4){
				printCallSign(rawData);
			}else if(typeCode == 19){
				AnalyticalMethod.velocity(rawData);
			}else if(9 <= typeCode && typeCode <= 18){
				print_Attitude_Nicnum(rawData, typeCode);

				if(judgeEven(rawData)){
					//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
					for(Data oddData : oddDataList){
						if(oddData.timeModeSEquals(rawData)){
								dataOdd = oddData.getData();
								//dataOよりdataの方が新しいデータなので、タイムスタンプを1,0とする
								AnalyticalMethod.calc_Position(rawData, dataOdd, 1, 0);
								break;
						}
					}
					//ArrayListへ追加
				    Data evenData = new Data(rawData);
					evenDataList.add(evenData);
					//タイムスタンプの新しい順にソート
					Collections.sort(evenDataList, new DataListComparator());
				}

				if(judgeOdd(rawData)){
					//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
					for(Data evenData : evenDataList){
						if(evenData.timeModeSEquals(rawData)){
								dataEven = evenData.getData();
								//dataEよりdataの方が新しいデータなので、タイムスタンプを0,1とする
								AnalyticalMethod.calc_Position(dataEven, rawData, 0, 1);
								break;
						}
					}

					//ArrayListへ追加
			        Data oddData = new Data(rawData);
					oddDataList.add(oddData);
					//タイムスタンプの新しい順にソート
					Collections.sort(oddDataList, new DataListComparator());
				}
			}
		}
		System.out.print(sb.toString());
		return null;
	}

	private static boolean judgeOdd(String rawData) {
		return Integer.parseInt(rawData.substring(109, 109+1), 2) == 1;
	}

	private static boolean judgeEven(String rawData) {
		return Integer.parseInt(rawData.substring(109, 109+1), 2) == 0;
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