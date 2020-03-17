package com.noharms.exercises.codewars.ExerciseChess;

import java.util.*;
import java.util.stream.Collectors;

import static com.noharms.exercises.codewars.ExerciseChess.ChessBasicMoves.*;
import static com.noharms.exercises.codewars.ExerciseChess.ChessBoard.*;


public class ExerciseIsCheckIsMate {

  private PieceConfig[] allPieces;
  private ChessBoard boardToAnalyse;

  ExerciseIsCheckIsMate(final PieceConfig[] allPieces) {
    this.allPieces = allPieces;
    boardToAnalyse = new ChessBoard(this.allPieces);
    boardToAnalyse.print();
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
  public Set<PieceConfig> isCheck(ChessBoard board, final PieceConfig[] allPieces, int colorToMove) {

    int colorLastMoved = (colorToMove == K_BLACK ? K_WHITE : K_BLACK);
    Set<PieceConfig> piecesOfColorToMove = getPiecesSameColor(allPieces, colorToMove);
    Set<PieceConfig> piecesOfColorLastMoved = getPiecesSameColor(allPieces, colorLastMoved);
//    PieceConfig kingColorToMove = getKing(piecesOfColorToMove, colorToMove);
//    PieceConfig kingColorLastMoved = getKing(piecesOfColorLastMoved, colorLastMoved);

    // 0. compute potential new positions (not considering whether move would put own king into check)
    Map<PieceConfig, Set<ChessMove>> piecesOfLastMovedColor2PotentialMoves =
            computePotentialMovesForPieces(board, piecesOfColorLastMoved);

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
        PieceConfig pieceAtTargetField = board.getPieceAtCoordinates(row, col);
        if (pieceAtTargetField != null
                && pieceAtTargetField.isKing()) {
          threateningPieces.add(pieceOfColorLastMoved);
          break; // other potential moves of this piece are irrelevant
        }
      }
    }

    return threateningPieces;
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
   * Note: assuming board is consistent with the piecesToMove (TODO: check that ?)
   *
   * @param piecesToMove
   * @param board
   * @return
   */
  private Map<PieceConfig, Set<ChessMove>> computePotentialMovesForPieces(
          ChessBoard board,
          Set<PieceConfig> piecesToMove
  ) {
    Map<PieceConfig, Set<ChessMove>> piece2moves = new HashMap<>();
    for (PieceConfig piece : piecesToMove) {
      Set<ChessMove> chessMoves = computePotentialMovesForPiece(board, piece);
      piece2moves.put(piece, chessMoves);
    }
    return piece2moves;
  }

