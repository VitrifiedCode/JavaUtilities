package com.github.vitrifiedcode.javautilities.junit.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsValueEqual<T> extends BaseMatcher<T>
{
    private final T expectedValue;

    public IsValueEqual(T expectedValue)
    {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean matches(Object actualValue)
    {
        if(expectedValue == null) { return actualValue == null; }
        if(actualValue == null) { return false; }
        if(expectedValue == actualValue || expectedValue.equals(actualValue)) { return true; }
        if(expectedValue instanceof String && actualValue instanceof Character) { return ((String) expectedValue).equals(new String(new char[] { (Character) actualValue })); }
        if(actualValue instanceof String && expectedValue instanceof Character) { return ((String) actualValue).equals(new String(new char[] { (Character) expectedValue })); }
        if(expectedValue instanceof Number && actualValue instanceof Number)
        {
            double x = ((Number) expectedValue).doubleValue();
            double y = ((Number) actualValue).doubleValue();
            return x == y || (Double.isNaN(x) && Double.isNaN(y));
        }

        return false;
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendValue(expectedValue);
    }

    @Factory
    public static <T> Matcher<T> equalTo(T operand)
    {
        return new IsValueEqual<T>(operand);
    }
}
