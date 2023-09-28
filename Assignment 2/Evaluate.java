/**
 * This class implements all the auxiliary methods needed by the algorithm that plays the game. 
 * @author Izum Adnan
 */

public class Evaluate {
	
	// Private Instance Variables
	private char gameBoard [][];
	private int tilesToWin;
	private int size;

/**
 * Constructor Method
 * @param size
 * @param tilesToWin
 * @param MaxLevels
 */
public Evaluate(int size, int tilesToWin, int MaxLevels) {
	
	this.gameBoard = new char[size][size];
	this.tilesToWin = tilesToWin;
	this.size = size;
	
	for (int i = 0; i < size; i++) {
		for (int j = 0; j<gameBoard[i].length; j++) {
			gameBoard[i][j] = 'e';
}}}

/**
 * This method returns an empty Dictionary of the size that you have selected. 
 * @return dict
 */
public Dictionary createDictionary() {
	
	Dictionary dict = new Dictionary(6967);
	return dict;
}

/**
 * This method first represents the content of the two dimensional array gameboard as a string, 
 * then it checks whether there is a record in dict with this string as key attribute. 
 * @param dict
 * @return
 */
public Record repeatedState(Dictionary dict) {
	
	String state = "";
	
	for (int i = 0; i<size; i++) {
		for (int j = 0; j<gameBoard[i].length; j++) {
			state += gameBoard[i][j];
	}}
	return dict.get(state);
}

/**
 * This method first represents the content of gameBoard as a string, 
 * then it creates an object of the class Record storing this string, score, and level
 * it then finally records with the same key attribute. 
 * @param dict
 * @param score
 * @param level
 */
public void insertState(Dictionary dict, int score, int level) {
	String state="";
	
	for (int i=0;i<size;i++) {
		for (int j=0; j<gameBoard[i].length; j++) {
			state+=gameBoard[i][j];
	}}
	
	Record record = new Record(state,score,level);
	dict.put(record);
}

/**
 * This method stores symbol in gameboard[row][col].
 * @param row
 * @param col
 * @param symbol
 */
public void storePlay(int row, int col, char symbol) {
	gameBoard[row][col]=symbol;
}

/**
 * This method returns true if gameboard is 'e'; otherwise returns false. 
 * @param row
 * @param col
 * @return
 */
public boolean squareIsEmpty (int row, int col) {
	if (gameBoard[row][col] == 'e') {
		return true;
	} else {
		return false;
	}
}

/**
 * This method returns true if gameboard is 'c'; otherwise returns false. 
 * @param row
 * @param col
 * @return
 */
public boolean tileOfComputer (int row, int col) {
	if (gameBoard[row][col]=='c'){
		return true;
	} else {
		return false;
	}
}

/**
 * This method returns true if gameboard is 'h'; otherwise returns false. 
 * @param row
 * @param col
 * @return
 */
public boolean tileOfHuman(int row, int col) {
	if (gameBoard[row][col]=='h'){
		return true;
	} else {
		return false;
	}
}

/**
 * This method returns true if there are the required number of adjacent tiles of type symbol in the same direction of gameboard; 
 * Otherwise returns false
 * @param symbol
 * @return
 */
public boolean wins (char symbol) {
	
	int row=0;
	
	for (int i = 0;  i<size; i++) {
		row = 0;
		for (int j = 0; j<gameBoard[i].length; j++) {
			if (row == tilesToWin) {
				return true;
			} if (gameBoard[i][j] != symbol) {
				row = 0;
			} if (gameBoard[i][j] == symbol) {
				row += 1;
		}}}
	
	for (int i = 0; i<size; i++) {
		row=0;
		for (int j = 0; j<gameBoard[i].length;  j++) {
			if (row == tilesToWin) {
				return true;
			} if (gameBoard[j][i]==symbol) {
				row += 1;
			} if (gameBoard[j][i]!=symbol) {
				row = 0;
			}}}
	
	int i =- 1;
	
	while (i != size-1) {
		i++;
		int j = 0, a = i;
		row = 0;
		while (a != size) {
			if (gameBoard[a][j] == symbol) {
				row += 1;
			} if (gameBoard[a][j] != symbol) {
				row = 0;
			} if (row == tilesToWin) {
				return true;
			}
			a++;
			j++;
		}}
	
	while (i != size-1) {
		i++;
		int j = 0, a = i;
		row = 0;
		while (a != size) {
			if (gameBoard[j][a] == symbol) {
				row += 1;
			} if (gameBoard[j][a] != symbol) { 
				row = 0;
			} if (row == tilesToWin) { 
				return true;
			}
			a++;
			j++;
		}}
	
	i = gameBoard.length;
	
	while (i != 0) {
		i--;
		int j = 0, a = i;
		row = 0;
		while (a != -1) {
			if (gameBoard[a][j] == symbol) {
				row += 1;
			} if (gameBoard[a][j]  != symbol) {
				row = 0;
			} if (row == tilesToWin) {
				return true;
			}
			a--;
			j++;
		}}
	
	i=-1;
	row=0;
	
	while (i != size) {
		i++;
		int j = size-1, a=i;
		row = 0;
		while (a != size) {
			if (gameBoard[a][j] == symbol) {
				row += 1;
			} if (gameBoard[a][j] != symbol) {
				row = 0;
			} if (row == tilesToWin) { 
				return true;
			}
			a++;
			j--;
		}}
	return false;
}

/**
 * Returns true if there are no empty positions left in gameboard; otherwise it returns false
 * @return
 */
public boolean isDraw() {
	for (int i=0;i<size;i++) {
		for (int j=0;j<gameBoard[i].length;j++) {
			if (gameBoard[i][j]=='e')
				return false;
	}} return true;
}

/**
 * Returns one of 3 values
 * - 3, if the computer has won
 * - 0, if the human player has won
 * 2, if the game is a draw
 * 1, if the game is still undecided
 * @return
 */
public int evalBoard() {
	
	if (wins('c') == true) {
		return 3;
	} else {
		if (wins('h') == true) {
			return 0;
		} else {
			if (isDraw() == true){
				return 2;
			} else {
				return 1;
			}
		}
	}

}}
