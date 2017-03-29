package com.github.vitrifiedcode.javautilities.math;

import com.github.vitrifiedcode.javautilities.reflection.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings({ "unused", "UnnecessaryBoxing" })
public final class MathUtil
{


    private MathUtil() {}


    public static final double PHI = 1.618033989;

    public static final double ROOT_2 = 1.414213562D;
    public static final double ROOT_3 = 1.732050808D;

    public static final Integer ZERO = new Integer(0);
    public static final Integer ONE = new Integer(1);

    /**
     * A table of sin values computed from 0 (inclusive) to 2*pi (exclusive), with steps of 2*PI / 65536.
     */
    private static final float[] SIN_TABLE = new float[65536];

    /**
     * Though it looks like an array, this is really more like a mapping.  Key (index of this array) is the upper 5 bits
     * of the result of multiplying a 32-bit unsigned integer by the B(2, 5) De Bruijn sequence 0x077CB531.  Value
     * (value stored in the array) is the unique index (from the right) of the leftmost one-bit in a 32-bit unsigned
     * integer that can cause the upper 5 bits to get that value.  Used for highly optimized "find the log-base-2 of
     * this number" calculations.
     */
    private static final int[] MULTIPLY_DE_BRUIJN_BIT_POSITION;
    private static final double FRAC_BIAS;
    private static final double[] ASINE_TAB;
    private static final double[] COS_TAB;

    static
    {
        for(int i = 0; i < 65536; ++i) { SIN_TABLE[i] = (float) Math.sin(((double) i) * Math.PI * 2.0D / 65536.0D); }

        SIN_TABLE[0] = 0;
        SIN_TABLE[16384] = 1;
        SIN_TABLE[32768] = 0;
        SIN_TABLE[49152] = 1;

        MULTIPLY_DE_BRUIJN_BIT_POSITION = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
        FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
        ASINE_TAB = new double[257];
        COS_TAB = new double[257];

        for(int i = 0; i < 257; ++i)
        {
            double d0 = ((double) i) / 256.0D;
            double d1 = Math.asin(d0);
            COS_TAB[i] = Math.cos(d1);
            ASINE_TAB[i] = d1;
        }
    }

    //region Degrees & Radions
    public static double DEG2RAD = Math.PI / 180.0D;
    public static double RAD2DEG = 180.0D / Math.PI;
    public static double DEG2RAD_NP = 1.0D / 180.0D;
    public static double RAD2DEG_NP = 180.0D;

    public static double deg2rad(final double in) { return in * DEG2RAD; }

    public static double rad2deg(final double in) { return in * RAD2DEG; }

    public static double deg2radNP(final double in) { return in * DEG2RAD_NP; }

    public static double rad2degNP(final double in) { return in * RAD2DEG_NP; }
    //endregion

    //region Random
    public static final Random RANDOM = new Random();

    public static void updateRandom()
    {
        try
        {
            Field rand = ReflectionUtil.getField(MathUtil.class, "RANDOM");
            ReflectionUtil.setFinal(rand, false);
            rand.set(null, new Random());
            ReflectionUtil.setFinal(rand, false);
        }
        catch(IllegalAccessException | NoSuchFieldException ignored) {}
    }

    public static void updateRandom(final long seed)
    {
        try
        {
            Field rand = ReflectionUtil.getField(MathUtil.class, "RANDOM");
            ReflectionUtil.setFinal(rand, false);
            rand.set(null, new Random(seed));
            ReflectionUtil.setFinal(rand, false);
        }
        catch(IllegalAccessException | NoSuchFieldException ignored) {}
    }
    //endregion

    /**
     * sin looked up in a table
     */
    public static float sin(final float value)
    {
        return SIN_TABLE[(int) (value * 10430.378F) & 65535];
    }

    /**
     * cos looked up in the sin table with the appropriate offset
     */
    public static float cos(float value)
    {
        return SIN_TABLE[(int) (value * 10430.378F + 16384.0F) & 65535];
    }

    public static float approachLinear(float a, float b, float max) { return a > b ? a - b < max ? b : a - max : b - a < max ? b : a + max; }

    public static double approachLinear(double a, double b, double max) { return a > b ? a - b < max ? b : a - max : b - a < max ? b : a + max; }

    public static float interpolate(float a, float b, float d) { return (float) approachExp(a, b, d); }

