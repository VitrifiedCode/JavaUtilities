package com.github.vitrifiedcode.javautilities.list.arraylist;

import com.github.vitrifiedcode.javautilities.list.AbstractPrimitiveList;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class ShortArrayList extends AbstractPrimitiveList<Short>
{
    protected static final short[] DEFAULT_ARRAY = new short[10];

    protected transient short[] elementData;

    public ShortArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public ShortArrayList(int capacity)
    {
        super();
        elementData = new short[capacity];
    }

    public int indexOf(short element)
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
        if(!(element instanceof Short)) { throw new IllegalArgumentException("Parameter must of type Short."); }
        return indexOf(((Short) element).shortValue());
    }

    public boolean contains(short element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Short)) { throw new IllegalArgumentException("Parameter must of type Short."); }
        return indexOf(((Short) element).shortValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.arraylist.ShortArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, short element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(short element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Short element) { add(index, element.shortValue()); }

    @Override
    @Deprecated
    public boolean add(Short element) { return add(size, element.shortValue()); }

    public short set(int index, short element)
    {
        growToSize(Math.max(index, ++size));
        short out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public short setShort(int index, Short element) { return set(index, element.shortValue()); }

    @Override
    @Deprecated
    public Short set(int index, Short element) { return new Short(set(index, element.shortValue())); }

    public short getShort(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Short get(int index) { return new Short(getShort(index)); }

    public short removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        short out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = 0x0000;
        return out;
    }

    public int removeShort(short element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeShort(Short element) { return removeShort(element.shortValue()); }

    @Override
    @Deprecated
    public Short remove(int index) { return new Short(removeIndex(index)); }
}
