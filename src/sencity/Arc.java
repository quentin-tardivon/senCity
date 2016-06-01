package sencity;

/**
 * Created by quentin on 5/30/16.
 */
public class Arc {
    private Trace sommet1;
    private Trace sommet2;
    private Double distance;

    public Arc(Trace sommet1, Trace sommet2, Double distance) {
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.distance = distance;
    }

    public Trace getSommet1() {
        return sommet1;
    }

    public void setSommet1(Trace sommet1) {
        this.sommet1 = sommet1;
    }

    public Trace getSommet2() {
        return sommet2;
    }

    public void setSommet2(Trace sommet2) {
        this.sommet2 = sommet2;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "sommet1=" + sommet1 +
                ", sommet2=" + sommet2 +
                ", distance=" + distance +
                '}' + "\n";
    }

}
