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
            PrimeSieve primeSieve = new PrimeSieve();
            int startingValue = getInput(scanner, "starting");
            int endingValue = getInput(scanner, "ending");
            List<Integer> primes = primeSieve.generate(startingValue, endingValue);

            try {
                BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
                log.write("[");
                if (primes.size() > 0) {
                    for (Integer prime : primes) {
                        log.write(prime + ", ");
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
        } while (option.isEmpty() || option.matches("[Yy]"));

        scanner.close();
    }

    private static int getInput(Scanner scanner, String valueName) {
        Pattern pattern = Pattern.compile("(-?\\d+)");
        System.out.print("Enter " + valueName + " integer: ");
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
