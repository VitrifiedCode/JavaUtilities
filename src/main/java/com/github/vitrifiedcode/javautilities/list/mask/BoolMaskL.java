package com.github.vitrifiedcode.javautilities.list.mask;

import com.github.vitrifiedcode.javautilities.math.MathUtil;

public class BoolMaskL extends AbstractBoolMask
{
    private long mask;

    public BoolMaskL(boolean... arr)
    {
        if(arr.length > 64) { throw new IllegalArgumentException("Cannot have more than 64 booleans in the bitmask."); }
        mask = super.init(arr);
    }

    public long getMask() { return mask; }

    @Override
    public boolean getBoolean(int bit) { return MathUtil.getBit(mask, bit); }

    @Override
    public String toString() { return super.toString(mask); }
}
