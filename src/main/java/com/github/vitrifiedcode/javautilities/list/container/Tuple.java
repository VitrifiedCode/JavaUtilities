package com.github.vitrifiedcode.javautilities.list.container;

import com.github.vitrifiedcode.javautilities.string.StringUtil;

import javax.annotation.Nonnull;

public class Tuple<L, M, R> implements Cloneable
{
    public L left;
    public M middle;
    public R right;

    public Tuple() {}

    public Tuple(L left, M middle, R right)
    {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public Tuple(Tuple<L, M, R> other)
    {
        left = other.left;
        middle = other.middle;
        right = other.right;
    }

    @Override
    public int hashCode()
    {
        int hashLeft = (left != null ? left.hashCode() : -1);
        int hashMiddle = (middle != null ? middle.hashCode() : 1);
        int hashRight = (right != null ? right.hashCode() : 1);

        return (hashLeft + hashMiddle + hashRight) * hashMiddle * (hashRight + hashLeft);
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Tuple)
        {
            Tuple otherTuple = (Tuple) other;
            return (left == otherTuple.left || (left != null && otherTuple.left != null && left.equals(otherTuple.left))) &&
                   (middle == otherTuple.middle || (middle != null && otherTuple.middle != null && middle.equals(otherTuple.middle))) &&
                   (right == otherTuple.right || (right != null && otherTuple.right != null && right.equals(otherTuple.right)));
        }

        return false;
    }

    @Override
    public Tuple<L, M, R> clone() { return new Tuple<L, M, R>(this); }

    @Nonnull
    @Override
    public String toString() { return StringUtil.build("(", left.toString(), ", ", middle.toString(), ", ", right.toString(), ")"); }
}