  private Set<ChessMove> computePotentialMovesForPiece(ChessBoard board, PieceConfig pieceToMove) {
    if (pieceToMove.isPawn()) {
      return computePotentialMovesForPawn(board, pieceToMove);
    } else if (pieceToMove.isRook()) {
      return computePotentialMovesForRunners(board, pieceToMove, K_BASIC_MOVES_ROOK);
    } else if (pieceToMove.isKnight()) {
      return computePotentialMovesForKnightOrKing(board, pieceToMove, K_BASIC_MOVES_KNIGHT);
    } else if (pieceToMove.isBishop()) {
      return computePotentialMovesForRunners(board, pieceToMove, K_BASIC_MOVES_BISHOP);
    } else if (pieceToMove.isQueen()) {
      return computePotentialMovesForRunners(board, pieceToMove, K_BASIC_MOVES_QUEEN);
    } else if (pieceToMove.isKing()) {
      return computePotentialMovesForKnightOrKing(board, pieceToMove, K_BASIC_MOVES_KING);
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
  private Set<ChessMove> computePotentialMovesForPawn(ChessBoard board, PieceConfig pieceToMove) {
    int colorToMove = pieceToMove.getColor();
    Coordinates oldCoors = new Coordinates(pieceToMove.getRow(), pieceToMove.getCol());
    Coordinates newCoors;
    Set<ChessMove> potentialMoves = new HashSet<>();
    int pawnsMoveDirection =
            (pieceToMove.getColor() == K_WHITE ? K_DIRECTION_WHITE : K_DIRECTION_BLACK); // white moves upwards (row index decreases)
    // 1. move one up/down
    Coordinates move1 = new Coordinates(pawnsMoveDirection, 0);
    newCoors = Coordinates.add(oldCoors, move1);
    if (newCoors.isInsideBoard()) {
      PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
      if(pieceOnNewPos == null) {
        potentialMoves.add(new ChessMove(newCoors, oldCoors,false));
      }
    }
    // 2. attack diagonal to smaller column side
    Coordinates attack1 = new Coordinates(pawnsMoveDirection, -1);
    newCoors = Coordinates.add(oldCoors, attack1);
    if (newCoors.isInsideBoard()) {
      PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
      if (pieceOnNewPos != null && pieceOnNewPos.getColor() != pieceToMove.getColor()) {
        potentialMoves.add(new ChessMove(newCoors, oldCoors, true));
      }
    }
    // 3. attack diagonal to greater column side
    Coordinates attack2 = new Coordinates(pawnsMoveDirection, 1);
    newCoors = Coordinates.add(oldCoors, attack2);
    if (newCoors.isInsideBoard()) {
      PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
      if (pieceOnNewPos != null && pieceOnNewPos.getColor() != pieceToMove.getColor()) {
        potentialMoves.add(new ChessMove(newCoors, oldCoors, true));
      }
    }
    // 4. move 2 up
    int rowPawnsStart = colorToMove == K_BLACK ? K_ROW_BLACK_PAWNS_START : K_ROW_WHITE_PAWNS_START;
    if (pieceToMove.getRow() == rowPawnsStart) {
      Coordinates move2 = new Coordinates(pawnsMoveDirection * 2, 0);
      newCoors = Coordinates.add(oldCoors, move2);
      if (newCoors.isInsideBoard()) {
        // check one step is OK
        PieceConfig pieceOnMiddleField = board.getPieceAtCoordinates(newCoors.row - pawnsMoveDirection, newCoors.col);
        if(pieceOnMiddleField == null) {
          // check two step is OK
          PieceConfig pieceOnNewPos = board.getPieceAtCoordinates(newCoors.row, newCoors.col);
          if (pieceOnNewPos == null) {
            potentialMoves.add(new ChessMove(newCoors, oldCoors, false));
          }
        }
      }
    }
    // 5. and 6. en passant attacks to left or right
    int rowToWaitForEnpassant =
            colorToMove == K_BLACK ? K_ROW_BLACK_PAWNS_WAIT_FOR_ENPASSANT : K_ROW_WHITE_PAWNS_WAIT_FOR_ENPASSANT;
    int rowOpposingPawnsStart =
            colorToMove == K_BLACK ? K_ROW_WHITE_PAWNS_START : K_ROW_BLACK_PAWNS_START;
    if (pieceToMove.getRow() == rowToWaitForEnpassant) {
      if (pieceToMove.getCol() > 0) {
        PieceConfig leftNeighbor = board.getBoardMatrix()[pieceToMove.getRow()][pieceToMove.getCol() - 1];
        // check if leftNeighbor made en passant movement
        if (leftNeighbor != null &&
                leftNeighbor.isPawn() && leftNeighbor.getColor() != colorToMove &&
                leftNeighbor.getPrevRow() == rowOpposingPawnsStart) {
          Coordinates attack3 = new Coordinates(pawnsMoveDirection, -1);
          newCoors = Coordinates.add(oldCoors, attack3);
          // if two-up move of the left neighbor was correct, no need to check if other piece is sitting at newcoors
          potentialMoves.add(new ChessMove(newCoors, oldCoors, true, true));
        }
      }
      if (pieceToMove.getCol() < 7) {
        PieceConfig rightNeighbor = board.getBoardMatrix()[pieceToMove.getRow()][pieceToMove.getCol() + 1];
        // check if right neighbor made en passent movement
        if (rightNeighbor != null &&
                rightNeighbor.isPawn() && rightNeighbor.getColor() != colorToMove &&
                rightNeighbor.getPrevRow() == rowOpposingPawnsStart) {
          Coordinates attack4 = new Coordinates(pawnsMoveDirection, 1);
          newCoors = Coordinates.add(oldCoors, attack4);
          potentialMoves.add(new ChessMove(newCoors, oldCoors, true, true));
        }
      }
    }
    return potentialMoves;
  }
  private Set<ChessMove> computePotentialMovesForKnightOrKing(
          ChessBoard board,
          PieceConfig pieceToMove,
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
          ChessBoard board,
          PieceConfig pieceToMove,
          Set<Coordinates> basicMoves) {
    Coordinates oldCoors = new Coordinates(pieceToMove.getRow(), pieceToMove.getCol());
    Set<ChessMove> potentialMoves = new HashSet<>();
    for (Coordinates basicMove : basicMoves) {
      Coordinates newCoors = Coordinates.add(oldCoors, basicMove);
      boolean newPosOccupiedBySameColor = false;
      boolean newPosOccupiedByOtherColor = false;
      // in contrast to knightMoves, there are potentially multiple newPositions for each multiple of the basic move
      // once the new position is occupied, no need to compute further positions beyond that
      while (newCoors.isInsideBoard() &&
              !newPosOccupiedBySameColor && !newPosOccupiedByOtherColor) {
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
        // update with further basic move
        newCoors = Coordinates.add(newCoors, basicMove);
      }
    }
    return potentialMoves;
  }

  public boolean isMate(ChessBoard board, final PieceConfig[] allPieces, int colorToMove) {

    int colorLastMoved = (colorToMove == K_BLACK ? K_WHITE : K_BLACK);
    Set<PieceConfig> piecesOfColorToMove = getPiecesSameColor(allPieces, colorToMove);
    Set<PieceConfig> piecesOfColorLastMoved = getPiecesSameColor(allPieces, colorLastMoved);
    PieceConfig kingColorToMove = getKing(piecesOfColorToMove, colorToMove);
    PieceConfig kingColorLastMoved = getKing(piecesOfColorLastMoved, colorLastMoved);

    Set<PieceConfig> piecesThreateningKingOfColorToMove = isCheck(board, allPieces, colorToMove);
    boolean isColorToMoveInCheck = !piecesThreateningKingOfColorToMove.isEmpty();
    if (!isColorToMoveInCheck) {
      return false;
    }
    // reaching here, means colorToMove is in check
    // compute all allowed moves of colorToMove and check if there is one move
    // that would put colorToMove out of check
    Map<PieceConfig, Set<ChessMove>> validMoves =
            computeValidMovesForPieces(board, piecesOfColorToMove, colorToMove);

    return validMoves.isEmpty();
  }

  /*  compute valid (=notPuttingOwnKingInCheck) moves

      1. compute the potential moves without caring whether the move is actually valid
         (a potential move can be invalid if it puts the own king into check)

      2. for each potential move, we have to compute the hypothetical board
         and check if in that board the own king is in check

  */
  private Map<PieceConfig, Set<ChessMove>> computeValidMovesForPieces(
          ChessBoard board,
          Set<PieceConfig> piecesToMove,
          int colorToMove){
    // 0. compute potential new positions (not considering whether move would put own king into check)
    Map<PieceConfig, Set<ChessMove>> piecesOfColorToMove2PotentialMoves =
            computePotentialMovesForPieces(board, piecesToMove);
    // 1. filter potential moves to valid moves
    //    (a move is valid, if afterwards the own king is not in check)
    Map<PieceConfig, Set<ChessMove>> piecesOfColorToMove2ValidMoves = new HashMap<>();
    for (Map.Entry<PieceConfig, Set<ChessMove>> entry : piecesOfColorToMove2PotentialMoves.entrySet()) {
      PieceConfig pieceOfColorToMove = entry.getKey();
      Set<ChessMove> potentialMoves = entry.getValue();
      Set<ChessMove> validMoves = new HashSet<>();
      for (ChessMove hypotheticalMove : potentialMoves) {
        ChessBoard hypotheticalBoard = new ChessBoard(board, pieceOfColorToMove, hypotheticalMove);
        PieceConfig[] allPiecesAfterHypotheticalMove = hypotheticalBoard.buildPiecesArray();
        Set<PieceConfig> piecesThreateningKing = isCheck(hypotheticalBoard, allPiecesAfterHypotheticalMove, colorToMove);
        if (piecesThreateningKing.isEmpty()) {
          validMoves.add(hypotheticalMove);
        } else {
          // king is threatened ! hypothetical move is invalid and thus not copied to validMoves set
        }
      }
      if (!validMoves.isEmpty()) {
        piecesOfColorToMove2ValidMoves.put(pieceOfColorToMove, validMoves);
      }
    }
    return piecesOfColorToMove2ValidMoves;
  }

}


