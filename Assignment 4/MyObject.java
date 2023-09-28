/**
 * MyObject Class which essentially returns and sets values throughout.
 * @author Izum Adnan
 */

public class MyObject implements MyObjectADT  {
	
	// Set Variables
	private int id, width, height;
	private String type;
	Location pos;
	private BinarySearchTree t_tree;
	
	/*
	 * Constructor Class
	 */
	public MyObject(int id, int width, int height, String type, Location pos) {
	
		this.id = id;
		this.width = width;
		this.height = height;
		this.pos = pos;
		this.type = type;
		t_tree = new BinarySearchTree();
	}
	
	/*
	 * Sets the type of this MyObject to the specified value
	 */
	public void setType(String type1) {
		type = type1;
	}
	
	/*
	 * Returns the width of the enclosing rectangle for this MyObject.
	 */
	public int getWidth() {
		return width;
	}
	
	/*
	 * Returns the height of the enclosing rectangle for this MyObject.
	 */
	public int getHeight() {
		return height;
	}
	
	/*
	 * Returns the type of this Myobject.
	 */
	public String getType() {
		return type;
	}
	
	/*
	 * Returns the id of this MyObject.
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Returns the locus of this MyObject
	 */
	public Location getLocus() {
		return pos;
	}
	
	/*
	 * Changes the locus of this MyObject to the specified value
	 */
	public void setLocus(Location value) {
		pos = value;
	}
	
	/*
	 * Inserts pix into the binary search tree associated with this MyObject. 
	 * Throws a DuplicatedKeyException if an error occurs when inserting the Pel object pix into the tree.	 
	 * */
	public void addPel(Pel pix) throws DuplicatedKeyException {
	 t_tree.put(t_tree.getRoot(), pix);
	}
	
	/*
	 * Returns true if this MyObject intersects the one specified in the parameter. 
	 * It returns false otherwise.
	 */
	public boolean intersects(MyObject otherObject) {
	    if(eRec(otherObject) ){
	            try {   	
	                for (Pel s = t_tree.smallest(t_tree.getRoot()); t_tree.successor(t_tree.getRoot(), 
	                		s.getLocus())!=null; s= t_tree.successor(t_tree.getRoot(), s.getLocus())){
	                    if (otherObject.find(s, otherObject)) 
	                        return true; }
	            } catch (EmptyTreeException exception) {
	                exception.printStackTrace();
	            }
	    }
	    return false;
	}
	
	/*
	 * Private helper class for intersects class
	 */
	private boolean find(Pel r, MyObject txt){
	    
		int x = r.getLocus().getx(), y = r.getLocus().gety(), xTemp = this.getLocus().getx(), yTemp = this.getLocus().gety(), xTemp2 = txt.getLocus().getx(), yTemp2 = txt.getLocus().gety();
	    Location newLocation = new Location((x + xTemp - xTemp2), (y + yTemp - yTemp2)); 
	    return (t_tree.get(t_tree.getRoot(), newLocation) != null);
	
	}
	
	/*
	 * Private helper class for intersects class
	 */
	private boolean eRec(MyObject txt){	
		
		if((txt.getLocus().gety() + txt.getHeight() < pos.gety()) || (pos.gety() + getHeight() < txt.getLocus().gety()) || (pos.getx() + getWidth() < txt.getLocus().getx()) || (txt.getLocus().getx() + txt.getWidth() < pos.getx())) {
				return false; 
		} else {
				return true;			
	}}
}