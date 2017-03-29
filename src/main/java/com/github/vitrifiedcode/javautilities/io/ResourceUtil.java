package com.github.vitrifiedcode.javautilities.io;

import com.github.vitrifiedcode.javautilities.propterties.Properties;
import com.github.vitrifiedcode.javautilities.string.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ResourceUtil
{
    private ResourceUtil() {}

    public static String assemblePath(String... in) { return StringUtil.buildDelim(false, Properties.OS.FILE_SEPERATOR, in); }

    public static InputStream getResource(String resource) throws IOException
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
