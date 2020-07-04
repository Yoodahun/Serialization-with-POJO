package pojo.googleAPI.subClass;

public class Location {
    private double lat;
    private double lng;

    public Location() {
        this.lat = -38.383494;
        this.lng = 33.427362;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
