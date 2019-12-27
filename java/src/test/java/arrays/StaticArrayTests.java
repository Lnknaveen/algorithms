package arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StaticArrayTests {

    private void shouldTestStaticArrayWithParams(int[] numbers, int valueToFind, boolean expected) {
        StaticArray array = new StaticArray(numbers);
        assertEquals(expected, array.find(valueToFind));
    }

    @Test
    public void shouldTestStaticArrayObject() {
        shouldTestStaticArrayWithParams(new int[] { 10, 20, 30, 40 }, 50, false);
        shouldTestStaticArrayWithParams(new int[] { 10, 20, 30, 40 }, 10, true);
    }

    @Test
    public void printElementsWithIndexes() {
        StaticArray array = new StaticArray(new int[] { 10, 20, 30, 40 });
        array.printElementsWithIndexes();
    }
}