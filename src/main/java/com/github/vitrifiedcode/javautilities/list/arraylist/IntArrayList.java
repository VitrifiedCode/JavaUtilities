package com.github.vitrifiedcode.javautilities.list.arraylist;

import com.github.vitrifiedcode.javautilities.list.AbstractPrimitiveList;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class IntArrayList extends AbstractPrimitiveList<Integer>
{
    protected static final int[] DEFAULT_ARRAY = new int[10];

    protected transient int[] elementData;

    public IntArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public IntArrayList(int capacity)
    {
        super();
        elementData = new int[capacity];
    }

    public int indexOf(int element)
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
        if(!(element instanceof Integer)) { throw new IllegalArgumentException("Parameter must of type Integer."); }
        return indexOf(((Integer) element).intValue());
    }

    public boolean contains(int element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Integer)) { throw new IllegalArgumentException("Parameter must of type Integer."); }
        return indexOf(((Integer) element).intValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.arraylist.IntArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }
    
    public boolean add(int index, int element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(int element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Integer element) { add(index, element.intValue()); }

    @Override
    @Deprecated
    public boolean add(Integer element) { return add(size, element.intValue()); }

    public int set(int index, int element)
    {
        growToSize(Math.max(index, ++size));
        int out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public int setInt(int index, Integer element) { return set(index, element.intValue()); }

    @Override
    @Deprecated
    public Integer set(int index, Integer element) { return new Integer(set(index, element.intValue())); }

    public int getInt(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Integer get(int index) { return new Integer(getInt(index)); }

    public int removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        int out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = 0;
        return out;
    }

    public int removeInt(int element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeInt(Integer element) { return removeInt(element.intValue()); }

    @Override
    @Deprecated
    public Integer remove(int index) { return new Integer(removeIndex(index)); }
}
