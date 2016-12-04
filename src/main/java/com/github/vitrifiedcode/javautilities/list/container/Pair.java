package com.github.vitrifiedcode.javautilities.list.container;

import com.github.vitrifiedcode.javautilities.string.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Pair<L, R>
{
    @Nullable
    public L left;
    @Nullable
    public R right;

    public Pair() {}

    public Pair(L left, R right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public int hashCode()
    {
        int hashLeft = (left != null ? left.hashCode() : 1);
        int hashRight = (right != null ? right.hashCode() : -1);

        return (hashLeft + hashRight) * hashRight + hashLeft;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Pair)
        {
            Pair otherPair = (Pair) other;
            return (left == otherPair.left || (left != null && otherPair.left != null && left.equals(otherPair.left))) &&
                   (right == otherPair.right || (right != null && otherPair.right != null && right.equals(otherPair.right)));
        }

        return false;
    }

    @NotNull
    @Override
    public String toString() { return StringUtil.build("(", left.toString(), ", ", right.toString(), ")"); }
}
