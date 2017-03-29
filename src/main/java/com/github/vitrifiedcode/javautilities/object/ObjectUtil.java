package com.github.vitrifiedcode.javautilities.object;

public class ObjectUtil
{
    public static <T> boolean equals(final T o0, final T o1)
    {
        boolean n0 = o0 == null;
        boolean n1 = o1 == null;
        return n0 && n1 || (!(n0 || n1) && o0.hashCode() == o1.hashCode() && o0.equals(o1));
    }
}
