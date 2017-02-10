import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.IntStream;

public class Sieve
    implements PrimeNumberGenerator {
    public List<Integer> generate(int startingValue, int endingValue) {
        boolean reverseOrder = false;

        if (startingValue > endingValue) {
            reverseOrder = true;
            int temp = startingValue;
            startingValue = endingValue;
            endingValue = temp;
        }
        if (startingValue < 2) {
            startingValue = 2;
        }
        if (endingValue < 2) {
            return new ArrayList<Integer>();
        }

        BitSet rangeBits = generateRangeBits(startingValue, endingValue);
        List<Integer> segmentPrimes = getPrimes((int) Math.sqrt(endingValue));

        for (int i = 2; i < segmentPrimes.size(); i++) {
            int num = ((int) Math.ceil(startingValue * 1.0 / segmentPrimes.get(i)) * segmentPrimes.get(i));
            for (int j = num; j <= endingValue; j += segmentPrimes.get(i)) {
                rangeBits.clear(j);
            }
        }

        if (startingValue <= (int) Math.sqrt(endingValue)) {
            // Adding missed segment primes to result
            for (int i = 0; i < segmentPrimes.size(); i++) {
                rangeBits.set(segmentPrimes.get(i));
            }
        }

        List<Integer> rangePrimes = new ArrayList<Integer>();
        for (int i = startingValue; i <= endingValue; i++) {
            if (rangeBits.get(i)) {
                rangePrimes.add(i);
            }
        }

        if (reverseOrder) {
            Collections.reverse(rangePrimes);
        }

        return rangePrimes;
    }

    private BitSet generateRangeBits (int startingValue, int endingValue) {
        BitSet range = new BitSet((endingValue - startingValue) + 1);
        for (int i = startingValue; i <= endingValue; i++) {
            range.set(i);
        }
        return range;
    }

    private List<Boolean> generateRange(int startingValue, int endingValue) {
        List<Boolean> range = new ArrayList<Boolean>((endingValue - startingValue) + 1);
        for (int i = startingValue; i <= endingValue; i++) {
            range.add(true);
        }
        return range;
    }

    private List<Integer> getPrimes(int max) {
        List<Boolean> booleanRange = generateRange(0, max);
        for (int i = 2; i <= max; i++) {
            if (booleanRange.get(i)) {
                for (int j = i * i; j <= max; j += i) {
                    booleanRange.set(j, false);
                }
            }
        }

        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 0; i < booleanRange.size(); i++) {
            if (booleanRange.get(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public boolean isPrime(int value) {
        // May not need this method
        return false;
    };
}
