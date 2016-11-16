package casestudy;

public class DB_Item_CallSign {

	private String modeSAddress_;
	private String callSign_;

	DB_Item_CallSign(String modeSAddress, String callSign){
		this.modeSAddress_ = modeSAddress;
		this.callSign_ = callSign;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public String getCallSign() {
		return callSign_;
	}

}
