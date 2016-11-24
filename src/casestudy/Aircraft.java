package casestudy;

public class Aircraft {

	private String modes;


	private String callsign;

	private float lat;

	private float lng;

	private float alt;

	private float h_velo;

	private float v_velo;

	private float h_dir;

	private float v_dir;

	public Aircraft(String modes, String callsign, float lat,  float lng, float alt, float h_velo, float v_velo,float h_dir, float v_dir){
		this.modes = modes;
		this.callsign = callsign;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
		this.h_velo = h_velo;
		this.v_velo = v_velo;
		this.h_dir = h_dir;
		this.v_dir = v_dir;

	}



	public String getModes() {
		return modes;
	}




	public void setModes(String modes) {
		this.modes = modes;
	}




	public String getCallsign() {
		return callsign;
	}




	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}




	public float getLat() {
		return lat;
	}




	public void setLat(float lat) {
		this.lat = lat;
	}




	public float getLng() {
		return lng;
	}




	public void setLng(float lng) {
		this.lng = lng;
	}




	public float getAlt() {
		return alt;
	}




	public void setAlt(float alt) {
		this.alt = alt;
	}




	public float getH_velo() {
		return h_velo;
	}




	public void setH_velo(float h_velo) {
		this.h_velo = h_velo;
	}




	public float getV_velo() {
		return v_velo;
	}




	public void setV_velo(float v_velo) {
		this.v_velo = v_velo;
	}




	public float getH_dir() {
		return h_dir;
	}




	public void setH_dir(float h_dir) {
		this.h_dir = h_dir;
	}




	public float getV_dir() {
		return v_dir;
	}




	public void setV_dir(float v_dir) {
		this.v_dir = v_dir;
	}







}
