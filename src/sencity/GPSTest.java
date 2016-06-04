package sencity;

import org.junit.Test;

import static org.testng.AssertJUnit.fail;

/**
 * Created by quentin on 6/4/16.
 */
public class GPSTest {
    @org.junit.Test
    public void getLatitude() throws Exception {

    }

    @Test
    public void getLongitude() throws Exception {

    }

    @Test
    public void setLatitude() throws Exception {

    }

    @Test
    public void setLongitude() throws Exception {

    }

    @Test
    public void equals() throws Exception {
        GPS gps1 = new GPS(23.23, 43.43);
        GPS gps2 = new GPS(23.23, 43.43);
        GPS gps3 = new GPS(21.23444, 2.455);

        if (!gps1.equals(gps2)) {
            System.out.println(gps1.toString() + " " +gps2.toString());
            fail("GPS1 n'est pas égal à GPS2");
        }

        if (gps1.equals(gps3)) {
            fail("GPS1 est égal à GPS3");
        }
    }

}