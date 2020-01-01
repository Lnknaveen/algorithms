package trees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * FenwickTreeTests
 */
public class FenwickTreeTests {

    @Test
    public void shouldTestFenwickTree() {
        long[] ar = { 100, -1, -2, -3, -4, -5, -6 };
        FenwickTree ft = new FenwickTree(ar);

        assertEquals(-21, ft.sum(1, 6));
        assertEquals(-15, ft.sum(1, 5));
        assertEquals(-10, ft.sum(1, 4));
        assertEquals(-6, ft.sum(1, 3));
        assertEquals(-3, ft.sum(1, 2));
        assertEquals(-1, ft.sum(1, 1));

        assertEquals(-6, ft.sum(6, 6));
        assertEquals(-5, ft.sum(5, 5));
        assertEquals(-4, ft.sum(4, 4));
        assertEquals(-3, ft.sum(3, 3));
        assertEquals(-2, ft.sum(2, 2));
        assertEquals(-1, ft.sum(1, 1));
    }
}