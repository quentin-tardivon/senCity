package sencity;


/**
 * Created by quentin on 5/13/16.
 */
public class TreeTraces extends AbstractTraces {
    Node root;
    Node focusNode;

    public TreeTraces() {
        root = null;
    }


    @Override
    public void ajouter(Trace trace) { //TODO Regarder attentivement les NODE.SON
        focusNode = root;
        Node interNode;

        for (int i = 0; i < trace.ssid.length(); i++) {
            if (focusNode == null) {
                focusNode = new Node(trace.ssid.charAt(i));
                focusNode = focusNode.son;
            } else {
                if (focusNode.letter == trace.ssid.charAt(i)) {
                    focusNode = focusNode.son;
                } else {
                    focusNode.brother = new Node(trace.ssid.charAt(i));
                    focusNode = focusNode.brother;
                }
            }
        }
        if (focusNode.listetraces == null){
            focusNode.listetraces = new LinkedListTraces();
            focusNode.listetraces.ajouter(trace);
        }
        else {
                focusNode.listetraces.ajouter(trace);
        }

    }

    @Override
    public Traces extract(String ssid) {
        Traces result;
        focusNode = root;
        for (int i=0;i<ssid.length()-1;i++) {
            if (i == focusNode.letter) {
                focusNode = focusNode.son;
            }
            else {
                focusNode = focusNode.brother;
            }
        }
        result = focusNode.listetraces;
        return result;
    }
}
