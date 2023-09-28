/**
 * This class implements a dictionary using a hash table in which collisions are resolved using separate chaining.
 * The hash table will store objects of the class Record.
 * @author Izum Adnan
 */
public class Dictionary implements DictionaryADT{
	
	// Private instance variables
	private int size;
	private int countRecords;
	private Node[] hashmap;

/**
 * Constructor Class
 * @param size
 */
public Dictionary(int size){
	this.size=size;
	this.countRecords = 0;
	this.hashmap = new Node[size];
	}

/**
 * Method which Inserts the given Record object referenced by rec in the dictionary. 
 */
public int put(Record rec)throws DuplicatedKeyException{
	
	int position = Hashget(rec.getKey());
	Node set = hashmap[position];
	
	if (set == null) {
		hashmap[position] = null;
		hashmap[position] = new Node();}
	
	Node pos = hashmap[position];
	
	if (pos.getElement() == null){
		pos.setElement(rec);
		countRecords++;
		return 0;
		}
	
		if (pos.getElement().getKey().equals(rec.getKey())){
			throw new DuplicatedKeyException(" ");}
		else{
			while (pos.getNext()!=null) {
				if (pos.getElement().getKey().equals(rec.getKey()))
					throw new DuplicatedKeyException(" ");
				pos=pos.getNext();}
		}
	
		pos.setNext(new Node());
		pos = pos.getNext();
		pos.setElement(rec);
		countRecords++;
		return 1;
}
	
/**
 * Method which removes the record object containing string key from the dictionary. 
 */
public void remove(String key)throws InexistentKeyException{
	
	int position = Hashget(key);
	
	if (hashmap[position] == null) {
		throw new InexistentKeyException(" Empty ");
	} else {
			if (hashmap[position].getElement().getKey().equals(key)) {
				if (hashmap[position].getNext() == null) {
					hashmap[position] = hashmap[position].getNext();
					countRecords--;
					return;
					} else {
						if (hashmap[position].getNext() != null) {
						hashmap[position]=hashmap[position].getNext();
			}}} else {
					while (hashmap[position].getNext() != null) {
						if (hashmap[position].getNext().getElement().getKey().equals(key)) {
							hashmap[position].setNext(hashmap[position].getNext());
							if (hashmap[position].getNext().getNext() != null) {
								hashmap[position].setNext(hashmap[position].getNext());
								} if (hashmap[position].getNext().getNext() == null) {
									hashmap[position].setNext(null);
								} else {
									hashmap[position] = hashmap[position].getNext();
								}
					}
				if (hashmap[position].getNext() == null) throw new InexistentKeyException(" Empty ");}}}
}

/**
 * A method which returns the Record objects stored in the hash table containing the given key value, 
 * or null if no Record object stored in the hash table contains the given key value.
 */
public Record get(String key) {
	
	int position=Hashget(key);
	Node set = hashmap[position];
	
	if (set!=null) {
		while (set.getElement()!=null) {
			if (set.getElement().getKey().equals(key)) {
				return set.getElement();
			} else {
				if(set.getNext()!=null) {
				set=set.getNext();
				} else {
				break;
				}
			}}}
	
	return null;
}


/**
 * Returns the number of Record objects stored in the hash table. 
 * @return countRecords
 */
public int numRecords()	{
	return countRecords; 
}

/**
 * This initializes a dictionary with an empty hash table of the specified size. 
 * @param key
 * @return
 */
private int Hashget(String key) {
	
	float change = 0;
	int prime_1 = 33;
	float s = size;
	
	for (int i = 0; i<key.length(); i++) {
		change += ((int)key.charAt(i) * Math.pow(prime_1, i))%s;
	}
	
	return (int)(change % s);
}
}
