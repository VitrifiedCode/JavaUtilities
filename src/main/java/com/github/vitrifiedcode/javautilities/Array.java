package com.github.vitrifiedcode.javautilities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "unused", "UnnecessaryUnboxing", "Convert2Diamond" })
public final class Array
{
    private Array() {}

    public static boolean[] toBoolean(@NotNull final String... in)
    {
        boolean[] out = new boolean[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Boolean.parseBoolean(in[i]); }
        return out;
    }

    public static char[] toChar(@NotNull final String... in)
    {
        char[] out = new char[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].charAt(0); }
        return out;
    }

    public static byte[] toByte(@NotNull final String... in)
    {
        byte[] out = new byte[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Byte.parseByte(in[i]); }
        return out;
    }


    public static int[] toInt(@NotNull final String... in)
    {
        int[] out = new int[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Integer.parseInt(in[i]); }
        return out;
    }

    public static short[] toShort(@NotNull final String... in)
    {
        short[] out = new short[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Short.parseShort(in[i]); }
        return out;
    }

    public static long[] toLong(@NotNull final String... in)
    {
        long[] out = new long[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Long.parseLong(in[i]); }
        return out;
    }

    public static float[] toFloat(@NotNull final String... in)
    {
        float[] out = new float[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Float.parseFloat(in[i]); }
        return out;
    }

    public static double[] toDouble(@NotNull final String... in)
    {
        double[] out = new double[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Double.parseDouble(in[i]); }
        return out;
    }

    public static boolean[] toBoolean(@NotNull String in) { return toBoolean(in.replace("[", "").replace("]", "").split(", ")); }

    public static char[] toChar(@NotNull String in) { return toChar(in.replace("[", "").replace("]", "").split(", ")); }

    public static String[] toString(@NotNull String in) { return in.replace("[", "").replace("]", "").split(", "); }

    public static byte[] toByte(@NotNull String in) { return toByte(in.replace("[", "").replace("]", "").split(", ")); }

    public static short[] toShort(@NotNull String in) { return toShort(in.replace("[", "").replace("]", "").split(", ")); }

    public static int[] toInt(@NotNull String in) { return toInt(in.replace("[", "").replace("]", "").split(", ")); }

    public static long[] toLong(@NotNull String in) { return toLong(in.replace("[", "").replace("]", "").split(", ")); }

    public static float[] toFloat(String in) { return toFloat(in.replace("[", "").replace("]", "").split(", ")); }

    public static double[] toDouble(String in) { return toDouble(in.replace("[", "").replace("]", "").split(", ")); }

    public static boolean[] toPrimitive(final Boolean[] in)
    {
        boolean[] out = new boolean[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].booleanValue(); }
        return out;
    }

    public static char[] toPrimitive(final Character[] in)
    {
        char[] out = new char[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].charValue(); }
        return out;
    }

    public static byte[] toPrimitive(final Byte[] in)
    {
        byte[] out = new byte[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].byteValue(); }
        return out;
    }

    public static short[] toPrimitive(final Short[] in)
    {
        short[] out = new short[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].shortValue(); }
        return out;
    }

    public static int[] toPrimitive(final Integer[] in)
    {
        int[] out = new int[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].intValue(); }
        return out;
    }

    public static long[] toPrimitive(final Long[] in)
    {
        long[] out = new long[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].longValue(); }
        return out;
    }

    public static float[] toPrimitive(final Float[] in)
    {
        float[] out = new float[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].floatValue(); }
        return out;
    }

    public static double[] toPrimitive(final Double[] in)
    {
        double[] out = new double[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].doubleValue(); }
        return out;
    }

    public static boolean[] append(boolean[] arr, final boolean app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static char[] append(char[] arr, final char app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static String[] append(String[] arr, final String app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static byte[] append(byte[] arr, final byte app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static short[] append(short[] arr, final short app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static int[] append(int[] arr, final int app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static long[] append(long[] arr, final long app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static float[] append(float[] arr, final float app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static double[] append(double[] arr, final double app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    public static Object[] append(Object[] arr, final Object app)
    {
        final int len = arr.length;
        arr = Arrays.copyOf(arr, len + 1);
        arr[len] = app;
        return arr;
    }

    @NotNull
    public static boolean[] toBooleanArray(@NotNull final List<Boolean> list) { return toPrimitive(list.toArray(new Boolean[list.size()])); }

    @NotNull
    public static char[] toCharArray(@NotNull final List<Character> list) { return toPrimitive(list.toArray(new Character[list.size()])); }

    @NotNull
    public static String[] toStringArray(@NotNull final List<String> list) { return list.toArray(new String[list.size()]); }

    @NotNull
    public static byte[] toByteArray(@NotNull final List<Byte> list) { return toPrimitive(list.toArray(new Byte[list.size()])); }

    @NotNull
    public static short[] toShortArray(@NotNull final List<Short> list) { return toPrimitive(list.toArray(new Short[list.size()])); }

    @NotNull
    public static int[] toIntArray(@NotNull final List<Integer> list)
    {
        if(list.size() < 28000) { return toPrimitive(list.toArray(new Integer[list.size()])); }
        return list.stream().mapToInt(v -> v).toArray();
    }

    @NotNull
    public static long[] toLongArray(@NotNull final List<Long> list)
    {
        if(list.size() < 28000) { return toPrimitive(list.toArray(new Long[list.size()])); }
        return list.stream().mapToLong(v -> v).toArray();
    }

    @NotNull
    public static float[] toFloatArray(@NotNull final List<Float> list) { return toPrimitive(list.toArray(new Float[list.size()])); }

    @NotNull
    public static double[] toDoubleArray(@NotNull final List<Double> list)
    {
        if(list.size() < 28000) { return toPrimitive(list.toArray(new Double[list.size()])); }
        return list.stream().mapToDouble(v -> v).toArray();
    }

    @NotNull
    public static Object[] toObjectArray(@NotNull final List<Object> list) { return list.toArray(new Object[list.size()]); }

    @NotNull
    public static <K, V> Map<K, List<V>> appendToMap(@NotNull Map<K, List<V>> map, @NotNull final K key, @NotNull final V value)
    {
        List<V> temp;
        if(!map.containsKey(key)) { temp = new ArrayList<V>(); }
        else { temp = map.get(key); }
        temp.add(value);
        map.put(key, temp);

        return map;
    }
}
