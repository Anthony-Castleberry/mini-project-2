package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Holds fractions and allows operations between multiple fractions.
 *
 * @author Anthony Castleberry
 */
public class BigFraction {

public final static int NEGATIVE_ONE = -1;

  /** The numerator of the fraction. Can be positive, zero or negative. */

  BigInteger num;


  /** The denominator of the fraction. Must be non-negative. */

  BigInteger denom;

  public BigFraction(BigInteger numerator, BigInteger denominator) {

    this.num = BigFraction.simplifynum(numerator, denominator);

    this.denom = BigFraction.simplifydenom(numerator, denominator);

  } // BigFraction(BigInteger, BigInteger)

  
  public BigFraction(int numerator, int denominator) {

    BigInteger num = BigInteger.valueOf(numerator);

    BigInteger denom = BigInteger.valueOf(denominator);

    this.num = BigFraction.simplifynum(num, denom);

    this.denom = BigFraction.simplifydenom(num, denom);

  } // BigFraction(int, int)



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


  

  public double doubleValue() {

    return this.num.doubleValue() / this.denom.doubleValue();

  } // doubleValue()


  
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


    tempDenominator = this.denom.multiply(subend.denom);

    tempNumerator =

      (this.num.multiply(subend.denom)).subtract(subend.num.multiply(this.denom));

    resultNumerator = BigFraction.simplifynum(tempNumerator, tempDenominator);

    
    resultDenominator = BigFraction.simplifydenom(tempNumerator, tempDenominator);

    return new BigFraction(resultNumerator, resultDenominator);

  } // subtract(BigFraction)


  public BigFraction multiply(BigFraction a){

    BigFraction b = new BigFraction((this.num.multiply(a.num)),(this.denom.multiply(a.denom)));

    return new BigFraction(simplifynum(b.num, b.denom), simplifydenom(b.num, b.denom));

  } // multiply(BigFraction)

  public BigFraction divide(BigFraction a){

    return this.multiply(new BigFraction(a.denom, a.num));

  } // divide(BigFraction)


 

  public BigInteger denominator() {

    return this.denom;

  } // denominator()




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

} // class BigFraction