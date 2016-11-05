package casestudyVerText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ADS_B_Analyst {

	String data;
	String dataE;
	String dataO;
	ArrayList<Data> evenDataList = new ArrayList<Data>();
	ArrayList<Data> oddDataList = new ArrayList<Data>();

	/*
	 * test1000.txt（1000件分のデータ）から読み込み解析する。解析結果がコンソールに表示される。
	 * →実際はリアルタイムで読み込み、解析後は解析データの入ったDB-Itemを出力するように書き換える必要有！
	 */
	int ch;
	String str;
	public String read_Data(){
		  StringBuilder sb = new StringBuilder();

		  try{
			  File file = new File("test1000.txt");
			  FileReader filereader = new FileReader(file);

			  while((ch = filereader.read()) != -1){
				  if((48 <= ch && ch <= 57) || (97 <= ch && ch <= 102)){
					  str = String.valueOf((char)ch);
					  String binary = Integer.toBinaryString(Integer.parseInt(str, 16));
					  binary = String.format("%04d", Integer.parseInt(binary));
					  sb.append(binary);
				  }

				  //改行されるとsbをdataに入れて、リセット
				  if (ch == 10){
					data = sb.toString();
					sb.delete(0, sb.length());

					/*
					 * 以下が実際のADS_B_Analystの処理
					 */
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
											dataO = oddData.getData();
											//dataOよりdataの方が新しいデータなので、タイムスタンプを1,0とする
											AnalyticalMethod.calc_Position(data, dataO, 1, 0);
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
											dataE = evenData.getData();
											//dataEよりdataの方が新しいデータなので、タイムスタンプを0,1とする
											AnalyticalMethod.calc_Position(dataE, data, 0, 1);
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
				  }
			  }

			  filereader.close();

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}

		return null;
	}

}