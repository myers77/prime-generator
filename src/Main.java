import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            Sieve sieve = new Sieve();

            System.out.println(sieve.isPrime(-100));
            System.out.println(sieve.isPrime(-1));
            System.out.println(sieve.isPrime(0));
            System.out.println(sieve.isPrime(1));
            System.out.println(sieve.isPrime(2));
            System.out.println(sieve.isPrime(Integer.MAX_VALUE));

            int startingValue = getInput(scanner, "starting");
            int endingValue = getInput(scanner, "ending");
            List<Integer> primes = sieve.generate(startingValue, endingValue);
//            List<Integer> primes = sieve.generate(0, Integer.MAX_VALUE);

            try {
                BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
                log.write("[");
                for (int i = 0; i < primes.size() - 1; i++) {
                    log.write(primes.get(i) + ", ");
                }
                log.write(primes.get(primes.size() - 1) + "]");
                log.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
            System.out.print("Run again? [Y/n] ");
            option = scanner.nextLine();
        } while (option.isEmpty() || option.matches("[Yy]"));

        scanner.close();
    }

    public static int getInput(Scanner scanner, String valueName) {
        System.out.print("Enter " + valueName + " integer: ");
        while (!scanner.hasNextInt()) {
            System.out.println("(value must be an integer)");
            System.out.print("Enter " + valueName + " integer: ");
            scanner.nextLine();
        }
        return Integer.parseInt(scanner.nextLine());
    }
}
