import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            Sieve sieve = new Sieve();
            int startingValue = getInput(scanner, "starting");
            int endingValue = getInput(scanner, "ending");
            System.out.println(sieve.generate(startingValue, endingValue));
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
