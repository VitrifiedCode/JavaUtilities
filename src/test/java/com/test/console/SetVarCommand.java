package com.test.console;

import com.github.vitrifiedcode.javautilities.reflection.ReflectionUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SetVarCommand extends ConsoleCommand<Object>
{
    public SetVarCommand()
    {
        super("setvar", "Set's any variable in the program.", new ParameterType("instance", false, null), new ParameterType("var", true, null), new ParameterType("value", true, null));
    }

    @Override
    public Result<Object> execute(@NotNull String[] parameters)
    {
        if(parameters.length < 2 || parameters.length > 3) { return Result.FALSE; }

        String[] loc = parameters[0].split("#");
        if(loc.length != 2) { return Result.FALSE; }
        try
        {
            Class c = Class.forName(loc[0]);
            Field field = ReflectionUtil.getField(c, loc[1]);
            Class<?> type = field.getType();
            if(!type.isPrimitive()) { return Result.FALSE; }
            if(field.isAnnotationPresent(ConsoleVar.class))
            {
                boolean b = field.getDeclaredAnnotation(ConsoleVar.class).editable();
                if(b) { return edit(c, field, parameters[1], b ? 1 : 0); }
            }
            else { return edit(c, field, parameters[1], -1); }
        }
        catch(ClassNotFoundException e) { System.out.println("Cannot find class `" + loc[0] + "`."); }
        catch(NoSuchFieldException e) { System.out.println("Cannot find method `" + loc[1] + "`."); }

        return Result.FALSE;
    }

    private void check()
    {

    }

    private Result<Object> edit(@NotNull Class c, @NotNull Field f, @NotNull String value, int editable)
    {
        if(Modifier.isFinal(f.getModifiers()) && editable == 1)
        {
            try
            {
                ReflectionUtil.setFinal(f, false);
            }
            catch(IllegalAccessException e) { return Result.FALSE; }
        }

        return Result.FALSE;
    }
}
