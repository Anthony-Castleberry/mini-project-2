package edu.grinnell.csc207.util;

import java.math.BigInteger;


public class BFCalculator {

  private static final BigInteger ZERO = BigInteger.valueOf(0);

  BigFraction lastval = new BigFraction(0,1);

  /**gets the last value used; 0 if none exist.*/
  public BigFraction get() {
    return lastval; 
  } // get

  /**adds val to last value used.
   * 
   * @param val
   */
  public void add(BigFraction val) {
    lastval = val.add(lastval);
  } // add

  /**subtracts val to last value used.
   * 
   * @param val
   */
  public void subtract(BigFraction val) {
    lastval = lastval.subtract(val);
  } // subtract

  /**multiplies val to last value used.
   * 
   * @param val
   */
  public void multiply(BigFraction val) {

    if (lastval.denom == ZERO && lastval.num == ZERO) {
      lastval = val;
    }

    lastval = val.multiply(lastval);

  } // multiply

  /**divides val to last value used.
   * 
   * @param val
   */
  public void divide(BigFraction val) {

    if (lastval.denom == ZERO && lastval.num == ZERO) {
      lastval = val;
    }

    lastval = lastval.divide(val);

  } // divide

  public void clear() {
    lastval = new BigFraction(0,1);
  }
}
