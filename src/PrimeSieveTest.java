import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class PrimeSieveTest {
    private List<Integer> primes;
    private List<Integer> reversePrimes;
    private List<Integer> highPrimes;
    private List<Integer> reverseHighPrimes;
    private List<Integer> giantPrimes;
    private List<Integer> reverseGiantPrimes;
    private List<Integer> empty;
    private PrimeSieve primeSieve;

    @Before
    public void setUp() throws Exception {
        primeSieve = new PrimeSieve();
        primes = new ArrayList<>();
        reversePrimes = new ArrayList<>();
        highPrimes = new ArrayList<>();
        reverseHighPrimes = new ArrayList<>();
        giantPrimes = new ArrayList<>();
        reverseGiantPrimes = new ArrayList<>();
        empty = new ArrayList<>();

        int[] intList = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
                         43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
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

        // Data obtained from https://primes.utm.edu/lists/small/100000.txt
        File file = new File("src/data/first_100_008_primes.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            Integer element = Integer.parseInt(scanner.next());
            giantPrimes.add(element);
            reverseGiantPrimes.add(element);
        }
        scanner.close();
        Collections.reverse(reverseGiantPrimes);
    }

    @Test
    public void generateZeroToPositive() throws Exception {
        assertEquals(primeSieve.generate(0, 100), primes);
    }

    @Test
    public void generateBelowSquareRootToPositive() throws Exception {
        assertEquals(primeSieve.generate(5, 100), primes.subList(2, primes.size()));
    }

    @Test
    public void generateSquareRootToPositive() throws Exception {
        assertEquals(primeSieve.generate(10, 100), primes.subList(4, primes.size()));
    }

    @Test
    public void generateAboveSquareRootToPositive() throws Exception {
        assertEquals(primeSieve.generate(50, 100), primes.subList(15, primes.size()));
    }

    @Test
    public void generatePositiveToZero() throws Exception {
        assertEquals(primeSieve.generate(100, 0), primes);
    }

    @Test
    public void generatePositiveToNegative() throws Exception {
        assertEquals(primeSieve.generate(100, -100), primes);
    }

    @Test
    public void generateZeroToNegative() throws Exception {
        assertEquals(primeSieve.generate(0, -100), empty);
    }

    @Test
    public void generateNegativeToZero() throws Exception {
        assertEquals(primeSieve.generate(-100, 0), empty);
    }

    @Test
    public void generateNegativeToPositive() throws Exception {
        assertEquals(primeSieve.generate(-100, 100), primes);
    }

    @Test
    public void generateHighPrimes() throws Exception {
        assertEquals(primeSieve.generate(7900, 7920), highPrimes);
    }

    @Test
    public void generateReverseHighPrimes() throws Exception {
        assertEquals(primeSieve.generate(7920, 7900), highPrimes);
    }

    @Test
    public void generateGiantList() throws Exception {
        assertEquals(primeSieve.generate(0, 1299827), giantPrimes);
    }

    @Test
    public void generateReverseGiantList() throws Exception {
        assertEquals(primeSieve.generate(1299827, 0), giantPrimes);
    }

    @Test
    public void generateSameInputZero() throws Exception {
        assertEquals(primeSieve.generate(0, 0), empty);
    }

    @Test
    public void generateSameInputPrime() throws Exception {
        assertEquals(primeSieve.generate(2, 2), primes.subList(0, 1));
    }

    @Test
    public void generateSameInputNonPrime() throws Exception {
        assertEquals(primeSieve.generate(4, 4), empty);
    }

    @Test
    public void generateStartOnPrimeEndOnNonPrime() throws Exception {
        assertEquals(primeSieve.generate(3, 12), primes.subList(1, 5));
    }

    @Test
    public void generateStartOnNonPrimeEndOnPrime() throws Exception {
        assertEquals(primeSieve.generate(4, 12), primes.subList(2, 5));
    }

    @Test
    public void generateStartOnPrimeEndOnPrime() throws Exception {
        assertEquals(primeSieve.generate(3, 11), primes.subList(1, 5));
    }

    @Test
    public void generateStartOnNonPrimeEndOnNonPrime() throws Exception {
        assertEquals(primeSieve.generate(4, 12), primes.subList(2, 5));
    }

    @Test
    public void isPrimeNegative() throws Exception {
        assertEquals(primeSieve.isPrime(-1), false);
    }

    @Test
    public void isPrimePrimes() throws Exception {
        for (Integer prime : primes) {
            assertEquals(primeSieve.isPrime(prime), true);
        }
    }

    @Test
    public void isPrimeNonPrimes() throws Exception {
        for (int i = 0; i < primes.get(primes.size() - 1); i++) {
            if (!primes.contains(i)) {
                assertEquals(primeSieve.isPrime(i), false);
            }
        }
    }
}