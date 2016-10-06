public class Altitude {
	public int alt_calc(String data) {
		int altitude;
		int unitbit;
		String frontbit;
		String backbit;
		String altbin;
		int n=0;

		frontbit=data.substring(96,96+7);
		backbit=data.substring(103,103+4);

		altbin = frontbit + backbit;
		unitbit = Integer.parseInt(data.substring(103,104),2);

		if(unitbit==1)n=25;
		if(unitbit==0)n=100;

		/*System.out.println("front = " + frontbit);
		System.out.println("back = " + backbit);
		System.out.println("altbin = " + altbin);
		System.out.println("unitbit = " + unitbit + " n = " + n);
		*/

		altitude = Integer.parseInt(altbin,2)*n-1000;


		return altitude;
	}
}
