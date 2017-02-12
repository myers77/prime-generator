import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            System.out.println("1) Generate range of primes");
            System.out.println("2) Test primality of specific number");
            System.out.println("3) Exit");
            System.out.print("Select option (default=1) ");
            option = scanner.nextLine();
            System.out.println();

            if (option.equals("1") || option.isEmpty()) {
                runPrimeRange(scanner);
            }
            if (option.equals("2")) {
                runSinglePrime(scanner);
            }
            if (option.equals("3")) {
                break;
            }
        } while (true);

        scanner.close();
    }

    private static void runPrimeRange(Scanner scanner) {
        String option;
        do {
            PrimeSieve primeSieve = new PrimeSieve();
            int startingValue = getInput(scanner, "starting ");
            int endingValue = getInput(scanner, "ending ");
            List<Integer> primes = primeSieve.generate(startingValue, endingValue);

            try {
                BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
                log.write("The primes between " + startingValue + " and " + endingValue + " are:\n");
                log.write("[");
                if (primes.size() > 0) {
                    for (int i = 0; i < primes.size() - 1; i++) {
                        log.write(primes.get(i) + ", ");
                    }
                    log.write(primes.get(primes.size() - 1) + "");
                }
                log.write("]");
                log.flush();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
            System.out.print("Run again? [Y/n] ");
            option = scanner.nextLine();
            System.out.println();
        } while (option.isEmpty() || option.matches("[Yy]"));
    }

    private static void runSinglePrime(Scanner scanner) {
        String option;
        do {
            PrimeSieve primeSieve = new PrimeSieve();
            int value = getInput(scanner, "");

            System.out.println(value + " is" + (primeSieve.isPrime(value) ? " " : " not ") + "prime");
            System.out.print("Run again? [Y/n] ");
            option = scanner.nextLine();
            System.out.println();
        } while (option.isEmpty() || option.matches("[Yy]"));
    }

    private static int getInput(Scanner scanner, String valueName) {
        Pattern pattern = Pattern.compile("(-?\\d+)");
        System.out.print("Enter " + valueName + "integer: ");
        while (!scanner.hasNextInt()) {
            System.out.println("(value must be an integer)");
            System.out.print("Enter " + valueName + " integer: ");
            scanner.nextLine();
        }

        Matcher matcher = pattern.matcher(scanner.nextLine());
        int result = 0;
        while (matcher.find()) {
            result = Integer.parseInt(matcher.group(1));
        }
        return result;
    }
}
