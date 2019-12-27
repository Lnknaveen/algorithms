package queues;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * QueueTests
 */
public class QueueTests {

    @Test
    public void testQueue() {
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(10);
        assertEquals(10, queue.peek().intValue());
        assertEquals(1, queue.size());

        queue.enqueue(20);
        assertEquals(10, queue.peek().intValue());
        assertEquals(2, queue.size());

        Integer value = queue.dequeue();
        assertEquals(10, value.intValue());
        assertEquals(1, queue.size());

        value = queue.dequeue();
        assertEquals(20, value.intValue());
        assertEquals(0, queue.size());
    }
}