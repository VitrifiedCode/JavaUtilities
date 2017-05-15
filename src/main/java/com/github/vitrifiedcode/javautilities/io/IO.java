package com.github.vitrifiedcode.javautilities.io;

import javax.annotation.Nonnull;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple class to simplify and shorten input/output tasks.
 */
@SuppressWarnings("unused")
public final class IO
{
    private IO() {}

    public static final Scanner INPUT = new Scanner(System.in);

    public static <T> void print(final @Nonnull T msg) { System.out.print(msg); }

    public static <T> void println(final @Nonnull T msg) { System.out.println(msg); }

    @Nonnull
    public static <T> String nextLine(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextLine();
    }

    @Nonnull
    public static String nextLine() { return INPUT.nextLine(); }

    @Nonnull
    public static <T> String next(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.next();
    }

    @Nonnull
    public static String next() { return INPUT.next(); }

    public static <T> byte nextByte(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextByte();
    }

    public static byte nextByte() { return INPUT.nextByte(); }

    public static <T> short nextShort(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextShort();
    }

    public static short nextShort() { return INPUT.nextShort(); }

    public static <T> int nextInt(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextInt();
    }

    public static int nextInt() { return INPUT.nextInt(); }

    public static <T> long nextLong(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextLong();
    }

    public static long nextLong() { return INPUT.nextLong(); }

    public static <T> float nextFloat(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextFloat();
    }

    public static float nextFloat() { return INPUT.nextFloat(); }

    public static <T> double nextDouble(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextDouble();
    }

    public static double nextDouble() { return INPUT.nextDouble(); }

    public static <T> boolean nextBoolean(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextBoolean();
    }

    public static boolean nextBoolean() { return INPUT.nextBoolean(); }

    @Nonnull
    public static <T> BigInteger nextBigInteger(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextBigInteger();
    }

    @Nonnull
    public static BigInteger nextBigInteger() { return INPUT.nextBigInteger(); }

    @Nonnull
    public static <T> BigDecimal nextBigDecimal(final @Nonnull T prefix)
    {
        System.out.print(prefix);
        return INPUT.nextBigDecimal();
    }

    @Nonnull
    public static BigDecimal nextBigDecimal() { return INPUT.nextBigDecimal(); }

    @Nonnull
    public static BufferedReader newBufReader(final @Nonnull InputStream is) { return new BufferedReader(new InputStreamReader(is)); }

    @Nonnull
    public static BufferedWriter newBufWriter(final @Nonnull OutputStream os) { return new BufferedWriter(new OutputStreamWriter(os)); }

    @Nonnull
    public static List<List<String>> readFile(final @Nonnull InputStream is)
    {
        List<List<String>> out = new ArrayList<>();
        try
        {
            BufferedReader reader = newBufReader(is);
            String line;
            int counter = 0;
            List<String> tmp = new ArrayList<>();
            while((line = reader.readLine()) != null)
            {
                if(++counter % 990_500_000 == 0)
                {
                    counter = 0;
                    out.add(tmp);
                    tmp = new ArrayList<>();
                }

                tmp.add(line);
            }
            if(counter != 0)
            {
                out.add(tmp);
            }
        }
        catch(IOException e) { e.printStackTrace(); }

        return out;
    }

    @Nonnull
    public static List<byte[]> readBinaryFile(final @Nonnull InputStream is)
    {
        List<byte[]> out = new ArrayList<>();

        final int size = 1_000_000;

        try
        {
            while(is.available() > 0)
            {
                byte[] arr = new byte[size];
                int total = is.read(arr);
                if(total < size)
                {
                    System.arraycopy(arr, 0, arr, 0, total);
                }

                out.add(arr);
            }
        }catch(Exception e) { e.printStackTrace(); }
        return out;
    }

    static
    {
        Runtime.getRuntime().addShutdownHook(new Thread(INPUT::close));
    }
}
