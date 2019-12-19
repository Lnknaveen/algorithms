package linked_lists;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SinglyLinkedListTests {

    @Test
    public void shouldTestSinglyLinkedList() {
        SinglyLinkedList<Integer> sList = new SinglyLinkedList<>();

        sList.insertFirst(new SinglyLinkedList.SNode<>(1));
        assertArrayEquals(new Integer[] { 1 }, sList.getAsArray());

        sList.insertLast(new SinglyLinkedList.SNode<>(10));
        assertArrayEquals(new Integer[] { 1, 10 }, sList.getAsArray());

        sList.insertAt(1, new SinglyLinkedList.SNode<>(5));
        assertArrayEquals(new Integer[] { 1, 5, 10 }, sList.getAsArray());

        sList.clear();
        assertArrayEquals(new Integer[] {}, sList.getAsArray());

        sList.insertLast(new SinglyLinkedList.SNode<>(20));
        assertArrayEquals(new Integer[] { 20 }, sList.getAsArray());

        sList.insertFirst(new SinglyLinkedList.SNode<>(10));
        assertArrayEquals(new Integer[] { 10, 20 }, sList.getAsArray());

        sList.insertLast(new SinglyLinkedList.SNode<>(25));
        assertArrayEquals(new Integer[] { 10, 20, 25 }, sList.getAsArray());

        sList.removeFirst();
        assertArrayEquals(new Integer[] { 20, 25 }, sList.getAsArray());

        sList.removeLast();
        assertArrayEquals(new Integer[] { 20 }, sList.getAsArray());

        sList.removeLast();
        assertArrayEquals(new Integer[] {}, sList.getAsArray());

        sList.insertLast(new SinglyLinkedList.SNode<>(10));
        assertArrayEquals(new Integer[] { 10 }, sList.getAsArray());

        sList.removeFirst();
        assertArrayEquals(new Integer[] {}, sList.getAsArray());
    }
}