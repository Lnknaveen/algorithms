package linked_lists;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<T> {
    public static class DNode<T> {

        private T data;
        private DNode<T> previous;
        private DNode<T> next;

        public DNode(T data) {
            this.data = data;
        }
    }

    private DNode<T> head = null;
    private DNode<T> tail = null;

    public DoublyLinkedList() {
        super();
    }

    public void insertFirst(DNode<T> dNode) {
        if (head == null && tail == null) {
            head = tail = dNode;
        } else {
            dNode.next = head;
            head.previous = dNode;
            head = dNode;
        }
    }

    public void insertLast(DNode<T> dNode) {
        if (head == null && tail == null) {
            head = tail = dNode;
        } else {
            dNode.previous = tail;
            tail.next = dNode;
            tail = dNode;
        }
    }

    public T[] getHeadArray() {
        List<T> returnValue = new ArrayList<>();
        DNode<T> traversal = head;

        while (traversal != null) {
            returnValue.add(traversal.data);
            traversal = traversal.next;
        }

        return (T[]) returnValue.toArray();
    }

    public T[] getTailArray() {
        List<T> returnValue = new ArrayList<>();
        DNode<T> traversal = tail;

        while (traversal != null) {
            returnValue.add(traversal.data);
            traversal = traversal.previous;
        }

        return (T[]) returnValue.toArray();
    }

    public void insertAt(int index, DNode<T> dNode) {
        int pointer = 0;
        DNode<T> traversal = head;
        while (traversal != null) {
            if (pointer == index) {

                DNode<T> prev = traversal.previous;

                dNode.next = traversal;
                traversal.previous = dNode;

                prev.next = dNode;
                dNode.previous = prev;

                break;
            }

            pointer++;
            traversal = traversal.next;
        }

        if (traversal == head) {
            dNode = head;
        }
    }

    public void clear() {
        DNode<T> traversal = head;

        while (traversal != null) {
            DNode<T> temp = traversal.next;
            traversal.next = traversal.previous = null;
            traversal.data = null;
            traversal = temp;
        }

        head = tail = null;
    }

    public void removeFirst() {
        if (head == tail) {
            head = tail = null;
        } else {
            DNode<T> temp = head.next;
            head.next = null;
            head = null;

            if (temp != null) {
                temp.previous = null;
                head = temp;
            }
        }
    }

    public void removeLast() {
        if (head == tail) {
            head = tail = null;
        } else {
            DNode<T> temp = tail.previous;
            tail.previous = null;
            tail = null;

            if (temp != null) {
                temp.next = null;
                tail = temp;
            }
        }
    }
}