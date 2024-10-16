package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Holds fractions and allows operations between multiple fractions.
 *
 * @author Anthony Castleberry
 */
public class BigFraction {

  /**
   * Constant holding -1 to avoid magic numbers.
   */
  public static final int NEGATIVE_ONE = -1;

  /**
   * The numerator of the fraction. Can be positive, zero or negative.
   */
  private BigInteger num;


  /**
   * The denominator of the fraction. Must be non-negative.
   */
  private BigInteger denom;

  /**
   * constructs a BigFraction from BigIntegers.
   *
   * @param numerator
   * @param denominator
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {

    this.num = BigFraction.simplifynum(numerator, denominator);

    this.denom = BigFraction.simplifydenom(numerator, denominator);

  } // BigFraction(BigInteger, BigInteger)


  /**
   * constructs a BigFraction from Integers.
   *
   * @param numerator will be converted to Biginteger
   * @param denominator wil be converted to BigInteger
   */
  public BigFraction(int numerator, int denominator) {

    BigInteger intnum = BigInteger.valueOf(numerator);

    BigInteger intdenom = BigInteger.valueOf(denominator);

    this.num = BigFraction.simplifynum(intnum, intdenom);

    this.denom = BigFraction.simplifydenom(intnum, intdenom);

  } // BigFraction(int, int)


  /**
   * Constucts a BigInteger from a Sting containing a fraction.
   *
   * @param str
   */
  public BigFraction(String str) {

    int slash = str.indexOf("/");

    if (slash == NEGATIVE_ONE) {

      this.num = BigInteger.valueOf(Integer.parseInt(str));

      this.denom = BigInteger.ONE;
    } else {

      String top = str.substring(0, slash);

      String bottom = str.substring((slash + 1));


      BigInteger strnum = BigInteger.valueOf((Integer.parseInt(top)));

      BigInteger strdenom = BigInteger.valueOf((Integer.parseInt(bottom)));

      this.num = BigFraction.simplifynum(strnum, strdenom);

      this.denom = BigFraction.simplifydenom(strnum, strdenom);
    } // if
  } // BigFraction



  /**
   * @return the BigFraction in the form of a double
   */
  public double doubleValue() {

    return this.num.doubleValue() / this.denom.doubleValue();

  } // doubleValue()


  /**
   * adds input BigFraction to this BigFraction.
   *
   * @param addend
   * @return new BigFraction resulting from the addition
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

  /**
   * subtracts input BigFraction to this BigFraction.
   *
   * @param subend
   * @return new BigFraction resulting from the subtraction
   */
  public BigFraction subtract(BigFraction subend) {

    BigInteger tempNumerator;

    BigInteger tempDenominator;

    BigInteger resultNumerator;

    BigInteger resultDenominator;


    tempDenominator = this.denom.multiply(subend.denom);

    tempNumerator =

      (this.num.multiply(subend.denom)).subtract(subend.num.multiply(this.denom));

    resultNumerator = BigFraction.simplifynum(tempNumerator, tempDenominator);


    resultDenominator = BigFraction.simplifydenom(tempNumerator, tempDenominator);

    return new BigFraction(resultNumerator, resultDenominator);

  } // subtract(BigFraction)

  /**
   * multiplies input BigFraction to this BigFraction.
   *
   * @param a
   * @return new BigFraction resulting from the multiplication
   */
  public BigFraction multiply(BigFraction a) {

    BigFraction b = new BigFraction((this.num.multiply(a.num)), (this.denom.multiply(a.denom)));

    return new BigFraction(simplifynum(b.num, b.denom), simplifydenom(b.num, b.denom));

  } // multiply(BigFraction)

  /**
   * divides input BigFraction to this BigFraction.
   *
   * @param a
   * @return new BigFraction resulting from the division
   */
  public BigFraction divide(BigFraction a) {

    return this.multiply(new BigFraction(a.denom, a.num));

  } // divide(BigFraction)



  /**
   * @return denominator
   */
  public BigInteger denominator() {

    return this.denom;

  } // denominator()



  /**
   * @return denominator
   */
  public BigInteger numerator() {

    return this.num;

  } // numerator()

  /**
   * @param x numerator
   * @param y denominator
   * @return simplified numerator
   */
  static BigInteger simplifynum(BigInteger x, BigInteger y) {

    BigInteger gcd = x.gcd(y);

    return x.divide(gcd);
  } // simplifynum()

  /**
   * @param x numerator
   * @param y denominator
   * @return simplified denominator
   */
  static BigInteger simplifydenom(BigInteger x, BigInteger y) {

    BigInteger gcd = x.gcd(y);

    return y.divide(gcd);
  } // simplifydenom


  /**
   * @return The BigFraction as a String in the from "num/denom"
   */
  public String toString() {

    // Special case: It's zero

    if (this.num.equals(BigInteger.ZERO)) {

      return "0";

    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {

      return this.num.toString();

    } // if it's zero

    return this.num + "/" + this.denom;

  } // toString()

  /**
   * @return numerator of this BigFraction
   */
  public BigInteger getnum() {
    return this.num;
  } // getnum()

  /**
   * @return denominator of this BigFraction
   */
  public BigInteger getdenom() {
    return this.denom;
  } // getdenom()

} // class BigFraction
