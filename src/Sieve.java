import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Sieve
    implements PrimeNumberGenerator {
    public List<Integer> generate(int startingValue, int endingValue) {

        BitSet rangeBits = generateRangeBits(startingValue, endingValue);
        List<Integer> segmentPrimes = getPrimes((int) Math.sqrt(endingValue));

        for (int i = 2; i < segmentPrimes.size(); i++) {
            int num = ((int) Math.ceil(startingValue * 1.0 / segmentPrimes.get(i)) * segmentPrimes.get(i)) - startingValue;
            for (int j = num; j <= endingValue - startingValue; j += segmentPrimes.get(i)) {
                rangeBits.clear(j);
            }
        }

        List<Integer> rangePrimes = new ArrayList<Integer>();
        for (int i = 0; i < rangeBits.size(); i++) {
            if (rangeBits.get(i)) {
                rangePrimes.add(i + startingValue);
            }
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
