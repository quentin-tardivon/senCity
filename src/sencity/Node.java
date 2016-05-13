package sencity;

/**
 * Created by quentin on 5/13/16.
 */
public class Node {
    char letter;
    Node brother;
    Node son;
    Traces listetraces = null;

    Node(char letter) {
        this.letter = letter;
    }
}
