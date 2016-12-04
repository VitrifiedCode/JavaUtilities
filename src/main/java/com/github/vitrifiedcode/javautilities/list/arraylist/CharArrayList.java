package com.github.vitrifiedcode.javautilities.list;

import com.github.vitrifiedcode.javautilities.string.StringUtil;

public class CharArrayList extends AbstractPrimitiveList<Character>
{
    protected static final char[] DEFAULT_ARRAY = new char[10];

    protected transient char[] elementData;

    public CharArrayList()
    {
        super();
        elementData = DEFAULT_ARRAY;
    }

    public CharArrayList(int capacity)
    {
        super();
        elementData = new char[capacity];
    }

    public int indexOf(char element)
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
        if(!(element instanceof Character)) { throw new IllegalArgumentException("Parameter must of type Character."); }
        return indexOf(((Character) element).charValue());
    }

    public boolean contains(char element) { return indexOf(element) >= 0; }

    @Override
    @Deprecated
    public boolean contains(Object element)
    {
        if(!(element instanceof Character)) { throw new IllegalArgumentException("Parameter must of type Character."); }
        return indexOf(((Character) element).charValue()) >= 0;
    }

    protected void ensureSize(int size, int index)
    {
        if(size < 0 || size > MAX_SIZE) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot expand array to size that is larger than ", MAX_SIZE, " or less than 0, the value ", size, "was passed to `com.github.vitrifiedcode.javautilities.list.CharacterArrayList#ensureSize(int)`")); }
        if(elementData.length >= size) { return; }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    public boolean add(int index, char element)
    {
        growToSize(Math.max(index, ++size), index);
        elementData[index] = element;
        return true;
    }

    public boolean add(char element) { return add(size, element); }

    @Override
    @Deprecated
    public void add(int index, Character element) { add(index, element.charValue()); }

    @Override
    @Deprecated
    public boolean add(Character element) { return add(size, element.charValue()); }

    public char set(int index, char element)
    {
        growToSize(Math.max(index, ++size));
        char out = elementData[index];
        elementData[index] = element;
        return out;
    }

    public char setCharacter(int index, Character element) { return set(index, element.charValue()); }

    @Override
    @Deprecated
    public Character set(int index, Character element) { return new Character(set(index, element.charValue())); }

    public char getCharacter(int index) { return elementData[index]; }

    @Override
    @Deprecated
    public Character get(int index) { return new Character(getCharacter(index)); }

    public char removeIndex(int index)
    {
        if(index < 0 || index >= size) { throw new IndexOutOfBoundsException(StringUtil.build("Cannot remove from array index ", index, ".")); }
        char out = elementData[index];
        elementData[index] = elementData[--size];
        elementData[size] = StringUtil.NULL;
        return out;
    }

    public int removeCharacter(char element)
    {
        int out = indexOf(element);
        removeIndex(out);
        return out;
    }

    public int removeCharacter(Character element) { return removeCharacter(element.charValue()); }

    @Override
    @Deprecated
    public Character remove(int index) { return new Character(removeIndex(index)); }
}
