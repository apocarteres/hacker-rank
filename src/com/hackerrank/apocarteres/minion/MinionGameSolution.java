package com.hackerrank.apocarteres.minion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

import static java.lang.System.out;
import static java.util.Arrays.binarySearch;

//Java solution for https://www.hackerrank.com/challenges/the-minion-game/problem
public final class MinionGameSolution {
  private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};

  private static final class Result {
    private final String name;
    private final long score;

    private Result(String name, long score) {
      this.name = name;
      this.score = score;
    }
  }

  private static final class Player {
    private final String name;
    private final Predicate<Character> charset;

    Player(String name, Predicate<Character> charset) {
      this.name = name;
      this.charset = charset;
    }

    Result solve(String s) {
      long score = 0;
      for (int i = 0; i < s.length(); ++i) {
        if (!charset.test(s.charAt(i))) {
          continue;
        }
        score += s.length() - i;
      }
      return new Result(name, score);
    }
  }

  public static void main(String[] args) throws IOException {
    var input = new BufferedReader(new FileReader(args[0])).readLine();
    var startedAt = System.currentTimeMillis();
    var stuart = new Player("Stuart", s -> !vowelsOnly(s)).solve(input);
    var kevin = new Player("Kevin", MinionGameSolution::vowelsOnly).solve(input);
    Result result;
    if (stuart.score == kevin.score) {
      out.println("Draw");
      return;
    } else if (kevin.score > stuart.score) {
      result = kevin;
    } else {
      result = stuart;
    }
    out.printf("%s %d%n", result.name, result.score);
    out.printf("took %d ms%n", System.currentTimeMillis() - startedAt);
  }

  private static boolean vowelsOnly(Character s) {
    return binarySearch(VOWELS, s) >= 0;
  }
}
