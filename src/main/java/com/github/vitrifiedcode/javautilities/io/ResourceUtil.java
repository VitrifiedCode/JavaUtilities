package com.github.vitrifiedcode.javautilities.io;

import com.github.vitrifiedcode.javautilities.propterties.Properties;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SuppressWarnings("unused")
public final class ResourceUtil
{
    private ResourceUtil() {}

    @Nonnull
    public static String assemblePath(final @Nonnull String... in) { return StringUtil.buildDelim(false, Properties.OS.FILE_SEPERATOR, in); }

    @Nonnull
    public static InputStream getResource(final @Nonnull String resource) throws FileNotFoundException
    {
        if(Properties.JAVA.IS_JAR) { return IO.class.getResourceAsStream(resource); }
        else
        {
            File f = new File(assemblePath(Properties.OS.USER_DIR, "src", "main", "resources", resource));
            if(!f.exists()) { f = new File(assemblePath(Properties.OS.USER_DIR, "src", "test", "resources", resource)); }
            if(!f.exists()) { f = new File(assemblePath(Properties.OS.USER_DIR, "res", resource)); }
            return new FileInputStream(f);
        }
    }
}
