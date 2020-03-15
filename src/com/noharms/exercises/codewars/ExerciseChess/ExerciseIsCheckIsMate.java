package com.noharms.exercises.codewars.ExerciseChess;

import java.util.*;
import java.util.stream.Collectors;

import static com.noharms.exercises.codewars.ExerciseChess.ChessBasicMoves.*;
import static com.noharms.exercises.codewars.ExerciseChess.ChessBoard.K_BLACK;
import static com.noharms.exercises.codewars.ExerciseChess.ChessBoard.K_WHITE;


public class ExerciseIsCheckIsMate {

  private int colorToMove;
  private int colorLastMoved;
  private PieceConfig[] allPieces;
  private ChessBoard boardToAnalyse;
  private Set<PieceConfig> piecesOfColorToMove;
  private Set<PieceConfig> piecesOfColorLastMoved;
  private PieceConfig kingColorToMove;
  private PieceConfig kingColorLastMoved;

  ExerciseIsCheckIsMate(final PieceConfig[] allPieces, int colorToMove) {
    this.colorToMove = colorToMove;
    colorLastMoved = (this.colorToMove == K_BLACK ? K_WHITE : K_BLACK);
    this.allPieces = allPieces;
    boardToAnalyse = new ChessBoard(this.allPieces);
    boardToAnalyse.print();
    piecesOfColorToMove = getPiecesSameColor(allPieces, this.colorToMove);
    piecesOfColorLastMoved = getPiecesSameColor(allPieces, colorLastMoved);
    kingColorToMove = getKing(piecesOfColorToMove, this.colorToMove);
    kingColorLastMoved = getKing(piecesOfColorLastMoved, colorLastMoved);
  }

  private Set<PieceConfig> getPiecesSameColor(PieceConfig[] pieces, int color) {
    return Arrays.stream(pieces).
            filter(piece -> piece.getColor() == color).
            collect(Collectors.toSet());
  }

  private PieceConfig getKing(Set<PieceConfig> pieces, int color) {
    for (PieceConfig piece : pieces) {
      if (piece.getPieceAsString().equals(ChessPiece.KING.toString())) {
        return piece;
      }
    }
    return null; // reaching here, means no king found
  }

  /**
   * White isCheck, if black has moved and any of black's pieces could hit the
   * white king with their next move.
   *
   * So, to check after a black move, whether or not white isCheck now, one needs
   * to compute the hypothetical next moves of all black figures.
   *
   * Note: a king can never pose check to another king, so we do not need to check
   * if the other king threatens the king under investigation.
   *
   * Note: for the question whether the opposite king is in check it is irrelevant
   *       if the piece threatening it could actually attack the king with a valid move !
   *       it suffices that there is a potential move, e.g. imagine you have
   *       moved a bishop inbetween an opposing rook and your king to guard it from check.
   *       if your bishop is threatening the opposing king from the new position
   *       this is check even though in fact you would not be allowed to move your
   *       bishop away due to the threat of the opposing rook.
   *
   * @return
   */
  public Set<PieceConfig> isCheck() {
    // 0. compute potential new positions (not considering whether move would put own king into check)
    Map<PieceConfig, Set<ChessMove>> piecesOfLastMovedColor2PotentialMoves =
            computePotentialMovesForPieces(piecesOfColorLastMoved, boardToAnalyse);

    // 1.  compare king position with potential moves
    Set<PieceConfig> threateningPieces = new HashSet<>();
    for (Map.Entry<PieceConfig, Set<ChessMove>> entry : piecesOfLastMovedColor2PotentialMoves.entrySet()) {
      PieceConfig pieceOfColorLastMoved = entry.getKey();
      if (pieceOfColorLastMoved.isKing()) {
        continue; // opposing king can never pose check
      }
      Set<ChessMove> potentialMoves = entry.getValue();
      for (ChessMove potentialMove : potentialMoves) {
        int row = potentialMove.getNewPos().row;
        int col = potentialMove.getNewPos().col;
        PieceConfig pieceAtTargetField = boardToAnalyse.getPieceAtCoordinates(row, col);
        if (pieceAtTargetField != null
                && pieceAtTargetField.isKing()) {
          threateningPieces.add(pieceOfColorLastMoved);
          break; // other potential moves of this piece are irrelevant
        }
      }
    }

    return threateningPieces;
  }


