package casestudy;

public class PlanePosition {

	private double Lon;
	private double Lat;

	PlanePosition(double Lon, double Lat){
		this.Lon = Lon;
		this.Lat = Lat;
	}

	public double getLon() {
		return Lon;
	}

	public void setLon(double lon) {
		Lon = lon;
	}

	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

}
