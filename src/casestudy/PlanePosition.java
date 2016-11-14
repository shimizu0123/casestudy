package casestudy;

public class PlanePosition {

	private double lat_;
	private double lon_;

	PlanePosition(double lat, double lon){
		this.lat_ = lat;
		this.lon_ = lon;
	}

	public double getLon() {
		return lon_;
	}

	public void setLon(double lon) {
		lon_ = lon;
	}

	public double getLat() {
		return lat_;
	}

	public void setLat(double lat) {
		lat_ = lat;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Lat = ");
		sb.append(lat_);
		sb.append("	,Lon = ");
		sb.append(this.lon_);
		return sb.toString();
	}


}
