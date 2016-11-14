package casestudy;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;




public class ADS_B_DBSample {

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
	            //出力先を作成する
	            FileWriter fw = new FileWriter("C:\\pleiades\\workspace\\casestudy2\\test.csv", false);  //※１
	            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

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

					/*
					 * 以下が実際のADS_B_Analystの処理
					 */
					int dfNum = Integer.parseInt(data.substring(56,56+5), 2);
					if(dfNum == 17){
//						System.out.println(data);

						int typeCode = DF17DataAnalysis.tc_analys(data);
						//System.out.println("TC = " + typeCode);

						String modeS = DF17DataAnalysis.modoS_analys(data);

						if(1 <= typeCode && typeCode <= 4){
							pw.print(modeS + ",");
							String callSign =  AnalyticalMethod.callSign(data);
							pw.println(callSign + ",null,null,null,null,null,null,null,null,"+DB_items.gettimestamp()+",1");
						}

						if(typeCode == 19){
							pw.print(modeS + ",null,null,null,null,");
							pw.print(AnalyticalMethod.velocity(data).Vel+",");
							pw.print(AnalyticalMethod.velocity(data).deg+",");
							pw.print(AnalyticalMethod.velocity(data).S_Vr+",");
							pw.println(AnalyticalMethod.velocity(data).Vr+",null,"+DB_items.gettimestamp()+",3");
						}

						if(9 <= typeCode && typeCode <= 18){
							//System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(data, typeCode));

							if(Integer.parseInt(data.substring(109, 109+1), 2) == 0){

								//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
								for(Data oddData : oddDataList){
									if(DF17DataAnalysis.modoS_analys(data).equals(oddData.getModeS()) && data.substring(108, 108+1).equals(oddData.getTime())){
										pw.print(modeS + ",null,");
										dataO = oddData.getData();
										pw.print(AnalyticalMethod.calc_Position(data, dataO, 1, 0).Lat + ",");
										pw.print(AnalyticalMethod.calc_Position(data, dataO, 1, 0).Lon + ",");

										pw.println(AnalyticalMethod.alt_calc(data) + ",null,null,null,null,null,"+DB_items.gettimestamp()+",2");

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
										pw.print(modeS + ",null,");											dataE = evenData.getData();
										pw.print(AnalyticalMethod.calc_Position(dataE, data, 0, 1).Lat+",");
										pw.print(AnalyticalMethod.calc_Position(dataE, data, 0, 1).Lon+",");
										pw.println(AnalyticalMethod.alt_calc(data) + ",null,null,null,null,null,"+DB_items.gettimestamp()+",2");

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

	            //ファイルに書き出す
	            pw.close();

	            //終了メッセージを画面に出力する
	            System.out.println("出力が完了しました。");


			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}

		return null;

	}

}