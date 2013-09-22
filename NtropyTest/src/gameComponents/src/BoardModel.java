package gameComponents.src;


public class BoardModel {

	// Container for all the squares
	private Square [][] squares;

	// The size of the grid
	private int size;
	// Default grid size
	private static final int DEFAULT_SIZE = 6;

	// The number of symbols in a row which Order must get to win
	private int orderedSymbolsRequired;

	// The number of possible "directions" in which Order could win
	private int orderedWinPossibilities;

	//A number representing the winner of the game. 1 if order, -1 if chaos, 0 if otherwise
	private int winner;

	//Represents whether the board should be checked for a winner, or if a check has been performed, and no move has
	// yet been made.
	private boolean canCheck;


	/**
	 * A constructor for objects of the BoardModel class.
	 * Creates a grid-based model for keeping track of gameplay.
	 * @param n The size of the n*n grid, n > 3.
	 */
	public BoardModel(int size) {
		if(size <= 3) {
			throw new IllegalArgumentException("Board must be at least 4 x 4");
		} else { 
			this.size = size;
			orderedSymbolsRequired = size - 1;
			orderedWinPossibilities = 2 * size + 6;
			winner = 0;
			squares = new Square[size][size];
			fillGridEmpty();
		}
	}

	/**
	 * A convenience constructor of the BoardModel class.
	 * Creates a grid-based model for keeping track of gameplay.
	 * The grid is of default size.
	 */
	public BoardModel() {
		this(DEFAULT_SIZE);
	}

	/**
	 * A method for changing the value of a square. Only changes the square if it isn't
	 * already marked.
	 * @param row An int corresponding to the row number of the square in the grid.
	 * @param col An int corresponding to the col number of the square in the grid.
	 * @param n An int that is expected to be 0 or 1, each representing the corresponding marking.
	 */
	public void changeSquare(int row, int col, int state) {
		if(state == 0)
			changeSquare(row, col, Square.State.ZERO);
		if(state == 1)
			changeSquare(row, col, Square.State.ONE);
	}

	/**
	 * A method for changing the value of a square. Only changes the square if it isn't
	 * already marked.
	 * @param row An int corresponding to the row number of the square in the grid.
	 * 		Must be in the range of zero to grid size - 1.
	 * @param col An int corresponding to the col number of the square in the grid.
	 * 			Must be in the range of zero to grid size - 1.
	 * @param n A State representing the desired state of the square at the specified location.
	 */
	public void changeSquare(int row, int col, Square.State state) {
		if(isValidSquare(row, col)) {
			Square square = squares[row][col];
			if(!square.isMarked()) {
				square.changeState(state);
			}
		} else {
			throw getIllegalException();
		}
	}

	/**
	 * Returns the state of the square in the specified location. 
	 * @param row An int corresponding to the row number of the square in the grid.
	 * 		Must be in the range of zero to grid size - 1.
	 * @param col An int corresponding to the column number of the square in the grid.
	 * 		Must be in the range of zero to grid size - 1.
	 * @return The state of the square. 
	 * @throws InvalidArgumentException if 
	 */
	public Square.State getState(int row, int col) {
		if(isValidSquare(row, col)) {
			return squares[row][col].getState();
		} else {
			throw getIllegalException();
		}
	}

	/**
	 * Returns an integer corresponding to the winner of the game. 
	 * @return An int representing the winner of the game. Returns 0 if there is no winner,
	 * 1 if the winner is order, or -1 if the order is chaos.
	 */
	public int getWinner() {
		if(canCheck) {
			for(int row = 0; 0 < size && orderedWinPossibilities > 0 && winner != 1; row++) {
				checkDirection(row, 0, 0, 1);
			}
			for(int col = 0; 0 < size && orderedWinPossibilities > 0 && winner != 1; col++) {
				checkDirection(0, col, 1, 0);
			}
			checkDirection(1, 0, 1, 1);
			checkDirection(0, 0, 1, 1);
			checkDirection(1, 0, 1, 1);
			checkDirection(0, size - 2, 1, -1);
			checkDirection(0, size -1, 1, -1);
			checkDirection(1, size - 1, 1, -1);
		}
		return winner;
	}


	/*
	 * Checks a given direction to check to see if Order has won the row or still can. Updates
	 * instance fields to more easily track changes to the Board's state.
	 * @param row An int representing a starting square's row number
	 * @param col An int representing a starting square's column number
	 * @param rowDelta An int representing by what the row number should be incremented
	 * @param colDelta An int representing by what the col number should be incremented
	 * @return an int representing whether or not order has or can win this direction. -1 if
	 * Order can't win, 0 if order can still win, and 1 if order has won.
	 */
	private int checkDirection(int row, int col, int rowDelta, int colDelta) {
		int countOnes = 0;
		int countZeroes = 0;
		int check = 0;
		while(isValidSquare(row, col) && check == 0) {
			Square square = squares[row][col];
			Square.State state = square.getState();
			if(state.equals(Square.State.ZERO))
				countZeroes++;
			else if(state.equals(Square.State.ONE))
				countOnes++;
			if(countZeroes >= 2 && countOnes >= 2)
				check = -1;
			if(countZeroes >= orderedSymbolsRequired || countOnes >= orderedSymbolsRequired)
				check = 1;
			row += rowDelta;
			col += colDelta;
		}
		if(check == 1)
			winner = 1;
		else if(check < 0)
			orderedWinPossibilities--;
		if(orderedWinPossibilities <= 0)
			winner = -1;
		return check;
	}
	/*
	 * Iterates through squares assigning a new square to each slot.
	 * Expected to be called only by the constructor.
	 */
	private void fillGridEmpty() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				Square square = new Square();
				squares[i][j] = square;
			}
		}
	}
	/*
	 * Returns true if there exists a square in the specified location
	 * @param row An int corresponding to the row number of the square in question
	 * @param col An int corresponding to the column number of the square in question
	 */
	private boolean isValidSquare(int row, int col) {
		return(0 <= row && 0 <= col && row < size && col < size);
	}

	private IllegalArgumentException getIllegalException() {
		return new IllegalArgumentException("Row and Column numbers must be between zero (inclusive)" +
				"and grid size (exclusive)");
	}
}
