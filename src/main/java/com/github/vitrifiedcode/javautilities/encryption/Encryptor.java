package com.github.vitrifiedcode.javautilities.encryption;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Encryptor
{
    private static int ApproximateNthPrime(int nn)
    {
        double n = (double) nn;
        double p;
        if(nn >= 7022) { p = n * Math.log(n) + n * (Math.log(Math.log(n)) - 0.9385); }
        else if(nn >= 6) { p = n * Math.log(n) + n * Math.log(Math.log(n)); }
        else if(nn > 0) { p = new int[] { 2, 3, 5, 7, 11 }[nn - 1]; }
        else { p = 0; }
        return (int) p;
    }

    // Find all primes up to and including the limit
    private static BitSet SieveOfEratosthenes(int limit)
    {
        BitSet bits = new BitSet(limit + 1);
        bits.set(0, limit, true);
        bits.set(0, false);
        bits.set(1, false);
        for(int i = 0; i * i <= limit; i++)
        {
            if(bits.get(i))
            {
                for(int j = i * i; j <= limit; j += i) { bits.set(j, false); }
            }
        }
        return bits;
    }

    public static List<Integer> GeneratePrimesSieveOfEratosthenes(int n)
    {
        int limit = ApproximateNthPrime(n);
        BitSet bits = SieveOfEratosthenes(limit);
        List<Integer> primes = new ArrayList<Integer>();
        for(int i = 0, found = 0; i < limit && found < n; i++)
        {
            if(bits.get(i))
            {
                primes.add(i);
                found++;
            }
        }
        return primes;
    }

    public static BitSet computePrimes(int limit)
    {
        final BitSet primes = new BitSet();
        primes.set(0, false);
        primes.set(1, false);
        primes.set(2, limit, true);
        for(int i = 0; i * i < limit; i++)
        {
            if(primes.get(i))
            {
                for(int j = i * i; j < limit; j += i)
                {
                    primes.clear(j);
                }
            }
        }
        return primes;
    }
}
