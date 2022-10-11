import java.util.Random;

public class Board {

	private int[][] board; // holds state of game
	private Random rnd = new Random(0); // setup random # generator
	private int tilesOccupied;
	
	/* default constructor for board */
	// constructors must match exactly the name
	// of the class.
	public Board() {

		// instantiate the board
		//int[][] board = new int[4][4];
		
		board = new int[4][4];
		
		board[1][1] = 2;
		board[2][2] = 2;
		
		
		//call helper method populatOne 2 times;)
	}

	/*
	 * return a String representation of the 2D array board
	 * 
	 * each row should be in its own line
	 * 
	 * Example:
	 * 
	 * { {1, 2, 3}, {4, 5, 6}} -> 1 2 3
	 * 
	 * 4 5 6
	 */

	
	//overriding a method is when a "child" class
	//implements the exact same method that its parent class has
	public String toString() {
		
		/*
		 * Use the String formatter to pad the numbers with leading 0s
		 * so that the print out does not become jagged
		 * An example is shown below. 
		 * String str = String.format("%04d", 9);  // 0009  
		 * int x = 30;
		 * System.out.println(String.format("%04d",x));
		 *     
		 */
		//setup loop to visit every spot possible
		String str = "";
		for(int r =0; r< board.length; r++) {
			for(int c=0; c< board[0].length; c++) {
				
				str += String.format("%04d", board[r][c]);
				str += " ";
				
				
			}
			str += "\n";
		}
		
	
		return str;
	}

	/*
	 * set one of the empty spaces (at random)
	 * 
	 * to a 2 or 4 (90/10 chance). an empty spot is defined to be a 0 element
	 * 
	 * Must use the Random class object rnd.
	 * 
	 * Example Use of rnd object.
	 * 
	 * int randomNum = rnd.nextInt(10); //returns a number in range [0 10) (not
	 * inclusive on the 10)
	 */

	public void populateOne() {
		
		int rand = 0;
	
		
		int spacePickerR = 0;
		int spacePickerC = 0;
		
		int counter = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(board[i][j] != 0) {
					counter++;
				}
			}
		}
	
		if(counter<16) {
		while(true) {
		
			 rand = rnd.nextInt(10);
			
			
			 spacePickerR = rnd.nextInt(4);
			 spacePickerC = rnd.nextInt(4);
		
		
		if(board[spacePickerR][spacePickerC] == 0) {
			if(rand == 3) {
				board[spacePickerR][spacePickerC] = 4;
				System.out.println("GOT4");
				break;			
			} else {
				board[spacePickerR][spacePickerC] = 2;
				System.out.println("GOT2");
				break;
			}
	
			
		
		}	
		}
		
		
		
			
		}

	}

	/*
	 * 
	 * Given an array of integers, slide all non-zero elements to the right.
	 * 
	 * zero elements are considered open spots.
	 * 
	 * example:
	 * 
	 * [0 2 0 2]->[0 0 2 2]
	 * 
	 * [2 0 0 2]->[0 0 2 2]
	 * 
	 * [4 0 0 0]->[0 0 0 4]
	 */

	public void slideRight(int[] row) {

		//find the first open left-most spot (a zero element)
				//then find the first non-zero element and swap
				for(int i = row.length-1; i >= 0; i--) {
					
					//check if spot is open
					if(row[i]==0) {
						//open! find the next non-zero element!
						for(int j = i - 1; j>=0; j--) {
							if(row[j]!=0) {
								//Swap element j and i
								int temp = row[i];
								row[i] = row[j];
								row[j] = temp;
								break;
								
							}
						}
					}
					
				}
		
	}

	/*
	 * 
	 * Move the numbers as far to the right as they can go
	 * 
	 * aka the numbers are trying to move to the right-most
	 * 
	 * empty spaces. This method must utilize the slideRight(int[] row) method
	 * 
	 * must utilize the helper method above for full credit.
	 * 
	 * param: a valid row of 2048 where 0s are "empty" spots
	 * 
	 * effect: row is modified so all numbers are to the right side
	 * 
	 * return: none
	 */

	public void slideRight() {

		// go through 2D array, move all digits as far right as possible

		// you should not overwriting values

		for(int r = board.length-1; r >=0; r--) {

			slideRight(board[r]);
					
		
		}
		
		
	}

	/**
	 * Given an array of integers, slide all non-zero elements to the left.
	 * 
	 * zero elements are considered open spots.
	 * 
	 * example:
	 * 
	 * [0 2 0 2] -> [2 2 0 0]
	 * 
	 * [2 0 0 2] -> [2 2 0 0]
	 */

	public void slideLeft(int[] arr) {

		//find the first open left-most spot (a zero element)
		//then find the first non-zero element and swap
		for(int i = 0; i < arr.length; i++) {
			
			//check if spot is open
			if(arr[i]==0) {
				//open! find the next non-zero element!
				for(int j = i + 1; j<arr.length; j++) {
					if(arr[j]!=0) {
						//Swap element j and i
						int temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
						break;
						
					}
				}
			}
			
		}
		
	}

	/*
	 * Slide all the numbers to the left so that
	 * 
	 * all of the empty spaces are on the right side
	 */

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public void slideLeft() {
		
			for(int r = 0; r < board.length; r++) {

				slideLeft(board[r]);
						
			
			}
			
		}
		
		

		//grabbing a row from a 2D array
		//if it's call arr then arr[i] grabs ONE row!
		
		//visit every single row in the 2D array
		//call the slideLeft method that takes in one argument
		
		
		
	

	/**
	 * Given a 2D array and a column number, return a 1D array representing the
	 * elements in the given column number.
	 */

	//int[][] tempBoard = new int[4][4];
	
	public int[] getCol(int[][] data, int c) {
		
		
		
		int[] tempCol = new int[4];
		
		for(int i = 0; i < 4; i++) {
			tempCol[i] = data[i][c];
		}
		
		System.out.println("tempcol");
					
				return tempCol;
				
		
	}

	/**
	 * Given an array of integers, slide all non-zero elements to the top.
	 * 
	 * zero elements are considered open spots.
	 */
	
	//int[] tempArr = getCol;
	
	
	public void slideUp(int c) {

		int[] arr = getCol(board, c);
	
		
		for(int i = 0; i < arr.length; i++) {
					
					//check if spot is open
					if(arr[i]==0) {
						//open! find the next non-zero element!
						for(int j = i + 1; j<arr.length; j++) {
							if(arr[j]!=0) {
								//Swap element j and i
								int temp = arr[i];
								arr[i] = arr[j];
								arr[j] = temp;
								break;
								
							}
						}
					}
					
				}

		putCol(arr, c);
		
	
		
		//find the first open left-most spot (a zero element)
				//then find the first non-zero element and swap
		
	
		
		
		
		
	}

