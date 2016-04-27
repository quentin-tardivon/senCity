package sencity;

/**
 * Created by quentin on 20/04/16.
 */

public class GPS {
    protected Double longitude;
    protected Double latitude;

    public GPS(Double latitude, Double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.getLatitude()+" "+this.getLongitude();
    }
}
