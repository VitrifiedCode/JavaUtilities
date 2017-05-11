package com.github.vitrifiedcode.javautilities.junit.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsEpsilonEqualD extends BaseMatcher<Double>
{
    private final double expectedValue;
    private final double epsilon;

    public IsEpsilonEqualD(double expectedValue, double epsilon)
    {
        this.expectedValue = expectedValue;
        this.epsilon = epsilon;
    }

    public IsEpsilonEqualD(double expectedValue) { this(expectedValue, 1.0E-5D); }

    @Override
    public boolean matches(Object actualValue)
    {
        return actualValue instanceof Double && areEqual((Double) actualValue, expectedValue, epsilon);
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendValue(expectedValue).appendValue(epsilon);
    }

    private static boolean areEqual(double actual, double expected, double epsilon)
    {
        if(Double.isNaN(actual)) { return Double.isNaN(expected); }

        return Math.abs(actual - expected) < epsilon;
    }

    @Factory
    public static Matcher<Double> equalTo(double operand, double epsilon)
    {
        return new IsEpsilonEqualD(operand, epsilon);
    }

    @Factory
    public static Matcher<Double> equalTo(double operand)
    {
        return new IsEpsilonEqualD(operand);
    }
}
