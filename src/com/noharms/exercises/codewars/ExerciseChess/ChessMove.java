package com.noharms.exercises.codewars.ExerciseChess;

public class ChessMove {
  private final Coordinates oldPos;
  private final Coordinates newPos;
  private final boolean isAttack;

  public ChessMove(int rowNew, int colNew, int rowOld, int colOld, boolean isAttack) {
    newPos = new Coordinates(rowNew, colNew);
    oldPos = new Coordinates(rowOld, colOld);
    this.isAttack = isAttack;
  }
  public ChessMove(Coordinates coorsNew, Coordinates coorsOld, boolean isAttack) {
    newPos = new Coordinates(coorsNew);
    oldPos = new Coordinates(coorsOld);
    this.isAttack = isAttack;
  }


  public boolean isAttack() { return isAttack; }
  public Coordinates getNewPos() {
    return newPos;
  }
  public Coordinates getOldPos() {
    return oldPos;
  }

  @Override
  public int hashCode() {
    int hashCode = 0;
    int kMult = 17;
    hashCode += newPos.getRow() + oldPos.getRow();
    hashCode += (newPos.getCol() + oldPos.getCol()) * kMult;
    hashCode += (isAttack ? 1 : 0) * kMult*kMult;
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
    ChessMove other = (ChessMove)obj;
    if (!newPos.equals(other.newPos)) {
      return false;
    }
    if (!oldPos.equals(other.oldPos)) {
      return false;
    }
    if (isAttack != other.isAttack) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
