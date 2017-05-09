package com.github.vitrifiedcode.javautilities.string;

import com.github.vitrifiedcode.javautilities.object.ObjectUtil;

import java.util.regex.Pattern;

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
     * Lots of predefined patterns
     */
    public static final Pattern SPACE_SINGLE_PATTERN            = StaticPattern.getPattern(" ");
    public static final Pattern SPACE_PATTERN                   = StaticPattern.getPattern(" +");
    public static final Pattern COMMA_SINGLE_PATTERN            = StaticPattern.getPattern(",");
    public static final Pattern COMMA_PATTERN                   = StaticPattern.getPattern(",+");
    public static final Pattern COLON_SINGLE_PATTERN            = StaticPattern.getPattern(":");
    public static final Pattern COLON_PATTERN                   = StaticPattern.getPattern(":+");
    public static final Pattern SEMICOLON_SINGLE_PATTERN        = StaticPattern.getPattern(";");
    public static final Pattern SEMICOLON_PATTERN               = StaticPattern.getPattern(";+");
    public static final Pattern EQUALS_SINGLE_PATTERN           = StaticPattern.getPattern("=");
    public static final Pattern EQUALS_PATTERN                  = StaticPattern.getPattern("=+");
    public static final Pattern NULL_SINGLE_PATTERN             = StaticPattern.getPattern("\0");
    public static final Pattern NULL_PATTERN                    = StaticPattern.getPattern("\0+");
    public static final Pattern a_z_SINGLE_PATTERN              = StaticPattern.getPattern("[a-z]");
    public static final Pattern a_z_PATTERN                     = StaticPattern.getPattern("[a-z]+");
    public static final Pattern A_Z_SINGLE_PATTERN              = StaticPattern.getPattern("[A-Z]");
    public static final Pattern A_Z_PATTERN                     = StaticPattern.getPattern("[A-Z]+");
    public static final Pattern Aa_Zz_SINGLE_PATTERN            = StaticPattern.getPattern("[a-zA-Z]+");
    public static final Pattern Aa_Zz_PATTERN                   = StaticPattern.getPattern("[a-zA-Z]+");
    public static final Pattern NUMBERS_SINGLE_PATTERN          = StaticPattern.getPattern("[0-9]");
    public static final Pattern NUMBERS_PATTERN                 = StaticPattern.getPattern("[0-9]+");
    public static final Pattern ALPHA_NUMERIC_SINGLE_PATTERN    = StaticPattern.getPattern("[a-zA-z0-9]");
    public static final Pattern ALPHA_NUMERIC_PATTERN           = StaticPattern.getPattern("[a-zA-z0-9]+");

    /**
     * A way of concatenating Strings with StringBuilder in a smaller {@literal &} more optimized fashion (most people don't set the size)
     *
     * Benchmarks:
     *  Benchmark             Mode  Cnt    Score    Error  Units
     *  Size Not Calculated:  avgt   40  350.021 ± 29.974  ns/op
     *  Size Calculated:      avgt   40  200.888 ±  8.381  ns/op
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
     * A way of concatenating Strings with StringBuilder in a smaller {@literal &} more optimized fashion (most people don't set the size)
     *
     * @param keepEnd Should the end of the string also contain the deliminator.
     * @param delim   A deliminator between Strings.
     * @param in      An array of strings to concatenate
     * @return The concatenated String
     */
    @SafeVarargs
    public static <T> String buildDelim(final boolean keepEnd, final String delim, final T... in)
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
        if(!keepEnd) { out = out.substring(0, out.length() - delim.length()); }
        return out;
    }

    public static boolean equals(final String s0, final String s1)
    {
        return ObjectUtil.equals(s0, s1);
    }

    public static boolean equalsIgnoreCase(final String s0, final String s1)
    {
        return ObjectUtil.equals(s0.toLowerCase(), s1.toLowerCase());
    }

    public static boolean containsIgnoreCase(final String in, final String search)
    {
        return in.toLowerCase().contains(search.toLowerCase());
    }

    public static String toString(String name, String... data)
    {
        if(data.length % 2 != 0) { return ""; }
        int tmp = data.length / 2;
        StringBuilder sb = new StringBuilder(4 + (7 * (tmp)) + (tmp - 1) + name.length() + (data[0].length() * 10 * data.length)).append(name).append(" {");
        for(int i = 0; i < data.length; i += 2)
        {
            sb.append(" \"").append(data[i]).append("\": \"").append(data[i + 1]).append('\"');
            if(i != data.length - 2) { sb.append(','); }
        }
        return sb.append(" }").toString();
    }
}
