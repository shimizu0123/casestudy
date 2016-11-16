package casestudy;

public class DB_Item_Velocity {

	private String modeSAddress_;
	private Velocity velocity_;

	DB_Item_Velocity(String modeSAddress, Velocity velocity){
		this.modeSAddress_ = modeSAddress;
		this.velocity_ = velocity;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public Velocity getVelocity() {
		return velocity_;
	}

}
