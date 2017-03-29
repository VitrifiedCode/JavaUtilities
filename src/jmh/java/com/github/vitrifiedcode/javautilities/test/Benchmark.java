package com.github.vitrifiedcode.javautilities.test;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Benchmark
{
    String a = "aaab";
    String b = "aaab";
    String c = "bbba";

    @org.openjdk.jmh.annotations.Benchmark
    public boolean b0()
    {
        return a.equals(b);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public boolean b1()
    {
        return a.equals(c);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public boolean b2()
    {
        return equals(a, b);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public boolean b3()
    {
        return equals(a, c);
    }

    public static boolean equals(final String s1, String s2)
    {
        boolean n0 = s1 == null;
        boolean n1 = s2 == null;
        return n0 && n1 || !(n0 || n1) && s1.hashCode() == s2.hashCode() && s1.equals(s2);

    }
}
