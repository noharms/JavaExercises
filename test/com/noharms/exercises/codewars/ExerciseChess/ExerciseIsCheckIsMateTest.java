package com.noharms.exercises.codewars.ExerciseChess;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.noharms.exercises.codewars.ExerciseChess.ChessBoard.K_BLACK;
import static com.noharms.exercises.codewars.ExerciseChess.ChessBoard.K_WHITE;
import static org.junit.jupiter.api.Assertions.*;

class ExerciseIsCheckIsMateTest {

  @Test
  void isCheckTest1() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("PAWN", K_BLACK, 6, 5)
    };
    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest2() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("ROOK", K_BLACK, 1, 4)
    };
    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest3() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("KNIGHT", K_BLACK, 6, 2)
    };
    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest4() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("BISHOP", K_BLACK, 3, 0)
    };
    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest5() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("QUEEN", K_BLACK, 1, 4)
    };
    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest6() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("QUEEN", K_BLACK, 4, 7)
    };
    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest7() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("PAWN", K_WHITE, 6, 4),
            new PieceConfig("PAWN", K_WHITE, 6, 5),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("BISHOP", K_WHITE, 7, 5),
            new PieceConfig("BISHOP", K_BLACK, 4, 1),
            new PieceConfig("ROOK", K_BLACK, 7, 2, 5, 2)
    };

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[5], pieces[6]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_WHITE);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }

  @Test
  void isCheckTest8() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 1, 1),
            new PieceConfig("KING", K_WHITE, 7, 7),
            new PieceConfig("PAWN", K_WHITE, 2, 2)
    };

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_BLACK);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
  }


  @Test
  void isMateTest1() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 0),
            new PieceConfig("KING", K_WHITE, 7, 7),
            new PieceConfig("QUEEN", K_WHITE, 1, 1),
            new PieceConfig("PAWN", K_WHITE, 2, 2)
    };    

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[2]));
    boolean expectedIsMate = true;

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_BLACK);
    boolean isMate = ex.isMate(new ChessBoard(pieces), pieces, K_BLACK);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
    assertEquals(expectedIsMate, isMate);
  }


  @Test
  void isMateTest2() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 3, 3),
            new PieceConfig("PAWN", K_BLACK, 3, 2),
            new PieceConfig("PAWN", K_BLACK, 3, 4),
            new PieceConfig("PAWN", K_BLACK, 4, 2),
            new PieceConfig("PAWN", K_BLACK, 4, 3),
            new PieceConfig("KING", K_WHITE, 7, 7),
            new PieceConfig("QUEEN", K_WHITE, 3, 6),
            new PieceConfig("ROOK", K_WHITE, 2, 0),
            new PieceConfig("ROOK", K_WHITE, 4, 0),
            new PieceConfig("BISHOP", K_WHITE, 5, 5)
    };

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[9]));
    boolean expectedIsMate = true;

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_BLACK);
    boolean isMate = ex.isMate(new ChessBoard(pieces), pieces, K_BLACK);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
    assertEquals(expectedIsMate, isMate);
  }


  @Test
  void isMateTest3() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("PAWN", K_BLACK, 1, 4),
            new PieceConfig("PAWN", K_BLACK, 1, 3),
            new PieceConfig("BISHOP", K_BLACK, 0, 5),
            new PieceConfig("QUEEN", K_BLACK, 0, 3),
            new PieceConfig("KING", K_WHITE, 7, 7),
            new PieceConfig("KNIGHT", K_WHITE, 3, 4),
            new PieceConfig("BISHOP", K_WHITE, 1, 5)
    };

    // expectedPieces
    Set<PieceConfig> expectedPieces = new HashSet<>(Arrays.asList(pieces[7]));
    boolean expectedIsMate = true;

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(new ChessBoard(pieces), pieces, K_BLACK);
    boolean isMate = ex.isMate(new ChessBoard(pieces), pieces, K_BLACK);

    // compare
    assertEquals(expectedPieces, piecesThreateningKing);
    assertEquals(expectedIsMate, isMate);
  }

}