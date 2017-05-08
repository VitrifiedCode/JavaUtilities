package com.github.vitrifiedcode.javautilities.object;

@SuppressWarnings("WeakerAccess")
public final class BitConverter
{
    //region Bit Modification
    //region Get
    public static byte getBit(byte n, int index)
    {
        return (byte) ((n >> index) & 0x01);
    }

    public static byte getBit(short n, int index)
    {
        return (byte) ((n >> index) & 0x01);
    }

    public static byte getBit(int n, int index)
    {
        return (byte) ((n >> index) & 0x01);
    }

    public static byte getBit(long n, int index)
    {
        return (byte) ((n >> index) & 0x01);
    }
    //endregion

    //region Set
    public static byte setBit(byte n, int index, boolean value)
    {
        if(index >= Byte.SIZE || index < 0) { throw new IllegalArgumentException("Index must be less than " + Byte.SIZE + " and larger than or equal to 0."); }
        byte tmp = (byte) (1 << index);
        return (byte) (value ? (n & ~tmp) | tmp : n & ~tmp);
    }

    public static short setBit(short n, int index, boolean value)
    {
        if(index >= Short.SIZE || index < 0) { throw new IllegalArgumentException("Index must be less than " + Short.SIZE + " and larger than or equal to 0."); }
        short tmp = (short) (1 << index);
        return (short) (value ? (n & ~tmp) | tmp : n & ~tmp);
    }

    public static int setBit(int n, int index, boolean value)
    {
        if(index >= Integer.SIZE || index < 0) { throw new IllegalArgumentException("Index must be less than " + Integer.SIZE + " and larger than or equal to 0."); }
        int tmp = 1 << index;
        return (value ? (n & ~tmp) | tmp : n & ~tmp);
    }

    public static long setBit(long n, int index, boolean value)
    {
        if(index >= Long.SIZE || index < 0) { throw new IllegalArgumentException("Index must be less than " + Long.SIZE + " and larger than or equal to 0."); }
        long tmp = (long) (1 << index);
        return (long) (value ? (n & ~tmp) | tmp : n & ~tmp);
    }
    //endregion
    //endregion

    //region To Bytes
    public static byte[] getBytes(boolean x)
    {
        return new byte[] { (byte) (x ? 1 : 0) };
    }

    public static byte[] getBytes(char c)
    {
        return new byte[] { (byte) (c & 0xff), (byte) (c >> 8 & 0xff) };
    }

    public static byte[] getBytes(short x)
    {
        return new byte[] { (byte) (x >>> 8), (byte) x };
    }

    public static byte[] getBytes(int x)
    {
        return new byte[] { (byte) (x >>> 24), (byte) (x >>> 16), (byte) (x >>> 8), (byte) x };
    }

    public static byte[] getBytes(long x)
    {
        return new byte[] { (byte) (x >>> 56), (byte) (x >>> 48), (byte) (x >>> 40), (byte) (x >>> 32), (byte) (x >>> 24), (byte) (x >>> 16), (byte) (x >>> 8), (byte) x };
    }

    public static byte[] getBytes(float x)
    {
        return getBytes(Float.floatToRawIntBits(x));
    }

    public static byte[] getBytes(double x)
    {
        return getBytes(Double.doubleToRawLongBits(x));
    }

    public static byte[] getBytes(String x)
    {
        return x.getBytes();
    }
    //endregion

    //region Float Bits
    public static long floatToInt32Bits(float x)
    {
        return Float.floatToRawIntBits(x);
    }

    public static double int32BitsToFloat(int x)
    {
        return Float.intBitsToFloat(x);
    }

    public static long doubleToInt64Bits(double x)
    {
        return Double.doubleToRawLongBits(x);
    }

    public static double int64BitsToDouble(long x)
    {
        return Double.longBitsToDouble(x);
    }
    //endregion

    //region From Bytes
    public static boolean toBoolean(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 1) { throw new IllegalArgumentException("The length of the byte array must be 1 byte long."); }
        return bytes[index] != 0;
    }

    public static short toInt16(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 8) { throw new IllegalArgumentException("The length of the byte array must be 8 bytes long."); }
        return (short) ((0xff & bytes[index]) << 8 | (0xff & bytes[index + 1]));
    }

    public static int toInt32(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 4) { throw new IllegalArgumentException("The length of the byte array must be 4 bytes long."); }
        return (((0xff & bytes[index]) << 24) |
                ((0xff & bytes[index + 1]) << 16) |
                ((0xff & bytes[index + 2]) << 8) |
                (0xff & bytes[index + 3]));
    }

    public static long toInt64(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 8) { throw new IllegalArgumentException("The length of the byte array must be 8 bytes long."); }
        return (((long) (0xff & bytes[index]) << 56) |
                ((long) (0xff & bytes[index + 1]) << 48) |
                ((long) (0xff & bytes[index + 2]) << 40) |
                ((long) (0xff & bytes[index + 3]) << 32) |
                ((long) (0xff & bytes[index + 4]) << 24) |
                ((long) (0xff & bytes[index + 5]) << 16) |
                ((long) (0xff & bytes[index + 6]) << 8) |
                (long) (0xff & bytes[index + 7]));
    }

    public static float toFloat(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 4) { throw new IllegalArgumentException("The length of the byte array must be 4 bytes long."); }
        return Float.intBitsToFloat(toInt32(index, bytes));
    }

    public static double toDouble(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 8) { throw new IllegalArgumentException("The length of the byte array must be 8 bytes long."); }
        return Double.longBitsToDouble(toInt64(index, bytes));
    }

    public static char toChar(int index, byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length != 2) { throw new IllegalArgumentException("The length of the byte array must be 2 bytes long."); }
        return (char) ((0xff & bytes[index]) << 8 | (0xff & bytes[index + 1]));
    }

    public static String toString(byte... bytes) throws IllegalArgumentException
    {
        if(bytes == null || bytes.length < 1) { throw new IllegalArgumentException("The byte array must have at least 1 byte."); }
        return new String(bytes);
    }


    public static boolean toBoolean(byte... bytes) throws IllegalArgumentException
    {
        return toBoolean(0, bytes);
    }

    public static short toInt16(byte... bytes) throws IllegalArgumentException
    {
        return toInt16(0, bytes);
    }

    public static short toShort(int index, byte... bytes) throws IllegalArgumentException
    {
        return toInt16(index, bytes);
    }

    public static short toShort(byte... bytes) throws IllegalArgumentException
    {
        return toInt16(0, bytes);
    }

    public static int toInt32(byte... bytes) throws IllegalArgumentException
    {
        return toInt32(0, bytes);
    }

    public static int toInt(int index, byte... bytes) throws IllegalArgumentException
    {
        return toInt32(index, bytes);
    }

    public static int toInt(byte... bytes) throws IllegalArgumentException
    {
        return toInt32(0, bytes);
    }

    public static long toInt64(byte... bytes) throws IllegalArgumentException
    {
        return toInt64(0, bytes);
    }

    public static long toLong(int index, byte... bytes) throws IllegalArgumentException
    {
        return toInt64(index, bytes);
    }

    public static long toLong(byte... bytes) throws IllegalArgumentException
    {
        return toInt64(0, bytes);
    }

    public static float toFloat(byte... bytes) throws IllegalArgumentException
    {
        return toFloat(0, bytes);
    }

    public static double toDouble(byte... bytes) throws IllegalArgumentException
    {
        return toDouble(0, bytes);
    }

    public static char toChar(byte... bytes) throws IllegalArgumentException
    {
        return toChar(0, bytes);
    }
    //endregion
}
