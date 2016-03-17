package senCity;

/**
 * Created by quentin on 16/03/16.
 */
public class Trace {
    protected String ts;
    protected String ssid;
    protected int signal;

    public Trace(String ts, String ssid, int signal) {
        this.ts = ts;
        this.ssid = ssid;
        this.signal = signal;
    }

    public String toString() {
        String result;
        result = ts + " " + ssid +" " + signal;
        return result;
    }

    public static void main(String[] args) {
        Trace testTrace = new Trace("123456", "eduroam", 23);
        System.out.println(testTrace.toString());
    }
}
