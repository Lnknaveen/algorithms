package linked_lists;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList<T> {
    private SNode<T> head = null;
    private SNode<T> tail = null;

    static class SNode<T> {
        T data;
        SNode<T> next;

        public SNode(T data) {
            this.data = data;
        }
    }

    public void insertLast(SNode<T> sNode) {
        if (head == null && tail == null) {
            tail = head = sNode;
        } else {
            tail.next = sNode;
            tail = sNode;
        }
    }

    public void insertFirst(SNode<T> sNode) {
        if (head == null && tail == null) {
            tail = head = sNode;
        } else {
            sNode.next = head;
            head = sNode;
        }
    }

    public T[] getAsArray() {
        List<T> collection = new ArrayList<>();

        SNode<T> traversal = head;
        while (traversal != null) {
            collection.add(traversal.data);
            traversal = traversal.next;
        }

        return (T[]) collection.toArray();
    }

    public void insertAt(int index, SNode<T> sNode) {
        SNode<T> traversalFront = head;
        SNode<T> traversalBack = null;

        int pointer = 0;
        while (traversalFront != null) {

            if (pointer == index) {
                sNode.next = traversalFront;

                if (traversalBack != null) {
                    traversalBack.next = sNode;
                }

                break;
            }

            pointer++;
            traversalBack = traversalFront;
            traversalFront = traversalFront.next;
        }

        if (sNode.next == head) {
            head = sNode;
        }
    }

    public void clear() {
        SNode<T> traversal = head;
        while (traversal != null) {
            SNode<T> temp = traversal.next;
            traversal.next = null;
            traversal = temp;
        }

        tail = head = null;
    }

    public void removeFirst() {
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
    }

    public void removeLast() {
        if (head == tail) {
            head = tail = null;
        } else {
            SNode<T> traversal = head;
            while (traversal != null) {
                if (traversal.next == tail) {
                    traversal.next = null;
                    tail = traversal;
                    break;
                }

                traversal = traversal.next;
            }
        }
    }
}