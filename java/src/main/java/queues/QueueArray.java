package queues;

/**
 * Queue
 */
public class QueueArray<T> {
    T[] elements;
    private int size;
    private int front;
    private int back;

    public QueueArray(int size) {
        elements = (T[]) new Object[size];
        front = 0;
        back = -1;
    }

    public void enqueue(T value) {
        back = increment(back);
        elements[back] = value;
        
        size++;
    }

    private int increment(int value) {
        if (++value == elements.length)
            value = 0;
        return value;
    }

    public int size() {
        return size;
    }

    public T peek() {
        return elements[front];
    }

    public T dequeue() {
        T returnValue = elements[front];
        front = increment(front);

        size--;

        return returnValue;
    }
}