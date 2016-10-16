package casestudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ADS_B_Analyst {

	public String data;
	public int num;
	ArrayList oddDataList = new ArrayList();

	/*
	 * .txtから読み込み解析する
	 * →リアルタイムで読み込み解析するように書き換える必要有！
	 */
	String str;
	public String Read_Data(){
		  StringBuilder sb = new StringBuilder();
		  DF17DataAnalysis dF17analysis =new DF17DataAnalysis();

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

					data=sb.toString();
					sb.delete(0, sb.length());

					if(DFnum(data) == 17){
						System.out.println(data);
						System.out.println("TC = "+DF17analys.tc_analys(data));
						System.out.println("modeS_Address = " + DF17analys.modoS_analys(data));
						tcnum=DF17analys.tc_analys(data);

						if(tcnum >= 1 && tcnum <= 4){
							System.out.println("Callsign = " +   AnalyticalMethod.callSign(data));
						}

						if(tcnum >= 9 && tcnum <= 18){
							System.out.println("Altitude = " +  Alt.alt_calc(data) + "ft");
							System.out.println("Nicnum = " + Nic.nic_analyz(data, tcnum));
						}

						if(tcnum == 19){
							Vel.velocity(data);
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