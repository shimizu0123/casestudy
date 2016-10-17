package casestudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ADS_B_Analyst {

	public String data;
	public int num;
	ArrayList<EvenData> evenDataList = new ArrayList<>();
	ArrayList<OddData> oddDataList = new ArrayList<>();

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
						System.out.println(data);
						System.out.println("TC = " + DF17DataAnalysis.tc_analys(data));
						System.out.println("modeS_Address = " + DF17DataAnalysis.modoS_analys(data));
						tcnum = DF17DataAnalysis.tc_analys(data);

						if(1 <= tcnum && tcnum <= 4){
							System.out.println("Callsign = " + AnalyticalMethod.callSign(data));
						}

						if(tcnum == 19){
							AnalyticalMethod.velocity(data);
						}

						if(9 <= tcnum && tcnum <= 18){
							System.out.println("Altitude = " + AnalyticalMethod.alt_calc(data) + "ft");
							System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(data, tcnum));

							if(Integer.parseInt(data.substring(109, 109+1), 2) == 0){
								/*
								 *  oddDataListを参照し、同じModeSアドレスのデータを探す
								 * （データが有った場合）→処理を続行
								 * （データがなかった場合）→「破棄」
								 */
								System.out.println("！EVENデータ格納！");
						        EvenData evenData = new EvenData(data);
								evenDataList.add(evenData);
							}

							if(Integer.parseInt(data.substring(109, 109+1), 2) == 1){
								/*
								 *  oddDataListを参照し、同じModeSアドレスのデータを探す
								 * （データが有った場合）→処理を続行
								 * （データがなかった場合）→「破棄」
								 */
								System.out.println("！ODDデータ格納！");
						        OddData oddData = new OddData(data);
								oddDataList.add(oddData);
							}

						}

					}

				  }

			  }


			  /*
			   * テスト用領域ここから
			   */


			  System.out.println("↓ここからDataList↓");

			  for(EvenData data : evenDataList){
				  System.out.println(data.getData());
				  System.out.println(data.timeStamp);
			  }

			  for(OddData data : oddDataList){
				  System.out.println(data.getData());
				  System.out.println(data.timeStamp);
			  }




			  /*
			   * テスト用領域ここまで
			   */


			  filereader.close();
			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}

		return null;

	}


	/*
	 * 自作のクラス EvenData
	 */
	private static class EvenData{

		private String data;
		private long timeStamp;

		EvenData(String data){
			this.data = data;
			this.timeStamp = System.currentTimeMillis();
		}

		public String getData(){
			return this.data;
		}
		public String getModeS(){
			return Integer.toHexString(Integer.parseInt(data.substring(64,64+24), 2));
		}
		public String getTime(){
			return data.substring(108,108+1);
		}

	}

	/*
	 * 自作のクラス OddData
	 */
	private static class OddData{

		private String data;
		private long timeStamp;

		OddData(String data){
			this.data = data;
			this.timeStamp = System.currentTimeMillis();
		}

		public String getData(){
			return this.data;
		}
		public String getModeS(){
			return Integer.toHexString(Integer.parseInt(data.substring(64,64+24), 2));
		}
		public String getTime(){
			return data.substring(108,108+1);
		}

	}

}