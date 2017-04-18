package checkers;

/* Note that this class has only private data, and does not have any getters
 * or setters, and it doesn't need any.  Code from outside the class can
 * create Piece objects, and test what color and type they are, without ever
 * having to know the details of exactly how Piece objects are stored
 * internally (for example, the fact that they're stored using enums).
 */

public class Piece {

  private enum PieceType { REGULAR, KING, NONE }
  private enum PieceColor { BLACK, WHITE, NONE }
  private PieceType type;
  private PieceColor color;

  public static final Piece BLACKPIECE= new Piece(PieceType.REGULAR,
                                                  PieceColor.BLACK);
  public static final Piece WHITEPIECE= new Piece(PieceType.REGULAR,
                                                  PieceColor.WHITE);
  public static final Piece BLACKKING= new Piece(PieceType.KING,
                                                 PieceColor.BLACK);
  public static final Piece WHITEKING= new Piece(PieceType.KING,
                                                 PieceColor.WHITE);
  public static final Piece EMPTYPIECE= new Piece(PieceType.NONE,
                                                  PieceColor.NONE);

  public Piece(PieceType type, PieceColor color) {
    this.type= type;
    this.color= color;
  }

  public boolean isRegularPiece() {
    return type == PieceType.REGULAR;
  }

  public boolean isKing() {
    return type == PieceType.KING;
  }

  public boolean isBlack() {
    return color == PieceColor.BLACK;
  }

  public boolean isWhite() {
    return color == PieceColor.WHITE;
  }

  public boolean isEmpty() {
    return type == PieceType.NONE && color == PieceColor.NONE;
  }

  public boolean sameType(Piece piece) {
    return type == piece.type;
  }

  public boolean sameColor(Piece piece) {
    return color == piece.color;
  }

  // returns true if the current object and the parameter Piece have
  // opposite colors, meaning black and white or white and black (note that
  // neither will be NONE if true is returned)
  public boolean oppositeColor(Piece piece) {
    return color == PieceColor.WHITE && piece.color == PieceColor.BLACK ||
           color == PieceColor.BLACK && piece.color == PieceColor.WHITE;
  }

  public String toString() {
    switch (type) {
      case REGULAR:
        if (color == PieceColor.BLACK)
          return "b";
        else return "w"; 
      case KING:
        if (color == PieceColor.BLACK)
          return "B";
        else return "W"; 
      case NONE:
        return "-";
    }

    return "-";  // make compiler happy even though we should never reach here
  }

  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    else
      if (!(obj instanceof Piece))
        return false;
      else {
        Piece otherPiece= (Piece) obj;
        return sameType(otherPiece) && sameColor(otherPiece);
      }
  }

  // if we implement equals() we must implement hashCode() (this will be
  // covered later)
  public int hashCode() {
    return 0;
  }

}
