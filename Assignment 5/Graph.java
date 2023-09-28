/**
 * Graph.java - This class represents an undirected graph.
 * @author Izum Adnan
 */

// Imports Needed
import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements GraphADT{
    
	// Variables
	private ArrayList<Node> nodes;
    private ArrayList<ArrayList<Edge>> edges;

    /**
     * Creates a graph with n nodes and no edges. This is the constructor for the class.
     * @param n
     */
    public Graph(int n){
      
    	edges = new ArrayList<ArrayList<Edge>>(n);
        nodes = new ArrayList<Node>();
        
        int i;
        for (i = 0; i < n; i++) {
            edges.add(i, new ArrayList<>());
            nodes.add(new Node(i));
        }
    }

    /**
     * Returns the node with the specified id. If no node with this id exists, the method should throw a GraphException
     * @return
     */
    public Node getNode(int id) throws GraphException{
        
    	Node a = null;
        
    	try {
            
    		a = nodes.get(id);
        } catch (Exception e) {
            
        	throw new GraphException("Node Does Not Exist");
        }
        return a;
    }

    /**
     *  Adds an edge of the given type connecting u and v. This method throws a GraphException if either node does not exist or if in the 
        graph there is already an edge connecting the given nodes.
     */
    public void addEdge(Node u, Node v, String edgeType) throws GraphException{
        
    	getNode(v.getId());
    	getNode(u.getId());
    	edges.get(v.getId()).add(new Edge(v, u, edgeType));
        edges.get(u.getId()).add(new Edge(u, v, edgeType));
        
    }

    /**
     * Returns a Java Iterator storing all the edges incident on node u. It returns null if u does not have any edges incident on it.
     * @return
     */
    public Iterator<Edge> incidentEdges(Node u) throws GraphException{
        
    	try {
            
    		Iterator<Edge> incident_edges = edges.get(u.getId()).iterator();
            if (!incident_edges.hasNext()) {
                return null;
            } else
                return incident_edges;
        } catch (Exception e) {
            
        	throw new GraphException("Void");
        }

    }

    /**
     * Returns the edge connecting nodes u and v. This method throws a GraphException if there is no edge between u and v.
     * @return
     */
    public Edge getEdge(Node u, Node v) throws GraphException{
        
    	Iterator<Edge> edge = incidentEdges(u);
        
    	if (edge == null) {
            throw new GraphException("Void");
        }
        
        Edge cont;
        
        while (edge.hasNext()) {
            cont = edge.next();
            if (cont.secondNode() == v) {
                return cont;
            }
        }
        
        throw new GraphException("Void");
    }

    /**
     * Returns true if nodes u and v are adjacent; it returns false otherwise.
     * @return
     */
    public boolean areAdjacent(Node u, Node v) throws GraphException{
        
    	getNode(u.getId());
        getNode(v.getId());
        
        try {
            getEdge(u, v);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}