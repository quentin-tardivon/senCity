package senCity;

/**
 * Created by Quentin TARDIVON
 * Extrait un Timestamp, un SSID et la puissance du Signal d'un fichier
 */
public class Trace {
    protected String ts;
    protected String ssid;
    protected int signal;
    protected GPS coord;

    public Trace(String ts, String ssid, int signal, GPS coord) {
        this.ts = ts;
        this.ssid = ssid;
        this.signal = signal;
        this.coord = coord;
    }

    public String toString() {
        String result;
        result = ts + " " + ssid +" " + signal+" "+coord.toString();
        return result;
    }


}
