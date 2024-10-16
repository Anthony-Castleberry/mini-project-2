package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;

import java.util.Scanner;

/**
 * A calculator that continously reads new inputs.
 *
 * @author Anthony Castleberry
 */
public class InteractiveCalculator {

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
   * Runs a BigCalculator that continuously takes commands.
   *
   * @param args
   */
  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);

    Scanner eyes = new Scanner(System.in);

    String command = eyes.nextLine();

    BFCalculator bfc = new BFCalculator();

    BFRegisterSet reg = new BFRegisterSet();


    while (!(command.equals("QUIT"))) {

      if (command.length() == 0) {
        pen.println("no inputs");
      } // if

      if (command.substring(0, STORE_STR).equals("STORE")) {
        if (command.length() == STORE_LENGTH && isletter(command.charAt(STORE_CHAR))) {
          reg.store(command.charAt(STORE_CHAR), bfc.get());
          pen.println(command + " --> STORED");
        } // if
      } else {
        for (int j = 0; j < command.length(); j++) {
          int initial = fraclength(command, 0);

          if (j == 0) {
            bfc.clear();
            if (isletter(command.charAt(0))) {
              bfc.add(reg.get(command.charAt(0)));
            } else {
              bfc.add(new BigFraction(command.substring(0, initial)));
              if (initial > command.length()) {
                pen.println(command + " --> " + command);
                j = command.length();
              } else {
                j += initial - 1;
              } // if
            } // if
          } // if

          if (command.charAt(j) == '/') {
            if (isletter(command.charAt(j + 2))) {
              bfc.divide(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              BigFraction div;
              div = new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2));
              bfc.divide(div);
              j += fraclength(command, j + 2) + 1;
            } // if
          } // if

          if (command.charAt(j) == '+') {
            if (isletter(command.charAt(j + 2))) {
              bfc.add(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              BigFraction plus;
              plus = new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2));
              bfc.add(plus);
              j += fraclength(command, j + 2) + 1;
            } // if
          } // if

          if (command.charAt(j) == '-') {
            if (isletter(command.charAt(j + 2))) {
              bfc.subtract(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              BigFraction sub;
              sub = new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2));
              bfc.subtract(sub);
              j += fraclength(command, j + 2) + 1;
            } // if
          } // if

          if (command.charAt(j) == '*') {
            if (isletter(command.charAt(j + 2))) {
              bfc.multiply(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              BigFraction mult;
              mult = new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2));
              bfc.multiply(mult);
              j += fraclength(command, j + 2) + 1;
            } // if
          } // if

          if (isletter(command.charAt(j))) {
            if (command.length() == ONE) {
              pen.println(reg.get(command.charAt(j)));
            } // if
          } // if
        } // for
        pen.println(command + " --> " + bfc.get());
      } // if
      pen.flush();
      command = eyes.nextLine();
    } // while
    pen.close();
    eyes.close();
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

} // class InteractiveCalculator
