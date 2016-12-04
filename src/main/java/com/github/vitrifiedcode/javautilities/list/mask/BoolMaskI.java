package com.github.vitrifiedcode.javautilities.list.mask;

import com.github.vitrifiedcode.javautilities.math.MathUtil;

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
    public boolean getBoolean(int bit) { return MathUtil.getBit(mask, bit); }

    @Override
    public String toString() { return super.toString(mask); }
}
