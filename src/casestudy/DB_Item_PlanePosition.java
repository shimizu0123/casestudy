package casestudy;

public class DB_Item_PlanePosition {

	private String modeSAddress_;
	private PlanePosition planePosition_;

	DB_Item_PlanePosition(String modeSAddress, PlanePosition planePosition){
		this.modeSAddress_ = modeSAddress;
		this.planePosition_ = planePosition;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public PlanePosition getPlanePosition() {
		return planePosition_;
	}

}
