import java.util.ArrayList;
import java.util.List;

public class Sieve
    implements PrimeNumberGenerator {
    public List<Integer> generate(int startingValue, int endingValue) {

        List<Boolean> range = generateRange(startingValue, endingValue);

        // Would work if starting value is 0, but needs to be modified for ranges
        for (int i = startingValue; i <= Math.floor(Math.sqrt(endingValue)); i++) {
            if (primes.get(i)) {
                for (int j = i; j < endingValue; j += i) {
                    primes.set(j, false);
                }
            }
        }

        return new ArrayList<Integer>();
    };

    private List<Boolean> generateRange(int startingValue, int endingValue) {
        List<Boolean> range = new ArrayList<Boolean>((endingValue - startingValue) + 1);
        for (int i = startingValue; i <= endingValue; i++) {
            range.add(true);
        }
        return range;
    }

    public boolean isPrime(int value) {
        // May not need this method
        return false;
    };
}
