package com.github.vitrifiedcode.javautilities.junit.matcher;

public class AtomicErrorCollector extends org.junit.rules.ErrorCollector
{
    @Override
    public synchronized void addError(Throwable error)
    {
        super.addError(error);
    }
}
