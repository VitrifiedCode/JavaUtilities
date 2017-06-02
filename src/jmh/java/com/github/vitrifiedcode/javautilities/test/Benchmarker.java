package com.github.vitrifiedcode.javautilities.test;

import com.github.vitrifiedcode.javautilities.other.RandomUtil;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Benchmarker
{
    static final int x = RandomUtil.getRandomInt(10, 3948636);

    @Benchmark
    public int mul()
    {
        return x * 2;
    }

    @Benchmark
    public int shift()
    {
        return x << 1;
    }
}
