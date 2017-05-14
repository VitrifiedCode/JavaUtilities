package com.github.vitrifiedcode.javautilities.encryption;

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SuppressWarnings({ "unused", "SameParameterValue" })
public class Encryptor
{
    public static KeyPair genKeys()
    {
        KeyPairGenerator keyGen;
        try
        {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            return keyGen.generateKeyPair();
        }
        catch(NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }

    public static SecretKeySpec genAESKey(String password)
    {
        byte[] bytes = password.getBytes();
        int pad = bytes.length % 16;
        if(pad != 0) { bytes = Arrays.copyOf(bytes, bytes.length + (16 - pad)); }
        return new SecretKeySpec(bytes, "AES");
    }

    public static byte[] encrypt(String m, Key key, String algo) throws Exception
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(m.getBytes());
    }

    public static byte[] encrypt(byte[] m, Key key, String algo) throws Exception
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(m);
    }

    public static String decrypt(byte[] c, Key key, String algo) throws Exception
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(c));
    }

    public static byte[] decryptB(byte[] c, Key key, String algo) throws Exception
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(c);
    }

    public static void encrypt(InputStream m, OutputStream c, Key key) throws Exception
    {
        c.write(encrypt(IOUtils.toByteArray(m), key, "AES"));
    }

    public static void decrypt(InputStream c, OutputStream m, Key key) throws Exception
    {
        m.write(decryptB(IOUtils.toByteArray(c), key, "AES"));
    }

    public static void encrypt(String file, Key key) throws Exception
    {
        InputStream m = new FileInputStream(file);
        OutputStream c = new FileOutputStream(file + ".crypt");
        c.write(encrypt(IOUtils.toByteArray(m), key, "AES"));
    }

    public static void decrypt(String file, Key key) throws Exception
    {
        InputStream c = new FileInputStream(file.endsWith(".crypt") ? file : file + ".crypt");
        OutputStream m = new FileOutputStream(file + ".decrypt");
        m.write(decryptB(IOUtils.toByteArray(c), key, "AES"));
    }
}
