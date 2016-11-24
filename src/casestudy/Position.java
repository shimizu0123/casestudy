package casestudy;

public class Position {

	private String modes;



	private float lat;

	private float lng;

	private float alt;

	public Position(String modes,  float lat,  float lng, float alt){
		this.modes = modes;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
	}


	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
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

}
