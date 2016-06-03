package sencity;

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

    public boolean equals(Trace trace) {
        if (this.ts.equals(trace.ts) && this.ssid.equals(trace.ssid) && this.signal == trace.signal && this.coord.equals(trace.coord)) {
            return true;
        }
        else {
            return false;
        }
    }

}
