package com.github.vitrifiedcode.javautilities.io;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * A simple class to simplify and shorten input/output tasks.
 */
public final class IO
{
    private IO() {}

    public static final Scanner INPUT = new Scanner(System.in);

    public static <T> void print(T msg) { System.out.print(msg); }

    public static <T> void println(T msg) { System.out.println(msg); }

    public static <T> String nextLine(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextLine();
    }

    public static String nextLine() { return INPUT.nextLine(); }

    public static <T> String next(T prefix)
    {
        System.out.print(prefix);
        return INPUT.next();
    }

    public static String next() { return INPUT.next(); }

    public static <T> byte nextByte(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextByte();
    }

    public static byte nextByte() { return INPUT.nextByte(); }

    public static <T> short nextShort(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextShort();
    }

    public static short nextShort() { return INPUT.nextShort(); }

    public static int nextInt(String prefix)
    {
        System.out.print(prefix);
        return INPUT.nextInt();
    }

    public static int nextInt() { return INPUT.nextInt(); }

    public static <T> long nextLong(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextLong();
    }

    public static long nextLong() { return INPUT.nextLong(); }

    public static <T> float nextFloat(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextFloat();
    }

    public static float nextFloat() { return INPUT.nextFloat(); }

    public static <T> double nextDouble(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextDouble();
    }

    public static double nextDouble() { return INPUT.nextDouble(); }

    public static <T> boolean nextBoolean(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextBoolean();
    }

    public static boolean nextBoolean() { return INPUT.nextBoolean(); }

    public static <T> BigInteger nextBigInteger(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextBigInteger();
    }

    public static BigInteger nextBigInteger() { return INPUT.nextBigInteger(); }

    public static <T> BigDecimal nextBigDecimal(T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextBigDecimal();
    }

    public static BigDecimal nextBigDecimal() { return INPUT.nextBigDecimal(); }

    static
    {
        Runtime.getRuntime().addShutdownHook(new Thread(INPUT::close));
    }
}
