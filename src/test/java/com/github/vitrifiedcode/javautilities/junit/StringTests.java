package com.github.vitrifiedcode.javautilities.junit;

import com.github.vitrifiedcode.javautilities.string.StringUtil;
import org.junit.Assert;
import org.junit.Test;

public class StringTests
{
    @Test
    public void build()
    {
        Assert.assertEquals("hello world", StringUtil.build("hello", " ", "world"));
        Assert.assertEquals("Hello World!", StringUtil.build("Hello", " ", "Wor", "ld", "!"));
        Assert.assertEquals("hello-- --world--", StringUtil.buildDelim(true, "--", "hello", " ", "world"));
        Assert.assertEquals("Hello=!= =!=Wor=!=ld=!=!=!=", StringUtil.buildDelim(true, "=!=", "Hello", " ", "Wor", "ld", "!"));
        Assert.assertEquals("hello-- --world", StringUtil.buildDelim(false, "--", "hello", " ", "world"));
        Assert.assertEquals("Hello=!= =!=Wor=!=ld=!=!", StringUtil.buildDelim(false, "=!=", "Hello", " ", "Wor", "ld", "!"));
    }
}
