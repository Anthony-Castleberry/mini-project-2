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

  private static final char a = 'a';

  private static final char z = 'z';

  private static final Integer STORE_LENGTH = 7;

  private static final Integer ONE = 1;

  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);

    Scanner eyes = new Scanner(System.in);

    pen.print("Enter command: ");

    String command = eyes.nextLine();

    BFCalculator bfc = new BFCalculator();

    BFRegisterSet reg = new BFRegisterSet();

  
    while ((command.equals("QUIT")) != true) {

    if (command.length() == 0) {
      pen.println("no inputs");
    }
      
      if (command.substring(0, 5).equals("STORE")) {
        if (command.length() == STORE_LENGTH && isletter(command.charAt(6))) {
          reg.store(command.charAt(6), bfc.get());
          pen.println(command + " --> STORED");
        }
      } else {
        for(int j = 0; j < command.length(); j++) {
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
                } 
            }
          }

          if (command.charAt(j) == '/') {
            if (isletter(command.charAt(j + 2))) {
              bfc.divide(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              bfc.divide(new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2)));
              j += fraclength(command, j + 2) + 1;
            }
          }

          if (command.charAt(j) == '+') {
            if (isletter(command.charAt(j + 2))) {
              bfc.add(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              bfc.add(new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2)));
              j += fraclength(command, j + 2) + 1;
            }
          }

          if (command.charAt(j) == '-') {
            if (isletter(command.charAt(j + 2))) {
              bfc.subtract(reg.get(command.charAt(j + 2)));
              j += 2;
            } else {
              bfc.subtract(new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2)));
              j += fraclength(command, j + 2) + 1;
            }
          }

            if (command.charAt(j) == '*') {
              if (isletter(command.charAt(j + 2))) {
                bfc.multiply(reg.get(command.charAt(j + 2)));
                j += 2;
              } else {
                bfc.multiply(new BigFraction(command.substring(j + 2, j + fraclength(command, j + 2) + 2)));
                j += fraclength(command, j + 2) + 1;
              }
            }

            if (isletter(command.charAt(j))) {
              if (command.length() == ONE) {
                pen.println(reg.get(command.charAt(j)));
              }
            }
          }
          pen.println(command + " --> " + bfc.get());
      }
      pen.flush();
      pen.print("Enter command: ");
      command = eyes.nextLine();
    }
  pen.close();
  eyes.close();
  }

  static boolean isletter (char c) {
    if (a <= c && c <= z) {
      return true;
    } else {
      return false;
    }
  }

  static int fraclength (String str, Integer index) {
      for (int k = index; k < str.length(); k++) {
        if (Character.isWhitespace(str.charAt(k))) {
          return k - index;
        }
      }
      return str.length() - index;
  }

  static boolean iswhole (String str) {
    return !(str.contains("/"));
  }

}
