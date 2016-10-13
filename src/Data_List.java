

public class Data_List {

	private String bin_E;
	private String bin_O;


	public Data_List(String data) {
		if(judge_Even(data))setBin_E(data);
		else if(judge_Odd(data))setBin_O(data);
	}

	public String getBin_E() {
		return bin_E;
	}

	public void setBin_E(String bin_E) {
		this.bin_E = bin_E;
	}

	public String getBin_O() {
		return bin_O;
	}

	public void setBin_O(String bin_O) {
		this.bin_O = bin_O;
	}

	public void data_E_list(String data) {

	}

	@Override
	public String toString(){
		if(bin_E == null) return toString_ModeS(bin_O);
		return toString_ModeS(bin_E);
	}

	@Override
	public boolean equals(Object o){
		return o.toString().equals(this.toString());
	}

	private String toString_ModeS(String bin) {
		return Integer.toHexString(Integer.parseInt(bin.substring(64,64+24), 2));
	}

	private static boolean judge_Odd(String data) {
		return Integer.parseInt(data.substring(108,108+1), 2) == 1;
	}
	private static boolean judge_Even(String data) {
		return Integer.parseInt(data.substring(108,108+1), 2) == 0;
	}

}
