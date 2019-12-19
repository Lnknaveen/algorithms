using System;
using NUnit.Framework;

namespace arrays {
    [TestFixture]
    public class StaticArrayTests {

        [TestCase (new [] { 10, 20, 30, 40 }, 10, true)]
        [TestCase (new [] { 10, 20, 30, 40 }, 50, false)]
        public void shouldTestStaticArray (int[] numbers, int valueToFind, bool expected) {
            StaticArray array = new StaticArray (numbers);
            Assert.AreEqual (expected, array.find (valueToFind));
        }

        [Test]
        public void printElementsWithIndexes () {
            StaticArray array = new StaticArray (new [] { 10, 20, 30, 40 });
            array.printElementsWithIndexes ();
        }

    }
}