package com.github.vitrifiedcode.javautilities.list.mask;

public abstract class AbstractBoolMask
{
    protected final long init(boolean... arr)
    {
        long out = 0L;
        byte len = (byte) (arr.length - 1);
        for(byte i = 0; i < arr.length; ++i) { out |= (arr[i] ? 1L : 0L) << (len - i); }
        return out;
    }

    public abstract boolean getBoolean(int bit);

    protected final String toString(byte mask) { return Integer.toBinaryString(mask); }

    protected final String toString(short mask) { return Integer.toBinaryString(mask); }

    protected final String toString(int mask) { return Integer.toBinaryString(mask); }

    protected final String toString(long mask) { return Long.toBinaryString(mask); }

    @Override
    public abstract String toString();
}
