package com.github.vitrifiedcode.javautilities;

import com.github.vitrifiedcode.javautilities.encryption.Encryptor;
import com.github.vitrifiedcode.javautilities.io.IO;
import com.github.vitrifiedcode.javautilities.math.HashUtils;
import com.github.vitrifiedcode.javautilities.object.BitConverter;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class EncryptionTest
{

    public static void main(String[] args) throws Exception
    {
        KeyPair keyPair = Encryptor.genKeys();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        Key key = Encryptor.genAESKey("Hello World");
        Encryptor.encrypt("README.md", key);
        Encryptor.decrypt("README.md", key);
    }

    public static void main0(String[] args)
    {
        BigInteger p = BigInteger.valueOf(17);
        BigInteger q = BigInteger.valueOf(19);


        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.valueOf(11);
        BigInteger d = e.modInverse(phi);

        String m = "Hello World";
        IO.println(m);
        String c = encrypt(e, n, m);
        IO.println(HashUtils.byteArrayToHexDelimitedString(c.getBytes()));
        String mm = decrypt(d, n, c);
        IO.println(mm);

    }

    public static String encrypt(BigInteger e, BigInteger n, String m)
    {
        StringBuilder out = new StringBuilder();

        for(char c : m.toCharArray())
        {
            BigInteger l = BigInteger.valueOf((long) c);
            BigInteger crypt = l.pow(e.intValue()).mod(n);
            for(byte b : BitConverter.getBytes(crypt.intValue())) { out.append((char) b); }
        }
        return out.toString();
    }

    public static String decrypt(BigInteger d, BigInteger n, String m)
    {
        StringBuilder out = new StringBuilder();

        if(m.length() % 4 != 0) { return m; }

        char[] chars = m.toCharArray();
        for(int i = 0; i < m.length(); i += 4)
        {
            BigInteger l = BigInteger.valueOf(BitConverter.toInt((byte) chars[i], (byte) chars[i + 1], (byte) chars[i + 2], (byte) chars[i + 3]));
            BigInteger crypt = l.pow(d.intValue()).mod(n);
            out.append((char) crypt.intValue());
        }
        return out.toString();
    }
}
