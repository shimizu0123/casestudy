public class Altitude {
	public int alt_calc(String data) {
		int n=0;

		String frontbit=data.substring(96,96+7);
		String backbit=data.substring(103,103+4);

		String altbin = frontbit + backbit;
		int unitbit = Integer.parseInt(data.substring(103,104),2);

		if(unitbit==1)n=25;
		if(unitbit==0)n=100;

		int altitude = Integer.parseInt(altbin,2)*n-1000;


		return altitude;
	}
}
