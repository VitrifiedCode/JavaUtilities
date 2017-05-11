package com.github.vitrifiedcode.javautilities.list.container;

import com.github.vitrifiedcode.javautilities.string.StringUtil;

import javax.annotation.Nonnull;

public class Quad<L, ML, MR, R> implements Cloneable
{
    public L left;
    public ML middleL;
    public MR middleR;
    public R right;

    public Quad() {}

    public Quad(L left, ML middleL, MR middleR, R right)
    {
        this.left = left;
        this.middleL = middleL;
        this.middleR = middleR;
        this.right = right;
    }

    public Quad(Quad<L, ML, MR, R> other)
    {
        left = other.left;
        middleL = other.middleL;
        middleR = other.middleR;
        right = other.right;
    }

    @Override
    public int hashCode()
    {
        int hashLeft = (left != null ? left.hashCode() : 1);
        int hashMiddleL = (middleL != null ? middleL.hashCode() : 1);
        int hashMiddleR = (middleR != null ? middleR.hashCode() : -1);
        int hashRight = (right != null ? right.hashCode() : -1);

        return (hashLeft + hashMiddleL + hashMiddleR + hashRight) * (hashMiddleL + hashMiddleR) * (hashRight + hashLeft);
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Quad)
        {
            Quad otherQuad = (Quad) other;
            return (left == otherQuad.left || left != null && otherQuad.left != null && left.equals(otherQuad.left))
                   && (middleL == otherQuad.middleL || middleL != null && otherQuad.middleL != null && middleL.equals(otherQuad.middleL))
                   && (middleR == otherQuad.middleR || middleR != null && otherQuad.middleR != null && middleR.equals(otherQuad.middleR))
                   && (right == otherQuad.right || right != null && otherQuad.right != null && right.equals(otherQuad.right));
        }

        return false;
    }

    @Override
    public Quad<L, ML, MR, R> clone() { return new Quad<L, ML, MR, R>(this); }

    @Nonnull
    @Override
    public String toString() { return StringUtil.build("(", left, ", ", middleL, ", ", middleR, ", ", right, ")"); }
}
