package com.github.vitrifiedcode.javautilities.list.arraylist;

import com.github.vitrifiedcode.javautilities.list.AbstractPrimitiveList;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class ByteArrayList extends AbstractPrimitiveList<Byte>
{
    protected static final byte[] DEFAULT_ARRAY = new byte[10];

    protected transient byte[] elementData;

    public ByteArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public ByteArrayList(int capacity)
    {
        super();
        elementData = new byte[capacity];
    }

    public int indexOf(byte element)
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
        if(!(element instanceof Byte)) { throw new IllegalArgumentException("Parameter must of type Byte."); }
        return indexOf(((Byte) element).byteValue());
    }

    public boolean contains(byte element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Byte)) { throw new IllegalArgumentException("Parameter must of type Byte."); }
        return indexOf(((Byte) element).byteValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.arraylist.ByteArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, byte element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(byte element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Byte element) { add(index, element.byteValue()); }

    @Override
    @Deprecated
    public boolean add(Byte element) { return add(size, element.byteValue()); }

    public byte set(int index, byte element)
    {
        growToSize(Math.max(index, ++size));
        byte out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public byte setByte(int index, Byte element) { return set(index, element.byteValue()); }

    @Override
    @Deprecated
    public Byte set(int index, Byte element) { return new Byte(set(index, element.byteValue())); }

    public byte getByte(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Byte get(int index) { return new Byte(getByte(index)); }

    public byte removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        byte out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = 0x00;
        return out;
    }

    public int removeByte(byte element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeByte(Byte element) { return removeByte(element.byteValue()); }

    @Override
    @Deprecated
    public Byte remove(int index) { return new Byte(removeIndex(index)); }
}
