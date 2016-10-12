public class DF17_analyzer {

	public int tc_analys(String data) {
		int tcnum;

		tcnum = Integer.parseInt(data.substring(88,88+5), 2);
		return tcnum;

	}

	public static String modoS_analys(String data) {
		 String ModeS_Address_hex = Integer.toHexString(Integer.parseInt(data.substring(64,64+24), 2));
		 return ModeS_Address_hex;
	}

}
