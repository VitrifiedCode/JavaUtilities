package com.github.vitrifiedcode.javautilities.junit;

import com.github.vitrifiedcode.javautilities.math.BaseConverter;
import com.github.vitrifiedcode.javautilities.math.MathUtil;
import org.junit.Assert;
import org.junit.Test;

public class MathTests
{
    @Test
    public void averages()
    {
        Assert.assertEquals(3, MathUtil.averageI(1, 2, 3, 4, 5));
        Assert.assertEquals(29, MathUtil.averageI(3, 6, 74, 35));
        Assert.assertEquals(794, MathUtil.averageI(754, 23, 5, 856, 2335));
        Assert.assertEquals(3, MathUtil.average(1, 2, 3, 4, 5), 0.01D);
        Assert.assertEquals(29.5, MathUtil.average(3, 6, 74, 35), 0.01D);
        Assert.assertEquals(794.6, MathUtil.average(754, 23, 5, 856, 2335), 0.01D);
    }

    @Test
    public void baseConversion_16()
    {
        BaseConverter b0 = new BaseConverter("0123456789ABCDEF");
        Assert.assertEquals("64", b0.convert(100));
        Assert.assertEquals("346", b0.convert(838));
        Assert.assertEquals(100, b0.convert("64"));
        Assert.assertEquals(838, b0.convert("346"));
    }

    @Test
    public void baseConversion_8()
    {
        BaseConverter b0 = new BaseConverter("01234567");
        Assert.assertEquals("144", b0.convert(100));
        Assert.assertEquals("1506", b0.convert(838));
        Assert.assertEquals(100, b0.convert("144"));
        Assert.assertEquals(838, b0.convert("1506"));
    }

    @Test
    public void baseConversion_34()
    {
        BaseConverter b0 = new BaseConverter("0123456789ABCDEFGHIJKLMNOPQRSTUVWX");
        Assert.assertEquals("2W", b0.convert(100));
        Assert.assertEquals("OM", b0.convert(838));
        Assert.assertEquals("Q5N2G", b0.convert(34967928));
        Assert.assertEquals(100, b0.convert("2W"));
        Assert.assertEquals(838, b0.convert("OM"));
        Assert.assertEquals(34967928, b0.convert("Q5N2G"));
    }
}
