package stacks;

/**
 * StackLinkedList
 */
public class StackSinglyLinkedList<T> {
    public static class SLLNode<T> {
        public SLLNode(T value) {
            data = value;
        }

        private SLLNode<T> next;
        private T data;
    }

    private int size;
    private SLLNode<T> head = null;

    public void push(T value) {
        if (head == null) {
            head = new SLLNode<T>(value);
        } else {
            SLLNode<T> newHead = new SLLNode<T>(value);
            newHead.next = head;
            head = newHead;
        }
        size++;
    }

    public T peek() {
        return head.data;
    }

    public int size() {
        return size;
    }

    public T pop() {
        T returnValue = head.data;

        SLLNode<T> newHead = head.next;
        head = null;
        head = newHead;
        size--;

        return returnValue;
    }
}