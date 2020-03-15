package com.noharms.exercises.codewars.ExerciseChess;

public enum ChessPiece {

  PAWN('P',  1),
  ROOK('R', 5),
  KNIGHT('N', 3),
  BISHOP('B', 3),
  QUEEN('Q', 9),
  KING('K', Integer.MAX_VALUE);

  private final char letter;
  private final int value;

  ChessPiece(char letter, int value) {
    this.letter = letter;
    this.value = value;
  }

  public char getLetter() {
    return letter;
  }
  public int getValue() {
    return value;
  }

}
