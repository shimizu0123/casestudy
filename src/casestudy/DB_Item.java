package casestudy;

public class DB_Item {

	private String modeSAddress;
	private String callSign;
	private Velocity velocity;
	private PlanePosition planePosition;

	DB_Item(String modeSAddress, String callSign){
		this.modeSAddress = modeSAddress;
		this.callSign = callSign;
		this.velocity = null;
		this.planePosition = null;
	}

	DB_Item(String modeSAddress, Velocity velocity){
		this.modeSAddress = modeSAddress;
		this.callSign = null;
		this.velocity = velocity;
		this.planePosition = null;
	}

	DB_Item(String modeSAddress, PlanePosition planePosition){
		this.modeSAddress = modeSAddress;
		this.callSign = null;
		this.velocity = null;
		this.planePosition = planePosition;
	}

	public String getModeSAddress() {
		return modeSAddress;
	}
	public void setModeSAddress(String modeSAddress) {
		this.modeSAddress = modeSAddress;
	}
	public String getCallSign() {
		return callSign;
	}
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
	public Velocity getVelocity() {
		return velocity;
	}
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}
	public PlanePosition getPlanePosition() {
		return planePosition;
	}
	public void setPlanePosition(PlanePosition planePosition) {
		this.planePosition = planePosition;
	}
}
