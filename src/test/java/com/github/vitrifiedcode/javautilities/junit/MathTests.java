package com.github.vitrifiedcode.javautilities.junit;

import com.github.vitrifiedcode.javautilities.encryption.XORUtils;
import com.github.vitrifiedcode.javautilities.io.IO;
import com.github.vitrifiedcode.javautilities.junit.matcher.AtomicErrorCollector;
import com.github.vitrifiedcode.javautilities.junit.matcher.IsEpsilonEqual;
import com.github.vitrifiedcode.javautilities.junit.matcher.IsValueEqual;
import com.github.vitrifiedcode.javautilities.math.BaseConverter;
import com.github.vitrifiedcode.javautilities.math.MathUtil;
import com.github.vitrifiedcode.javautilities.other.RandomUtil;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.UUID;

public class MathTests
{
    @Rule
    public ErrorCollector collector = new AtomicErrorCollector();

    @Test
    public void averages()
    {
        collector.checkThat(3, IsValueEqual.equalTo(MathUtil.averageI(1, 2, 3, 4, 5)));
        collector.checkThat(29, IsValueEqual.equalTo(MathUtil.averageI(3, 6, 74, 35)));
        collector.checkThat(794, IsValueEqual.equalTo(MathUtil.averageI(754, 23, 5, 856, 2335)));
        collector.checkThat(3.0D, IsEpsilonEqual.equalTo(MathUtil.average(1, 2, 3, 4, 5), 0.01F));
        collector.checkThat(29.5D, IsEpsilonEqual.equalTo(MathUtil.average(3, 6, 74, 35), 0.01F));
        collector.checkThat(794.6D, IsEpsilonEqual.equalTo(MathUtil.average(754, 23, 5, 856, 2335), 0.01F));
    }

    @Test
    public void baseConversion_16()
    {
        BaseConverter b0 = new BaseConverter("0123456789ABCDEF");
        collector.checkThat("b10(100) -> b16(64)", "64", IsValueEqual.equalTo(b0.convert(100)));
        collector.checkThat("b10(838) -> b16(346)", "346", IsValueEqual.equalTo(b0.convert(838)));
        collector.checkThat("b16(346) -> b10(100)", 100L, IsValueEqual.equalTo(b0.convert("64")));
        collector.checkThat("b16(838) -> b10(838)", 838L, IsValueEqual.equalTo(b0.convert("346")));
    }

    @Test
    public void baseConversion_8()
    {
        BaseConverter b0 = new BaseConverter("01234567");
        collector.checkThat("b10(100) -> b8(144)", "144", IsValueEqual.equalTo(b0.convert(100)));
        collector.checkThat("b10(838) -> b8(1506)", "1506", IsValueEqual.equalTo(b0.convert(838)));
        collector.checkThat("b8(144) -> b10(100)", 100L, IsValueEqual.equalTo(b0.convert("144")));
        collector.checkThat("b8(1506) -> b10(838)", 838L, IsValueEqual.equalTo(b0.convert("1506")));
    }

    @Test
    public void baseConversion_34()
    {
        BaseConverter b0 = new BaseConverter("0123456789ABCDEFGHIJKLMNOPQRSTUVWX");
        collector.checkThat("b10(100) -> b34(2W)", "2W", IsValueEqual.equalTo(b0.convert(100)));
        collector.checkThat("b10(838) -> b34(OM)", "OM", IsValueEqual.equalTo(b0.convert(838)));
        collector.checkThat("b10(34967928) -> b34(Q5N2G)", "Q5N2G", IsValueEqual.equalTo(b0.convert(34967928)));
        collector.checkThat("b34(2W) -> b10(100)", 100L, IsValueEqual.equalTo(b0.convert("2W")));
        collector.checkThat("b34(OM) -> b10(838)", 838L, IsValueEqual.equalTo(b0.convert("OM")));
        collector.checkThat("b34(Q5N2G) -> b10(34967928)", 34967928L, IsValueEqual.equalTo(b0.convert("Q5N2G")));
    }

    private static int ID = 0;

    class SinRunner implements Runnable
    {
        public final int min;
        public final int max;
        public final int id;

        public SinRunner(int min, int max)
        {
            this.min = min;
            this.max = max;
            id = ID++;
        }

        @Override
        public void run()
        {
            for(int i = min; i < max; ++i)
            {
                if(i % 100000 == 0) { IO.println(id + ": " + i); }
                collector.checkThat("sin of " + i, Math.sin(i), IsEpsilonEqual.equalTo(MathUtil.sin(i), 0.075F));
                collector.checkThat("cos of " + i, Math.cos(i), IsEpsilonEqual.equalTo(MathUtil.cos(i), 0.075F));
            }
        }
    }

    @Test
    public void sin()
    {
        int x = Integer.MIN_VALUE;
        long inc = 4_294_967_296L / 8;

        Thread t0 = new Thread(new SinRunner(x, x += inc));
        Thread t1 = new Thread(new SinRunner(x, x += inc));
        Thread t2 = new Thread(new SinRunner(x, x += inc));
        Thread t3 = new Thread(new SinRunner(x, x += inc));
        Thread t4 = new Thread(new SinRunner(x, x += inc));
        Thread t5 = new Thread(new SinRunner(x, x += inc));
        Thread t6 = new Thread(new SinRunner(x, x += inc));
        Thread t7 = new Thread(new SinRunner(x, x += inc));
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }

    @Test
    public void xor()
    {
        int xor = RandomUtil.getRandomInt();
        String str = RandomUtil.getRandomString(25);
        UUID uuid = RandomUtil.getRandomUUID();
        collector.checkThat(str, IsEqual.equalTo(XORUtils.xor(XORUtils.xor(str, xor), xor)));
        collector.checkThat(str, IsEqual.equalTo(XORUtils.xorD(XORUtils.xorE(str, uuid), uuid)));
        collector.checkThat(str, IsEqual.equalTo(XORUtils.xorE(XORUtils.xorD(str, uuid), uuid)));
    }
}
