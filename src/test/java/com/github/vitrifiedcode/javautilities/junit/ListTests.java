package com.github.vitrifiedcode.javautilities.junit;

import com.github.vitrifiedcode.javautilities.list.mask.BoolMaskB;
import com.github.vitrifiedcode.javautilities.list.mask.BoolMaskI;
import com.github.vitrifiedcode.javautilities.list.mask.BoolMaskL;
import com.github.vitrifiedcode.javautilities.list.mask.BoolMaskS;
import org.junit.Assert;
import org.junit.Test;

public class ListTests
{
    @Test
    public void bitMask()
    {
        BoolMaskB b0 = new BoolMaskB(true, true, false, true, false, false, false, true);
        BoolMaskS b1 = new BoolMaskS(true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true);
        BoolMaskI b2 = new BoolMaskI(true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true);
        BoolMaskL b3 = new BoolMaskL(true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, true);
        Assert.assertEquals((byte) 0xD1, b0.getMask());
        Assert.assertEquals((short) 0xD1D1, b1.getMask());
        Assert.assertEquals(0xD1D1D1D1, b2.getMask());
        Assert.assertEquals(0xD1D1D1D1D1D1D1D1L, b3.getMask());
    }
}
