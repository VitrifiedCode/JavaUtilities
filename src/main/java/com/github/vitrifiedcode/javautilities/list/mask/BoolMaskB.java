package com.github.vitrifiedcode.javautilities.list;

import com.github.vitrifiedcode.javautilities.math.MathUtil;

public class BoolMaskB
{
    private byte mask;

    public BoolMaskB(boolean... arr)
    {
        if(arr.length > 8) { throw new IllegalArgumentException("Cannot have more than 8 booleans in the bitmask"); }
        mask = 0x00;
        for(byte i = 0; i < arr.length; ++i)
        {
            mask |= (arr[i] ? 1 : 0) << i;
        }
    }

    public byte getMask() { return mask; }

    public boolean getBoolean(int bit) { return MathUtil.getBit(mask, bit); }
}
