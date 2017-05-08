package com.github.vitrifiedcode.javautilities.other;

import com.github.vitrifiedcode.javautilities.math.HashUtils;

import java.util.UUID;

public final class XORUtils
{
    private XORUtils() {}

    /**
     * Get absolute value with special case to ensure value doesn't cause XORed String be the same as the input.
     *
     * @param x byte to get the absolute value of.
     * @return absolute value.
     */
    private static byte abs(byte x) { return (byte) Math.abs(x <= 0 ? x + 1 : x); }

    /**
     * Get absolute value with special case to ensure value doesn't cause XORed String be the same as the input.
     *
     * @param x short to get the absolute value of.
     * @return absolute value.
     */
    private static short abs(short x) { return (short) Math.abs(x <= 0 ? x + 1 : x); }

    /**
     * Get absolute value with special case to ensure value doesn't cause XORed String be the same as the input.
     *
     * @param x int to get the absolute value of.
     * @return absolute value.
     */
    private static int abs(int x) { return Math.abs(x <= 0 ? x + 1 : x); }

    /**
     * Get absolute value with special case to ensure value doesn't cause XORed String be the same as the input.
     *
     * @param x long to get the absolute value of.
     * @return absolute value.
     */
    private static long abs(long x) { return Math.abs(x <= 0 ? x + 1 : x); }

    /**
     * XOR's a String with an byte.
     *
     * @param in  input string to XOR.
     * @param xor byte to use as the XOR code.
     * @return XORed String.
     */
    public static String xor(String in, byte xor)
    {
        xor = abs(xor);
        final char[] chars = in.toCharArray();
        for(int i = 0; i < in.length(); ++i) { chars[i] = (char) (((byte) chars[i]) ^ xor); }
        return new String(chars);
    }

    /**
     * XOR's a String with an short.
     *
     * @param in  input string to XOR.
     * @param xor short to use as the XOR code.
     * @return XORed String.
     */
    public static String xor(String in, short xor)
    {
        xor = abs(xor);
        final char[] chars = in.toCharArray();
        for(int i = 0; i < in.length(); ++i) { chars[i] = (char) (((short) chars[i]) ^ xor); }
        return new String(chars);
    }

    /**
     * XOR's a String with an int.
     *
     * @param in  input string to XOR.
     * @param xor int to use as the XOR code.
     * @return XORed String.
     */
    public static String xor(String in, int xor)
    {
        xor = abs(xor);
        final char[] chars = in.toCharArray();
        for(int i = 0; i < in.length(); ++i) { chars[i] = (char) (((int) chars[i]) ^ xor); }
        return new String(chars);
    }

    /**
     * XOR's a String with a long.
     *
     * @param in  input string to XOR.
     * @param xor long to use as the XOR code.
     * @return XORed String.
     */
    public static String xor(String in, long xor)
    {
        xor = abs(xor);
        final char[] chars = in.toCharArray();
        for(int i = 0; i < in.length(); ++i) { chars[i] = (char) (((long) chars[i]) ^ xor); }
        return new String(chars);
    }

    /**
     * Encrypts a String with an {@link UUID}.
     *
     * @param in  input string to encrypt.
     * @param xor uuid to use as the XOR code.
     * @return encrypt String.
     */
    public static String xorE(final String in, final UUID xor)
    {
        final long least = abs(xor.getLeastSignificantBits());
        final long most = abs(xor.getMostSignificantBits());

        final char[] chars = in.toCharArray();
        for(int i = 0; i < in.length(); ++i) { chars[i] = (char) ((((long) chars[i]) ^ least) ^ most); }
        return new String(chars);
    }

    /**
     * Decrypts a String which was encrypted with an {@link UUID}.
     *
     * @param in  input string to decrypt.
     * @param xor uuid to use as the XOR code.
     * @return decrypted String.
     */
    public static String xorD(final String in, final UUID xor) { return xorE(in, new UUID(xor.getLeastSignificantBits(), xor.getMostSignificantBits())); }

    @Deprecated
    public static String xor(String in, String xor)
    {
        if(in.length() != xor.length()) { return null; }
        final char[] chars = in.toCharArray();
        final char[] xors = xor.toCharArray();
        for(int i = 0; i < in.length(); ++i) { chars[i] = (char) (((int) chars[i]) ^ ((int) xors[i])); }
        return new String(chars);
    }

    public static String xor(String in, Object xor) { return xor(in, HashUtils.hashCode(xor)); }

    public static long xor(byte[] bytes)
    {
        long out = 0;
        for(int i = 0; i < bytes.length; ++i) { out ^= (((long) bytes[i]) << (i % 48)); }
        return out;
    }
}
