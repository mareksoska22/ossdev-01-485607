package cz.muni.fi.pb173.hw01;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marek Soska
 */
public class PrimesInterval {
    private List<Integer> primes;

    /**
     * Creates a list of prime numbers in a given interval.
     *
     * @param start Beginning of an interval.
     * @param end End of an interval.
     */
    public PrimesInterval(int start, int end) {
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("interval must contain only positive numbers");
        }
        if (start > end) {
            throw new IllegalArgumentException("reversed interval format");
        }
        this.primes = primesList(start, end);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Primes: ");
        for (Integer prime : primes) {
            builder.append(prime);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    /**
     * Basic getter function.
     *
     * @return Returns an unmodifiable list of generated prime numbers.
     */
    public List<Integer> getPrimes() {
        return Collections.unmodifiableList(primes);
    }

    /**
     * Generates a list of prime numbers in an interval [start, end].
     *
     * @param start The start number of the interval (start number included).
     * @param end The end number of the interval (end number included).
     * @return Returns prime numbers list from lowest to highest.
     */
    private List<Integer> primesList(int start, int end) {
        List<Integer> primesList = new ArrayList<Integer>();

        for (int num = 2; num <= end; num++) {
            if (!isDividable(num, primesList)) {
                primesList.add(num);
            }
        }
        primesList.removeIf(num -> num < start);
        return primesList;
    }

    /**
     * Checks if a number is divisible by all of the numbers in a list.
     *
     * @param num Given number.
     * @param primesList Given divisors.
     * @return Returns true if is divisible by all of then, returns false otherwise.
     */
    private boolean isDividable(int num, List<Integer> primesList) {
        for (Integer prime : primesList) {
            if (num % prime == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes user input of two integers from a console.
     * Then outputs an interval of prime numbers between these integers.
     *
     * @param args Given arguments.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type in the start of an index:");
        int start = input.nextInt();
        System.out.println("Type in the end of an index:");
        int end = input.nextInt();
        System.out.println(new PrimesInterval(start, end));
    }
}
