/**
 * This class represents the location (x, y) of a pel. 
 * @author Izum Adnan
 */

public class Location{

	// Variables
	private int x,y;
	
	// Constructor
	public Location(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	// Returns the x coordinate
	public int getx(){
		return x;
	}
	
	// Returns the y coordinate
	public int gety(){
		return y;
	}
	
	// Checks the relationship between the x and y locations and returns accordingly
	public int compareTo(Location p){
		if ((this.gety() > p.gety()) || (this.gety() == p.gety() && this.getx() > p.getx())) {
			return 1; }
		else {
			if ((this.getx() == p.getx()) && (this.gety()==p.gety())) {
			return 0; }
		else {
			if ((this.gety() < p.gety()) || ((this.gety() == p.gety()) && (this.getx()<p.getx()))) {
			return -1; }
	else {
		return 0;
		}}}}
}
