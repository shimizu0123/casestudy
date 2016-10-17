package casestudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ADS_B_Analyst {

	public String data;
	public String dataE;
	public String dataO;

	public int num;
	ArrayList<Data> evenDataList = new ArrayList<Data>();
	ArrayList<Data> oddDataList = new ArrayList<Data>();

	/*
	 * .txtから読み込み解析する
	 * →リアルタイムで読み込み解析するように書き換える必要有！
	 */
	String str;
	public String read_Data(){
		  StringBuilder sb = new StringBuilder();
		  DF17DataAnalysis dF17DataAnalysis =new DF17DataAnalysis();

		  try{
			  File file = new File("test1000.txt");
			  FileReader filereader = new FileReader(file);

			  int ch;
			  int tcnum;

			  while((ch = filereader.read()) != -1){

				  if((48 <= ch && ch <= 57) || (97 <= ch && ch <= 102)){
					  str = String.valueOf((char)ch);
					  String binary = Integer.toBinaryString(Integer.parseInt(str, 16));
					  binary = String.format("%04d", Integer.parseInt(binary));
					  sb.append(binary);
				  }

				  if (ch == 10){

					data = sb.toString();
					sb.delete(0, sb.length());

					if(Integer.parseInt(data.substring(56,56+5), 2) == 17){
//						System.out.println(data);
//						System.out.println("TC = " + DF17DataAnalysis.tc_analys(data));
//						System.out.println("modeS_Address = " + DF17DataAnalysis.modoS_analys(data));
						tcnum = DF17DataAnalysis.tc_analys(data);

						if(1 <= tcnum && tcnum <= 4){
//							System.out.println("Callsign = " + AnalyticalMethod.callSign(data));
						}

						if(tcnum == 19){
//							AnalyticalMethod.velocity(data);
						}

						if(9 <= tcnum && tcnum <= 18){
//							System.out.println("Altitude = " + AnalyticalMethod.alt_calc(data) + "ft");
//							System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(data, tcnum));

							if(Integer.parseInt(data.substring(109, 109+1), 2) == 0){
								/*
								 *  oddDataListを参照し、同じModeSアドレスのデータを探す
								 * （データが有った場合）→処理を続行
								 * （データがなかった場合）→「破棄」
								 */

								//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
								for(Data oddData : oddDataList){
									if((DF17DataAnalysis.modoS_analys(data) == oddData.getModeS()) && (data.substring(108,108+1) == oddData.getTime())){
											System.out.println("緯度経度計算実行");
											dataO = oddData.getData();
											AnalyticalMethod.calc_Position(data, dataO);
//											break;
									}
								}

								//ArrayListへ追加
								System.out.println("！EVENデータ格納！");
							    Data evenData = new Data(data);
								evenDataList.add(evenData);

								//タイムスタンプの新しい順にソート
								Collections.sort(evenDataList, new DataListComparator());
							}

							if(Integer.parseInt(data.substring(109, 109+1), 2) == 1){
								/*
								 *  oddDataListを参照し、同じModeSアドレスのデータを探す
								 * （データが有った場合）→処理を続行
								 * （データがなかった場合）→「破棄」
								 */

								//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
								for(Data evenData : evenDataList){
									System.out.println(DF17DataAnalysis.modoS_analys(data) + " " + evenData.getModeS());
									if(DF17DataAnalysis.modoS_analys(data).equals(evenData.getModeS()) /*&& (data.substring(108,108+1) == evenData.getTime())*/){
											System.out.println("緯度経度計算実行");
											dataE = evenData.getData();
											AnalyticalMethod.calc_Position(dataE, data);
											break;
									}
								}

								//ArrayListへ追加
								System.out.println("！ODDデータ格納！");
						        Data oddData = new Data(data);
								oddDataList.add(oddData);

								//タイムスタンプの新しい順にソート
								Collections.sort(oddDataList, new DataListComparator());
							}

						}

					}

				  }

			  }


//			  /*
//			   * テスト用領域ここから
//			   */
//
//
//			  System.out.println("↓ここからDataList↓");
//
//			  for(Data data : evenDataList){
//				  System.out.println(data.getData());
//				  System.out.println(data.getTimeStamp());
//			  }
//
//			  for(Data data : oddDataList){
//				  System.out.println(data.getData());
//				  System.out.println(data.getTimeStamp());
//			  }
//
//
//			  /*
//			   * テスト用領域ここまで
//			   */


			  filereader.close();
			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}

		return null;

	}

}