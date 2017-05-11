package com.github.vitrifiedcode.javautilities.junit;

import com.github.vitrifiedcode.javautilities.junit.matcher.IsEpsilonEqualF;
import com.github.vitrifiedcode.javautilities.math.BaseConverter;
import com.github.vitrifiedcode.javautilities.math.MathUtil;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class MathTests
{
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void averages()
    {
        collector.checkThat(3, IsEqual.equalTo(MathUtil.averageI(1, 2, 3, 4, 5)));
        collector.checkThat(29, IsEqual.equalTo(MathUtil.averageI(3, 6, 74, 35)));
        collector.checkThat(794, IsEqual.equalTo(MathUtil.averageI(754, 23, 5, 856, 2335)));
        collector.checkThat(3.0F, IsEpsilonEqualF.equalTo(MathUtil.average(1, 2, 3, 4, 5), 0.01F));
        collector.checkThat(29.5F, IsEpsilonEqualF.equalTo(MathUtil.average(3, 6, 74, 35), 0.01F));
        collector.checkThat(794.6F, IsEpsilonEqualF.equalTo(MathUtil.average(754, 23, 5, 856, 2335), 0.01F));
    }

    @Test
    public void baseConversion_16()
    {
        BaseConverter b0 = new BaseConverter("0123456789ABCDEF");
        collector.checkThat("64", IsEqual.equalTo(b0.convert(100)));
        collector.checkThat("346", IsEqual.equalTo(b0.convert(838)));
        collector.checkThat(100L, IsEqual.equalTo(b0.convert("64")));
        collector.checkThat(838L, IsEqual.equalTo(b0.convert("346")));
    }

    @Test
    public void baseConversion_8()
    {
        BaseConverter b0 = new BaseConverter("01234567");
        collector.checkThat("144", IsEqual.equalTo(b0.convert(100)));
        collector.checkThat("1506", IsEqual.equalTo(b0.convert(838)));
        collector.checkThat(100L, IsEqual.equalTo(b0.convert("144")));
        collector.checkThat(838L, IsEqual.equalTo(b0.convert("1506")));
    }

    @Test
    public void baseConversion_34()
    {
        BaseConverter b0 = new BaseConverter("0123456789ABCDEFGHIJKLMNOPQRSTUVWX");
        collector.checkThat("2W", IsEqual.equalTo(b0.convert(100)));
        collector.checkThat("OM", IsEqual.equalTo(b0.convert(838)));
        collector.checkThat("Q5N2G", IsEqual.equalTo(b0.convert(34967928)));
        collector.checkThat(100L, IsEqual.equalTo(b0.convert("2W")));
        collector.checkThat(838L, IsEqual.equalTo(b0.convert("OM")));
        collector.checkThat(34967928L, IsEqual.equalTo(b0.convert("Q5N2G")));
    }

    @Test
    public void sin()
    {
        for(int i = 0; i < 0x1FFFF; ++i)
        {
            collector.checkThat("sin of " + i, (float) Math.sin(i), IsEpsilonEqualF.equalTo(MathUtil.sin(i), 0.075F));
            collector.checkThat("cos of " + i, (float) Math.cos(i), IsEpsilonEqualF.equalTo(MathUtil.cos(i), 0.075F));
        }
    }
}
