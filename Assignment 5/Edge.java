/**
 * Edge.java - This class represents an edge of the graph. 
 * @author Izum Adnan
 */

public class Edge {
	
	// Variables
    private Node u, v;
    private String type;

    /**
     * The constructor for the class. The first two parameters are the end-points of the edge. The last parameter is the type of the edge.
     * @param u
     * @param v
     * @param type
     */
    public Edge(Node u, Node v, String type) {
        this.u = u;
        this.v = v;
        this.type = type;
    }

    /**
     * Returns the first endpoint of the edge.
     * @return
     */
    public Node firstNode() {
        return u;
    }

    /**
     * Returns the second endpoint of the edge.
     * @return
     */
    public Node secondNode() {
        return v;
    }

    /**
     * Returns the type of the edge.
     * @return
     */
    public String getType() {
        return type;
    }
}
