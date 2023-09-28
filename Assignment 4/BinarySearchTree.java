/**
 * This class implements an ordered dictionary using a binary search tree
 * @author Izum Adnan
 */

public class BinarySearchTree implements BinarySearchTreeADT{
	
	// Private Variable
	private BNode root_node;
	
	/*
	 * Constructor Class
	 */
	public BinarySearchTree(){
		root_node = new BNode();
	}

	/*
	 * Returns the Pel object storing the given key, if the key is stored in the tree; returns null otherwise.
	 */
	public Pel get(BNode r, Location key){
		
		int temp = r.getData().getLocus().compareTo(key);
		
		if ((r.isLeaf()) && (temp != 0)) {
			return null;
			}
		else 
			if (temp == 1)
				if (r.leftChild()==null){
					return null;
					}
				else 
					return get(r.leftChild(),key);
		else 
			if (temp == 0)
				return r.getData();
		
		
		if (r.rightChild()==null){
			return null;
			}
		else {
			return get(r.rightChild(),key);
			}
		
	}

	/*
	 *  Inserts newData in the tree if no data item with the same key is already there; if a node already stores the same key, 
	 *  the algorithm throws a DuplicatedKeyException.
	 */
	public void put(BNode r, Pel newdata) throws DuplicatedKeyException{
		
		if (r == null){
			r = new BNode();
			root_node = r;
			}
		
		if (r.getData() == null) 
			r.setContent(newdata);
		else{
			int temp2 = r.getData().getLocus().compareTo(newdata.getLocus());
			if (temp2 == 0)
				throw new DuplicatedKeyException("Already Exists");
			if (r.isLeaf()){
				if (temp2 == -1){
					BNode temp3 = new BNode(newdata, null, null, r);
					r.setRightChild(temp3);}
				else
					if (temp2 == 0)
						throw new DuplicatedKeyException("Already Exists");
					else 
						if (temp2 == 1){
							BNode temp3 = new BNode(newdata, null, null, r);
							r.setLeftChild(temp3);}}
			else
				if (temp2 == 1){	
						if (r.leftChild() == null) {
							 BNode temp3 = new BNode(newdata, null, null, r);
							 r.setLeftChild(temp3);}
						else 
							put(r.leftChild(), newdata);}
				else 
					if (r.rightChild() == null) {
						BNode temp3 = new BNode(newdata, null, null, r);
						r.setRightChild(temp3);}
					else 
						put(r.rightChild(),newdata);
	}}

	/*
	 *  Removes the data item with the given key, if the key is stored in the tree; throws an InexistentKeyException otherwise.
	 */
	public void remove(BNode r, Location key) throws InexistentKeyException{
		
		BNode cont = getNode(r, key);
		
		if (get(r, key) == null) 
			throw new InexistentKeyException("Cannot Throw Here");
		else 
			if ((cont.parent() == null) && (cont.isLeaf()))
				root_node=null;
		else {
			if(!cont.isLeaf()){
				cont.setContent(SN(cont.rightChild()).getData());
				remove(cont.rightChild(),SN(cont.rightChild()).getData().getLocus());}
			else 
				if (cont.isLeaf()){
					if(cont.parent().leftChild() == cont)
						cont.parent().setLeftChild(null);
					else 
						cont.parent().setRightChild(null);
				setNode(cont,null);}
				else 
					if (cont.rightChild() == null) 
				setNode(cont, cont.leftChild());
				else 
					if (cont.leftChild() == null) 
				setNode(cont, cont.rightChild());
	}} 
	
	/*
	 * Returns the Pel object with the smallest key larger than the given one (note that the tree does not need to store a node with the given key). 
	 * Returns null if the given key has no successor
	 */
	public Pel successor(BNode r, Location key){
		
		BNode cont = getNode(r,key);
		int temp = cont.getData().getLocus().compareTo(key);
		
		if (temp == -1){
			while (temp != 1){
				if (cont.parent() != null){
					cont = cont.parent(); 
					temp = cont.getData().getLocus().compareTo(key);}
				else 
					return null;
			}
			return cont.getData();
		} else {
			if (temp == 0){
				if (cont.rightChild() != null)
					return LN(cont).getData();
				else 
					while (temp != 1){
						if (cont.parent() != null){
							cont=cont.parent(); 
							temp = cont.getData().getLocus().compareTo(key);}
						else 
							return null;
					}
				return cont.getData();
			} else {
				return cont.getData();
	}}}

