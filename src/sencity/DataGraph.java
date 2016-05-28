package sencity;

/**
 * Created by quentin on 5/18/16.
 */
public class DataGraph {

    public Double distance(GPS point1, GPS point2) {
       return Math.sqrt(Math.pow(point2.getLatitude() - point1.getLatitude(),2) + Math.pow(point2.getLongitude() - point1.getLongitude(),2));
    }

    
}
