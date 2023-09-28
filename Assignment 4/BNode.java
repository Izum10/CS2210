/**
 * This class represents the nodes of the binary search tree.
 * @author Izum Adnan
 */

public class BNode{

	// Variables
	private Pel value;
	private BNode left1,right1,parent1;
	
	// Constructor Class
	public BNode(Pel value, BNode left, BNode right, BNode parent){
		this.value = value;
		left1 = left;
		right1 = right;
		parent1 = parent;
	}
	
	// Constructor class that initializes a leaf node. Sets everything to null.
	public BNode(){
		value = null;
		left1 = null;
		right1 = null; 
		parent1 = null;
	}
	
	// Returns the parent.
	public BNode parent(){
		return parent1;
	}
	
	// Sets the parent of node to the specified value.
	public void setParent(BNode newParent){
		parent1 = newParent;
	}
	
	// Sets the left child of node to the specified value. 
	public void setLeftChild(BNode p){
		left1 = p;
	}
	
	// Sets the right child of node to the specified value.
	public void setRightChild(BNode p){
		right1 = p;
	}
	
	// Stores the given Pel object in this node.
	public void setContent(Pel value){
		this.value = value;
	}
	
	// Boolean statement which returns true if node is a lead, otherwise false.
	public boolean isLeaf(){
		if ((left1 == null) && (right1 == null)){
			return true;}
		else {
			return false;
	}}
	
	// Returns the Pel object.
	public Pel getData(){
		return value;
	}
	
	// Returns the left child. 
	public BNode leftChild(){
		return left1;
	}
	
	// Returns the right child. 
	public BNode rightChild(){
		return right1;
	}
}		

