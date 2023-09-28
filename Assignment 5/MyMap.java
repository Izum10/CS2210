/**
 * MyMap.java - This class represents the roadmap.
 * @author Izum Adnan
 */

// Imports Needed
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MyMap{

	// Variables
    private Graph map;
    private int SN,
            EN,
            Width,
            Length,
            PR,
            CR;

    /**
     * Constructor for building a graph from the input file specified in the parameter; this graph represents the roadmap. 
       If the input file does not exist, this method should throw a MapException.
     * @param inputFile
     * @throws MapException
     */
    public MyMap(String inputFile) throws MapException {
        try {
            int a = -1;
            int b, c;
            String cont;

            File f = new File(inputFile);
            Scanner r = new Scanner(f);
            r.nextLine();

            this.SN = r.nextInt();
            this.EN = r.nextInt();
            this.Width = r.nextInt();
            this.Length = r.nextInt();
            this.PR = r.nextInt();
            this.CR = r.nextInt();
            this.map = new Graph(Width * Length);

            int lineLength = (Width * 2) - 1;
            r.nextLine();

            while (r.hasNextLine()) {
                b = 0;
                cont = r.nextLine();
                while (b < (lineLength)) {
                    if (cont.charAt(b) == '+') {
                        a++;
                        b++;
                    } else if (cont.charAt(b) == 'V') {
                        map.addEdge(map.getNode(a), map.getNode(a + 1), "private");
                        b++;
                    } else if (cont.charAt(b) == 'P') {
                        map.addEdge(map.getNode(a), map.getNode(a + 1), "public");
                        b++;
                    } else if (cont.charAt(b) == 'C') {
                        map.addEdge(map.getNode(a), map.getNode(a + 1), "construction");
                        b++;
                    } else
                        b++;
                }
                if (r.hasNextLine()) {
                    b = 0;
                    c = 0;
                    cont = r.nextLine();
                    while (c < lineLength) {
                        if (cont.charAt(c) == 'V') {
                            map.addEdge(map.getNode(a + b + 1), map.getNode((a + b + 1) - Width), "private");
                            b++;
                            c++;

                        } else if (cont.charAt(c) == 'P') {
                            map.addEdge(map.getNode(a + b + 1), map.getNode((a + b + 1) - Width), "public");
                            b++;
                            c++;
                        } else if (cont.charAt(c) == 'C') {
                            map.addEdge(map.getNode(a + b + 1), map.getNode((a + b + 1) - Width), "construction");
                            b++;
                            c++;
                        } else if (c % 2 == 0) {
                            b++;
                            c++;
                        } else
                            c++;
                    }
                }
            }
            r.close();
        } catch (Exception e) {
            throw new MapException("File Does Not Exist");
        }
    }

    /**
     * Returns the graph representing the roadmap.
     * @return
     */
    public Graph getGraph() {
        return map;
    }

    /**
     * Returns the id of the starting node
     * @return
     */
    public int getStartingNode() {
        return SN;
    }

    /**
     * Returns the id of the destination node
     * @return
     */
    public int getDestinationNode() {
        return EN;
    }

    /**
     * Returns the maximum number allowed of private roads in the path from the starting node to the destination.
     * @return
     */
    public int maxPrivateRoads() {
        return PR;
    }

    /**
     * Returns the maximum number allowed of construction roads in the path from the starting node to the destination.
     * @return
     */
    public int maxConstructionRoads() {
        return CR;
    }

    /**
     * Returns a Java Iterator containing the nodes of a path from the start node to the destination node such that the path uses at 
       most maxPrivate private roads and maxConstruction construction roads, if such a path exists. 
       If the path does not exist, this method returns the value null.
     * @param start
     * @param destination
     * @param maxPrivate
     * @param maxConstruction
     * @return
     * @throws GraphException
     */
    public Iterator<Node> findPath(int start, int destination, int maxPrivate, int maxConstruction)
            throws GraphException {

        ArrayList<Node> path = new ArrayList<>();
        path.add(map.getNode(start));
        findPathList(start, destination, maxPrivate, maxConstruction, path);
        Iterator<Node> found = path.iterator();
        if(path.size()==1) {
        	found=null;
        }
        return found;

    }

    // Helper Method
    private ArrayList<Node> findPathList(int s, int d, int mP, int mC, ArrayList<Node> path)
            throws GraphException {
        if (s == d)
            return path;
        Iterator<Edge> edges = map.incidentEdges(map.getNode(s));
        if (edges.hasNext()) {
            Edge currentEdge;
            Iterator<Edge> current = edges;
            while (current.hasNext()) {
                currentEdge = current.next();
                if (!currentEdge.secondNode().getMark() && currentEdge.getType() == "public") {
                    if (currentEdge.secondNode().getId() == 12) {
                        System.out.println("");
                    }
                    currentEdge.secondNode().markNode(true);
                    map.getNode(s).markNode(true);
                    path.add(currentEdge.secondNode());
                    if (findPathList(currentEdge.secondNode().getId(), d, mP, mC, path) == null) {
                        currentEdge.secondNode().markNode(false);
                        path.remove(currentEdge.secondNode());
                    } else
                        return path;
                }
            }
            current = map.incidentEdges(map.getNode(s));
            ;
            while (current.hasNext()) {
                currentEdge = current.next();
                if (!currentEdge.secondNode().getMark() && currentEdge.getType() == "private" && mP > 0) {
                    currentEdge.secondNode().markNode(true);
                    map.getNode(s).markNode(true);
                    path.add(currentEdge.secondNode());
                    if (findPathList(currentEdge.secondNode().getId(), d, mP - 1, mC, path) == null) {
                        currentEdge.secondNode().markNode(false);
                        path.remove(currentEdge.secondNode());
                    } else
                        return path;
                }
            }
            current = map.incidentEdges(map.getNode(s));
            ;
            while (current.hasNext()) {
                currentEdge = current.next();
                if (!currentEdge.secondNode().getMark() && currentEdge.getType() == "construction" && mC > 0) {
                    currentEdge.secondNode().markNode(true);
                    map.getNode(s).markNode(true);
                    path.add(currentEdge.secondNode());
                    if (findPathList(currentEdge.secondNode().getId(), d, mP, mC - 1, path) == null) {
                        currentEdge.secondNode().markNode(false);
                        path.remove(currentEdge.secondNode());
                    } else
                        return path;
                }
            }
        }
        return null;
    }
}
