package com.github.vitrifiedcode.javautilities.json;

import java.lang.reflect.Field;

public class BJSONConverter
{
    public static void convert(Object object) throws IllegalAccessException
    {
        Field[] fields = object.getClass().getDeclaredFields();
        if(fields.length < 1) { return; }

        BJSONField[] outFields = new BJSONField[fields.length];

        for(int i = 0; i < fields.length; ++i)
        {
            BJSONField.FieldType fieldType = BJSONField.FieldType.getType(fields[i].getType());
            if(fieldType == BJSONField.FieldType.OBJECT) { convert(fields[i].get(object)); }
            outFields[i] = new BJSONField(fields[i].hashCode(), BJSONField.FieldType.getType(fields[i].getType()), fields[i].get(object));
        }
    }
}
