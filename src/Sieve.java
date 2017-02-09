import java.util.ArrayList;
import java.util.List;

public class Sieve
    implements PrimeNumberGenerator {
    public List<Integer> generate(int startingValue, int endingValue) {

        List<Boolean> booleanRange = generateRange(startingValue, endingValue);
        List<Integer> primes = getPrimes((int) Math.sqrt(endingValue));

        return new ArrayList<Integer>();
    };

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
        System.out.println(primes);
        return primes;
    }

    public boolean isPrime(int value) {
        // May not need this method
        return false;
    };
}
