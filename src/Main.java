public class Main {
    public static void main(String[] args) {
        Sieve sieve = new Sieve();
        System.out.println(sieve.generate(0, (int) Math.pow(2, 30)));
    }
}
