
public class Nic_analyzer {
	public double nic_analyz(String data,int tcnum) {
		double Nicnum=0;

		if(tcnum==9){
			Nicnum=7.5;
		}
		if(tcnum==10){
			Nicnum=25;
		}
		if(tcnum==11){
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
				Nicnum=74;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				Nicnum=185.2;
			}
		}
		if(tcnum==12){
			Nicnum=185.2*2;
		}
		if(tcnum==13){
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
					Nicnum=185.2*3;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				Nicnum=185.2*5;
			}
		}
		if(tcnum==14){
			Nicnum=1852;
		}
		if(tcnum==15){
			Nicnum=1852*2;
		}
		if(tcnum==16){
			if(Integer.parseInt(data.substring(95,95+1),2) == 1){
				Nicnum=1852*4;
			}
			if(Integer.parseInt(data.substring(95,95+1),2) == 0){
				Nicnum=1852*8;
			}
		}
		if(tcnum==17){
			Nicnum=1852*20;
		}
		if(tcnum==18){
			Nicnum=2852*2;

		}
		return Nicnum;
	}
}
