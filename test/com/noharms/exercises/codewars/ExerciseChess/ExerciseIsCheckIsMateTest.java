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
  void isCheckTest() {
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
    ExerciseIsCheckIsMate ex = new ExerciseIsCheckIsMate(pieces, K_WHITE);
    Set<PieceConfig> piecesThreateningKing = ex.isCheck();

    // compare
    assertEquals(expected, piecesThreateningKing);
  }

}