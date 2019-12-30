package trees;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BinarySearchTreeTests {

    @Test
    public void shouldTestBST() {
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        assertEquals(tree.height(), 0);

        // Tree should look like:
        // ````````M
        tree.add("M");
        assertEquals(tree.height(), 1);

        // Tree should look like:
        // ````````M
        // ``````J``S
        tree.add("J");
        assertEquals(tree.height(), 2);
        tree.add("S");
        assertEquals(tree.height(), 2);

        // Tree should look like:
        // ````````M
        // ``````J``S
        // ````B```N`Z
        tree.add("B");
        assertEquals(tree.height(), 3);
        tree.add("N");
        assertEquals(tree.height(), 3);
        tree.add("Z");
        assertEquals(tree.height(), 3);

        // Tree should look like:
        // ````````M
        // ``````J``S
        // ````B```N`Z
        // ``A
        tree.add("A");
        assertEquals(tree.height(), 4);

        assertArrayEquals(new String[] { "M", "J", "B", "A", "S", "N", "Z" }, tree.traverse("PRE").toArray());
        assertArrayEquals(new String[] { "A", "B", "J", "M", "N", "S", "Z" }, tree.traverse("IN").toArray());
        assertArrayEquals(new String[] { "A", "B", "J", "N", "Z", "S", "M" }, tree.traverse("POST").toArray());
        assertArrayEquals(new String[] { "M", "J", "S", "B", "N", "Z", "A" }, tree.traverse("LEVEL").toArray());

        // Tree should look like:
        // ````````M
        // ``````J``S
        // ````A```N`Z
        tree.remove("B");
        assertEquals(tree.height(), 3);

        assertArrayEquals(new String[] { "M", "J", "A", "S", "N", "Z" }, tree.traverse("PRE").toArray());
        assertArrayEquals(new String[] { "A", "J", "M", "N", "S", "Z" }, tree.traverse("IN").toArray());
        assertArrayEquals(new String[] { "A", "J", "N", "Z", "S", "M" }, tree.traverse("POST").toArray());
        assertArrayEquals(new String[] { "M", "J", "S", "A", "N", "Z" }, tree.traverse("LEVEL").toArray());

        // Tree should look like:
        // ````````M
        // ``````J``Z
        // ````A```N
        tree.remove("S");
        assertEquals(tree.height(), 3);
        assertFalse(tree.contains("S"));

        assertArrayEquals(new String[] { "M", "J", "A", "Z", "N" }, tree.traverse("PRE").toArray());
        assertArrayEquals(new String[] { "A", "J", "M", "N", "Z" }, tree.traverse("IN").toArray());
        assertArrayEquals(new String[] { "A", "J", "N", "Z", "M" }, tree.traverse("POST").toArray());
        assertArrayEquals(new String[] { "M", "J", "Z", "A", "N" }, tree.traverse("LEVEL").toArray());

        // Tree should look like:
        // ````````M
        // ``````J``Z
        // ````A
        tree.remove("N");
        assertEquals(tree.height(), 3);

        assertArrayEquals(new String[] { "M", "J", "A", "Z" }, tree.traverse("PRE").toArray());
        assertArrayEquals(new String[] { "A", "J", "M", "Z" }, tree.traverse("IN").toArray());
        assertArrayEquals(new String[] { "A", "J", "Z", "M" }, tree.traverse("POST").toArray());
        assertArrayEquals(new String[] { "M", "J", "Z", "A" }, tree.traverse("LEVEL").toArray());
    }
}