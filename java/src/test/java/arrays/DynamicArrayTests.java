package arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DynamicArrayTests {

    @Test
    public void shouldValidateArray() {
        Integer[] array = new Integer[] { 10, 20, 30, 40 };
        DynamicArray<Integer> dynamicArray = new DynamicArray<Integer>(array);

        dynamicArray.add(50);
        assertArrayEquals(new Integer[] { 10, 20, 30, 40, 50 }, dynamicArray.getValues());

        Integer removedElement = dynamicArray.removeAt(0);
        assertEquals(10, removedElement.intValue());
        assertArrayEquals(new Integer[] { 20, 30, 40, 50 }, dynamicArray.getValues());

        dynamicArray.insertAt(1, 10);
        assertArrayEquals(new Integer[] { 20, 10, 30, 40, 50 }, dynamicArray.getValues());
    }
}