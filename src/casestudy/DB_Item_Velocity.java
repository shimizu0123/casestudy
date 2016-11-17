package casestudy;

public class DB_Item_Velocity {

	private long timeStamp_;
	private String modeSAddress_;
	private Velocity velocity_;

	DB_Item_Velocity(String modeSAddress, Velocity velocity){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.velocity_ = velocity;
	}

	public long getTimeStamp() {
		return timeStamp_;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public Velocity getVelocity() {
		return velocity_;
	}

//	@Override
//	public String toString(){
//		StringBuilder sb = new StringBuilder();
//		sb.append("ModeS Address = ");
//		sb.append(modeSAddress_);
//		sb.append(", ");
//		sb.append(velocity_.toString());
//		sb.append('\n');
//		sb.append("TimeStamp = ");
//		sb.append(timeStamp_);
//		return sb.toString();
//	}
	
	@Override
	public String toString(){
		return (timeStamp_ + "," + modeSAddress_ + "," + velocity_.toString());
	}

}
