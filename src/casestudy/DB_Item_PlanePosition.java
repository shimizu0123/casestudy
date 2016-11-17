package casestudy;

public class DB_Item_PlanePosition {

	private long timeStamp_;
	private String modeSAddress_;
	private PlanePosition planePosition_;

	DB_Item_PlanePosition(String modeSAddress, PlanePosition planePosition){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.planePosition_ = planePosition;
	}

	public long getTimeStamp() {
		return timeStamp_;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public PlanePosition getPlanePosition() {
		return planePosition_;
	}

//	@Override
//	public String toString(){
//		StringBuilder sb = new StringBuilder();
//		sb.append("ModeS Address = ");
//		sb.append(modeSAddress_);
//		sb.append(", ");
//		sb.append(planePosition_.toString());
//		sb.append('\n');
//		sb.append("TimeStamp = ");
//		sb.append(timeStamp_);
//		return sb.toString();
//	}

	@Override
	public String toString(){
		return ("PlanePosition," + timeStamp_ + "," + modeSAddress_ + "," + planePosition_.toString());
	}

}
