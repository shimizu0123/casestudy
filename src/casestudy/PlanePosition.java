package casestudy;

public class PlanePosition {


	private double lat_;
	private double lon_;
	private double alt_;

	PlanePosition(double lat, double lon, double alt){
		this.lat_ = lat;
		this.lon_ = lon;
		this.alt_ = alt;
	}

	public double getLat() {
		return lat_;
	}

	public double getLon() {
		return lon_;
	}

	public double getAlt_() {
		return alt_;
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
