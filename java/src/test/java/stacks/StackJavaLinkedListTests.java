package stacks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * StackTests
 */
public class StackJavaLinkedListTests {

    @Test
    public void shouldTestStacks() {
        StackJavaLinkedList<Integer> stack = new StackJavaLinkedList<>();

        stack.push(10);
        assertEquals(10, stack.peek().intValue());
        assertEquals(1, stack.size());

        stack.push(20);
        assertEquals(20, stack.peek().intValue());
        assertEquals(2, stack.size());

        stack.push(30);
        assertEquals(30, stack.peek().intValue());
        assertEquals(3, stack.size());

        Integer value = stack.pop();
        assertEquals(30, value.intValue());
        assertEquals(20, stack.peek().intValue());
        assertEquals(2, stack.size());

        value = stack.pop();
        assertEquals(20, value.intValue());
        assertEquals(10, stack.peek().intValue());
        assertEquals(1, stack.size());

        value = stack.pop();
        assertEquals(10, value.intValue());
        assertEquals(0, stack.size());
    }
}