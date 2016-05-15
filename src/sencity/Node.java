package sencity;

/**
 * Created by quentin on 5/13/16.
 */
public class Node {
    protected char letter;
    protected Node brother;
    protected Node son;
    protected Traces listetraces = null;

    Node(char letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "letter " + letter + "\n" + "brother " + brother + "\n" + "son " + son + "\n" + "liste " + listetraces + "\n";
    }

    public char getLetter() {
        return letter;
    }

    public Node getBrother() {
        return brother;
    }

    public Node getSon() {
        return son;
    }

    public Traces getListetraces() {
        return listetraces;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setBrother(Node brother) {
        this.brother = brother;
    }

    public void setSon(Node son) {
        this.son = son;
    }

    public void setListetraces(Traces listetraces) {
        this.listetraces = listetraces;
    }
}
