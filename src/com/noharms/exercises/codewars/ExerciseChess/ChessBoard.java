package com.noharms.exercises.codewars.ExerciseChess;


import java.util.ArrayList;
import java.util.List;

/**
 * Convention:
 *
 * Let top-left corner of board be (row 0, col 0), with black starting at the stop and white starting at the bottom.
 *
 *           0    1    2    3   4   5    6  7
 *    0      Rb  Nb   Bb   Qb   Kb  Bb  Nb  Rb
 *    1      Pb  Pb   Pb   Pb   Pb  Pb  Pb  Pb
 *    2
 *    3
 *    4
 *    5
 *    6      Pw  Pw   Pw   Pw   Pw  Pw  Pw  Pw
 *    7      Rw  Nw   Bw   Qw   Kw  Bw  Nw   Rw
 */
public class ChessBoard {

  public static final int K_DIM = 8;

  public static final int K_WHITE = 0;
  public static final int K_BLACK = 1;

  public static final int K_DIRECTION_WHITE = -1;
  public static final int K_DIRECTION_BLACK = 1;

  public static final int K_ROW_WHITE_PAWNS_START= 6;
  public static final int K_ROW_BLACK_PAWNS_START = 1;
  public static final int K_ROW_BLACK_PAWNS_WAIT_FOR_ENPASSANT = 4;
  public static final int K_ROW_WHITE_PAWNS_WAIT_FOR_ENPASSANT = 3;

  // TODO: the 2d board array could be of a smaller datatype than PieceConfig
  //       --> PieceConfig stores row and column, which is redundant because it is given
  //           by the board coordinates already
  private final PieceConfig[][] board = new PieceConfig[K_DIM][K_DIM];

  // cstr - empty board
  ChessBoard() {
  }

  // cstr - board filled from Array
  ChessBoard(PieceConfig[] pieces) {
    fillBoardFromArrayOfPieces(pieces);
  }

  // cstr - copy constructor
  ChessBoard(ChessBoard other) {
    for (int i = 0; i < K_DIM; ++i) {
      System.arraycopy(other.board[i], 0, board[i], 0, K_DIM);
    }
  }

  // cstr - board filled from old board with a piece making a move
  ChessBoard(ChessBoard oldBoard, PieceConfig pieceToMove, ChessMove move) {
    this(oldBoard);
    board[pieceToMove.getRow()][pieceToMove.getCol()] = null;
    board[move.getNewPos().row][move.getNewPos().col] = new PieceConfig(pieceToMove, move);
    if (move.isEnPassantAttack()) { // in that case captured piece is on other field - need to remove it from board
      board[move.getOldPos().row][move.getNewPos().col] = null;
    }
  }

  private void fillBoardFromArrayOfPieces(PieceConfig[] pieces) {
    for (PieceConfig piece : pieces) {
      int row = piece.getRow();
      int col = piece.getCol();
      board[row][col] = new PieceConfig(piece);
    }
  }

  public PieceConfig[][] getBoardMatrix() {
    return board;
  }

  public PieceConfig getPieceAtCoordinates(int row, int col) {
    if (row < 0 || row >= K_DIM) {
      throw new Error("Unreasonable row index: " + row);
    }
    if (col < 0 || col >= K_DIM) {
      throw new Error("Unreasonable col index: " + col);
    }
    return board[row][col];
  }

  public void print() {
    //System.out.print("\u2657");  // --> prints a bishop

    for (int row = 0; row < K_DIM; ++row) {
      StringBuilder strbuilder = new StringBuilder("|");
      for (int col = 0; col < K_DIM; ++col) {
        strbuilder.append(" ");
        PieceConfig piece = this.board[row][col];
        if (piece == null) {
          strbuilder.append("  ");
        } else {
          strbuilder.append(piece.getColor() == K_WHITE ? 'w' : 'b');
          strbuilder.append(piece.getPieceType().getLetter());
        }
        strbuilder.append(" |");
      }
      // strbuilder.setLength(strbuilder.length() > 0 ? strbuilder.length() - 1 : 0); // remove last character
      System.out.println(strbuilder.toString());
      System.out.println("-".repeat(Math.max(0, strbuilder.length())));
    }
    return;
  }

  public PieceConfig[] buildPiecesArray() {
    List<PieceConfig> pieces = new ArrayList<>();
    for (int row = 0; row < K_DIM; ++row) {
      for (int col = 0; col < K_DIM; ++col) {
        if (board[row][col] == null) {
          continue;
        } else {
          pieces.add(new PieceConfig(board[row][col]));
        }
      }
    }
    return pieces.toArray(PieceConfig[]::new);
  }
}
