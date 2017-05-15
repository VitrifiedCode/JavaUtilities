package com.github.vitrifiedcode.javautilities.encryption;

import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
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

    @Nonnull
    public static SecretKeySpec genAESKey(@Nonnull String password)
    {
        byte[] bytes = password.getBytes();
        int pad = bytes.length % 16;
        if(pad != 0) { bytes = Arrays.copyOf(bytes, bytes.length + (16 - pad)); }
        return new SecretKeySpec(bytes, "AES");
    }

    @Nonnull
    public static byte[] encrypt(final @Nonnull String m, final @Nonnull Key key, final @Nonnull String algo) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(m.getBytes());
    }

    @Nonnull
    public static byte[] encrypt(final @Nonnull byte[] m, final @Nonnull Key key, final @Nonnull String algo) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(m);
    }

    @Nonnull
    public static String decrypt(final @Nonnull byte[] c, final @Nonnull Key key, final @Nonnull String algo) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(c));
    }

    @Nonnull
    public static byte[] decryptB(final @Nonnull byte[] c, final @Nonnull Key key, final @Nonnull String algo) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(c);
    }

    public static void encrypt(final @Nonnull InputStream m, final @Nonnull OutputStream c, final @Nonnull Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
    {
        c.write(encrypt(IOUtils.toByteArray(m), key, "AES"));
    }

    public static void decrypt(final @Nonnull InputStream c, final @Nonnull OutputStream m, final @Nonnull Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
    {
        m.write(decryptB(IOUtils.toByteArray(c), key, "AES"));
    }

    public static void encrypt(final @Nonnull String file, final @Nonnull Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
    {
        InputStream m = new FileInputStream(file);
        OutputStream c = new FileOutputStream(file + ".crypt");
        c.write(encrypt(IOUtils.toByteArray(m), key, "AES"));
    }

    public static void decrypt(final @Nonnull String file, final @Nonnull Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
    {
        InputStream c = new FileInputStream(file.endsWith(".crypt") ? file : file + ".crypt");
        OutputStream m = new FileOutputStream(file + ".decrypt");
        m.write(decryptB(IOUtils.toByteArray(c), key, "AES"));
    }
}
