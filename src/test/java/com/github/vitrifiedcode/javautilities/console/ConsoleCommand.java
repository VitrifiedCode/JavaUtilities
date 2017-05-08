package com.github.vitrifiedcode.javautilities.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class ConsoleCommand<T>
{
    @NotNull
    public final String command;
    @NotNull
    public final String description;
    @Nullable
    public final ParameterType[] parameterTypes;

    public ConsoleCommand(@NotNull String command, @NotNull String description, @Nullable ParameterType... parameterTypes)
    {
        this.command = command;
        this.description = description;
        this.parameterTypes = parameterTypes;
    }

    public ConsoleCommand(@NotNull String command, @NotNull String description, @Nullable String parameters)
    {
        this(command, description, parameters == null ? null : identifyParameters(parameters));
    }

    @NotNull
    @Override
    public final String toString()
    {
        return command + ": " + description;
    }

    @NotNull
    public final String getUsage()
    {
        StringBuilder out = new StringBuilder();
        if(parameterTypes == null) { out.append(" "); }
        else { for(ParameterType p : parameterTypes) { out.append(p.representation()).append(" "); } }
        return "Usage: `" + command + " " + out.deleteCharAt(out.length()).toString() + "`.";
    }

    public abstract Result<T> execute(@NotNull String[] parameters);

    @NotNull
    private static ParameterType[] identifyParameters(final @NotNull String parameters)
    {
        final List<ParameterType> parametersOut = new ArrayList<ParameterType>();
        StringTokenizer tokens = new StringTokenizer(parameters.replaceAll(" ", ""), "<>[]", true);
        MutableParameterType current = new MutableParameterType();
        while(tokens.hasMoreElements())
        {
            String element = tokens.nextToken();
            if("<".equals(element))
            {
                current.required = true;
                continue;
            }
            else if("[".equals(element))
            {
                current.required = false;
                continue;
            }
            else if(">".equals(element) || "]".equals(element))
            {
                current.closed = true;
                parametersOut.add(new ParameterType(current));
                continue;
            }

            current.name = element;
        }
        return parametersOut.toArray(new ParameterType[parametersOut.size()]);
    }

    private static class MutableParameterType
    {
        @NotNull
        public String name = "";
        public boolean required;
        public boolean opened = false;
        public boolean closed;
        @Nullable
        public ConsoleCommand.MutableParameterType dependency;

        public MutableParameterType() {}

        public MutableParameterType(@Nullable String name, boolean required, @Nullable ConsoleCommand.MutableParameterType dependency, boolean closed)
        {
            this.name = name == null ? "" : name;
            this.required = required;
            opened = true;
            this.closed = closed;
            this.dependency = dependency;
        }

        public MutableParameterType(@NotNull ConsoleCommand.MutableParameterType copy) { this(copy.name, copy.required, copy.dependency, copy.closed); }

        @Override
        public String toString()
        {
            return "MutableParameterType: { \"Name\": \"" + name + "\"; \"Required\": \"" + required + "\"; \"Dependency\": \"" + dependency + "\"; };";
        }

        public ParameterType getDependency()
        {
            return dependency == null ? null : new ParameterType(dependency);
        }
    }

    public static class ParameterType
    {
        @NotNull
        public final String name;
        public final boolean required;
        @Nullable
        public final ConsoleCommand.ParameterType dependency;

        public ParameterType(@NotNull String name, boolean required, @Nullable ConsoleCommand.ParameterType dependency)
        {
            this.name = name;
            this.required = required;
            this.dependency = dependency;
        }

        public ParameterType(@NotNull ConsoleCommand.ParameterType copy) { this(copy.name, copy.required, copy.dependency); }

        public ParameterType(@NotNull ConsoleCommand.MutableParameterType copy) { this(copy.name, copy.required, copy.getDependency()); }

        @Override
        public String toString()
        {
            return "ParameterType: { \"Name\": \"" + name + "\"; \"Required\": \"" + required + "\"; \"Dependency\": \"" + dependency.toString() + "\"; };";
        }

        @NotNull
        public String representation()
        {
            StringBuilder out = new StringBuilder();
            boolean dep = dependency != null;
            boolean req = false;
            if(dep)
            {
                out.append(dependency.representation());
                out.deleteCharAt(out.length() - 1);
                if(dependency.required) { req = true; }
            }

            if(required) { out.append("<"); }
            else { out.append("["); }
            out.append(name);
            if(required) { out.append(">"); }
            else { out.append("]"); }
            if(dep)
            {
                if(req) { out.append(">"); }
                else { out.append("]"); }
            }

            return out.toString();
        }
    }

    public static class Result<T>
    {
        public static final Result<Object> FALSE = new Result<Object>(false);
        public static final Result<Object> TRUE = new Result<Object>(true);

        public boolean successful;
        @Nullable
        public String message;
        @Nullable
        public T value;

        public Result() {}

        public Result(boolean successful, @Nullable String message, @Nullable T value)
        {
            this.successful = successful;
            this.message = message;
            this.value = value;
        }

        public Result(boolean successful, @Nullable String message)
        {
            this.successful = successful;
            this.message = message;
        }

        public Result(boolean successful)
        {
            this.successful = successful;
        }
    }
}
