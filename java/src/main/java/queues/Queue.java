package queues;

import java.util.LinkedList;

/**
 * Queue
 */
public class Queue<T> {

    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T value) {
        list.addLast(value);
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        return list.peekFirst();
    }

    public T dequeue() {
        return list.removeFirst();
    }
}