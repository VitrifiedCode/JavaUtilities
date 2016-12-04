package com.github.vitrifiedcode.javautilities.list.mask;

import com.github.vitrifiedcode.javautilities.math.MathUtil;

public class BoolMaskB extends AbstractBoolMask
{
    private byte mask;

    public BoolMaskB(boolean... arr)
    {
        if(arr.length > 8) { throw new IllegalArgumentException("Cannot have more than 8 booleans in the bitmask."); }
        mask = (byte) super.init(arr);
    }

    public byte getMask() { return mask; }

    @Override
    public boolean getBoolean(int bit) { return MathUtil.getBit(mask, bit); }

    @Override
    public String toString() { return super.toString(mask); }
}