    public static double interpolate(double a, double b, double d) { return approachExp(a, b, d); }

    public static double approachExp(double a, double b, double ratio) { return a + (b - a) * ratio; }

    public static double approachExp(double a, double b, double ratio, double cap)
    {
        double d = (b - a) * ratio;
        if(Math.abs(d) > cap) { d = Math.signum(d) * cap; }
        return a + d;
    }

    public static double retreatExp(double a, double b, double c, double ratio, double kick)
    {
        double d = (Math.abs(c - a) + kick) * ratio;
        if(d > Math.abs(b - a)) { return b; }
        return a + Math.signum(b - a) * d;
    }

    public static double clip(double value, double min, double max) { return value > max ? max : (value < min ? min : value); }

    public static boolean between(double a, double x, double b)
    {
        return a <= x && x <= b;
    }

    public static int approachExpI(int a, int b, double ratio)
    {
        int r = (int) Math.round(approachExp(a, b, ratio));
        return r == a ? b : r;
    }

    public static int retreatExpI(int a, int b, int c, double ratio, int kick)
    {
        int r = (int) Math.round(retreatExp(a, b, c, ratio, kick));
        return r == a ? b : r;
    }

    /**
     * Unchecked implementation to round a number. Parameter should be known to be valid in advance.
     */
    public static int round(double d) { return (int) (d + 0.5D); }

    public static int ceilU(float value) { return (int) (value + 0.999999F); }

    public static int ceil(float value)
    {
        int i = (int) value;
        return value > (float) i ? i + 1 : i;
    }

    /**
     * Unchecked implementation to round a number up. Parameter should be known to be valid in advance.
     */
    public static int ceilU(double d) { return (int) (d + 0.999999D); }

    public static int ceil(double value)
    {
        int i = (int) value;
        return value > (double) i ? i + 1 : i;
    }

    /**
     * Unchecked implementation to round a number down. Parameter should be known to be valid in advance.
     * Returns the greatest integer less than or equal to the float argument
     */
    public static int floor(float value)
    {
        int i = (int) value;
        return value < (float) i ? i - 1 : i;
    }

    /**
     * returns value cast as an int, and no greater than Integer.MAX_VALUE-1024
     */
    public static int fastFloor(double value) { return ((int) (value + 1024.0D)) - 1024; }

    /**
     * Returns the greatest integer less than or equal to the double argument
     */
    public static int floor(double value)
    {
        int i = (int) value;
        return value < (double) i ? i - 1 : i;
    }

    /**
     * Long version of floor()
     */
    public static long lfloor(double value)
    {
        long i = (long) value;
        return value < (double) i ? i - 1L : i;
    }

    public static int absFloor(double value)
    {
        return (int) (value >= 0.0D ? value : -value + 1.0D);
    }

    /**
     * Unchecked implementation to determine the smaller of two Floats. Parameters should be known to be valid in advance.
     */
    public static float minF(float a, float b)
    {
        return a < b ? a : b;
    }

    public static float minF(int a, float b)
    {
        return a < b ? a : b;
    }

    public static float minF(float a, int b)
    {
        return a < b ? a : b;
    }

    /**
     * Unchecked implementation to determine the larger of two Floats. Parameters should be known to be valid in advance.
     */
    public static float maxF(float a, float b)
    {
        return a > b ? a : b;
    }

    public static float maxF(int a, float b)
    {
        return a > b ? a : b;
    }

    public static float maxF(float a, int b)
    {
        return a > b ? a : b;
    }

    public static double maxAbs(double a, double b)
    {
        if(a < 0.0D) { a = -a; }
        if(b < 0.0D) { b = -b; }
        return a > b ? a : b;
    }

    public static byte setBit(byte mask, int bit, boolean value) { return (byte) (mask | ((value ? 1 : 0) << bit)); }

    public static boolean getBit(byte mask, int bit) { return (mask & 1 << bit) != 0; }

    public static short setBit(short mask, int bit, boolean value) { return (short) (mask | ((value ? 1 : 0) << bit)); }

    public static boolean getBit(short mask, int bit) { return (mask & 1 << bit) != 0; }

    public static int setBit(int mask, int bit, boolean value) { return mask | ((value ? 1 : 0) << bit); }

    public static boolean getBit(int mask, int bit) { return (mask & 1 << bit) != 0; }

