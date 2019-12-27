package stacks;

import java.util.LinkedList;

/**
 * StackLinkedList
 */
public class StackJavaLinkedList<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T value) {
        list.addLast(value);
    }

    public T peek() {
        return list.peekLast();
    }

    public int size() {
        return list.size();
    }

    public T pop() {
        return list.removeLast();
    }
}