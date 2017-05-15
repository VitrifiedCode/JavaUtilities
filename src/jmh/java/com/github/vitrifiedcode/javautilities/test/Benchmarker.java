package com.github.vitrifiedcode.javautilities.test;

import com.github.vitrifiedcode.javautilities.array.ArrayUtil;
import com.github.vitrifiedcode.javautilities.other.RandomUtil;
import com.github.vitrifiedcode.javautilities.string.StaticPattern;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Benchmarker
{
    public static final double x = (double) (Math.random() * 5_000_000);
    public static final double y = (double) (Math.random() * 5_000_000);

    public static final String STR = RandomUtil.getRandomString(50, 100);

    public static final Pattern PATTERN = Pattern.compile(";");

    //    @Benchmark
    public String replaceCompiled()
    {
        return PATTERN.matcher(STR).replaceAll("hi");
    }

    //    @Benchmark
    public String replace()
    {
        return STR.replaceAll(";", "hi");
    }

    //    @Benchmark
    public String replaceStaticPattern()
    {
        return StaticPattern.getPattern(";").matcher(STR).replaceAll("hi");
    }


    static List<Integer> l0 = new ArrayList<>();
    static List<Integer> l1 = new ArrayList<>();

    static
    {
        for(int i = 0; i < 27990; ++i)
        {
            l0.add(RandomUtil.getRandomInt());
            l1.add(RandomUtil.getRandomInt());
        }

        for(int i = 0; i < 100; ++i)
        {
            l1.add(RandomUtil.getRandomInt());
        }
    }

    @Benchmark
    public int[] toPrimArr0()
    {
        return ArrayUtil.toPrimitive(l0.toArray(new Integer[l0.size()]));
    }

    @Benchmark
    public int[] toPrimArr1()
    {
        return l0.stream().mapToInt(v -> v).toArray();
    }

    @Benchmark
    public int[] toPrimArr2()
    {
        return ArrayUtil.toPrimitive(l1.toArray(new Integer[l1.size()]));
    }

    @Benchmark
    public int[] toPrimArr3()
    {
        return l1.stream().mapToInt(v -> v).toArray();
    }
}
