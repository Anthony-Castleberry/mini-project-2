package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;

public class QuickCalculator {

  private static final char a = 'a';

  private static final char z = 'z';

  private static final Integer STORE_LENGTH = 7;

  private static final Integer ONE = 1;

  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);

    BFCalculator bfc = new BFCalculator();

    BFRegisterSet reg = new BFRegisterSet();

    int length = args.length;

    if (length == 0) {
      pen.println("no inputs");
    }

    for (int i = 0; i < length; i++) {
      
      if (args[i].substring(0, 5).equals("STORE")) {
        if (args[i].length() == STORE_LENGTH && isletter(args[i].charAt(6))) {
          reg.store(args[i].charAt(6), bfc.get());
          pen.println(args[i] + " --> STORED");
        }
      } else {
        for(int j = 0; j < args[i].length(); j++) {
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
                } 
            }
          }

          if (args[i].charAt(j) == '/') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.divide(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              bfc.divide(new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2)));
              j += fraclength(args[i], j + 2) + 1;
            }
          }

          if (args[i].charAt(j) == '+') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.add(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              bfc.add(new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2)));
              j += fraclength(args[i], j + 2) + 1;
            }
          }

          if (args[i].charAt(j) == '-') {
            if (isletter(args[i].charAt(j + 2))) {
              bfc.subtract(reg.get(args[i].charAt(j + 2)));
              j += 2;
            } else {
              bfc.subtract(new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2)));
              j += fraclength(args[i], j + 2) + 1;
            }
          }

            if (args[i].charAt(j) == '*') {
              if (isletter(args[i].charAt(j + 2))) {
                bfc.multiply(reg.get(args[i].charAt(j + 2)));
                j += 2;
              } else {
                bfc.multiply(new BigFraction(args[i].substring(j + 2, j + fraclength(args[i], j + 2) + 2)));
                j += fraclength(args[i], j + 2) + 1;
              }
            }

            if (isletter(args[i].charAt(j))) {
              if (args[i].length() == ONE) {
                pen.println(reg.get(args[i].charAt(j)));
              }
            }
          }
          pen.println(args[i] + " --> " + bfc.get());
        } 
      }
  pen.close();
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
