package cz.muni.fi.pb173.hw01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marek Soska
 */
public class PrimesInterval {
    private List<Integer> primes;

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

    private List<Integer> primesList(int start, int end) {
        List<Integer> primes = new ArrayList<Integer>();

        for (int num = start; num <= end; num++) {
            if (num == 0 || num == 1) {
                continue;
            }
            if (!isDividable(num, primes)) {
                primes.add(num);
            }
        }
        return primes;
    }

    private boolean isDividable(int num, List<Integer> primes) {
        for (Integer prime : primes) {
            if (num % prime == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type in the start of an index:");
        int start = input.nextInt();
        System.out.println("Type in the end of an index:");
        int end = input.nextInt();
        System.out.println(new PrimesInterval(start, end));
    }
}