  /* TODO:  draft thoughts on how to compute the potential moves for a piece ?

      1. compute the potential moves without caring whether the move is actually allowed
         (may be disallowed because would put own king into check)

      2. for each potential move, we have to compute the hypothetical board
         and check if in that board the own king is on a field that
         the pieces of the other color could potentially move to
       ==> if yes, the move would put the own king into check and
           is thus not allowed

        simulate each of the potential moves of color A by creating the hypothetical board that would be the result
        of the move, then computing the potential moves of the opposite color, B, on the hypothetical board,
        and then comparing whether one of the potential moves of B is an attack on the king of A
         --> if yes, the currently checked move of color A is not allowed

      for (Map.Entry<PieceConfig, Set<ChessMove>> entry : piecesOfLastMovedColor2PotentialMoves.entrySet()) {
        PieceConfig pieceOfColorLastMoved = entry.getKey();
        if (pieceOfColorLastMoved.isKing()) {
          piecesOfLastMovedColor2PotentialMoves.remove(pieceOfColorLastMoved);
          continue;
        }
        Set<ChessMove> potentialMoves = entry.getValue();
        for (ChessMove hypotheticalMove : potentialMoves) {
          ChessBoard hypotheticalBoard = new ChessBoard(board, pieceOfColorLastMoved, hypotheticalMove);
          // TODO: create a copy of piecesOfColorToMove that has a figure removed
          //       if it was captured by the hypothetical Move
          Map<PieceConfig, Set<ChessMove>> piecesOfColorToMove2PotentialMoves =
                  computePotentialMovesForPieces(piecesOfColorToMove, hypotheticalBoard);
        }
      }

  */
  private Map<PieceConfig, Set<ChessMove>> computeValidMovesForPieces(
          Set<PieceConfig> piecesToAnalyse,
          ChessBoard board
  ) {
    return null; // TODO
  }


  private Map<PieceConfig, Set<ChessMove>> computePotentialMovesForPieces(
          Set<PieceConfig> piecesToAnalyse,
          ChessBoard board
  ) {
    Map<PieceConfig, Set<ChessMove>> piece2moves = new HashMap<>();
    for (PieceConfig piece : piecesToAnalyse) {
      Set<ChessMove> chessMoves = computePotentialMovesForPiece(piece, board);
      piece2moves.put(piece, chessMoves);
    }
    return piece2moves;
  }

  private Set<ChessMove> computePotentialMovesForPiece(PieceConfig pieceToMove, ChessBoard board) {
    if (pieceToMove.isPawn()) {
      return computePotentialMovesForPawn(pieceToMove, board);
    } else if (pieceToMove.isRook()) {
      return computePotentialMovesForRunners(pieceToMove, board, K_BASIC_MOVES_ROOK);
    } else if (pieceToMove.isKnight()) {
      return computePotentialMovesForKnightOrKing(pieceToMove, board, K_BASIC_MOVES_KNIGHT);
    } else if (pieceToMove.isBishop()) {
      return computePotentialMovesForRunners(pieceToMove, board, K_BASIC_MOVES_BISHOP);
    } else if (pieceToMove.isQueen()) {
      return computePotentialMovesForRunners(pieceToMove, board, K_BASIC_MOVES_QUEEN);
    } else if (pieceToMove.isKing()) {
      return computePotentialMovesForKnightOrKing(pieceToMove, board, K_BASIC_MOVES_KING);
    } else {
      throw new Error("Unknown piece type. " + pieceToMove.toString());
    }
  }

