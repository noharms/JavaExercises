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
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

  @Test
  void isCheckTest2() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("ROOK", K_BLACK, 1, 4)
    };
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

  @Test
  void isCheckTest3() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("KNIGHT", K_BLACK, 6, 2)
    };
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

  @Test
  void isCheckTest4() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("BISHOP", K_BLACK, 3, 0)
    };
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

  @Test
  void isCheckTest5() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("QUEEN", K_BLACK, 1, 4)
    };
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

  @Test
  void isCheckTest6() {
    // input
    PieceConfig[] pieces = new PieceConfig[] {
            new PieceConfig("KING", K_BLACK, 0, 4),
            new PieceConfig("KING", K_WHITE, 7, 4),
            new PieceConfig("QUEEN", K_BLACK, 4, 7)
    };
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[2]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
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
    //OutputPiecesAsBoard.print(pieces);

    // expected
    Set<PieceConfig> expected = new HashSet<>(Arrays.asList(pieces[5], pieces[6]));

    // actual
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck(pieces, K_WHITE);

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

}