package com.github.vitrifiedcode.javautilities.list;

import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class FloatArrayList extends AbstractPrimitiveList<Float>
{
    protected static final float[] DEFAULT_ARRAY = new float[10];

    protected transient float[] elementData;

    public FloatArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public FloatArrayList(int capacity)
    {
        super();
        elementData = new float[capacity];
    }

    public int indexOf(float element)
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
        if(!(element instanceof Float)) { throw new IllegalArgumentException("Parameter must of type Float."); }
        return indexOf(((Float) element).floatValue());
    }

    public boolean contains(float element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Float)) { throw new IllegalArgumentException("Parameter must of type Float."); }
        return indexOf(((Float) element).floatValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.FloatArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, float element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(float element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Float element) { add(index, element.floatValue()); }

    @Override
    @Deprecated
    public boolean add(Float element) { return add(size, element.floatValue()); }

    public float set(int index, float element)
    {
        growToSize(Math.max(index, ++size));
        float out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public float setFloat(int index, Float element) { return set(index, element.floatValue()); }

    @Override
    @Deprecated
    public Float set(int index, Float element) { return new Float(set(index, element.floatValue())); }

    public float getFloat(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Float get(int index) { return new Float(getFloat(index)); }

    public float removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        float out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = 0.0F;
        return out;
    }

    public int removeFloat(float element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeFloat(Float element) { return removeFloat(element.floatValue()); }

    @Override
    @Deprecated
    public Float remove(int index) { return new Float(removeIndex(index)); }
}
