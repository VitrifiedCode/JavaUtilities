package com.github.vitrifiedcode.javautilities.json;

public class BJSONField
{
    public int id;
    public FieldType fieldType;
    public Object value;

    //region Constructors
    public BJSONField(int id, FieldType fieldType, Object value)
    {
        this.id = id;
        this.fieldType = fieldType;
        this.value = value;
    }

    public BJSONField(int id, FieldType fieldType)
    {
        this.id = id;
        this.fieldType = fieldType;
    }

    public BJSONField(int id, Object value)
    {
        this.id = id;
        this.value = value;
    }

    public BJSONField(FieldType fieldType, Object value)
    {
        this.fieldType = fieldType;
        this.value = value;
    }

    public BJSONField(Object value) { this.value = value; }

    public BJSONField(int id) { this.id = id; }

    public BJSONField(FieldType fieldType) { this.fieldType = fieldType; }

    public BJSONField() {}
    //endregion

    public void toBinary()
    {
    }

    public enum FieldType
    {
        BOOLEAN(boolean.class, Boolean.class),
        BYTE(byte.class, Byte.class),
        SHORT(short.class, Short.class),
        INT(int.class, Integer.class),
        LONG(long.class, Long.class),
        FLOAT(float.class, Float.class),
        DOUBLE(double.class, Double.class),
        CHAR(char.class, Character.class),
        STRING(String.class),
        OBJECT(Object.class);

        public static final FieldType[] VALUES = values();

        public final Class<?> primitiveType;
        public final Class<?> type;

        FieldType(Class<?> primitiveType, Class<?> type)
        {
            this.primitiveType = primitiveType;
            this.type = type;
        }

        FieldType(Class<?> type)
        {
            this.primitiveType = null;
            this.type = type;
        }

        public static FieldType getType(Class<?> type)
        {
            if(type == null) { return null; }
            for(FieldType fieldType : VALUES)
            {
                if((fieldType.primitiveType != null && type.isAssignableFrom(fieldType.primitiveType)) || type.isAssignableFrom(fieldType.type)) { return fieldType; }
            }
            return null;
        }
    }
}
