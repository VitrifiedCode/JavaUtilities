package com.github.vitrifiedcode.javautilities.other;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil
{
    private RandomUtil() {}

    public static final Random RANDOM = ThreadLocalRandom.current();
    public static final char[] DEFAULT_CHAR_SET;
    public static final String DEFAULT_STR_CHAR_SET;

    static
    {
        StringBuilder tmp = new StringBuilder(95);
        for(int i = 32; i < 127; ++i) { tmp.append((char) i); }
        DEFAULT_STR_CHAR_SET = tmp.toString();
        DEFAULT_CHAR_SET = DEFAULT_STR_CHAR_SET.toCharArray();
    }

    public static int getRandomInt() { return RANDOM.nextInt(); }

    public static int getRandomInt(int min, int max) { return RANDOM.nextInt((max - min) + 1) + min; }

    public static char getRandomChar(char[] charSet) { return charSet[getRandomInt(0, charSet.length - 1)]; }

    public static char getRandomChar(String charSet) { return getRandomChar(charSet.toCharArray()); }

    public static char getRandomChar() { return getRandomChar(DEFAULT_CHAR_SET); }

    public static String getRandomString(int size, char[] charSet)
    {
        StringBuilder out = new StringBuilder(size);
        for(int i = 0; i < size; ++i) { out.append(getRandomChar(charSet)); }
        return out.toString();
    }

    public static String getRandomString(int size, String charSet) { return getRandomString(size, charSet.toCharArray()); }

    public static String getRandomString(int min, int max, char[] charSet) { return getRandomString(getRandomInt(min, max), charSet); }

    public static String getRandomString(int min, int max, String charSet) { return getRandomString(getRandomInt(min, max), charSet.toCharArray()); }

    public static String getRandomString(int min, int max) { return getRandomString(getRandomInt(min, max), DEFAULT_CHAR_SET); }

    public static UUID getRandomUUID(Random rand)
    {
        long i = rand.nextLong() & -61441L | 16384L;
        long j = rand.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
        return new UUID(i, j);
    }

    /**
     * Generates a random UUID using ThreadLocalRandom.current()
     */
    public static UUID getRandomUUID() { return getRandomUUID(RANDOM); }
}