    public static long setBit(long mask, int bit, boolean value) { return mask | ((value ? 1 : 0) << bit); }

    public static boolean getBit(long mask, int bit) { return (mask & 1 << bit) != 0; }

    //region Logarithmic Functions

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
    public static double log2(double in) { return StrictMath.log(in) / 0.6931471806D; }

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
     *
     * @param in The value to find the log of.
     * @return The ln(in)
     */
    public static double loge(double in) { return StrictMath.log(in); }

    /**
     * Calculates the natural logarithm (base e).
     *
     * @param in The value to find the log of.
     * @return The ln(in)
     */
    public static double ln(double in) { return StrictMath.log(in); }
    //endregion

    //region Exponentiation Functions
    public static double pow2(double x) { return x * x; }

    public static double pow3(double x) { return x * x * x; }

    public static double pow4(double x) { return x * x * x * x; }

    public static double pow5(double x) { return x * x * x * x * x; }

    public static double pow6(double x) { return x * x * x * x * x * x; }

    public static double pow7(double x) { return x * x * x * x * x * x * x; }

    public static double pow8(double x) { return x * x * x * x * x * x * x * x; }

    public static double pow9(double x) { return x * x * x * x * x * x * x * x * x; }

    public static double pow10(double x) { return x * x * x * x * x * x * x * x * x * x; }

    public static float pow2f(float x) { return x * x; }

    public static float pow3f(float x) { return x * x * x; }

    public static float pow4f(float x) { return x * x * x * x; }

    public static float pow5f(float x) { return x * x * x * x * x; }

    public static float pow6f(float x) { return x * x * x * x * x * x; }

    public static float pow7f(float x) { return x * x * x * x * x * x * x; }

    public static float pow8f(float x) { return x * x * x * x * x * x * x * x; }

    public static float pow9f(float x) { return x * x * x * x * x * x * x * x * x; }

    public static float pow10f(float x) { return x * x * x * x * x * x * x * x * x * x; }
    //endregion

    /**
     * Returns the value of the first parameter, clamped to be within the lower and upper limits given by the second and
     * third parameters.
     */
    public static int clamp(int num, int min, int max) { return num < min ? min : (num > max ? max : num); }

    /**
     * Returns the value of the first parameter, clamped to be within the lower and upper limits given by the second and
     * third parameters
     */
    public static float clamp(float num, float min, float max) { return num < min ? min : (num > max ? max : num); }

    public static double clamp(double num, double min, double max) { return num < min ? min : (num > max ? max : num); }

    public static double clampedLerp(double lowerBnd, double upperBnd, double slide) { return slide < 0.0D ? lowerBnd : (slide > 1.0D ? upperBnd : lowerBnd + (upperBnd - lowerBnd) * slide); }

    /**
     * Maximum of the absolute value of two numbers.
     */
    public static double absMax(double x, double y)
    {
        if(x < 0.0D) { x = -x; }
        if(y < 0.0D) { y = -y; }
        return x > y ? x : y;
    }

    /**
     * Buckets an integer with specified bucket sizes.
     */
    public static int intFloorDiv(int x, int y) { return x < 0 ? -((-x - 1) / y) - 1 : x / y; }

    public static int nextInt(Random random, int minimum, int maximum) { return minimum >= maximum ? minimum : random.nextInt(maximum - minimum + 1) + minimum; }

    public static float nextFloat(Random random, float minimum, float maximum) { return minimum >= maximum ? minimum : random.nextFloat() * (maximum - minimum) + minimum; }

    public static double nextDouble(Random random, double minimum, double maximum) { return minimum >= maximum ? minimum : random.nextDouble() * (maximum - minimum) + minimum; }

    public static <T extends Number> double average(T[] values)
    {
        double i = 0.0D;
        for(T j : values) { i += j.doubleValue(); }
        return i / (double) values.length;
    }

    public static float average(float[] values)
    {
        double i = 0.0D;
        for(float j : values) { i += (double) j; }
        return (float) (i / (double) values.length);
    }

    public static float average(byte[] values)
    {
        long i = 0L;
        for(byte j : values) { i += (long) j; }
        return ((float) i) / (float) values.length;
    }

    public static byte averageI(byte[] values)
    {
        long i = 0L;
        for(byte j : values) { i += (long) j; }
        return (byte) (i / (long) values.length);
    }

