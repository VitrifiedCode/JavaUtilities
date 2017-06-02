package com.github.vitrifiedcode.javautilities.console.command;

import com.github.vitrifiedcode.javautilities.reflection.ReflectionUtil;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SetVarCommand extends Command<Object>
{
    public SetVarCommand()
    {
        super("setvar", "Set's any variable in the program.", new ParameterType("instance", false, null), new ParameterType("var", true, null), new ParameterType("value", true, null));
    }

    @Override
    public Result<Object> execute(@Nonnull String[] parameters)
    {
        if(parameters.length < 2 || parameters.length > 3) { return Result.FALSE; }

        String[] loc = parameters[0].split("#");
        if(loc.length != 2) { return Result.FALSE; }
        try
        {
            Class c = Class.forName(loc[0]);
            Field field = ReflectionUtil.getField(c, loc[1]);
            if(!Modifier.isStatic(field.getModifiers())) { return Result.FALSE; }
            Class<?> type = field.getType();
            if(!(type.isPrimitive() && !type.isAssignableFrom(String.class))) { return Result.FALSE; }
            if(field.isAnnotationPresent(ConsoleVar.class))
            {
                boolean b = field.getDeclaredAnnotation(ConsoleVar.class).editable();
                if(b) { return edit(c, field, parameters[1], type, 1); }
            }
//            else { return edit(c, field, parameters[1], -1); }
        }
        catch(ClassNotFoundException e) { System.out.println("Cannot find class `" + loc[0] + "`."); }
        catch(NoSuchFieldException e) { System.out.println("Cannot find method `" + loc[1] + "`."); }

        return Result.FALSE;
    }

    private void check()
    {

    }

    private Result<Object> edit(@Nonnull Class c, @Nonnull Field f, @Nonnull String value, Class<?> type, int editable)
    {
        try
        {
            if(Modifier.isFinal(f.getModifiers()) && editable == 1) { ReflectionUtil.setFinal(f, false); }
            if(type.isAssignableFrom(String.class))
            {
                if(value.startsWith("\"") && value.endsWith("\""))
                {
                    f.set(null, value.substring(1, value.length() - 1));
                    return Result.TRUE;
                }
            }
            else
            {
                if(type.isAssignableFrom(Number.class))
                {
                    if(type.isAssignableFrom(Byte.class)) { f.set(null, Byte.valueOf(value)); }
                    else if(type.isAssignableFrom(Short.class)) { f.set(null, Short.valueOf(value)); }
                    else if(type.isAssignableFrom(Integer.class)) { f.set(null, Integer.valueOf(value)); }
                    else if(type.isAssignableFrom(Long.class)) { f.set(null, Long.valueOf(value)); }
                    else if(type.isAssignableFrom(Float.class)) { f.set(null, Float.valueOf(value)); }
                    else if(type.isAssignableFrom(Double.class)) { f.set(null, Double.valueOf(value)); }
                    else { return Result.FALSE; }
                }
                else if(type.isAssignableFrom(Character.class) && value.length() == 1) { f.set(null, value.charAt(0)); }
                else if(type.isAssignableFrom(Boolean.class) && value.length() == 1) { f.set(null, Boolean.valueOf(value)); }
                else { return Result.FALSE; }
                return Result.TRUE;
            }

        }
        catch(IllegalAccessException e) { return Result.FALSE; }

        return Result.FALSE;
    }
}
