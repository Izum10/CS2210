/**
 * Node.java - This class represent a node of the graph.
 * @author Izum Adnan
 */

public class Node {
	
	// Variables
    private int name;
    private boolean mark;

    /**
     * This is the constructor for the class and it creates a node with the given id
     * @param name
     */
    public Node(int name) {
        this.name = name;
    }

    /**
     * Marks the node with the specified value, either true or false
     * @param mark
     */
    public void markNode(boolean mark) {
        this.mark = mark;
    }

    /**
     * Returns the value with which the node has been marked
     * @return
     */
    public boolean getMark() {
        return mark;
    }

    /**
     *  Returns the id of this node
     * @return
     */
    public int getId() {
        return name;
    }
}
