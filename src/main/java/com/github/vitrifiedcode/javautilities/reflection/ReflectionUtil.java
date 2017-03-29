package com.github.vitrifiedcode.javautilities.reflection;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ReflectionUtil
{
    private ReflectionUtil() {}

    private static Field MODIFIERS;

    static
    {
        try
        {
            MODIFIERS = Field.class.getDeclaredField("modifiers");
            MODIFIERS.setAccessible(true);
            setFinal(MODIFIERS, true);
        }
        catch(NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); }

    }

    public static Field getField(@NotNull Class c, @NotNull String field) throws NoSuchFieldException
    {
        Field out = c.getDeclaredField(field);
        out.setAccessible(true);
        return out;
    }

    public static void setModifier(@NotNull Field field, int mod, boolean enabled) throws IllegalAccessException
    {
        field.setAccessible(true);
        int outMod = field.getModifiers();
        if(enabled) { outMod |= mod; }
        else { outMod &= ~mod; }
        MODIFIERS.setInt(field, outMod);
    }

    public static void setPublic(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.PUBLIC, enabled); }

    public static void setPrivate(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.PRIVATE, enabled); }

    public static void setProtected(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.PROTECTED, enabled); }

    public static void setStatic(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.STATIC, enabled); }

    public static void setFinal(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.FINAL, enabled); }

    public static void setSynchronized(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.SYNCHRONIZED, enabled); }

    public static void setVolatile(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.VOLATILE, enabled); }

    public static void setTransient(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.TRANSIENT, enabled); }

    public static void setStrict(@NotNull Field field, boolean enabled) throws IllegalAccessException { setModifier(field, Modifier.STRICT, enabled); }
}
