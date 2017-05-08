package com.github.vitrifiedcode.javautilities.list.mask;

import com.github.vitrifiedcode.javautilities.object.BitConverter;

public class BoolMaskI extends AbstractBoolMask
{
    private int mask;

    public BoolMaskI(boolean... arr)
    {
        if(arr.length > 32) { throw new IllegalArgumentException("Cannot have more than 32 booleans in the bitmask."); }
        mask = (int) super.init(arr);
    }

    public int getMask() { return mask; }

    @Override
    public boolean getBoolean(int bit) { return BitConverter.getBit(mask, bit) == 1; }

    @Override
    public String toString() { return super.toString(mask); }
}
