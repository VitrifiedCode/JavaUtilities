package com.github.vitrifiedcode.javautilities.test;

import com.github.vitrifiedcode.javautilities.other.RandomUtil;
import com.github.vitrifiedcode.javautilities.string.StaticPattern;
import org.openjdk.jmh.annotations.*;

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

    @Benchmark
    public String replaceCompiled()
    {
        return PATTERN.matcher(STR).replaceAll("hi");
    }

    @Benchmark
    public String replace()
    {
        return STR.replaceAll(";", "hi");
    }

    @Benchmark
    public String replaceStaticPattern()
    {
        return StaticPattern.getPattern(";").matcher(STR).replaceAll("hi");
    }
}
