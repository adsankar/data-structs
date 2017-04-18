package checkers;

import java.util.NoSuchElementException;

/**
 * This file contains the methods and fields to create a checkerboard (stored in
 * a two dimensional array of Piece values). It contains methods for checking if
 * a move is valid and for making the moves.
 * 
 * @author Aleksander Sankar
*/
public class Checkers {

	// the contents of the board are held in a 2D array of Piece values
	private Piece[][] c = new Piece[8][8];// 8 x 8 board size

	/**
	 * Create a new Checkers object, where the values are stored in a two
	 * dimensional array of Piece objects.
	 */
	public Checkers() {
		// nested for loop goes through each space and places an empty piece
		// spaceholder there
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				this.setEntry(i, j, Piece.EMPTYPIECE);
			}
		}
	}

	/**
	 * Copy the contents of one board to the current object. This takes the form
	 * of a deep copy of the board in the parameter.
	 * 
	 * @param otherGame
	 */
	public Checkers(Checkers otherGame) {
		if (otherGame == null) {
			throw new NullPointerException();
		}

		// iterate through the board by rows, then by columns and copy over each
		// entry
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				this.setEntry(i, j, otherGame.getEntry(i, j));
			}
		}
	}

	/**
	 * Set the entry of a specific space on the checkers board
	 * 
	 * @param col
	 *            the column of the board
	 * @param row
	 *            the row of the board
	 * @param piece
	 *            the piece we are placing on the board
	 * @throws NoSuchElementException
	 *             if the space requested is not on the board
	 */
	public void setEntry(int col, int row, Piece piece)
			throws NoSuchElementException {
		if (piece == null) {
			throw new NullPointerException();
		}

		if (col > 8 || col < 1 || row > 8 || row < 1) { // check if the space is
			// within the board and if not, throw an exception
			throw new NoSuchElementException();
		} else {// valid space so set that element of the 2D array to the
				// parameter
			c[col - 1][8 - row] = piece;
		}
	}

	/**
	 * Get the entry of a specific space on the checkers board
	 * 
	 * @param col
	 *            the column of the board
	 * @param row
	 *            the row of the board
	 * @return the piece on that space
	 * @throws NoSuchElementException
	 *             if the space requested is not on the board
	 */
	public Piece getEntry(int col, int row) throws NoSuchElementException {
		if (col > 8 || col < 1 || row > 8 || row < 1) { // check if the space is
			// within the board and if not, throw an exception
			throw new NoSuchElementException();
		} else {// valid space so return the piece at that part of the board
			return c[col - 1][8 - row];
		}
	}

	/**
	 * Convert the board to a string representation, helpful for visualizing the
	 * board. b for black and w for white, while uppercase letters mean that
	 * piece is a king. "-" represents an empty space on the board.
	 */
	public String toString() {
		String board = "";
		// iterate through rows and columns
		for (int i = 8; i >= 1; i--) {
			board += i + " "; // print the row number on the side (beginning of
			// each row)
			for (int j = 1; j <= 8; j++) {
				Piece p = getEntry(j, i);
				if (p.equals(Piece.EMPTYPIECE)) {
					board += "-";
				} else if (p.equals(Piece.BLACKKING)) {
					board += "B";
				} else if (p.equals(Piece.BLACKPIECE)) {
					board += "b";
				} else if (p.equals(Piece.WHITEKING)) {
					board += "W";
				} else if (p.equals(Piece.WHITEPIECE)) {
					board += "w";
				}
			}
			board += "\n";
		}
		board += "  12345678\n";// shows the column number underneath
		return board;
	}

	/**
	 * Compares one board to another and finds if they hold the same values.
	 * 
	 * @param obj
	 */
	public boolean equals(Object obj) {
		boolean equal = true;
		if (obj == null)
			return false;
		if (obj instanceof Checkers) {
			Checkers c = (Checkers) obj;
			// iterate through every space on the board and compare the piece
			// at each space
			for (int i = 1; i <= 8; i++) {
				for (int j = 1; j <= 8; j++) {
					if (!(c.getEntry(i, j).equals(this.getEntry(i, j)))) {
						equal = false; // if the pieces at the corresponding
						// space are not the same, the boards are not equal
					}
				}
			}
		} else {
			// if the object is not a board, they are not equal
			return false;
		}
		return equal;
	}

	// if we implement equals() we must implement hashCode() (this will be
	// covered later)
	public int hashCode() {
		return 0;
	}

	/**
	 * This method goes through several cases and finds out if the move provided
	 * by a starting and ending space is a legal move.
	 * 
	 * @param curCol
	 *            the column we are starting on
	 * @param curRow
	 *            the row we are starting on
	 * @param endCol
	 *            the column we are ending on
	 * @param endRow
	 *            the row we are ending on
	 * @return true if it is a valid move, false if not
	 */
	public boolean validMove(int curCol, int curRow, int endCol, int endRow) {
		// check if both of the spaces are on the board
		if (curCol > 8 || curCol < 1 || curRow > 8 || curRow < 1 || endCol > 8
				|| endCol < 1 || endRow > 8 || endRow < 1) {
			return false;
		}

		// check if there is a piece to move and the final space is empty
		if (this.getEntry(curCol, curRow).equals(Piece.EMPTYPIECE)
				&& !(this.getEntry(endCol, endRow).equals(Piece.EMPTYPIECE))) {
			return false;
		}

		// check if the spaces are black spaces
		// mathematically, all black squares have even numbers as the sum of
		// their row and column numbers
		if ((curCol + curRow) % 2 == 1 && (endCol + endRow) % 2 == 1) {
			return false;
		}

		// check to see if the piece is not making a diagonal move
		if (curCol == endCol || curRow == endRow) {
			return false;
		}

		// check to see if the piece is moving too far
		if (Math.abs(curRow - endRow) > 2 || Math.abs(curCol - endCol) > 2) {
			return false;
		}

		// we have established that the piece is not out of bounds
		Piece p = this.getEntry(curCol, curRow);

		// if the piece is making a non-jumping move
		if (Math.abs(endCol - curCol) == 1) {
			if (p.isBlack() && (curRow - endRow) == 1) {
				return true; // simple black move
			}
			if (p.isWhite() && (endRow - curRow) == 1) {
				return true; // simple white move
			}
		}

		// if the piece is making a jump
		if (Math.abs(endCol - curCol) == 2) {
			// make sure the piece it is jumping over is of the opposite color
			if (p.isBlack()
					&& (curRow - endRow) == 2
					&& this.getEntry((curCol + endCol) / 2,
							(curRow + endRow) / 2).isWhite()) {
				return true; // black jumps over white
			}
			if (p.isWhite()
					&& (endRow - curRow) == 2
					&& this.getEntry((curCol + endCol) / 2,
							(curRow + endRow) / 2).isBlack()) {
				return true; // white jumps over black
			}
		}
		// special moves (backwards movements) that only kings can do
		if (p.isKing()) {
			if (Math.abs(endCol - curCol) == 1) {
				if (p.isBlack() && (curRow - endRow) == -1) {
					return true; // black king only move
				}
				if (p.isWhite() && (endRow - curRow) == -1) {
					return true; // white king only move
				}
			}
			if (Math.abs(endCol - curCol) == 2) {
				if (p.isBlack()
						&& (curRow - endRow) == -2
						&& this.getEntry((curCol + endCol) / 2,
								(curRow + endRow) / 2).isWhite()) {
					return true; // black king jumps over white
				}
				if (p.isWhite()
						&& (endRow - curRow) == -2
						&& this.getEntry((curCol + endCol) / 2,
								(curRow + endRow) / 2).isBlack()) {
					return true; // white king jumps over black
				}
			}
		}

		return false;
	}

	/**
	 * This method actually carries out the moves and modifies the spaces on the
	 * board accordingly.
	 * 
	 * @param curCol
	 *            the column we are starting on
	 * @param curRow
	 *            the row we are starting on
	 * @param endCol
	 *            the column we are ending on
	 * @param endRow
	 *            the row we are ending on
	 * @return true if the move was successful, false if not
	 */
	public boolean move(int curCol, int curRow, int endCol, int endRow) {
		if (validMove(curCol, curRow, endCol, endRow)) {
			Piece p = this.getEntry(curCol, curRow);

			// forward black piece move
			if (p.isBlack() && curRow - endRow == 1) {
				// the space we were on before is now unoccupied
				this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
				// maintain rank as a king or crown the piece if it goes to the
				// appropriate row
				if (p.isKing() || endRow == 1) {
					this.setEntry(endCol, endRow, Piece.BLACKKING);
				} else {
					this.setEntry(endCol, endRow, Piece.BLACKPIECE);
				}
				return true;
			}

			// forward white piece move
			if (p.isWhite() && endRow - curRow == 1) {
				this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
				// maintain rank as a king or crown the piece if it goes to the
				// appropriate row
				if (p.isKing() || endRow == 8) {
					this.setEntry(endCol, endRow, Piece.WHITEKING);
				} else {
					this.setEntry(endCol, endRow, Piece.WHITEPIECE);
				}
				return true;
			}

			// jumps
			if (Math.abs(endCol - curCol) == 2) {
				// check that the piece we are jumping over is of the opposite
				// color
				if (p.isBlack()
						&& (curRow - endRow) == 2
						&& this.getEntry((curCol + endCol) / 2,
								(curRow + endRow) / 2).isWhite()) {
					// move piece to correct spot and remove the piece it
					// jumped over
					this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
					this.setEntry((curCol + endCol) / 2, (curRow + endRow) / 2,
							Piece.EMPTYPIECE);
					// maintain rank as a king or crown the piece if it goes
					// to the appropriate row
					if (p.isRegularPiece() && p.isBlack() && endRow == 8) {
						this.setEntry(endCol, endRow, Piece.BLACKKING);
					} else {
						this.setEntry(endCol, endRow, Piece.BLACKPIECE);
					}
					return true; // black jumps over white
				}
				// similar procedure for a white piece
				if (p.isWhite()
						&& (endRow - curRow) == 2
						&& this.getEntry((curCol + endCol) / 2,
								(curRow + endRow) / 2).isBlack()) {
					this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
					this.setEntry((curCol + endCol) / 2, (curRow + endRow) / 2,
							Piece.EMPTYPIECE);
					if (p.isRegularPiece() && p.isWhite() && endRow == 8) {
						this.setEntry(endCol, endRow, Piece.WHITEKING);
					} else {
						this.setEntry(endCol, endRow, Piece.WHITEPIECE);
					}
					return true; // white jumps over black
				}
			}

			// These are moves specific to kings (moving the opposite direction
			// of its color)
			if (p.isKing()) {

				// white king move
				if (p.isKing() && p.isWhite() && curRow - endRow == 1) {
					this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
					this.setEntry(endCol, endRow, Piece.WHITEKING);
					return true;
				}

				// black king move
				if (p.isKing() && p.isBlack() && endRow - curRow == 1) {
					this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
					this.setEntry(endCol, endRow, Piece.BLACKKING);
					return true;
				}

				// backwards jumps for kings only
				if (Math.abs(endCol - curCol) == 2) {
					if (p.isBlack()
							&& (curRow - endRow) == -2
							&& this.getEntry((curCol + endCol) / 2,
									(curRow + endRow) / 2).isWhite()) {
						this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
						this.setEntry((curCol + endCol) / 2,
								(curRow + endRow) / 2, Piece.EMPTYPIECE);
						this.setEntry(endCol, endRow, Piece.BLACKKING);
						return true; // black king jumps over white
					}
					if (p.isWhite()
							&& (endRow - curRow) == -2
							&& this.getEntry((curCol + endCol) / 2,
									(curRow + endRow) / 2).isBlack()) {
						this.setEntry(curCol, curRow, Piece.EMPTYPIECE);
						this.setEntry((curCol + endCol) / 2,
								(curRow + endRow) / 2, Piece.EMPTYPIECE);
						this.setEntry(endCol, endRow, Piece.WHITEKING);
						return true; // white king jumps over black
					}

				}

			}

			return false;
		} else
			return false;
	}

}// end class