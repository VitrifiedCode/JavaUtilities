package com.github.vitrifiedcode.javautilities.array;


import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "unused", "UnnecessaryUnboxing", "Convert2Diamond", "UnnecessaryBoxing", "unchecked" })
public final class Array
{
    private Array() {}

    //region String2Primitive
    public static boolean[] toBoolean(@Nonnull final String... in)
    {
        boolean[] out = new boolean[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Boolean.parseBoolean(in[i]); }
        return out;
    }

    public static char[] toChar(@Nonnull final String... in)
    {
        char[] out = new char[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].charAt(0); }
        return out;
    }

    public static byte[] toByte(@Nonnull final String... in)
    {
        byte[] out = new byte[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Byte.parseByte(in[i]); }
        return out;
    }


    public static int[] toInt(@Nonnull final String... in)
    {
        int[] out = new int[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Integer.parseInt(in[i]); }
        return out;
    }

    public static short[] toShort(@Nonnull final String... in)
    {
        short[] out = new short[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Short.parseShort(in[i]); }
        return out;
    }

    public static long[] toLong(@Nonnull final String... in)
    {
        long[] out = new long[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Long.parseLong(in[i]); }
        return out;
    }

    public static float[] toFloat(@Nonnull final String... in)
    {
        float[] out = new float[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Float.parseFloat(in[i]); }
        return out;
    }

    public static double[] toDouble(@Nonnull final String... in)
    {
        double[] out = new double[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Double.parseDouble(in[i]); }
        return out;
    }

    public static boolean[] toBoolean(@Nonnull String in) { return toBoolean(in.replace("[", "").replace("]", "").split(", ")); }

    public static char[] toChar(@Nonnull String in) { return toChar(in.replace("[", "").replace("]", "").split(", ")); }

    public static String[] toString(@Nonnull String in) { return in.replace("[", "").replace("]", "").split(", "); }

    public static byte[] toByte(@Nonnull String in) { return toByte(in.replace("[", "").replace("]", "").split(", ")); }

    public static short[] toShort(@Nonnull String in) { return toShort(in.replace("[", "").replace("]", "").split(", ")); }

    public static int[] toInt(@Nonnull String in) { return toInt(in.replace("[", "").replace("]", "").split(", ")); }

    public static long[] toLong(@Nonnull String in) { return toLong(in.replace("[", "").replace("]", "").split(", ")); }

    public static float[] toFloat(String in) { return toFloat(in.replace("[", "").replace("]", "").split(", ")); }

    public static double[] toDouble(String in) { return toDouble(in.replace("[", "").replace("]", "").split(", ")); }
    //endregion

    //region ToPrimitive
    @Nonnull
    public static boolean[] toPrimitive(final @Nonnull Boolean[] in)
    {
        boolean[] out = new boolean[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].booleanValue(); }
        return out;
    }

    @Nonnull
    public static char[] toPrimitive(final @Nonnull Character[] in)
    {
        char[] out = new char[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].charValue(); }
        return out;
    }

    @Nonnull
    public static byte[] toPrimitive(final @Nonnull Byte[] in)
    {
        byte[] out = new byte[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].byteValue(); }
        return out;
    }

    @Nonnull
    public static short[] toPrimitive(final @Nonnull Short[] in)
    {
        short[] out = new short[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].shortValue(); }
        return out;
    }

    @Nonnull
    public static int[] toPrimitive(final @Nonnull Integer[] in)
    {
        int[] out = new int[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].intValue(); }
        return out;
    }

    @Nonnull
    public static long[] toPrimitive(final @Nonnull Long[] in)
    {
        long[] out = new long[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].longValue(); }
        return out;
    }

    @Nonnull
    public static float[] toPrimitive(final @Nonnull Float[] in)
    {
        float[] out = new float[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].floatValue(); }
        return out;
    }

    @Nonnull
    public static double[] toPrimitive(final @Nonnull Double[] in)
    {
        double[] out = new double[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = in[i].doubleValue(); }
        return out;
    }
    //endregion

    //region FromPrimitive
    @Nonnull
    public static Boolean[] fromPrimitive(final @Nonnull boolean[] in)
    {
        Boolean[] out = new Boolean[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Boolean.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Character[] fromPrimitive(final @Nonnull char[] in)
    {
        Character[] out = new Character[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Character.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Byte[] fromPrimitive(final @Nonnull byte[] in)
    {
        Byte[] out = new Byte[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Byte.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Short[] fromPrimitive(final @Nonnull short[] in)
    {
        Short[] out = new Short[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Short.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Integer[] fromPrimitive(final @Nonnull int[] in)
    {
        Integer[] out = new Integer[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Integer.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Long[] fromPrimitive(final @Nonnull long[] in)
    {
        Long[] out = new Long[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Long.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Float[] fromPrimitive(final @Nonnull float[] in)
    {
        Float[] out = new Float[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Float.valueOf(in[i]); }
        return out;
    }

    @Nonnull
    public static Double[] fromPrimitive(final @Nonnull double[] in)
    {
        Double[] out = new Double[in.length];
        for(int i = 0; i < in.length; ++i) { out[i] = Double.valueOf(in[i]); }
        return out;
    }
    //endregion

    //region Append
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
    //endregion

    //region Concat
    public static <T> T[] concat(T[]... arrs)
    {
        int newLength = 0;
        for(T[] t : arrs) { newLength += t.length; }
        T[] out = (T[]) (new Object[newLength]);
        int offset = 0;
        for(T[] t : arrs)
        {
            System.arraycopy(t, 0, out, offset, t.length);
            offset = t.length;
        }
        return out;
    }
    //endregion

    //region List2Array
    @Nonnull
    public static boolean[] toBooleanArray(@Nonnull final List<Boolean> list) { return toPrimitive(list.toArray(new Boolean[list.size()])); }

    @Nonnull
    public static char[] toCharArray(@Nonnull final List<Character> list) { return toPrimitive(list.toArray(new Character[list.size()])); }

    @Nonnull
    public static String[] toStringArray(@Nonnull final List<String> list) { return list.toArray(new String[list.size()]); }

    @Nonnull
    public static byte[] toByteArray(@Nonnull final List<Byte> list) { return toPrimitive(list.toArray(new Byte[list.size()])); }

    @Nonnull
    public static short[] toShortArray(@Nonnull final List<Short> list) { return toPrimitive(list.toArray(new Short[list.size()])); }

    @Nonnull
    public static int[] toIntArray(@Nonnull final List<Integer> list)
    {
        if(list.size() < 28000) { return toPrimitive(list.toArray(new Integer[list.size()])); }
        return list.stream().mapToInt(v -> v).toArray();
    }

    @Nonnull
    public static long[] toLongArray(@Nonnull final List<Long> list)
    {
        if(list.size() < 28000) { return toPrimitive(list.toArray(new Long[list.size()])); }
        return list.stream().mapToLong(v -> v).toArray();
    }

    @Nonnull
    public static float[] toFloatArray(@Nonnull final List<Float> list) { return toPrimitive(list.toArray(new Float[list.size()])); }

    @Nonnull
    public static double[] toDoubleArray(@Nonnull final List<Double> list)
    {
        if(list.size() < 28000) { return toPrimitive(list.toArray(new Double[list.size()])); }
        return list.stream().mapToDouble(v -> v).toArray();
    }

    @Nonnull
    public static Object[] toObjectArray(@Nonnull final List<Object> list) { return list.toArray(new Object[list.size()]); }
    //endregion

    @Nonnull
    public static <K, V> Map<K, List<V>> appendToMap(final @Nonnull Map<K, List<V>> map, @Nonnull final K key, @Nonnull final V value)
    {
        List<V> temp;
        if(!map.containsKey(key)) { temp = new ArrayList<V>(); }
        else { temp = map.get(key); }
        temp.add(value);
        map.put(key, temp);

        return map;
    }

    @Nonnull
    public static <T> List<T> remove(final @Nonnull List<T> list, final int index)
    {
        final int size = list.size();
        if(index == size) { list.remove(index); }
        else { list.set(index, list.remove(size)); }
        return list;
    }

    @Nonnull
    public static <T> List<T> remove(final @Nonnull List<T> list, final @Nonnull T element)
    {
        final int index = list.indexOf(element);
        final int size = list.size();
        if(index == size) { list.remove(element); }
        else { list.set(index, list.remove(size)); }
        return list;
    }

    @Nonnull
    public static <T> T[] reverse(final @Nonnull T[] arr)
    {
        @SuppressWarnings("unchecked")
        T[] out = (T[]) new Object[arr.length];
        int len = arr.length - 1;
        for(int i = len; i >= 0; ++i)
        {
            out[len - i] = arr[i];
        }
        return out;
    }
}
