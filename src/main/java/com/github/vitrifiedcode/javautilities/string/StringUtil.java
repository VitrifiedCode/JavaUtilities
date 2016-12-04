package com.github.vitrifiedcode.javautilities.string;

public final class StringUtil
{
    private StringUtil() {}

    /**
     * An empty String to reduce RAM usage (Pointers work wonders).
     */
    public static final String EMPTY = "";

    /**
     * A simple pre-defined null escape character.
     */
    public static final char NULL = (char) 0;

    /**
     * A way of concatenating Strings with StringBuilder in a smaller & more optimized fashion (most people don't set the size)
     *
     * @param in An array of strings to concatenate
     * @return The concatenated String
     */
    @SafeVarargs
    public static <T> String build(final T... in)
    {
        if(in == null || in.length == 0) { return ""; }
        int length = 0;
        for(T s : in)
        {
            if(s == null) { return ""; }
            length += s.toString().length();
        }
        StringBuilder sb = new StringBuilder(length);
        for(T s : in) { sb.append(s); }
        return sb.toString();
    }

    /**
     * A way of concatenating Strings with StringBuilder in a smaller & more optimized fashion (most people don't set the size)
     *
     * @param keepEnd Should the end of the string also contain the deliminator.
     * @param delim   A deliminator between Strings.
     * @param in      An array of strings to concatenate
     * @return The concatenated String
     */
    @SafeVarargs
    public static <T> String build(final boolean keepEnd, final String delim, final T... in)
    {
        if(in == null || in.length == 0) { return ""; }
        int length = 0;
        for(T s : in)
        {
            if(s == null) { return ""; }
            length += s.toString().length();
        }
        StringBuilder sb = new StringBuilder(length);
        for(T s : in) { sb.append(s).append(delim); }
        String out = sb.toString();
        if(!keepEnd) { out = out.substring(out.length() - delim.length() - 1); }
        return out;
    }
}
