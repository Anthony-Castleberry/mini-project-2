package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * Behind scenes calculator that runs the operations and stores the last value computed.
 *
 * @author Anthony Castleberry
 */
public class BFCalculator {

  /**
   * Hols the last value computed in the calculator, starts at 0.
   */
  private BigFraction lastval = new BigFraction(0, 1);

  /**
   * gets the last value used; 0 if none exist.
   *
   * @return BigFraction
   *
   */
  public BigFraction get() {
    return lastval;
  } // get()

  /**adds val to last value used.
   *
   * @param val
   */
  public void add(BigFraction val) {
    lastval = val.add(lastval);
  } // add(BigFraction)

  /**subtracts val to last value used.
   *
   * @param val
   */
  public void subtract(BigFraction val) {
    lastval = lastval.subtract(val);
  } // subtract(BigFraction)

  /**multiplies val to last value used.
   *
   * @param val
   */
  public void multiply(BigFraction val) {

    if (lastval.getdenom() == BigInteger.ZERO && lastval.getnum() == BigInteger.ZERO) {
      lastval = val;
    } // if

    lastval = val.multiply(lastval);

  } // multiply(BigFraction)

  /**divides val to last value used.
   *
   * @param val
   */
  public void divide(BigFraction val) {

    if (lastval.getdenom() == BigInteger.ZERO && lastval.getnum() == BigInteger.ZERO) {
      lastval = val;
    } // if

    lastval = lastval.divide(val);

  } // divide(BigFraction)

  /**
   * resets the calculator by replacing lastval with 0.
   */
  public void clear() {
    lastval = new BigFraction(0, 1);
  } // clear()
} // class BFCalculator
