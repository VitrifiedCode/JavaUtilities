package com.github.vitrifiedcode.javautilities.io;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

    public static BufferedReader newBufReader(InputStream is) { return new BufferedReader(new InputStreamReader(is)); }

    public static BufferedWriter newBufWriter(OutputStream os) { return new BufferedWriter(new OutputStreamWriter(os)); }

    public static List<List<String>> readFile(InputStream is)
    {
        List<List<String>> out = new ArrayList<List<String>>();
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

    public static List<byte[]> readBinaryFile(InputStream is)
    {
        List<byte[]> out = new ArrayList<byte[]>();

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
