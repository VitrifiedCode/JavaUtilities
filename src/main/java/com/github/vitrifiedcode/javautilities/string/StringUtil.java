package com.github.vitrifiedcode.javautilities;

public final class StringUtil
{
    private StringUtil() {}

    /**
     * An empty String to reduce RAM usage.
     */
    public static final String EMPTY = "";

    /**
     * A way of concatenating Strings with StringBuilder in a smaller & more optimized fashion (most people don't set the size)
     * @param in An array of strings to concatenate
     * @return The concatenated String
     */
    public static String build(String... in)
    {
        int length = 0;
        for(String s : in) { length += s.length(); }
        StringBuilder sb = new StringBuilder(length);
        for(String s : in) { sb.append(s); }
        return sb.toString();
    }
}
