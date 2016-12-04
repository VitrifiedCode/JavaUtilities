package com.github.vitrifiedcode.javautilities.list.arraylist;

import com.github.vitrifiedcode.javautilities.list.AbstractPrimitiveList;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class DoubleArrayList extends AbstractPrimitiveList<Double>
{
    protected static final double[] DEFAULT_ARRAY = new double[10];

    protected transient double[] elementData;

    public DoubleArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public DoubleArrayList(int capacity)
    {
        super();
        elementData = new double[capacity];
    }

    public int indexOf(double element)
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
        if(!(element instanceof Double)) { throw new IllegalArgumentException("Parameter must of type Double."); }
        return indexOf(((Double) element).doubleValue());
    }

    public boolean contains(double element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Double)) { throw new IllegalArgumentException("Parameter must of type Double."); }
        return indexOf(((Double) element).doubleValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.arraylist.DoubleArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, double element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(double element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Double element) { add(index, element.doubleValue()); }

    @Override
    @Deprecated
    public boolean add(Double element) { return add(size, element.doubleValue()); }

    public double set(int index, double element)
    {
        growToSize(Math.max(index, ++size));
        double out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public double setDouble(int index, Double element) { return set(index, element.doubleValue()); }

    @Override
    @Deprecated
    public Double set(int index, Double element) { return new Double(set(index, element.doubleValue())); }

    public double getDouble(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Double get(int index) { return new Double(getDouble(index)); }

    public double removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        double out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = 0.0D;
        return out;
    }

    public int removeDouble(double element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeDouble(Double element) { return removeDouble(element.doubleValue()); }

    @Override
    @Deprecated
    public Double remove(int index) { return new Double(removeIndex(index)); }
}
