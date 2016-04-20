package senCity;

/**
 * Created by quentin on 20/04/16.
 */
public class testGPS {
    public static void main(String[] args) {
        double longi = 12.3;
        double lati = 34.3;
        GPS coord = new GPS(longi,lati);
        System.out.println(coord.getLatitude());
        System.out.println(coord.toString());
        coord.setLatitude(23.0);
        coord.setLongitude(12.45);
        System.out.println(coord.toString());
    }
}
