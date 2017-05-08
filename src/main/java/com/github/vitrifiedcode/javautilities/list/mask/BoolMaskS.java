package com.github.vitrifiedcode.javautilities.list.mask;

import com.github.vitrifiedcode.javautilities.object.BitConverter;

public class BoolMaskS extends AbstractBoolMask
{
    private short mask;

    public BoolMaskS(boolean... arr)
    {
        if(arr.length > 16) { throw new IllegalArgumentException("Cannot have more than 16 booleans in the bitmask."); }
        mask = (short) super.init(arr);
    }

    public short getMask() { return mask; }

    @Override
    public boolean getBoolean(int bit) { return BitConverter.getBit(mask, bit) == 1; }

    @Override
    public String toString() { return super.toString(mask); }
}
