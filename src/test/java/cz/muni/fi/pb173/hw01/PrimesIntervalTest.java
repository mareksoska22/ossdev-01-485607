package cz.muni.fi.pb173.hw01;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marek Soska
 */
public class PrimesIntervalTest {
    private static final Integer[] PRIMES_0_150 = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
            71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149
    };

    private static final Integer[] PRIMES_1000_1100 = {
            1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069,
            1087, 1091, 1093, 1097
    };

    private Collection<Integer> primes;

    @Before
    public void setUp() {
        primes = new PrimesInterval(100, 100000).getPrimes();
    }

    @Test (expected = IllegalArgumentException.class)
    public void negativeInInterval() {
        new PrimesInterval(-1, 10);
        new PrimesInterval(-100, -5);
        new PrimesInterval(-10, 0);
        new PrimesInterval(Integer.MIN_VALUE, 1);
        new PrimesInterval(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void badInterval() {
        new PrimesInterval(1, 0);
        new PrimesInterval(101, 100);
        new PrimesInterval(-100, -101);
        new PrimesInterval(Integer.MAX_VALUE, 1);
    }

    @Test
    public void isSorted() {
        List<Integer> sortedPrimes = new ArrayList<Integer>(primes);
        Collections.sort(sortedPrimes);

        assertThat(sortedPrimes).isEqualTo(primes);
    }

    @Test
    public void noDuplicates() {
        List<Integer> primesNoDuplicates = new ArrayList<>(new HashSet<Integer>(primes));
        Collections.sort(primesNoDuplicates);
        assertThat(primes).isEqualTo(primesNoDuplicates);
    }

    @Test
    public void rightPrimes() {
        assertThat(new PrimesInterval(0, 150).getPrimes())
                .isEqualTo(Arrays.asList(PRIMES_0_150));
        assertThat(new PrimesInterval(1000, 1100).getPrimes())
                .isEqualTo(Arrays.asList(PRIMES_1000_1100));
        assertThat(new PrimesInterval(500, 502).getPrimes())
                .isEqualTo(new ArrayList<Integer>());
    }
}
