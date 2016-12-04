package com.github.vitrifiedcode.javautilities.list.container;

import com.github.vitrifiedcode.javautilities.string.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Tuple<L, M, R>
{
    @Nullable
    public L left;
    @Nullable
    public M middle;
    @Nullable
    public R right;

    public Tuple() {}

    public Tuple(L left, M middle, R right)
    {
        this.left = left;
        this.middle = middle;
        this.right = right;
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

    @NotNull
    @Override
    public String toString() { return StringUtil.build("(", left.toString(), ", ", middle.toString(), ", ", right.toString(), ")"); }
}
