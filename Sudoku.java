//Author: Anthony Panisales

import java.util.Random;

//Class Purpose: Produces a 9x9 Sudoku game board
public class Sudoku {
	
	protected int[][] board = new int[9][9];
	Random randomNumber = new Random();

	//Function Purpose: Fills the Sudoku board with numbers
	protected boolean fillBoard(int row, int column) {
		//Base Case
		if (row == 9) {
			return true;
		} else { //Step Case
			/*Each spot in the first row has a random number
			 * from 1 to 9
			 */
			if (row == 0) {
				int num = randomNumber.nextInt(9) + 1;
				while (!safeToPlace(row, column, num)) {
					num = randomNumber.nextInt(9) + 1;
				}
				if (column != 8) {
					board[row][column] = num;
					if (fillBoard(row, column+1)) {
						return true;
					}
				} else {
					board[row][column] = num;
					if (fillBoard(row+1, 0)) {
						return true;
					} 
				}
				return false;
			} else { //Each row after the first reacts to the rows above it
				int[] sudokuNums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
				for (int s = 0; s < sudokuNums.length; s++) {
					/*If a number is not safe to place then the number that follows
					 * it in the array sudokuNums will be checked
					 */
					if (safeToPlace(row, column, sudokuNums[s])) {
						board[row][column] = sudokuNums[s];
						if (column != 8) {
							if (fillBoard(row, column+1)) {
								return true;
							}
						} else {
							if (fillBoard(row+1, 0)) {
								return true;
							} 
						}
					}
				}
				return false;
			}
		}
	}
	
	/*Function Purpose: Checks if a specific number is safe to
	 * be placed at a specific spot on the board
	 */
	protected boolean safeToPlace(int row, int column, int num) {
		//Check up
		if (row != 0) {
			for (int i = 1; i <= row; i++) {
				if (board[row-i][column] == num) {
					return false;
				}
			}
		}
		
		//Check left
		if (column != 0) {
			for (int i = 1; i <= column; i++) {
				if (board[row][column-i] == num) {
					return false;
				}
			}
		}
		
		//Check left up diagonal
		if (column != 0 && column != 3 && column != 6) {
			if (row == 2 || row == 5 || row == 8) {
				if (column == 2 || column == 5 || column == 8) {
					for (int i = 1; i <= 2; i++) {
						for(int j = 1; j <=2; j++) {
							if (board[row-i][column-j] == num) {
								return false;
							}
						}
					}
				} else {
					for (int i = 1; i <= 2; i++) {
						if (board[row-i][column-1] == num) {
							return false;
						}
					}
				}
			} else if (row == 1 || row == 4 || row == 7) {
				if (column == 2 || column == 5 || column == 8) {
					for (int i = 1; i <= 2; i++) {
						if (board[row-1][column-i] == num) {
							return false;
						}
					}
				} else {
					if (board[row-1][column-1] == num) {
						return false;
					}
				}
			}
		}
		
		//Check right up diagonal
		if (row != 0 && column != 2 && column != 5 && column != 8) {
			if (row == 2 || row == 5 || row == 8) {
				if (column == 0 || column == 3 || column == 6) {
					for (int i = 1; i <= 2; i++) {
						for(int j = 1; j <=2; j++) {
							if (board[row-i][column+j] == num) {
								return false;
							}
						}
					}
				} else {
					for (int i = 1; i <= 2; i++) {
						if (board[row-i][column+1] == num) {
							return false;
						}
					}
				}			
				
			} else if (row == 1 || row == 4 || row == 7) {
				if (column == 0 || column == 3 || column == 6) {
					for (int i = 1; i <= 2; i++) {
						if (board[row-1][column+i] == num) {
							return false;
						}
					}
				} else {
					if (board[row-1][column+1] == num) {
						return false;
					}
				}
			}
		}
		return true;
	}
	

	//Function Purpose: Prints the Sudoku game board
	protected void printBoard() {
		System.out.print(" -----------------------" + "\n" + "| ");
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if (column != 0 && column%8 == 0) {
					if (row == 2 || row == 5) {
						System.out.print(board[row][column] + " |" + "\n");
						System.out.print(" -----------------------" + "\n" + "| ");
					} else if (row == 8) {
						System.out.print(board[row][column] + " |" + "\n" + " -----------------------");
					} else {
						System.out.print(board[row][column] + " |" + "\n" + "| ");
					}
				} else {
					System.out.print(board[row][column] + " ");
					if (column == 2 || column == 5) {
						System.out.print("| ");
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		
		sudoku.fillBoard(0, 0);
		sudoku.printBoard();
	}

}
