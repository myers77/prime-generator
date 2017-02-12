import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            Sieve sieve = new Sieve();
            int startingValue = getInput(scanner, "starting");
            int endingValue = getInput(scanner, "ending");
            List<Integer> primes = sieve.generate(startingValue, endingValue);

            // Using BufferedWriter to avoid Heap overflow when printing large ranges
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
