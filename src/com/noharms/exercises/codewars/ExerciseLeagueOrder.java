package com.noharms.exercises.codewars;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLeagueOrder {
  private static class TeamAndResult implements Comparable<TeamAndResult>{
    public int id, points, scoringDiff, goalsScored;
    TeamAndResult(int id, int points, int scoringDiff, int goalsScored) {
      this.id = id;
      this.points = points;
      this.scoringDiff = scoringDiff;
      this.goalsScored = goalsScored;
    }
    @Override
    public int compareTo(TeamAndResult o) {
      if (points != o.points) {
        return points - o.points;
      } else {
        if (scoringDiff != o.scoringDiff) {
          return scoringDiff - o.scoringDiff;
        } else {
          return goalsScored - o.goalsScored; // if we reach here and goalsScored are equal, the teams share a place
        }
      }
    }
  }

  /**
   * convention to represent a game :  [1, 5, 0, 1] -> Team1 vs Team5 ended 0:1
   *
   * convention for return array:  [4, 2, 1, 2] means Team2 is 1st, Team1 and Team3 shared 2nd, Team0 is 4th place
   *
   * @param numberTeams
   * @param games
   * @return
   */
  public static int[] computeRanks(int numberTeams, int[][] games) {
    List<TeamAndResult> teamsAndResults = processGameResults(numberTeams, games);
    teamsAndResults.sort(TeamAndResult::compareTo); // sorts in ascending order !
    int[] ranking = new int[numberTeams];
    for (int i = numberTeams - 1; i >= 0; ) {
      int rank = numberTeams - i;
      // find equal teams
      int k = i;
      while (k > 0 && teamsAndResults.get(k).compareTo(teamsAndResults.get(k - 1)) == 0) {
        --k;
      }
      int nEqual = i - k;
      while (i >= k) {
        ranking[teamsAndResults.get(i).id] = rank;
        --i;
      };
    }
    return ranking;
  }

  private static List<TeamAndResult> processGameResults(int numberTeams, int[][] games) {
    List<TeamAndResult> teamsWithResults = new ArrayList<>() {
      {
        for (int i = 0; i < numberTeams; ++i) {
          add(new TeamAndResult(i, 0, 0, 0));
        }
      }
    };
    for (int[] game : games) {
      TeamAndResult team1 = teamsWithResults.get(game[0]);
      TeamAndResult team2 = teamsWithResults.get(game[1]);
      int goals1 = game[2];
      int goals2 = game[3];
      team1.goalsScored += goals1;
      team1.scoringDiff += goals1 - goals2;
      team2.goalsScored += goals2;
      team2.scoringDiff += goals2 - goals1;
      if (goals1 > goals2) {
        team1.points += 2;
      } else if  (goals1 < goals2) {
        team2.points += 2;
      } else { // draw
        team1.points += 1;
        team2.points += 1;
      }
    }
    return teamsWithResults;
  }
}
