
package linked_lists;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * DoublyLinkedListTests
 */
public class DoublyLinkedListTests {

    @Test
    public void shouldTestDoublyLinkedList() {
        DoublyLinkedList<Integer> dList = new DoublyLinkedList<>();

        dList.insertFirst(new DoublyLinkedList.DNode<>(1));
        assertArrayEquals(new Integer[] { 1 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 1 }, dList.getTailArray());

        dList.insertLast(new DoublyLinkedList.DNode<>(10));
        assertArrayEquals(new Integer[] { 1, 10 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 10, 1 }, dList.getTailArray());

        dList.insertAt(1, new DoublyLinkedList.DNode<>(5));
        assertArrayEquals(new Integer[] { 1, 5, 10 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 10, 5, 1 }, dList.getTailArray());

        dList.clear();
        assertArrayEquals(new Integer[] {}, dList.getHeadArray());
        assertArrayEquals(new Integer[] {}, dList.getTailArray());

        dList.insertLast(new DoublyLinkedList.DNode<>(20));
        assertArrayEquals(new Integer[] { 20 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 20 }, dList.getTailArray());

        dList.insertFirst(new DoublyLinkedList.DNode<>(10));
        assertArrayEquals(new Integer[] { 10, 20 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 20, 10 }, dList.getTailArray());

        dList.insertLast(new DoublyLinkedList.DNode<>(25));
        assertArrayEquals(new Integer[] { 10, 20, 25 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 25, 20, 10 }, dList.getTailArray());

        dList.removeFirst();
        assertArrayEquals(new Integer[] { 20, 25 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 25, 20 }, dList.getTailArray());

        dList.removeLast();
        assertArrayEquals(new Integer[] { 20 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 20 }, dList.getTailArray());

        dList.removeLast();
        assertArrayEquals(new Integer[] {}, dList.getHeadArray());
        assertArrayEquals(new Integer[] {}, dList.getTailArray());

        dList.insertLast(new DoublyLinkedList.DNode<>(10));
        assertArrayEquals(new Integer[] { 10 }, dList.getHeadArray());
        assertArrayEquals(new Integer[] { 10 }, dList.getTailArray());

        dList.removeFirst();
        assertArrayEquals(new Integer[] {}, dList.getHeadArray());
        assertArrayEquals(new Integer[] {}, dList.getTailArray());
    }
}