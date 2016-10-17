package casestudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ADS_B_Analyst {

	public String data;
	public int num;
	ArrayList<String> oddDataList = new ArrayList();

	/*
	 * .txtから読み込み解析する
	 * →リアルタイムで読み込み解析するように書き換える必要有！
	 */
	String str;
	public String Read_Data(){
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
						System.out.println(data);
						System.out.println("TC = " + DF17DataAnalysis.tc_analys(data));
						System.out.println("modeS_Address = " + DF17DataAnalysis.modoS_analys(data));
						tcnum = DF17DataAnalysis.tc_analys(data);

						if(tcnum >= 1 && tcnum <= 4){
							System.out.println("Callsign = " + AnalyticalMethod.callSign(data));
						}

						if(tcnum >= 9 && tcnum <= 18){
							System.out.println("Altitude = " + AnalyticalMethod.alt_calc(data) + "ft");
							System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(data, tcnum));
						}

						if(tcnum == 19){
							AnalyticalMethod.velocity(data);
						}

						//EVENフレームの場合
						if((9 <= tcnum && tcnum <= 18) && data.substring(108,108+1) == "0"){
							/*
							 *  oddDataListを参照し、同じModeSアドレスのデータを探す
							 * （データが有った場合）→処理を続行
							 * （データがなかった場合）→「破棄」
							 */

						}

						//ODDフレームの場合
						if((9 <= tcnum && tcnum <= 18) && data.substring(108,108+1) == "1"){
							/*
							 * arraylist：oddDataListにdataを格納
							 */
							oddDataList.add(data);
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