public int[] putCol(int[] col, int c) {

	
		for(int i = 0; i < 4; i++) {
			board[i][c] = col[i];
		}
		
		System.out.println("actcol");
					
				return col;
}
		
	/*
	 * 
	 * Slide all elements in the board towards the top.
	 * 
	 * You must use slideUp and getCol for full credit.
	 */

	public void slideUp() {

		//visit every column index
		//grab each column as an aarray using getCol -> keep track of it in a 1d array
		//variable/refereence
		//copy over the 1d array representatin of the column
		//back to the 2d board array
		
		for(int c = 0; c < board.length; c++) {

			slideUp(c);		
		
		}
		
		
		
		
	}

	public void slideDown(int c) {

		int[] row = getCol(board, c);
		
		for(int i = row.length-1; i >= 0; i--) {
			
			//check if spot is open
			if(row[i]==0) {
				//open! find the next non-zero element!
				for(int j = i - 1; j>=0; j--) {
					if(row[j]!=0) {
						//Swap element j and i
						int temp = row[i];
						row[i] = row[j];
						row[j] = temp;
						break;
						
					}
				}
			}
			
		}
		putCol(row, c);
	}

	/*
	 * slide all the numbers down so that any
	 * 
	 * empty space is at the top
	 * 
	 * You must use slideDown and getCol for full credit.
	 */

	public void slideDown() {

		for(int c = 0; c < board.length; c++) {
			
			slideDown(c);
			
		}
		
		
	}

	/*
	 * Given the 2D array, board, combineRight will take adjacent numbers that
	 * are the same and combine them (add them).
	 * 
	 * After adding them together, one of the numbers is zeroed out. For
	 * example, if row 0 contained [0 0 4 4],
	 * 
	 * a call to combineRight will produce [0 0 0 8]. If row 1 contained [2 2 2
	 * 2], a call to combineRight will
	 * 
	 * produce [0 4 0 4].
	 * 
	 * Notice that the left element is zeroed out.
	 */

	public void combineRight() {

		for(int i = board[0].length-1; i >= 0 ; i--) {
			for(int j = board[i].length-2; j >=0; j--) {
				if(board[i][j+1]==board[i][j] && board[i][j] !=0) {
					board[i][j]=0;
					board[i][j+1] = board[i][j+1]*2;
					
				}
			}
			
		}
		
		
		
	}

	/*
	 * same behavior as combineRight but the right element is zeroed out when
	 * two elements are combined
	 */

	public void combineLeft() {
		
		for(int i = 0; i < board[0].length; i++) {
			for(int j = 1; j < board[i].length; j++) {
				
				if(board[i][j-1]==board[i][j] && board[i][j] !=0) {
					board[i][j]=0;
					board[i][j-1] = board[i][j-1]*2;
					
				}
			}
			
		}
			
	}

	/*
	 * same behavior as combineRight but the bottom element is zeroed out when
	 * two elements are combined
	 */

	public void combineUp() {

		for(int j = 0; j < board.length; j++) {
			for(int i = 1; i <board[j].length; i++) {
				
				if(board[i-1][j]==board[i][j] && board[i][j] !=0) {
					board[i][j]=0;
					board[i-1][j] = board[i-1][j]*2;
					
				}
			}
			
		}
		
	}

	/*
	 * same behavior as combineRight but the top element is zeroed out when two
	 * elements are combined
	 */

	public void combineDown() {
		
		for(int j = board.length-1; j >=0; j--) {
			for(int i = board.length-2; i >=0; i--) {
				
				if(board[i+1][j]==board[i][j] && board[i][j] !=0) {
					board[i][j]=0;
					board[i+1][j] = board[i+1][j]*2;
					
				}
			}
			
		}
		

	}

	
	
	public void left() {
		slideLeft();
		combineLeft();
		slideLeft();
		
	}

	public void right() {
		slideRight();
		combineRight();
		slideRight();
	}

	public void up() {
		slideUp();
		combineUp();
		slideUp();

	}

	public void down() {
		slideDown();
		combineDown();
		slideDown();

	}

	public boolean gameOver() {
		return false;
	}

	public int[][] getBoard() {
		return board;
	}

	// populate with a given 2d array
	public void populate(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				board[r][c] = arr[r][c];
			}
		}
	}

}
