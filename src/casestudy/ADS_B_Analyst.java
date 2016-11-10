package casestudy;

import java.util.ArrayList;
import java.util.Collections;
/**
 * ADSBからのデータを解析
 */
public class ADS_B_Analyst {

	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	public static String analyzeData(String data){
		data = HexToBinary.hexToBinary(data);

		String dataEven;
		String dataOdd;


		int dfNum = Integer.parseInt(data.substring(56,56+5), 2);
		if(dfNum == 17){
			System.out.println(data);

			int typeCode = DF17DataAnalysis.tc_analys(data);
			System.out.println("TC = " + typeCode);

			String modeS = DF17DataAnalysis.modoS_analys(data);
			System.out.println("modeS_Address = " + modeS);

			if(1 <= typeCode && typeCode <= 4){
				String callSign =  AnalyticalMethod.callSign(data);
				System.out.println("Callsign = " + callSign);
			}

			if(typeCode == 19){
				AnalyticalMethod.velocity(data);
			}

			if(9 <= typeCode && typeCode <= 18){
				System.out.println("Altitude = " + AnalyticalMethod.alt_calc(data) + "ft");
				System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(data, typeCode));

				if(Integer.parseInt(data.substring(109, 109+1), 2) == 0){
					//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
					for(Data oddData : oddDataList){
						if(DF17DataAnalysis.modoS_analys(data).equals(oddData.getModeS()) && data.substring(108, 108+1).equals(oddData.getTime())){
								dataOdd = oddData.getData();
								//dataOよりdataの方が新しいデータなので、タイムスタンプを1,0とする
								AnalyticalMethod.calc_Position(data, dataOdd, 1, 0);
								break;
						}
					}

					//ArrayListへ追加
				    Data evenData = new Data(data);
					evenDataList.add(evenData);
					//タイムスタンプの新しい順にソート
					Collections.sort(evenDataList, new DataListComparator());
				}

				if(Integer.parseInt(data.substring(109, 109+1), 2) == 1){
					//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
					for(Data evenData : evenDataList){
						if(DF17DataAnalysis.modoS_analys(data).equals(evenData.getModeS()) && data.substring(108, 108+1).equals(evenData.getTime())){
								dataEven = evenData.getData();
								//dataEよりdataの方が新しいデータなので、タイムスタンプを0,1とする
								AnalyticalMethod.calc_Position(dataEven, data, 0, 1);
								break;
						}
					}

					//ArrayListへ追加
			        Data oddData = new Data(data);
					oddDataList.add(oddData);
					//タイムスタンプの新しい順にソート
					Collections.sort(oddDataList, new DataListComparator());
				}
			}
		}
		return null;
	}
}