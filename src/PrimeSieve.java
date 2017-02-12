import java.util.*;

public class PrimeSieve implements PrimeNumberGenerator {
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

        BitSet range = generateRangeBitSet(startingValue, endingValue);
        BitSet segmentPrimes = removeNonPrimesFromSegment(generateRangeBitSet(2, (int) Math.sqrt(endingValue)));

        range = removeNonPrimesFromRange(segmentPrimes, range, startingValue, endingValue);
        range = addMissingSegmentPrimesToRange(segmentPrimes, range, startingValue, endingValue);

        return convertBitSetToIntegerList(range);
    }


    public boolean isPrime(int value) {
        if (value <= 1) {
            return false;
        }

        BitSet sievePrimes = removeNonPrimesFromSegment(generateRangeBitSet(2, (int) Math.sqrt(value)));
        for (int i = sievePrimes.nextSetBit(0); i >= 0; i = sievePrimes.nextSetBit(i+1)) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }

    private BitSet generateRangeBitSet(int startingValue, int endingValue) {
        BitSet range = new BitSet((endingValue - startingValue) + 1);
        for (int i = startingValue; i <= endingValue; i++) {
            range.set(i);
        }
        return range;
    }

    private BitSet removeNonPrimesFromSegment(BitSet bitset) {
        for (int i = bitset.nextSetBit(0); i >= 0; i = bitset.nextSetBit(i + 1)) {
            for (int j = i * i; j <= bitset.length(); j += i) {
                bitset.clear(j);
            }
        }
        return bitset;
    }

    private BitSet removeNonPrimesFromRange(BitSet segmentPrimes, BitSet range, int startingValue, int endingValue) {
        for (int i = segmentPrimes.nextSetBit(0); i >= 0; i = segmentPrimes.nextSetBit(i+1)) {
            int firstMultipleInRange = ((int) Math.ceil(startingValue * 1.0 / i) * i);
            for (int j = firstMultipleInRange; j <= endingValue && j > 0; j += i) {
                range.clear(j);
            }
            if (i == Integer.MAX_VALUE) {
                break;
            }
        }
        return range;
    }

    private BitSet addMissingSegmentPrimesToRange(BitSet segmentPrimes, BitSet range, int startingValue, int endingValue) {
        if (startingValue <= (int) Math.sqrt(endingValue)) {
            for (int i = segmentPrimes.nextSetBit(0); i >= 0; i = segmentPrimes.nextSetBit(i + 1)) {
                if (i >= startingValue) {
                    range.set(i);
                }
            }
        }
        return range;
    }

    private List<Integer> convertBitSetToIntegerList(BitSet bitset) {
        List<Integer> rangePrimes = new ArrayList<>();
        for (int i = bitset.nextSetBit(0); i >= 0; i = bitset.nextSetBit(i+1)) {
            rangePrimes.add(i);
            if (i == Integer.MAX_VALUE) {
                break;
            }
        }
        return rangePrimes;
    }
}
