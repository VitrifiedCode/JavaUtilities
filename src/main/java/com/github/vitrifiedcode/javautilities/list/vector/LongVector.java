package com.github.vitrifiedcode.javautilities.list.vector;

import com.github.vitrifiedcode.javautilities.list.arraylist.LongArrayList;

public class LongVector extends LongArrayList
{
    @Override
    protected void grow() { ensureSize(size << 1, size); }

    @Override
    protected void growToSize(int size) { ensureSize(Math.max(size, this.size << 1)); }

    @Override
    protected void growToSize(int size, int index) { ensureSize(Math.max(size, this.size << 1), index); }
}
