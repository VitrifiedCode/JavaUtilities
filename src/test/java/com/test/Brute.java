package com.test;

import java.util.ArrayList;
import java.util.List;

public class Brute
{
    public static final List<String> pwords = new ArrayList<String>();

    public static final String[] names = { "stronghold", "first", "2017" };
    public static final char[] charset = "~!@#$%^&*()-_=+:;,.<>?/\\|[]{}'\"` ".toCharArray();

    public static void brute()
    {
        char c[] = { charset[0], charset[0], charset[0], charset[0] };
        int len = charset.length;
        long counter = 0;
        for(int i = 0; i < Math.pow(len, 4); ++i, ++counter)
        {
            long c0 = counter;
            c[0] = charset[(int) (counter % len)];
            c0 /= len;
            c[1] = charset[(int) (counter % len)];
            c0 /= len;
            c[2] = charset[(int) (counter % len)];
            c0 /= len;
            c[3] = charset[(int) (counter % len)];

            pwords.add(c[0] + names[0] + c[1] + names[1] + c[2] + names[2] + c[3]);
            pwords.add(c[0] + names[1] + c[1] + names[2] + c[2] + names[0] + c[3]);
            pwords.add(c[0] + names[2] + c[1] + names[0] + c[3] + names[1] + c[3]);
            pwords.add(c[0] + names[0] + c[1] + names[2] + c[2] + names[1] + c[3]);
            pwords.add(c[0] + names[1] + c[1] + names[0] + c[2] + names[2] + c[3]);
            pwords.add(c[0] + names[2] + c[1] + names[1] + c[2] + names[0] + c[3]);
            System.out.println(counter);
        }
    }
}
