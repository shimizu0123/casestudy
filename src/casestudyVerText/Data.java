package casestudyVerText;

public class Data {
	private String data;
	private long timeStamp;

	Data(String data){
		this.data = data;
		this.timeStamp = System.currentTimeMillis();
	}

	public String getData(){
		return this.data;
	}
	public long getTimeStamp(){
		return this.timeStamp;
	}

	public String getModeS(){
		return Integer.toHexString(Integer.parseInt(data.substring(64,64+24), 2));
	}
	public String getTime(){
		return data.substring(108,108+1);
	}

}
