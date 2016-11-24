package casestudy;

import java.text.SimpleDateFormat;

public class DB_Item_CallSign {

	private long timeStamp_;
	private String modeSAddress_;
	private String callSign_;

	DB_Item_CallSign(String modeSAddress, String callSign){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.callSign_ = callSign;
	}

	public long getTimeStamp() {
		return timeStamp_;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public String getCallSign() {
		return callSign_;
	}

//	@Override
//	public String toString(){
//		StringBuilder sb = new StringBuilder();
//		sb.append("ModeS Address = ");
//		sb.append(modeSAddress_);
//		sb.append(", CallSign = ");
//		sb.append(callSign_);
//		sb.append('\n');
//		sb.append("TimeStamp = ");
//		sb.append(timeStamp_);
//		return sb.toString();
//	}

	@Override
	public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss");
		return ("CallSign," + "'"+sdf.format(timeStamp_) + "," + modeSAddress_ + "," + callSign_);
	}

}
