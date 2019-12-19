using System;
using NUnit.Framework;

namespace linked_lists {

    [TestFixture]
    public class SinglyLinkedListTests {

        [TestCase]
        public void shouldTestSinglyLinkedList () {
            SinglyLinkedList<int> sList = new SinglyLinkedList<int> ();

            sList.insertFirst (new SinglyLinkedList<int>.SNode (1));
            Assert.AreEqual (new [] { 1 }, sList.getAsArray ());

            sList.insertLast (new SinglyLinkedList<int>.SNode (10));
            Assert.AreEqual (new [] { 1, 10 }, sList.getAsArray ());

            sList.insertAt (1, new SinglyLinkedList<int>.SNode (5));
            Assert.AreEqual (new [] { 1, 5, 10 }, sList.getAsArray ());

            sList.clear ();
            Assert.AreEqual (new int[] { }, sList.getAsArray ());

            sList.insertLast (new SinglyLinkedList<int>.SNode (20));
            Assert.AreEqual (new [] { 20 }, sList.getAsArray ());

            sList.insertFirst (new SinglyLinkedList<int>.SNode (10));
            Assert.AreEqual (new [] { 10, 20 }, sList.getAsArray ());

            sList.insertLast (new SinglyLinkedList<int>.SNode (25));
            Assert.AreEqual (new [] { 10, 20, 25 }, sList.getAsArray ());

            sList.removeFirst ();
            Assert.AreEqual (new [] { 20, 25 }, sList.getAsArray ());

            sList.removeLast ();
            Assert.AreEqual (new [] { 20 }, sList.getAsArray ());

            sList.removeLast ();
            Assert.AreEqual (new int[] { }, sList.getAsArray ());

            sList.insertLast (new SinglyLinkedList<int>.SNode (10));
            Assert.AreEqual (new [] { 10 }, sList.getAsArray ());

            sList.removeFirst ();
            Assert.AreEqual (new int[] { }, sList.getAsArray ());
        }
    }
}