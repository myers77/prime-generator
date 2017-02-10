import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class SieveTest {
    private List<Integer> primes;
    private List<Integer> reversePrimes;
    private List<Integer> highPrimes;
    private List<Integer> reverseHighPrimes;
    private List<Integer> empty;
    private Sieve sieve;

    @Before
    public void setUp() throws Exception {
        sieve = new Sieve();
        primes = new ArrayList<>();
        reversePrimes = new ArrayList<>();
        highPrimes = new ArrayList<>();
        reverseHighPrimes = new ArrayList<>();
        empty = new ArrayList<>();

        int[] intList = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        for (int i: intList) {
            primes.add(i);
            reversePrimes.add(i);
        }
        Collections.reverse(reversePrimes);

        int[] highIntList = {7901, 7907, 7919};
        for (int i: highIntList) {
            highPrimes.add(i);
            reverseHighPrimes.add(i);
        }
        Collections.reverse(reverseHighPrimes);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generateZeroToPositive() throws Exception {
        assertEquals(sieve.generate(0, 100), primes);
    }

    @Test
    public void generateBelowSquareRootToPositive() throws Exception {
        assertEquals(sieve.generate(5, 100), primes.subList(2, primes.size()));
    }

    @Test
    public void generateSquareRootToPositive() throws Exception {
        assertEquals(sieve.generate(10, 100), primes.subList(4, primes.size()));
    }

    @Test
    public void generateAboveSquareRootToPositive() throws Exception {
        assertEquals(sieve.generate(50, 100), primes.subList(15, primes.size()));
    }

    @Test
    public void generatePositiveToZero() throws Exception {
        assertEquals(sieve.generate(100, 0), reversePrimes);
    }

    @Test
    public void generatePositiveToNegative() throws Exception {
        assertEquals(sieve.generate(100, -100), reversePrimes);
    }

    @Test
    public void generateZeroToNegative() throws Exception {
        assertEquals(sieve.generate(0, -100), empty);
    }

    @Test
    public void generateNegativeToZero() throws Exception {
        assertEquals(sieve.generate(-100, 0), empty);
    }

    @Test
    public void generateNegativeToPositive() throws Exception {
        assertEquals(sieve.generate(-100, 100), primes);
    }

    @Test
    public void generateHighPrimes() throws Exception {
        assertEquals(sieve.generate(7900, 7920), highPrimes);
    }


    @Test
    public void generateReverseHighPrimes() throws Exception {
        assertEquals(sieve.generate(7920, 7900), reverseHighPrimes);
    }
}