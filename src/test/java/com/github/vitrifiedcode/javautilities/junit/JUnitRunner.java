package com.github.vitrifiedcode.javautilities.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JUnitRunner
{
    static boolean logToFile = System.getProperty("logToFile") != null || System.getenv("logToFile") != null;
    static BufferedWriter bw;
    static File f = new File("logs/log@" + System.nanoTime() + ".txt");
    static File logs = new File("logs");

    static
    {
        if(logToFile)
        {
            if(!logs.exists()) { logs.mkdir(); }
            try
            {
                f.createNewFile();
                bw = new BufferedWriter(new FileWriter(f));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void writeln(String str)
    {
        if(logToFile && bw != null)
        {
            try
            {
                bw.write(str);
                bw.newLine();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    static void close()
    {
        if(bw != null)
        {
            try
            {
                bw.flush();
                bw.close();

                if(f.length() == 0L) { f.delete(); }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        Result result = JUnitCore.runClasses(MathTests.class, StringTests.class, ListTests.class, MathTests.SinTest.class);
        for(Failure failure : result.getFailures())
        {
            System.out.println(failure.getMessage());
            System.out.println(failure.toString());
            writeln(failure.getMessage());
            writeln(failure.toString());
        }

        close();
        System.out.flush();
        System.err.flush();
        Thread.sleep(100);

        /* This is so that a Travis-CI build fails when a JUnit test fails. */
        if(result.getFailureCount() > 0) { throw new Exception("JUnit test has failed."); }
    }
}
