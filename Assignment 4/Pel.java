/**
 * This class represents the data items to be stored in the nodes of the binary search tree.
 * @author Izum Adnan
 */

public class Pel{

	// Variables
	private Location p;
	private int color;

	// Constructor
	public Pel(Location p, int color){
		this.p=p;
		this.color=color;
	}
	
	// Returns Location
	public Location getLocus(){
		return p;
	}
	
	// Returns Color
	public int getColor(){
		return color;
	}
}