    public static boolean epsilonEquals(float x, float y) { return Math.abs(y - x) < 1.0E-5F; }

    public static int positiveModulo(int numerator, int denominator) { return (numerator % denominator + denominator) % denominator; }

    public static float positiveModulo(float numerator, float denominator) { return (numerator % denominator + denominator) % denominator; }

    public static double positiveModulo(double numerator, double denominator) { return (numerator % denominator + denominator) % denominator; }

    /**
     * the angle is reduced to an angle between -180 and +180 by mod, and a 360 check
     */
    public static float wrapDegrees(float value)
    {
        value %= 360.0F;

        if(value >= 180.0F) { value -= 360.0F; }
        else if(value < -180.0F) { value += 360.0F; }

        return value;
    }

    /**
     * the angle is reduced to an angle between -180 and +180 by mod, and a 360 check
     */
    public static double wrapDegrees(double value)
    {
        value %= 360.0D;

        if(value >= 180.0D) { value -= 360.0D; }
        else if(value < -180.0D) { value += 360.0D; }

        return value;
    }

    /**
     * Adjust the angle so that his value is in range [-180;180[
     */
    public static int clampAngle(int angle)
    {
        angle %= 360;

        if(angle >= 180) { angle -= 360; }
        else if(angle < -180) { angle += 360; }

        return angle;
    }

    /**
     * Returns the input value rounded up to the next highest power of two.
     */
    public static int smallestEncompassingPowerOfTwo(int value)
    {
        int i = value - 1;
        i |= i >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i + 1;
    }

    /**
     * Is the given value a power of two?  (1, 2, 4, 8, 16, ...)
     */
    public static boolean isPowerOfTwo(int value) { return value != 0 && (value & value - 1) == 0; }

    /**
     * Uses a B(2, 5) De Bruijn sequence and a lookup table to efficiently calculate the log-base-two of the given
     * value. Optimized for cases where the input value is a power-of-two. If the input value is not a power-of-two,
     * then subtract 1 from the return value.
     */
    public static int log2DeBruijn(int value)
    {
        value = isPowerOfTwo(value) ? value : smallestEncompassingPowerOfTwo(value);
        return MULTIPLY_DE_BRUIJN_BIT_POSITION[(int) ((long) value * 125613361L >> 27) & 31];
    }

    /**
     * Efficiently calculates the floor of the base-2 log of an integer value.  This is effectively the index of the
     * highest bit that is set.  For example, if the number in binary is 0...100101, this will return 5.
     */
    public static int log2(int value)
    {
        /**
         * Uses a B(2, 5) De Bruijn sequence and a lookup table to efficiently calculate the log-base-two of the given
         * value. Optimized for cases where the input value is a power-of-two. If the input value is not a power-of-two,
         * then subtract 1 from the return value.
         */
        return log2DeBruijn(value) - (isPowerOfTwo(value) ? 0 : 1);
    }

    /**
     * Rounds the first parameter up to the next interval of the second parameter.
     * <p>
     * For instance, {@code roundUp(1, 4)} returns 4; {@code roundUp(0, 4)} returns 0; and {@code roundUp(4, 4)} returns
     * 4.
     */
    public static int roundUp(int number, int interval)
    {
        if(interval == 0) { return 0; }
        else if(number == 0) { return interval; }
        else
        {
            if(number < 0) { interval *= -1; }
            int i = number % interval;
            return i == 0 ? number : number + interval - i;
        }
    }

    public static long getCoordinateRandom(int x, int y, int z)
    {
        long i = (long) (x * 3129871) ^ (long) z * 116129781L ^ (long) y;
        i = i * i * 42317861L + i * 11L;
        return i;
    }

    /**
     * Makes an integer color from the given red, green, and blue float values
     */
    public static int rgb(float r, float g, float b) { return rgb(floor(r * 255.0F), floor(g * 255.0F), floor(b * 255.0F)); }

    /**
     * Makes a single int color with the given red, green, and blue values.
     */
    public static int rgb(int r, int g, int b) { return (((r << 8) + g) << 8) + b; }

