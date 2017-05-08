package com.github.vitrifiedcode.javautilities.junit;

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
}
