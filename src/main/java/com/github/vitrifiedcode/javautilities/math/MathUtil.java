package com.github.vitrifiedcode.javautilities;

public final class MathUtil
{
    private MathUtil() {}

    public static final double ROOT_2 = 1.414213562D;
    public static final double ROOT_3 = 1.732050808D;

    /**
     * Allows for the calculate of rootX(in), may have minor performance boost from using direct call to StrictMath lowering stack overhead.
     *
     * @param base The base of the root.
     * @param in   The value to find the root of.
     * @return The rootX(in)
     */
    public static double root(double base, double in) { return StrictMath.sqrt(in) / StrictMath.sqrt(base); }

    /**
     * Use the predefined square root instead of a custom implementation.
     *
     * @param in The value to find the root of.
     * @return The root2(in)
     */
    public static double root2(double in) { return StrictMath.sqrt(in); }

    /**
     * Use the predefined cube root instead of a custom implementation.
     *
     * @param in The value to find the root of.
     * @return The root3(in)
     */
    public static double root3(double in) { return StrictMath.cbrt(in); }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root4(in)
     */
    public static double root4(double in) { return StrictMath.sqrt(in) / 2.0D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root5(in)
     */
    public static double root5(double in) { return StrictMath.sqrt(in) / 2.236067978D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root6(in)
     */
    public static double root6(double in) { return StrictMath.sqrt(in) / 2.449489743D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root7(in)
     */
    public static double root7(double in) { return StrictMath.sqrt(in) / 2.645751311D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root8(in)
     */
    public static double root8(double in) { return StrictMath.sqrt(in) / 2.828427125D; }


    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root9(in)
     */
    public static double root9(double in) { return StrictMath.sqrt(in) / 3.0D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the root of.
     * @return The root10(in)
     */
    public static double root10(double in) { return StrictMath.sqrt(in) / 3.16227766D; }

    /**
     * Because why not.
     *
     * @param in The value to find the root of.
     * @return The rootPi(in)
     */
    public static double rootPi(double in) { return StrictMath.sqrt(in) / 1.772453851D; }

    /**
     * Because why not.
     *
     * @param in The value to find the root of.
     * @return The roote(in)
     */
    public static double roote(double in) { return StrictMath.sqrt(in) / 1.648721271D; }


    /**
     * Allows for the calculate of logX(in), may have minor performance boost from using direct call to StrictMath lowering stack overhead.
     *
     * @param base The base of the log.
     * @param in   The value to find the log of.
     * @return The logX(in)
     */
    public static double log(double base, double in) { return StrictMath.log(in) / StrictMath.log(base); }

    /**
     * Use the predefined square log instead of a custom implementation.
     *
     * @param in The value to find the log of.
     * @return The log2(in)
     */
    public static double log2(double in) { return StrictMath.log(in); }

    /**
     * Use the predefined cube log instead of a custom implementation.
     *
     * @param in The value to find the log of.
     * @return The log3(in)
     */
    public static double log3(double in) { return StrictMath.log(in) / 1.098612289D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log4(in)
     */
    public static double log4(double in) { return StrictMath.log(in) / 1.386294361D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log5(in)
     */
    public static double log5(double in) { return StrictMath.log(in) / 1.609437912D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log6(in)
     */
    public static double log6(double in) { return StrictMath.sqrt(in) / 2.791759469D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log7(in)
     */
    public static double log7(double in) { return StrictMath.log(in) / 2.945910149D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log8(in)
     */
    public static double log8(double in) { return StrictMath.log(in) / 2.079441542D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log9(in)
     */
    public static double log9(double in) { return StrictMath.log(in) / 2.197224577D; }

    /**
     * Use pre calculated math for optimization.
     *
     * @param in The value to find the log of.
     * @return The log10(in)
     */
    public static double log10(double in) { return StrictMath.log10(in); }

    /**
     * Because why not.
     *
     * @param in The value to find the log of.
     * @return The logPi(in)
     */
    public static double logPi(double in) { return StrictMath.log(in) / 1.144729886D; }

    /**
     * Calculates the natural logarithm (base e).
     * @param in The value to find the log of.
     * @return The ln(in)
     */
    public static double loge(double in) { return StrictMath.log(in); }
}