    public static int multiplyColor(int hexColor0, int hexColor1)
    {
        int i = (hexColor0 & 16711680) >> 16;
        int j = (hexColor1 & 16711680) >> 16;
        int k = (hexColor0 & 65280) >> 8;
        int l = (hexColor1 & 65280) >> 8;
        int i1 = (hexColor0 & 255);
        int j1 = (hexColor1 & 255);
        int k1 = (int) ((float) i * (float) j / 255.0F);
        int l1 = (int) ((float) k * (float) l / 255.0F);
        int i2 = (int) ((float) i1 * (float) j1 / 255.0F);
        return hexColor0 & -16777216 | k1 << 16 | l1 << 8 | i2;
    }

    /**
     * Gets the decimal portion of the given double. For instance, {@code frac(5.5)} returns {@code .5}.
     */
    public static double frac(double number) { return number - Math.floor(number); }

    public static UUID getRandomUUID(Random rand)
    {
        long i = rand.nextLong() & -61441L | 16384L;
        long j = rand.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
        return new UUID(i, j);
    }

    /**
     * Generates a random UUID using the shared random
     */
    public static UUID getRandomUUID() { return getRandomUUID(RANDOM); }

    public static double pct(double x, double y, double z) { return (x - y) / (z - y); }

    public static double atan2(double x, double y)
    {
        double d0 = (y * y) + (x * x);

        if(Double.isNaN(d0)) { return Double.NaN; }
        else
        {
            boolean flag = x < 0.0D;
            if(flag) { x = -x; }

            boolean flag1 = y < 0.0D;
            if(flag1) { y = -y; }

            boolean flag2 = x > y;
            if(flag2)
            {
                double tmp = y;
                y = x;
                x = tmp;
            }

            double d9 = fastInvSqrt(d0);
            y = y * d9;
            x = x * d9;

            double d2 = FRAC_BIAS + x;
            int i = (int) Double.doubleToRawLongBits(d2);

            double d3 = ASINE_TAB[i];
            double d4 = COS_TAB[i];
            double d5 = (x * d4) - (y * x);
            double d6 = (6.0D + (d5 * d5)) * d5 * 0.16666666666666666D;
            double d7 = d3 + d6;

            if(flag2) { d7 = (Math.PI / 2D) - d7; }
            if(flag1) { d7 = Math.PI - d7; }
            if(flag) { d7 = -d7; }

            return d7;
        }
    }


    public static double fastInvSqrt(double value)
    {
        double d0 = 0.5D * value;
        long i = Double.doubleToRawLongBits(value);
        i = 6910469410427058090L - (i >> 1);
        value = Double.longBitsToDouble(i);
        value *= (1.5D - d0 * value * value);
        return value;
    }

    public static int hsvToRGB(float hue, float saturation, float value)
    {
        int i = ((int) (hue * 6.0F)) % 6;
        float f = hue * 6.0F - ((float) i);
        float f1 = value * (1.0F - saturation);
        float f2 = value * (1.0F - f * saturation);
        float f3 = value * (1.0F - (1.0F - f) * saturation);
        float f4;
        float f5;
        float f6;

        switch(i)
        {
            case 0:
                f4 = value;
                f5 = f3;
                f6 = f1;
                break;
            case 1:
                f4 = f2;
                f5 = value;
                f6 = f1;
                break;
            case 2:
                f4 = f1;
                f5 = value;
                f6 = f3;
                break;
            case 3:
                f4 = f1;
                f5 = f2;
                f6 = value;
                break;
            case 4:
                f4 = f3;
                f5 = f1;
                f6 = value;
                break;
            case 5:
                f4 = value;
                f5 = f1;
                f6 = f2;
                break;
            default:
                throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        }

        int j = clamp((int) (f4 * 255.0F), 0, 255);
        int k = clamp((int) (f5 * 255.0F), 0, 255);
        int l = clamp((int) (f6 * 255.0F), 0, 255);
        return j << 16 | k << 8 | l;
    }

    public static int hash(int value) { return (((((value >>> 16) * -2048144789) >>> 13) * -1028477387) >>> 16); }

    public static int barrelShiftL(int var, int shift) { return shift == 0 ? var : (var << shift) | (var >> (32 - shift)); }

    public static int barrelShiftR(int var, int shift) { return shift == 0 ? var : (var >> shift) | (var << (32 - shift)); }

    public static int barrelShiftRU(int var, int shift) { return shift == 0 ? var : (var >>> shift) | (var << (32 - shift)); }
}
