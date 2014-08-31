package dto;

public class Point {
	
	private Double lon;
	private Double lat;
	
	public Point(Double lon, Double lat){
		this.lon = lon;
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lat) {
		this.lon = lat;
	}

	public Double getlat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	

}
