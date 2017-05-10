package com.github.vitrifiedcode.javautilities.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JUnitRunner
{
    public static void main(String[] args) throws Exception
    {
        Result result = JUnitCore.runClasses(MathTests.class, StringTests.class);
        for(Failure failure : result.getFailures())
        {
            System.out.println(failure.getMessage());
            System.out.println(failure.toString());
        }

        /* This is so that a Travis-CI build fails when a JUnit test fails. */
        if(result.getFailureCount() > 0) { throw new Exception("JUnit test has failed."); }
    }
}
