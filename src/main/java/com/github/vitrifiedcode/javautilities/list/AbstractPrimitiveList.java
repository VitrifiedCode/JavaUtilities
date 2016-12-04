package com.github.vitrifiedcode.javautilities.list;

import java.util.AbstractList;

public abstract class AbstractPrimitiveList<E> extends AbstractList<E>
{
    protected static final int MAX_SIZE = Integer.MAX_VALUE - 8;

    protected int size;

    public AbstractPrimitiveList() { size = 0; }

    public int length() { return size; }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    @Deprecated
    public abstract int indexOf(Object element);

    @Override
    @Deprecated
    public abstract boolean contains(Object element);

    protected abstract void ensureSize(int size, int index);

    protected void ensureSize(int size) { ensureSize(size, this.size); }

    protected void grow() { ensureSize(size + (size >> 1), size); }

    protected void growToSize(int size) { ensureSize(Math.max(size, this.size + (this.size >> 1))); }

    protected void growToSize(int size, int index) { ensureSize(Math.max(size, this.size + (this.size >> 1)), index); }

    @Override
    @Deprecated
    public abstract void add(int index, E element);

    @Override
    @Deprecated
    public abstract boolean add(E element);

    @Override
    @Deprecated
    public abstract E set(int index, E element);

    @Override
    @Deprecated
    public abstract E get(int index);

    @Override
    @Deprecated
    public abstract E remove(int index);
}
