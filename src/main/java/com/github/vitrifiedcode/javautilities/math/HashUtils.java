package com.github.vitrifiedcode.javautilities.math;

import com.github.vitrifiedcode.javautilities.encryption.XORUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("WeakerAccess")
public final class HashUtils
{
    private HashUtils() {}

    private static MessageDigest sha1;
    private static MessageDigest sha256;
    private static MessageDigest sha384;
    private static MessageDigest sha512;

    public static byte[] serialize(final Object obj)
    {
        try
        {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(obj);
            return b.toByteArray();
        }
        catch(IOException e) { e.printStackTrace(); }
        return null;
    }

    public static long hashCode(final Object o)
    {
        try
        {
            return XORUtils.xor(SHA1Sum(serialize(o)));
        }
        catch(Exception e) { return o.hashCode(); }
    }

    public static byte[] SHA1Sum(final byte[] bytes) { return sha1.digest(bytes); }

    public static byte[] SHA256Sum(final byte[] bytes) { return sha256.digest(bytes); }

    public static byte[] SHA384Sum(final byte[] bytes) { return sha384.digest(bytes); }

    public static byte[] SHA512Sum(final byte[] bytes) { return sha512.digest(bytes); }

    public static byte[] SHA768Sum(final byte[] bytes)
    {
        byte[] sha256Bytes = sha256.digest(bytes);
        byte[] sha512Bytes = sha512.digest(bytes);
        byte[] out = new byte[192];
        System.arraycopy(sha256Bytes, 0, out, 0, sha256Bytes.length);
        System.arraycopy(sha512Bytes, 0, out, 32, sha512Bytes.length);
        return out;
    }

    public static byte[] SHA1024Sum(final byte[] bytes)
    {
        byte[] sha512Bytes = sha512.digest(bytes);
        byte[] sha512Bytes0 = sha512.digest(sha512Bytes);
        byte[] out = new byte[256];
        System.arraycopy(sha512Bytes, 0, out, 0, sha512Bytes.length);
        System.arraycopy(sha512Bytes0, 0, out, 64, sha512Bytes0.length);
        return out;
    }


    public static byte[] SHA1Sum(final Object o) { return SHA1Sum(serialize(o)); }

    public static byte[] SHA256Sum(final Object o) { return SHA256Sum(serialize(o)); }

    public static byte[] SHA384Sum(final Object o) { return SHA384Sum(serialize(o)); }

    public static byte[] SHA512Sum(final Object o) { return SHA512Sum(serialize(o)); }

    public static byte[] SHA768Sum(final Object o) { return SHA768Sum(serialize(o)); }

    public static byte[] SHA1024Sum(final Object o) { return SHA1024Sum(serialize(o)); }


    public static String byteArrayToHexString(final byte[] hash)
    {
        StringBuilder out = new StringBuilder(hash.length * 2);
        for(byte b : hash) { out.append(String.format("%02x", b).toUpperCase()); }
        return out.toString();
    }

    public static String byteArrayToHexDelimString(final byte[] hash)
    {
        StringBuilder out = new StringBuilder(hash.length * 3);
        for(byte b : hash) { out.append(String.format("%02x", b).toUpperCase()).append("-"); }
        return out.subSequence(0, out.length() - 1).toString();
    }

    static
    {
        try
        {
            sha1 = MessageDigest.getInstance("SHA-1");
            sha256 = MessageDigest.getInstance("SHA-256");
            sha384 = MessageDigest.getInstance("SHA-384");
            sha512 = MessageDigest.getInstance("SHA-512");
        }
        catch(NoSuchAlgorithmException e) { e.printStackTrace(); }
    }
}
