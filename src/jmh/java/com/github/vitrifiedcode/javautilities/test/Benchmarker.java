package com.github.vitrifiedcode.javautilities.test;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Benchmarker
{
    public static final double x = (double) (Math.random() * 5_000_000);
    public static final double y = (double) (Math.random() * 5_000_000);

    @Benchmark
    public double add()
    {
        return x + y;
    }

    @Benchmark
    public double subtract()
    {
        return x - y;
    }

    @Benchmark
    public double multiply()
    {
        return x * y;
    }

    @Benchmark
    public double divide()
    {
        return x / y;
    }
}
