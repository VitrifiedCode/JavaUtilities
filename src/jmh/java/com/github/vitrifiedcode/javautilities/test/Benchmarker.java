package com.github.vitrifiedcode.javautilities.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.SerializationUtils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Benchmarker
{
    static Pattern pattern = Pattern.compile("\\/\\*( *)[a-zA-Z0-9_]+( *)\\*\\/");
    static Gson gson = new GsonBuilder()
//                .excludeFieldsWithModifiers(Modifier.STATIC)
//                .setPrettyPrinting()
.create();
    byte[] bb = SerializationUtils.serialize(pattern);
    String s = gson.toJson(pattern);


    @Benchmark
    public void createNew()
    {
        Pattern pattern = Pattern.compile("\\/\\*( *)[a-zA-Z0-9_]+( *)\\*\\/");
    }

    @Benchmark
    public void deBytes()
    {
        Pattern pattern0 = SerializationUtils.deserialize(bb);
    }

    @Benchmark
    public void deJson()
    {
        Pattern pattern1 = gson.fromJson(s, new TypeToken<Pattern>() { }.getType());
    }
}