  /**
   * Pawn has three basic moves:
   *
   * move one row up/down
   * attack one diagonal up/down left, if occupied by enemy
   * attack one diagonal up/down right, if occupied by enemy
   *
   * And three special moves:
   *
   * move two row up/down, if still in starting position
   * attack one diagonal up/down left, if enemy pawn made two step across target field
   * attack diagonal up/down right, if enemy pawn made two step across target field
   *
   *
   * @param pieceToMove
   * @return
   */
  private Set<ChessMove> computePotentialMovesForPawn(PieceConfig pieceToMove, ChessBoard board) {
    Coordinates oldCoors = new Coordinates(pieceToMove.getRow(), pieceToMove.getCol());
    Coordinates newCoors;
    Set<ChessMove> potentialMoves = new HashSet<>();
    int rowDir = (pieceToMove.getColor() == 0 ? -1 : 1); // white moves upwards (row index decreases)
    // 1. move one up/down
    Coordinates move1 = new Coordinates(rowDir, 0);
    newCoors = Coordinates.add(oldCoors, move1);
    if (newCoors.isInsideBoard()) {
      PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
      if(pieceOnNewPos == null) {
        potentialMoves.add(new ChessMove(newCoors, oldCoors,false));
      }
    }
    // 2. attack diagonal to smaller column side
    Coordinates attack1 = new Coordinates(rowDir, -1);
    newCoors = Coordinates.add(oldCoors, attack1);
    if (newCoors.isInsideBoard()) {
      PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
      if (pieceOnNewPos != null && pieceOnNewPos.getColor() != pieceToMove.getColor()) {
        potentialMoves.add(new ChessMove(newCoors, oldCoors, true));
      }
    }
    // 3. attack diagonal to greater column side
    Coordinates attack2 = new Coordinates(rowDir, 1);
    newCoors = Coordinates.add(oldCoors, attack2);
    if (newCoors.isInsideBoard()) {
      PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
      if (pieceOnNewPos != null && pieceOnNewPos.getColor() != pieceToMove.getColor()) {
        potentialMoves.add(new ChessMove(newCoors, oldCoors, true));
      }
    }
    // 4. move 2 up
    Coordinates move2 = new Coordinates(rowDir * 2, 0);
    newCoors = Coordinates.add(oldCoors, move2);
    if (newCoors.isInsideBoard()) {
      // check one step is OK
      PieceConfig pieceOnMiddleField = board.getPieceAtCoordinates(newCoors.row - rowDir, newCoors.col);
      if(pieceOnMiddleField == null) {
        // check two step is OK
        PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
        if (pieceOnNewPos == null) {
          potentialMoves.add(new ChessMove(newCoors, oldCoors, false));
        }
      }
    }
    // 5. on passant attack to smaller column side - TODO
    // 6. on passant attack to smaller column side - TODO
    return potentialMoves;
  }
  private Set<ChessMove> computePotentialMovesForKnightOrKing(
          PieceConfig pieceToMove,
          ChessBoard board,
          Set<Coordinates> basicMoves) {
    Coordinates oldCoors = new Coordinates(pieceToMove.getRow(), pieceToMove.getCol());
    Set<ChessMove> potentialMoves = new HashSet<>();
    for (Coordinates basicMove : basicMoves) {
      Coordinates newCoors = Coordinates.add(oldCoors, basicMove);
      if (newCoors.isInsideBoard()) {
        boolean occupiedBySameColor = false;
        boolean occupiedByOtherColor = false;
        PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
        if (pieceOnNewPos != null) {
          if (pieceOnNewPos.getColor() == pieceToMove.getColor()) {
            occupiedBySameColor = true;
          } else {
            occupiedByOtherColor = true;
          }
        }
        if (!occupiedBySameColor) {
          potentialMoves.add(new ChessMove(newCoors, oldCoors, occupiedByOtherColor));
        }
      }
    }
    return potentialMoves;
  }

  /**
   * Let's call the pieces that can move 1 to 8 fields in a direction "Runners": rook, bishop, queen
   *
   * @param pieceToMove
   * @param board
   * @param basicMoves
   * @return
   */
  private Set<ChessMove> computePotentialMovesForRunners(
          PieceConfig pieceToMove,
          ChessBoard board,
          Set<Coordinates> basicMoves) {
    Coordinates oldCoors = new Coordinates(pieceToMove.getRow(), pieceToMove.getCol());
    Set<ChessMove> potentialMoves = new HashSet<>();
    for (Coordinates basicMove : basicMoves) {
      Coordinates newCoors = new Coordinates(oldCoors);
      boolean newPosOccupiedBySameColor = false;
      boolean newPosOccupiedByOtherColor = false;
      // in contrast to knightMoves, there are potentially multiple newPositions for each multiple of the basic move
      // once the new position is occupied, no need to compute further positions beyond that
      while (newCoors.isInsideBoard() &&
              !newPosOccupiedBySameColor && !newPosOccupiedByOtherColor) {
        newCoors = Coordinates.add(newCoors, basicMove);
        PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
        if (pieceOnNewPos != null) {
          if (pieceOnNewPos.getColor() == pieceToMove.getColor()) {
            newPosOccupiedBySameColor = true;
          } else {
            newPosOccupiedByOtherColor = true;
          }
        }
        if (!newPosOccupiedBySameColor) {
          potentialMoves.add(new ChessMove(newCoors, oldCoors, newPosOccupiedByOtherColor));
        }
      }
    }
    return potentialMoves;
  }

  public boolean isMate(final PieceConfig[] arrPieces, int player) {
    throw new RuntimeException("Not implemented yet!");
  }
}
