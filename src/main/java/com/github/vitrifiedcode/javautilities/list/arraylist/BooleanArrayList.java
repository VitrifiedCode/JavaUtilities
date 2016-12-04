package com.github.vitrifiedcode.javautilities.list.arraylist;

import com.github.vitrifiedcode.javautilities.list.AbstractPrimitiveList;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

@Deprecated
public class BooleanArrayList extends AbstractPrimitiveList<Boolean>
{
    protected static final boolean[] DEFAULT_ARRAY = new boolean[10];

    protected transient boolean[] elementData;

    public BooleanArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public BooleanArrayList(int capacity)
    {
        super();
        elementData = new boolean[capacity];
    }

    public int indexOf(boolean element)
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
        if(!(element instanceof Boolean)) { throw new IllegalArgumentException("Parameter must of type Boolean."); }
        return indexOf(((Boolean) element).booleanValue());
    }

    public boolean contains(boolean element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Boolean)) { throw new IllegalArgumentException("Parameter must of type Boolean."); }
        return indexOf(((Boolean) element).booleanValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.arraylist.BooleanArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, boolean element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(boolean element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Boolean element) { add(index, element.booleanValue()); }

    @Override
    @Deprecated
    public boolean add(Boolean element) { return add(size, element.booleanValue()); }

    public boolean set(int index, boolean element)
    {
        growToSize(Math.max(index, ++size));
        boolean out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public boolean setBoolean(int index, Boolean element) { return set(index, element.booleanValue()); }

    @Override
    @Deprecated
    public Boolean set(int index, Boolean element) { return new Boolean(set(index, element.booleanValue())); }

    public boolean getBoolean(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Boolean get(int index) { return new Boolean(getBoolean(index)); }

    public boolean removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        boolean out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = false;
        return out;
    }

    public int removeBoolean(boolean element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeBoolean(Boolean element) { return removeBoolean(element.booleanValue()); }

    @Override
    @Deprecated
    public Boolean remove(int index) { return new Boolean(removeIndex(index)); }
}