	/*
	 * Returns the Pel object with the largest key smaller than the given one (note that the tree does not need to store a node with the given key).
	 * Returns null if the given key has no predecessor.
	 */
	public Pel predecessor(BNode r, Location key){
		
		BNode cont = getNode(r,key);
		int temp = cont.getData().getLocus().compareTo(key);
		
		if (temp == 1){
			while (temp != -1){
				if (cont.parent() != null){
					cont = cont.parent(); 
					temp = cont.getData().getLocus().compareTo(key);}
				else 
					return null;
			}
			return cont.getData();
		} else {
			if (temp == 0){
				if (cont.leftChild() != null)
					return SN(cont).getData();
				else 
					while (temp != -1){
						if (cont.parent() != null){
							cont=cont.parent(); 
							temp = cont.getData().getLocus().compareTo(key);}
						else 
							return null;
					}
				return cont.getData();
			} else {
				return cont.getData();
	}}}

	/*
	 * Returns the Pel object in the tree with the smallest key. 
	 * Throws an EmptyTreeException if the tree does not contain any data
	 */
	public Pel smallest(BNode r) throws EmptyTreeException {
		
		if((r.isLeaf()) && (r.getData() == null)){
			throw new EmptyTreeException("Null");
		} else {
			while (!r.isLeaf()){
				if (r.leftChild() != null)
					r = r.leftChild();
				else 
					break;}
			return r.getData();
	}}

	/*
	 * Returns the Pel object in the tree with the largest key. 
	 * Throws an EmptyTreeException if the tree does not contain any data.
	 */
	public Pel largest(BNode r) throws EmptyTreeException{
		
		if((r.isLeaf()) && (r.getData() == null)){
			throw new EmptyTreeException("Null");
		} else {
			while (!r.isLeaf()){
				if (r.rightChild() != null)
					r = r.rightChild();
				else 
					break;}
			return r.getData();
	}}
	
	/*
	 * Returns the root of the binary search tree
	 */
	public BNode getRoot(){
		return root_node;
	}
	
	private BNode getNode(BNode r, Location key){
		
		int temp = r.getData().getLocus().compareTo(key);
		
		if ((r.isLeaf()) && (temp != 0)) {
			return r;
			}
		else 
			if (temp == 1)
				if (r.leftChild()==null){
					return r;
					}
				else 
					return getNode(r.leftChild(),key);
		else 
			if (temp == 0)
				return r;
		
		
		if (r.rightChild()==null){
			return r;
			} else {
			return getNode(r.rightChild(),key);
			}
		
	}

	/*
	 * Private void class helping set up nodes by checking if they are null
	 */
	private void setNode(BNode temp1, BNode temp2){
		
		if (temp2 == null){
			temp1.setContent(null);
			temp1.setLeftChild(null);
			temp1.setRightChild(null);
			temp1.setParent(null);
		} else {
			temp1.setContent(temp2.getData());
			temp1.setLeftChild(temp2.leftChild());
			temp1.setRightChild(temp2.rightChild());
			temp1.setParent(temp2.parent());
	}}
	
	/*
	 * Private helper class for smallest node used throughout program
	 */
	private BNode SN(BNode r){
		
		if((r.isLeaf()) && (r.getData() == null)){
			return r;
		} else {
			while (!r.isLeaf()){
				if (r.leftChild() != null)
					r = r.leftChild();
				else 
					break;}
			return r;
	}}
	
	/*
	 * Private helper class for largest node used throughout program
	 */
	private BNode LN(BNode r){
		
		if (r == null)
			return r;
		if((r.isLeaf()) && (r.getData() == null)){
			return r;
		} else {
			while (!r.isLeaf()){
				if (r.rightChild() != null)
					r = r.rightChild();
				else 
					break;}
			return r;
	}}
}
