package com.github.vitrifiedcode.javautilities.test;

import com.github.vitrifiedcode.javautilities.other.RandomUtil;
import com.github.vitrifiedcode.javautilities.string.StringUtil;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Benchmarker
{
    public static final double x = (double) (Math.random() * 5_000_000);
    public static final double y = (double) (Math.random() * 5_000_000);

    public static final String[] STRS = new String[10];

    static
    {
        for(int i = 0; i < STRS.length; ++i)
        {
            STRS[i] = RandomUtil.getRandomString(10, 20);
        }
    }

    @Benchmark
    public String strUtilBuild()
    {
        return StringUtil.build(STRS);
    }

    @Benchmark
    public String benchBuild()
    {
        return build(STRS);
    }

    @SafeVarargs
    public static <T> String build(final T... in)
    {
        if(in == null || in.length == 0) { return ""; }
        StringBuilder sb = new StringBuilder();
        for(T s : in) { sb.append(s); }
        return sb.toString();
    }
}
