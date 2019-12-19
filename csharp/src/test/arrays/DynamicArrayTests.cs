using System.Linq;
using NUnit.Framework;

namespace arrays {

    [TestFixture]
    class DynamicArrayTests {

        [TestCase]
        public void shouldAddItemsToDynamicArray () {
            int[] array = new int[] { 10, 20, 30, 40 };
            DynamicArray<int> dynamicArray = new DynamicArray<int> (array);

            dynamicArray.add (50);
            Assert.AreEqual (new [] { 10, 20, 30, 40, 50 }, dynamicArray.getValues ());

            int removedElement = dynamicArray.removeAt (0);
            Assert.AreEqual (10, removedElement);
            Assert.AreEqual (new int[] { 20, 30, 40, 50 }, dynamicArray.getValues ());

            dynamicArray.insertAt (1, 10);
            Assert.AreEqual (new int[] { 20, 10, 30, 40, 50 }, dynamicArray.getValues ());
        }
    }

}