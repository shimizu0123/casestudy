package casestudy;

import java.util.ArrayList;
import java.util.Collections;
/**
 * ADSBからのデータを解析
 */
public class SBS3DataAnalyst {

	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	public static String analyzeData(String rawData){
		rawData = HexToBinary.hexToBinary(rawData);

		String dataEven;
		String dataOdd;

		if(judgedADS_B_Data(rawData)){
			System.out.println(rawData);

			int typeCode = DF17DataAnalysis.tc_analys(rawData);
			System.out.println("TC = " + typeCode);

			String modeS = DF17DataAnalysis.modoS_analys(rawData);
			System.out.println("modeS_Address = " + modeS);

			if(1 <= typeCode && typeCode <= 4){
				String callSign =  AnalyticalMethod.callSign(rawData);
				System.out.println("Callsign = " + callSign);
			}

			if(typeCode == 19){
				AnalyticalMethod.velocity(rawData);
			}

			if(9 <= typeCode && typeCode <= 18){
				System.out.println("Altitude = " + AnalyticalMethod.alt_calc(rawData) + "ft");
				System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(rawData, typeCode));

				if(Integer.parseInt(rawData.substring(109, 109+1), 2) == 0){
					//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
					for(Data oddData : oddDataList){
						if(DF17DataAnalysis.modoS_analys(rawData).equals(oddData.getModeS()) && rawData.substring(108, 108+1).equals(oddData.getTime())){
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

				if(Integer.parseInt(rawData.substring(109, 109+1), 2) == 1){
					//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
					for(Data evenData : evenDataList){
						if(DF17DataAnalysis.modoS_analys(rawData).equals(evenData.getModeS()) && rawData.substring(108, 108+1).equals(evenData.getTime())){
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
		return null;
	}

	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}
}