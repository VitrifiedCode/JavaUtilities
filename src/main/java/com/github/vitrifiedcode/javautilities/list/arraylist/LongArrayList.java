package com.github.vitrifiedcode.javautilities.list;

import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class LongArrayList extends AbstractPrimitiveList<Long>
{
    protected static final long[] DEFAULT_ARRAY = new long[10];

    protected transient long[] elementData;

    public LongArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public LongArrayList(int capacity)
    {
        super();
        elementData = new long[capacity];
    }

    public int indexOf(long element)
    {
        for(int i = 0; i < size; ++i)
        {
            if(elementData[i] == element) { return i; }
        }
        return -1;
    }

    @Override
    @Deprecated
    public int indexOf(Object element)
    {
        if(!(element instanceof Long)) { throw new IllegalArgumentException("Parameter must of type Long."); }
        return indexOf(((Long) element).longValue());
    }

    public boolean contains(long element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Long)) { throw new IllegalArgumentException("Parameter must of type Long."); }
        return indexOf(((Long) element).longValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.LongArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, long element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(long element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Long element) { add(index, element.longValue()); }

    @Override
    @Deprecated
    public boolean add(Long element) { return add(size, element.longValue()); }

    public long set(int index, long element)
    {
        growToSize(Math.max(index, ++size));
        long out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public long setLong(int index, Long element) { return set(index, element.longValue()); }

    @Override
    @Deprecated
    public Long set(int index, Long element) { return new Long(set(index, element.longValue())); }

    public long getLong(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Long get(int index) { return new Long(getLong(index)); }

    public long removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        long out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = 0L;
        return out;
    }

    public int removeLong(int element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeLong(Long element) { return removeLong(element.longValue()); }

    @Override
    @Deprecated
    public Long remove(int index) { return new Long(removeIndex(index)); }
}
