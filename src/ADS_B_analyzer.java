import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ADS_B_analyzer<SBS_3_Data> {

	SBS_3_Data Data;
	public String data;
	public int num;
	public List<Data_List> dataList = new ArrayList<Data_List>();

	String str;


	public String Read_Data(){
		  StringBuilder sb = new StringBuilder();
		  DF17_analyzer DF17analys =new DF17_analyzer();
		  Callsign Call =new Callsign();
		  Altitude Alt = new Altitude();
		  Calc_Velocity Vel = new Calc_Velocity();
		  Nic_analyzer Nic = new Nic_analyzer();

		  try{
			  File file = new File("test1000.txt");
			  FileReader filereader = new FileReader(file);

			  int ch;
			  int tcnum;

			  while((ch = filereader.read()) != -1){

				  if((48 <= ch && ch <= 57) || (97 <= ch && ch <= 102)){
					  str = String.valueOf((char)ch);
					  String binary = Integer.toBinaryString(Integer.parseInt(str, 16));
					  binary = String.format("%04d",Integer.parseInt(binary));


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
							System.out.println("Callsign = " +  Call.CallSign(data));
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
	@Deprecated
	private boolean judge_Odd(String data) {
		return Integer.parseInt(data.substring(108,108+1), 2) == 1;
	}
	@Deprecated
	private boolean judge_Even(String data) {
		return Integer.parseInt(data.substring(108,108+1), 2) == 0;
	}

	public int indexOf(Object modeS){
		return dataList.indexOf(modeS);
	}

	public int DFnum(String data){

		num = Integer.parseInt(data.substring(56,56+5), 2);

		return num;
	}

	public void add_Data_List(String data) {
		dataList.add(new Data_List(data));
	}

	public void Overwrite_Data_List(String data) {
		dataList.set(indexOf(data), data);
	}

}


