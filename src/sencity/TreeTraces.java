package sencity;



/**
 * Created by quentin on 5/13/16.
 */
public class TreeTraces extends AbstractTraces {
    Node root;

    public TreeTraces() {
        root = null;
    }


    @Override
    public void ajouter(Trace trace) { //TODO Regarder attentivement les NODE.SON
        Node focusNode = root;
        int i = 1;
        if (root == null) {
            root = new Node(trace.ssid.charAt(0));
            focusNode = root;
        } else {
            if (focusNode.getLetter() != trace.ssid.charAt(0)) {
                while (focusNode.getBrother() != null && focusNode.getBrother().getLetter() != trace.ssid.charAt(0)) {
                    focusNode = focusNode.getBrother();
                }
                if (focusNode.getBrother() == null) {
                    focusNode.setBrother(new Node(trace.ssid.charAt(0)));
                }
                focusNode = focusNode.getBrother();
            }
        }
        while (i < trace.ssid.length()) {
            if (focusNode.getSon() != null) {
                if (focusNode.getSon().getLetter() == trace.ssid.charAt(i)) {
                    focusNode = focusNode.getSon();
                    i += 1;
                } else {
                    while (focusNode.getSon().getBrother() != null && focusNode.getSon().getBrother().getLetter() != trace.ssid.charAt(i)) {
                        focusNode = focusNode.getSon().getBrother();
                    }
                    if (focusNode.getBrother() == null) {
                        focusNode.setBrother(new Node(trace.ssid.charAt(i)));
                    }
                    focusNode = focusNode.getBrother();
                    i += 1;
                }
            } else {
                focusNode.setSon(new Node(trace.ssid.charAt(i)));
                focusNode = focusNode.getSon();
                i += 1;
            }
            if (focusNode.getListetraces() == null) {
                focusNode.setListetraces(new LinkedListTraces());
            }
        }
        focusNode.getListetraces().ajouter(trace);
    }


    @Override
    public Traces extract(String ssid) throws IllegalArgumentException {
        Node focusNode = root;
        int prof = ssid.length();
        int i = 0;
        while (i<prof-1) {
            System.out.println(i);
            if (focusNode.getLetter() == ssid.charAt(i)) {
                i +=1;
                focusNode = focusNode.getSon();
            }
            else {
                focusNode = focusNode.getBrother();
            }
        }
        return focusNode.getListetraces();
    }

}
