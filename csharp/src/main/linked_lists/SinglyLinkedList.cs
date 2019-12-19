using System;
using System.Collections.Generic;
using System.Linq;

namespace linked_lists {
    public class SinglyLinkedList<T> {
        public SNode Head { get; set; }

        public SNode Tail { get; set; }

        internal void insertFirst (SNode sNode) {
            if (Head == null && Tail == null) {
                Head = Tail = sNode;
            } else {
                sNode.Next = Head;
                Head = sNode;
            }
        }

        internal void insertLast (SNode sNode) {
            if (Head == null && Tail == null) {
                Head = Tail = sNode;
            } else {
                Tail.Next = sNode;
                Tail = sNode;
            }
        }

        internal void clear () {
            SNode traversal = Head;
            while (traversal != null) {
                SNode temp = traversal.Next;
                traversal.Next = null;
                traversal = temp;
            }

            Head = Tail = null;
        }

        internal void insertAt (int index, SNode sNode) {
            SNode traversalFront = Head;
            SNode traversalBack = null;

            int pointer = 0;
            while (traversalFront != null) {

                if (pointer == index) {
                    sNode.Next = traversalFront;

                    if (traversalBack != null) {
                        traversalBack.Next = sNode;
                    }

                    break;
                }

                pointer++;
                traversalBack = traversalFront;
                traversalFront = traversalFront.Next;
            }

            if (sNode.Next == Head) {
                Head = sNode;
            }
        }

        internal void removeLast () {
            if (Head == Tail) {
                Head = Tail = null;
            } else {
                SNode traversal = Head;
                while (traversal != null) {
                    if (traversal.Next == Tail) {
                        traversal.Next = null;
                        Tail = traversal;
                        break;
                    }

                    traversal = traversal.Next;
                }
            }
        }

        internal void removeFirst () {
            if (Head == Tail) {
                Head = Tail = null;
            } else {
                Head = Head.Next;
            }
        }

        internal T[] getAsArray () {
            return getDataAsEnumerable ().ToArray ();
        }

        private IEnumerable<T> getDataAsEnumerable () {
            SNode traversal = Head;
            while (traversal != null) {
                yield return traversal.Data;
                traversal = traversal.Next;
            }
        }

        public class SNode {
            public T Data { get; set; }

            public SNode Next { get; set; }

            public SNode (T data) {
                Data = data;
            }
        }
    }
}