package com.github.vitrifiedcode.javautilities.string;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StaticPattern
{
    public static final Map<String, Pattern> PATTERNS = new HashMap<String, Pattern>();

    public static Pattern getPattern(String pattern)
    {
        if(pattern == null || pattern.isEmpty()) { return null; }
        if(PATTERNS.containsKey(pattern))
        {
            return PATTERNS.get(pattern);
        }

        Pattern out = Pattern.compile(pattern);
        PATTERNS.put(pattern, out);
        return out;
    }
}
