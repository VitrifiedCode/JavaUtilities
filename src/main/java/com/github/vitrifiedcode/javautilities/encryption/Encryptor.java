package com.github.vitrifiedcode.javautilities.encryption;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Encryptor
{
    public static List<Integer> computePrimes(int limit)
    {
        final BitSet primes = new BitSet(limit);
        primes.set(0, false);
        primes.set(1, false);
        primes.set(2, limit, true);
        for(int i = 0; i * i < limit; i++)
        {
            if(primes.get(i))
            {
                for(int j = i * i; j < limit; j += i)
                {
                    primes.set(j, false);
                }
            }
        }

        List<Integer> out = new ArrayList<>();
        for(int i = 0; i < limit; ++i)
        {
            if(primes.get(i))
            {
                out.add(i);
            }
        }
        return out;
    }
}
