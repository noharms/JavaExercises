package com.noharms.exercises.codewars;

import java.util.HashSet;
import java.util.Set;

public class ExerciseShortestKnightPath {

  private static Set<Coordinates> knightMoves = new HashSet<>() {
    {
      add(new Coordinates(2, 1));
      add(new Coordinates(2, -1));
      add(new Coordinates(1, 2));
      add(new Coordinates(1, -2));
      add(new Coordinates(-2, -1));
      add(new Coordinates(-2, 1));
      add(new Coordinates(-1, -2));
      add(new Coordinates(-1, 2));
    }
  };

  private static class Coordinates {
    private int row, col; // row 0 == a, col 0 == 1
    Coordinates(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Coordinates)) {
        return false;
      }
      Coordinates other = (Coordinates)obj;
      return (row == other.row && col == other.col);
    }

    @Override
    public int hashCode() {
      return (row % (Integer.MAX_VALUE / 2) + col % (Integer.MAX_VALUE / 2));
    }

    public Coordinates add(Coordinates other) {
      return new Coordinates(row + other.getRow(), col + other.getCol());
    }

    private boolean isOutsideBoard(){
      return (row < 0 || col < 0 || row > 7 || col > 7);
    }


    public int getRow() {
      return row;
    }
    public void setRow(int row) {
      this.row = row;
    }

    public int getCol() {
      return col;
    }

    public void setCol(int col) {
      this.col = col;
    }
  }

  public static int numberMovesShortestKnightPath(String start, String finish) {
    int nMoves = 0;
    Coordinates startCoordinates = algebraicToCoordinateNotation(start);
    Coordinates endCoordinates = algebraicToCoordinateNotation(finish);
    Set<Coordinates> reachableCells = new HashSet<>() {
      {
        add(startCoordinates);
      }
    };
    Set<Coordinates> reachableCellsNew = new HashSet<>();
    while (!reachableCellsNew.contains(endCoordinates)) {
      reachableCellsNew.clear();
      for (Coordinates old : reachableCells) {
        reachableCellsNew.addAll(cellsReachableWithNextMove(old));
      }
      reachableCells.clear();
      reachableCells.addAll(reachableCellsNew);
      ++nMoves;
    }
    return nMoves;
  }

  private static Coordinates algebraicToCoordinateNotation(String algebraic) {
    return new Coordinates(algebraic.charAt(1) - '1', algebraic.charAt(0) - 'a');
  }

  private static Set<Coordinates> cellsReachableWithNextMove(Coordinates pos) {
    Set<Coordinates> reachableCoordinates = new HashSet<>();
    for (Coordinates move : knightMoves) {
      Coordinates newPos = pos.add(move);
      if (!newPos.isOutsideBoard()) {
        reachableCoordinates.add(newPos);
      }
    }
    return reachableCoordinates;
  }

}
