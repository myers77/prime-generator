import java.util.*;

public class Sieve
    implements PrimeNumberGenerator {
    public List<Integer> generate(int startingValue, int endingValue) {
        if (startingValue > endingValue) {
            int temp = startingValue;
            startingValue = endingValue;
            endingValue = temp;
        }
        if (endingValue < 2) {
            return new ArrayList<>();
        }
        if (startingValue < 2) {
            startingValue = 2;
        }

        BitSet rangeBits = generateNewBitSet(startingValue, endingValue);
        BitSet segmentPrimes = removeNonPrimes(generateNewBitSet(2, (int) Math.sqrt(endingValue)));

        for (int i = segmentPrimes.nextSetBit(0); i >= 0; i = segmentPrimes.nextSetBit(i+1)) {
            int num = ((int) Math.ceil(startingValue * 1.0 / i) * i);
            for (int j = num; j <= endingValue && j > 0; j += i) {
                rangeBits.clear(j);
            }
            if (i == Integer.MAX_VALUE || i < 0) {
                break;
            }
        }

        // Add any missing segment primes to result
        if (startingValue <= (int) Math.sqrt(endingValue)) {
            for (int i = segmentPrimes.nextSetBit(0); i >= 0; i = segmentPrimes.nextSetBit(i+1)) {
                if (i <= (int) Math.sqrt(endingValue)) {
                    rangeBits.set(i);
                }
                if (i >= Integer.MAX_VALUE - i) {
                    break; // or (i+1) would overflow
                }
            }
        }

        List<Integer> rangePrimes = new ArrayList<Integer>();
        for (int i = startingValue; i <= endingValue; i++) {
            if (rangeBits.get(i)) {
                rangePrimes.add(i);
            }
            if (i == Integer.MAX_VALUE) {
                break;
            }
        }

        return rangePrimes;
    }

    private BitSet generateNewBitSet(int startingValue, int endingValue) {
        BitSet range = new BitSet((endingValue - startingValue) + 1);
        for (int i = startingValue; i <= endingValue; i++) {
            range.set(i);
            if (i == Integer.MAX_VALUE) {
                break;
            }
        }
        return range;
    }

    private BitSet removeNonPrimes(BitSet bitset) {
        BitSet primes = new BitSet();
        for (int i = 0; i <= bitset.length(); i++) {
            if (bitset.get(i)) {
                for (int j = i * i; j <= bitset.length(); j += i) {
                    bitset.clear(j);
                }
            }
        }
        return bitset;
    }

    public boolean isPrime(int value) {
        // May not need this method
        return false;
    };
}
