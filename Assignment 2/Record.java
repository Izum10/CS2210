/**
 * This class represents the records that will be stored in the hash table, implemented in the class, Dictionary.java
 * @ Izum Adnan
 */
public class Record {
	
	// Private Instance Variables
	private String key;
	private int score;
	private int level;

/**
* The constructor method for the class
 * @param key
 * @param score
 * @param level
 */
public Record(String key, int score, int level) {
	this.key=key;
	this.score=score;
	this.level=level;
}
	
/**
 * Returns the string stored in this Record object
 * @return key
 */
public String getKey() {
	return key;		
}

/**
 * Returns the first integer stored in this record object
 * @return score
 */
public int getScore() {
	return score;
}

/**
 * Returns the second integer stored in this record object
 * @return level
 */
public int getLevel() {
	return level;
}
}