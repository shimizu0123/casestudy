package IteratorPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ADS_B_Analyst_Iterator適用版 {

	String data;
	String dataE;
	String dataO;
    ConcreteAggregate evenData = new ConcreteAggregate();
    ConcreteAggregate oddData = new ConcreteAggregate();

	int ch;
	String str;
	public String read_Data(){
		  StringBuilder sb = new StringBuilder();
		  try{
			  File file = new File("test1000.txt");
			  FileReader filereader = new FileReader(file);

			  while((ch = filereader.read()) != -1){
				  textdataHextoBin(sb);
				  if (ch == 10){
					data = sb.toString();
					sb.delete(0, sb.length());

					if(Integer.parseInt(data.substring(56,56+5), 2) == 17){
						System.out.println(data);
						int typeCode = DF17DataAnalysis.tc_analys(data);
						System.out.println("TC = " + typeCode);
						System.out.println("modeS_Address = " + DF17DataAnalysis.modoS_analys(data));
						if(1 <= typeCode && typeCode <= 4){
							System.out.println("Callsign = " + AnalyticalMethod.callSign(data));
						}
						if(typeCode == 19){
							AnalyticalMethod.velocity(data);
						}
						if(9 <= typeCode && typeCode <= 18){
							System.out.println("Altitude = " + AnalyticalMethod.alt_calc(data) + "ft");
							System.out.println("Nicnum = " + AnalyticalMethod.nic_analyz(data, typeCode));
							position();
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

	private void position() {
		//EVEN判定
		if(Integer.parseInt(data.substring(109, 109+1), 2) == 0){
			//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
		    Iterator it = oddData.iterator();
		    while (it.hasNext()) {
		        Data oddData = (Data) it.next();
				if(DF17DataAnalysis.modoS_analys(data).equals(oddData.getModeS()) && data.substring(108, 108+1).equals(oddData.getTime())){
					dataO = oddData.getData();
					AnalyticalMethod.calc_Position(data, dataO);
					break;
				}
		    }
			//ArrayListへ追加
			evenData.addData(new Data(data));
			//タイムスタンプの新しい順にソート
			evenData.sortDataList();
		}
		//ODD判定
		else{
			//リストを参照　モードSアドレスが同じ　かつ　Timeビットが同じ　→　計算可能
		    Iterator it = evenData.iterator();
		    while (it.hasNext()) {
		        Data evenData = (Data) it.next();
				if(DF17DataAnalysis.modoS_analys(data).equals(evenData.getModeS()) && data.substring(108, 108+1).equals(evenData.getTime())){
					dataE = evenData.getData();
					AnalyticalMethod.calc_Position(dataE, data);
					break;
				}
		    }
			//ArrayListへ追加
			oddData.addData(new Data(data));
			//タイムスタンプの新しい順にソート
			oddData.sortDataList();
		}
	}

	private void textdataHextoBin(StringBuilder sb) {
		if((48 <= ch && ch <= 57) || (97 <= ch && ch <= 102)){
			  str = String.valueOf((char)ch);
			  String binary = Integer.toBinaryString(Integer.parseInt(str, 16));
			  binary = String.format("%04d", Integer.parseInt(binary));
			  sb.append(binary);
		  }
	}
}