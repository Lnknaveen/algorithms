package hash_tables;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * HashTableSeparationChainTests
 */
public class HashTableSeparateChainTests {

    @Test
    public void testHashTable() {
        HashTableSeparateChain<Integer, Integer> table = new HashTableSeparateChain<>();

        Integer returnValue = table.put(10, 100);
        assertEquals(null, returnValue);
        assertEquals(100, table.get(10).intValue());
        assertEquals(1, table.size());

        returnValue = table.put(10, 200);
        assertEquals(100, returnValue.intValue());
        assertEquals(200, table.get(10).intValue());
        assertEquals(1, table.size());

        returnValue = table.put(1, 200);
        assertEquals(null, returnValue);
        assertEquals(200, table.get(1).intValue());
        assertEquals(2, table.size());

        returnValue = table.remove(1);
        assertEquals(200, returnValue.intValue());
        assertEquals(1, table.size());
    }
}