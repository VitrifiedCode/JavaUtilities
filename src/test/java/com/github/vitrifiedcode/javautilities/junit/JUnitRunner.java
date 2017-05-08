package com.github.vitrifiedcode.javautilities.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JUnitRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(MathTests.class);
        for(Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }
    }
}
