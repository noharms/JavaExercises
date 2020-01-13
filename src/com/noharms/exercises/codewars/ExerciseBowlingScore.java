package com.noharms.exercises.codewars;

public class ExerciseBowlingScore {

  private static int nRoundsToScoreDueToPrevPrevRoll = 0; // could be a boolean, since it can only be 1 or 0
  private static int nRoundsToScoreDueToPrevRoll = 0; // can be 2 (strike), 1 (spare), 0 else

  /**
   * Task: given a string representing the rolls of a player,
   *       compute the total amount of points received.
   *
   *       The 10 different rounds of a player a separated by
   *       a blank space. A strike is represented by X.
   *       A spare is represented by a /.
   *
   *       Note: after a strike the next two rolls count twice,
   *       in their own round and also to the score in the strike round.
   *       Note: after a spare the next roll counts twice,
   *       in its own round and also to the score in the spare round.
   *       Note: the 10th round is special ! a strike
   *       or a spare made in the tenth round does not
   *       make the next two rolls count twice. the 10th
   *       round is the only one in which one can have three
   *       rolls, namely when you do three strikes
   *       (thus every round can maximally yield 30 points).
   *
   *  Example:  "44 31 -1 -- 8/ 43 X 31 11 18"
   *
   *  --> 8 + 4 + 1 + (10 + 4) + 7 + (10 + 3 + 1) + 4 + 2 + 9 = 63
   *
   *  Algorithm:
   *
   *        1. get the scores per round
   *        2. if just two numbers or a miss, it is easy to add up
   *        3. if a strike or a spare was thrown before, we need to add points
   *           to a previous round (rsp, second previous round)
   *
   *        --> go through 10 rounds evaluating the score
   *
   *  Complexity: O(10) in time, O(10) in space
   *
   * @param input
   * @return
   */
  public static int computeBowlingScore(String input) {
    String[] rounds = input.split(" ");
    int totalScore = 0;
    for (int k = 0; k < rounds.length; ++k) {
      char[] chars = rounds[k].toCharArray();
      for (int i = 0; i < chars.length; ++i) {
        Character roll = chars[i];
        if (Character.isDigit(roll)) {
          int score = roll - '0';
          totalScore += score + computeBonusScoreAndUpdateMonitors(score);
        } else if (roll.compareTo('/') == 0) {
          int score = 10 - (chars[i - 1] - '0');
          totalScore += score + computeBonusScoreAndUpdateMonitors(score);
          nRoundsToScoreDueToPrevRoll = (k < 9 ? 1 : 0);
        } else if (roll.compareTo('X') == 0) {
          int score = 10;
          totalScore += score + computeBonusScoreAndUpdateMonitors(score);
          nRoundsToScoreDueToPrevRoll = (k < 9 ? 2 : 0);
        }
      }
    }
    return totalScore;
  }

  private static int computeBonusScoreAndUpdateMonitors(int scoreThisRoll) {
    int bonusScore = 0;
    if (nRoundsToScoreDueToPrevPrevRoll > 0) {
      bonusScore += scoreThisRoll;
      --nRoundsToScoreDueToPrevPrevRoll;
    }
    if (nRoundsToScoreDueToPrevRoll > 0) {
      bonusScore += scoreThisRoll;
      --nRoundsToScoreDueToPrevRoll;
      if (nRoundsToScoreDueToPrevRoll == 1) {
        nRoundsToScoreDueToPrevPrevRoll = 1;
        nRoundsToScoreDueToPrevRoll = 0;
      }
    }
    return bonusScore;
  }
}
