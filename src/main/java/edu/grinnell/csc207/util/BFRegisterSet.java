package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * Stores and gets BigFractions from a register of letters.
 *
 * @author Anthony Castleberry
 */
public class BFRegisterSet {


  /**value that corrects the int value so tha mod 26 works when making an int a char.*/
  private static final int ASCII_OFFSET_CHAR = 97;
  /**size of the alphabet.*/
  private static final int ALPHABET_SIZE = 26;

  /**
   * An array to store BigFraction values.
   */
  private BigFraction[] values = new BigFraction[ALPHABET_SIZE];


  /**stores value in specified register.
   *
   * @param register
   * @param val
  */
  public void store(char register, BigFraction val) {
    int index = letter2int(register);
    values[index] = val;
  } // store

  /**gets value from specified register.
   *
   * @return BigFraction associated with register
   * @param register
   */
  public BigFraction get(char register) {

    int index = letter2int(register);
    BigInteger num = values[index].getnum();
    BigInteger denom = values[index].getdenom();

    return new BigFraction(num, denom);
  } // get


  /**Takes an char and returns the corresponding int for a key.
   * @param letter the char to be transformed.
   * @return int
  */
  private static int letter2int(char letter) {
    int i = letter;
    i = (i - ASCII_OFFSET_CHAR) % ALPHABET_SIZE;
    return i;
  } // letter2int
} // class BFRegisterSet
