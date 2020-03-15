package com.noharms.exercises.codewars.ExerciseChess;

/**
 *  Note: all members are final, so after instantiasation one can only
 *        read the members but never change them
 */
public class PieceConfig {

  private static final int K_WITHOUT_PREVIOUS_POSITION = -1;

  private final String pieceAsString; // TODO: change from String to Enum ChessPiece (only for codewars using String)
  private final ChessPiece pieceType;
  private final int color;
  private final int row;
  private final int col;
  private final int prevRow;
  private final int prevCol;

  public PieceConfig(String pieceAsString, int color, int row, int col, int prevRow, int prevCol) {
    this.pieceAsString = pieceAsString;
    this.pieceType = ChessPiece.valueOf(pieceAsString);
    this.color = color;
    this.row = row;
    this.col = col;
    this.prevRow = prevRow;
    this.prevCol = prevCol;
  }
  public PieceConfig(String pieceAsString, int color, int row, int col) {
    this.pieceAsString = pieceAsString;
    this.pieceType = ChessPiece.valueOf(pieceAsString);
    this.color = color;
    this.row = row;
    this.col = col;
    this.prevRow = K_WITHOUT_PREVIOUS_POSITION;
    this.prevCol = K_WITHOUT_PREVIOUS_POSITION;
  }
  public PieceConfig(PieceConfig other) {
    this.pieceAsString = other.pieceAsString;
    this.pieceType = ChessPiece.valueOf(pieceAsString);
    this.color = other.color;
    this.row = other.row;
    this.col = other.col;
    this.prevRow = other.prevRow;
    this.prevCol = other.prevCol;
  }
  public PieceConfig(PieceConfig other, ChessMove move) {
    this.pieceAsString = other.pieceAsString;
    this.pieceType = ChessPiece.valueOf(pieceAsString);
    this.color = other.color;
    this.row = move.getNewPos().getRow();
    this.col = move.getNewPos().getCol();
    this.prevRow = other.row;
    this.prevCol = other.col;
    if (other.getCol() != move.getOldPos().col
            || other.getRow() != move.getOldPos().row) {
      throw new Error("Unreasonable combination of piece to move and move.");
    }
  }

  public String getPieceAsString() {
    return pieceAsString;
  }
  public ChessPiece getPieceType() {
    return pieceType;
  }
  public int getColor() {
    return color;
  }
  public int getRow() { return row;}
  public int getCol() { return col;}
  public int getPrevRow() throws RuntimeException {
    if (prevRow < 0) {
      throw new RuntimeException();
    }
    return prevRow;
  }
  public int getPrevCol() throws RuntimeException {
    if (prevCol < 0) {
      throw new RuntimeException();
    }
    return prevCol;
  }

  public boolean isPawn() {
    return getPieceAsString().equals(ChessPiece.PAWN.toString());
  }

  public boolean isRook() {
    return getPieceAsString().equals(ChessPiece.ROOK.toString());
  }

  public boolean isKnight() {
    return getPieceAsString().equals(ChessPiece.KNIGHT.toString());
  }

  public boolean isBishop() {
    return getPieceAsString().equals(ChessPiece.BISHOP.toString());
  }

  public boolean isQueen() {
    return getPieceAsString().equals(ChessPiece.QUEEN.toString());
  }

  public boolean isKing() {
    return getPieceAsString().equals(ChessPiece.KING.toString());
  }

  @Override
  public int hashCode() {
    int hashCode = 0;
    final int kMult = 17;
    for (int i = 0; i < pieceAsString.length(); ++i) {
      hashCode *= kMult;
      hashCode += (pieceAsString.charAt(i) + row + col + prevRow + prevCol);
    }
    if (color == 1) {
      hashCode *= -1;
    }
    return hashCode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    PieceConfig otherPieceConfig = (PieceConfig)obj;
    if (!this.pieceAsString.equals(otherPieceConfig.pieceAsString)) {
      return false;
    }
    if (this.row != otherPieceConfig.row) {
      return false;
    }
    if (this.col != otherPieceConfig.col) {
      return false;
    }
    if (this.prevRow != otherPieceConfig.prevRow) {
      return false;
    }
    if (this.prevCol != otherPieceConfig.prevCol) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("piece: ");
    builder.append(pieceAsString);
    builder.append(", owner: ");
    builder.append(color);
    builder.append(", row: ");
    builder.append(row);
    builder.append(", col: ");
    builder.append(col);
    if (prevRow != K_WITHOUT_PREVIOUS_POSITION) {
      builder.append(", prevX: ");
      builder.append(prevRow);
    }
    if (prevCol != K_WITHOUT_PREVIOUS_POSITION) {
      builder.append(", prevY: ");
      builder.append(prevCol);
    }
    return builder.toString();
  }
}
