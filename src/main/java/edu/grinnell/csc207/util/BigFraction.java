package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class BigFraction {

  // +------------------+---------------------------------------------

  // | Design Decisions |

  // +------------------+


  /*

   * (1) Denominators are always positive. Therefore, negative fractions

   * are represented with a negative numerator. Similarly, if a fraction

   * has a negative numerator, it is negative.

   *

   * (2) Fractions are not necessarily stored in simplified form. To

   * obtain a fraction in simplified form, one must call the `simplify`

   * method.

   */


  // +-----------+---------------------------------------------------

  // | Constants |

  // +-----------+


  /** The default numerator when creating fractions. */

  // private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);

  private static final int NEGATIVE_ONE = -1;
  /** The default denominator when creating fractions. */

  // private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);


  // +--------+-------------------------------------------------------

  // | Fields |

  // +--------+


  /** The numerator of the fraction. Can be positive, zero or negative. */

  BigInteger num;


  /** The denominator of the fraction. Must be non-negative. */

  BigInteger denom;


  // +--------------+-------------------------------------------------

  // | Constructors |

  // +--------------+


  /**DEFAULT_DENOMINATOR

   * Build a new fraction with numerator num and denominator denom.

   *

   * Warning! Not yet stable.

   *

   * @param numerator

   *   The numerator of the fraction.

   * @param denominator

   *   The denominator of the fraction.

   */

  public BigFraction(BigInteger numerator, BigInteger denominator) {

    this.num = BigFraction.simplifynum(numerator, denominator);

    this.denom = BigFraction.simplifydenom(numerator, denominator);

  } // BigFraction(BigInteger, BigInteger)


  /**

   * Build a new fraction with numerator num and denominator denom.

   *

   * Warning! Not yet stable.

   *

   * @param numerator

   *   The numerator of the fraction.

   * @param denominator

   *   The denominator of the fraction.

   */

  public BigFraction(int numerator, int denominator) {

    BigInteger num = BigInteger.valueOf(numerator);

    BigInteger denom = BigInteger.valueOf(denominator);

    this.num = BigFraction.simplifynum(num, denom);

    this.denom = BigFraction.simplifydenom(num, denom);

  } // BigFraction(int, int)


  /**

   * Build a new fraction by parsing a string.

   *

   * Warning! Not yet implemented.

   *

   * @param str

   *   The fraction in string form

   */

  public BigFraction(String str) {

    int slash = str.indexOf("/");

    if (slash == NEGATIVE_ONE) {

      this.num = BigInteger.valueOf(Integer.parseInt(str));

      this.denom = BigInteger.ONE;
    } else {

        String top = str.substring(0, slash);

        String bottom = str.substring((slash + 1));


        BigInteger num = BigInteger.valueOf((Integer.parseInt(top)));

        BigInteger denom = BigInteger.valueOf((Integer.parseInt(bottom)));

        this.num = BigFraction.simplifynum(num, denom);

        this.denom = BigFraction.simplifydenom(num, denom);
      }
  } // BigFraction


  // +---------+------------------------------------------------------

  // | Methods |

  // +---------+


  /**

   * Express this fraction as a double.

   *

   * @return the fraction approxiamted as a double.

   */

  public double doubleValue() {

    return this.num.doubleValue() / this.denom.doubleValue();

  } // doubleValue()


  /**

   * Add another faction to this fraction.

   *

   * @param addend

   *   The fraction to add.

   *

   * @return the result of the addition.

   */

  public BigFraction add(BigFraction addend) {

    BigInteger tempNumerator;

    BigInteger tempDenominator;

    BigInteger resultNumerator;

    BigInteger resultDenominator;
    // The denominator of the result is the product of this object's

    // denominator and addend's denominator

    tempDenominator = this.denom.multiply(addend.denom);

    // The numerator is more complicated

    tempNumerator =

      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));



    resultNumerator = BigFraction.simplifynum(tempNumerator, tempDenominator);
    // Return the computed value
    
    resultDenominator = BigFraction.simplifydenom(tempNumerator, tempDenominator);

    return new BigFraction(resultNumerator, resultDenominator);

  } // add(BigFraction)

  public BigFraction subtract(BigFraction subend) {

    BigInteger tempNumerator;

    BigInteger tempDenominator;

    BigInteger resultNumerator;

    BigInteger resultDenominator;
    // The denominator of the result is the product of this object's

    // denominator and addend's denominator

    tempDenominator = this.denom.multiply(subend.denom);

    // The numerator is more complicated

    tempNumerator =

      (this.num.multiply(subend.denom)).subtract(subend.num.multiply(this.denom));



    resultNumerator = BigFraction.simplifynum(tempNumerator, tempDenominator);
    // Return the computed value
    
    resultDenominator = BigFraction.simplifydenom(tempNumerator, tempDenominator);

    return new BigFraction(resultNumerator, resultDenominator);

  } // add(BigFraction)


  public BigFraction multiply(BigFraction a){

    BigFraction b = new BigFraction((this.num.multiply(a.num)),(this.denom.multiply(a.denom)));

    return new BigFraction(simplifynum(b.num, b.denom), simplifydenom(b.num, b.denom));

  } // multiply(BigFraction)

  public BigFraction divide(BigFraction a){

    return this.multiply(new BigFraction(a.denom, a.num));

  } // multiply(BigFraction)


  public BigFraction fractional(){

    return new BigFraction(this.num.mod(this.denom), this.denom);

  }


  /**

   * Get the denominator of this fraction.

   *

   * @return the denominator

   */

  public BigInteger denominator() {

    return this.denom;

  } // denominator()


  /**

   * Get the numerator of this fraction.

   *

   * @return the numerator

   */

  public BigInteger numerator() {

    return this.num;

  } // numerator()


  static BigInteger simplifynum(BigInteger x, BigInteger y) {

    BigInteger gcd = x.gcd(y);

    return x.divide(gcd);
  }

  static BigInteger simplifydenom(BigInteger x, BigInteger y) {

    BigInteger gcd = x.gcd(y);

    return y.divide(gcd);
  }


  /**

   * Convert this fraction to a string for ease of printing.

   *

   * @return a string that represents the fraction.

   */

  public String toString() {

    // Special case: It's zero

    if (this.num.equals(BigInteger.ZERO)) {

      return "0";

    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {

      return this.num.toString();

    } // if it's zero


    // Lump together the string represention of the numerator,

    // a slash, and the string representation of the denominator

    // return this.num.toString().concat("/").concat(this.denom.toString());

    return this.num + "/" + this.denom;

  } // toString()

} // class BigFraction