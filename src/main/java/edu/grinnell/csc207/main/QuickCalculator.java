package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;


/**
 * A calculator that reads from a command-line input.
 *
 * @author Anthony Castleberry
 */
public class QuickCalculator {

  /**
   * low end of ascii numbers for lower case letters.
   */
  private static final char A = 'a';

  /**
   * high end of ascii numbers for lower case letters.
   */
  private static final char Z = 'z';

  /**
   * how  long the string for a STORE command should be.
   */
  private static final Integer STORE_LENGTH = 7;

  /**
   * how long the string for a STORE part of STORE command should be.
   */
  private static final Integer STORE_STR = 5;

  /**
   * wher in the string the letter is for a STORE command.
   */
  private static final Integer STORE_CHAR = 6;

  /**
   * constant to hold 1 to avoid magic numbers.
   */
  private static final Integer ONE = 1;

  /**
   * runs commands specified in an input string.
   *
   * @param args
   */
  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);

    BFCalculator bfc = new BFCalculator();

    BFRegisterSet reg = new BFRegisterSet();

    int length = args.length;

    if (length == 0) {
      pen.println("no inputs");
    } // if

    for (int i = 0; i < length; i++) {
      if (args[i].substring(0, STORE_STR).equals("STORE")) {
        if (args[i].length() == STORE_LENGTH && isletter(args[i].charAt(STORE_CHAR))) {
          reg.store(args[i].charAt(STORE_CHAR), bfc.get());
          pen.println(args[i] + " --> STORED");
        } // if
      } else {
        for (int j = 0; j < args[i].length(); j++) {
          int initial = fraclength(args[i], 0);
          if (j == 0) {
            bfc.clear();
            if (isletter(args[i].charAt(0))) {
              bfc.add(reg.get(args[i].charAt(0)));
            } else {
              bfc.add(new BigFraction(args[i].substring(0, initial)));
              if (initial > args[i].length()) {
                pen.println(args[i] + " --> " + args[i]);
                j = args[i].length();
              } else {
                j += initial - 1;
              } // if
            } // if
          } // if

          if (args[i].charAt(j) == '/') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.divide(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              BigFraction div;
              div = new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2));
              bfc.divide(div);
              j += fraclength(args[i], j + 2) + 1;
            } // if
          } // if

          if (args[i].charAt(j) == '+') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.add(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              BigFraction plus;
              plus = new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2));
              bfc.add(plus);
              j += fraclength(args[i], j + 2) + 1;
            } // if
          } // if

          if (args[i].charAt(j) == '-') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.subtract(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              BigFraction sub;
              sub = new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2));
              bfc.subtract(sub);
              j += fraclength(args[i], j + 2) + 1;
            } // if
          } // if

          if (args[i].charAt(j) == '*') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.multiply(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              BigFraction mult;
              mult = new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2));
              bfc.multiply(mult);
              j += fraclength(args[i], j + 2) + 1;
            } // if
          } // if

          if (isletter(args[i].charAt(j))) {
            if (args[i].length() == ONE) {
              pen.println(reg.get(args[i].charAt(j)));
            } // if
          } // if
        } // for
        pen.println(args[i] + " --> " + bfc.get());
      } // if
    } // for
    pen.close();
  } // main(String[])

  static boolean isletter(char c) {
    return (A <= c && c <= Z);
  } // isletter(char)

  static int fraclength(String str, Integer index) {
    for (int k = index; k < str.length(); k++) {
      if (Character.isWhitespace(str.charAt(k))) {
        return k - index;
      } // if
    } // for
    return str.length() - index;
  } // fraclength(String, Integer)

  static boolean iswhole(String str) {
    return !(str.contains("/"));
  } // iswhole(String)
} // class QuickCalculator
