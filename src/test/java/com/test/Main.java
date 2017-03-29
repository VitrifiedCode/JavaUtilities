package com.test;

import com.github.vitrifiedcode.javautilities.io.IO;
import com.test.console.ConsoleCommand;

import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args)
    {
//        ConsoleCommand c = new ConsoleCommand();
//        for(ConsoleCommand.ParameterType p : c.parameterTypes)
//        {
//            System.out.println(p);
//        }
        Brute.brute();
        IO.println("\n\n\n");
        Brute.pwords.forEach(System.out::println);
    }
}
