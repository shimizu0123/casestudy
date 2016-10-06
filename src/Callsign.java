

public class Callsign {
	public static String CallSign(String data){

		String code = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ#####_###############0123456789######";
		StringBuilder sb = new StringBuilder();

		for(int i = 96; i < 143; i += 6){

			//System.out.println(data.substring(i,i+6));

			//System.out.println(Integer.parseInt(data.substring(i,i+6), 2));

			sb.append(code.charAt(Integer.parseInt(data.substring(i,i+6), 2)));
		}

		String CallSign = sb.toString();

		return CallSign;
	}

}
