package com.github.vitrifiedcode.javautilities.math;

/**
 * Creates a custom base numeral system.
 */
public class BaseConverter
{
    /**
     * The charset to use for representing the number
     */
    private final char[] charset;

    public BaseConverter(char[] charset)
    {
        if(charset.length == 1) { throw new IllegalArgumentException("BaseConverter does not support unary."); }
        if(charset.length == 0) { throw new IllegalArgumentException("BaseConverter does not support base0."); }
        this.charset = charset;
    }

    public BaseConverter(String charset) { this(charset.toCharArray()); }

    public BaseConverter(int base)
    {
        charset = new char[base];
        for(int i = 0; i < base; ++i)
        {
            charset[i] = (char) (i + 0x20);
        }
    }

    /**
     * Returns the number of characters the converted number will take to represent
     *
     * @param x      The input number
     * @param length The length of the charset
     * @return The number of chars required
     */
    private static int outLength(long x, int length)
    {
        return ((int) MathUtil.logX(x, length)) + 1;
    }

    /**
     * Converts a long to a string using the custom base
     *
     * @param x The input number
     * @return A string representing the number in the form of a custom base
     */
    public String convert(long x)
    {
        //Initialize the output string with the proper length
        StringBuilder out = new StringBuilder(outLength(x, charset.length));

        //Iterate through all digit segments
        while(x != 0)
        {
            //Find the char which represents a segment of digits
            out.append(charset[(int) (x % charset.length)]);
            //Goto next segment
            x /= charset.length;
        }

        //Reverse the string to be in the proper direction
        return out.reverse().toString();
    }

    private int indexOf(char c)
    {
        for(int i = 0; i < charset.length; ++i)
        {
            if(c == charset[i]) { return i; }
        }
        return 0;
    }

    public long convert(String input)
    {
        //Reverse the input for processing
        input = new StringBuilder(input).reverse().toString();
        //The output value
        long out = 0;
        //The current position
        int power = -1;

        //Convert the number
        for(char c : input.toCharArray())
        {
            //Get the index of the current char and convert it to a number
            out += indexOf(c) * Math.pow(charset.length, ++power);
        }
        return out;
    }
}
