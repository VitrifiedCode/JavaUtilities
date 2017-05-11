package com.github.vitrifiedcode.javautilities.junit.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsEpsilonEqualF extends BaseMatcher<Float>
{
    private final float expectedValue;
    private final float epsilon;

    public IsEpsilonEqualF(float expectedValue, float epsilon)
    {
        this.expectedValue = expectedValue;
        this.epsilon = epsilon;
    }

    public IsEpsilonEqualF(float expectedValue) { this(expectedValue, 1.0E-5F); }

    @Override
    public boolean matches(Object actualValue)
    {
        return actualValue instanceof Float && areEqual((Float) actualValue, expectedValue, epsilon);
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendValue(expectedValue).appendValue(epsilon);
    }

    private static boolean areEqual(float actual, float expected, float epsilon)
    {
        if(Float.isNaN(actual)) { return Float.isNaN(expected); }

        return Math.abs(actual - expected) < epsilon;
    }

    @Factory
    public static Matcher<Float> equalTo(float operand, float epsilon)
    {
        return new IsEpsilonEqualF(operand, epsilon);
    }

    @Factory
    public static Matcher<Float> equalTo(float operand)
    {
        return new IsEpsilonEqualF(operand);
    }
}
