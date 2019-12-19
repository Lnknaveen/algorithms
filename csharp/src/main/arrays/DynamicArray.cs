using System;
using System.Diagnostics.Contracts;

namespace arrays {
    class DynamicArray<T> {
        private T[] array;
        private int size;
        private int capacity;

        public DynamicArray (T[] initialArray) {
            this.array = initialArray;
            this.size = initialArray.Length;
            this.capacity = initialArray.Length;
        }

        public int Size { get { return size; } }

        internal void add (T value) {
            EnsureCapacity ();

            array[size++] = value;
        }

        internal T removeAt (int index) {
            T returnValue = array[index];

            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }

            array[--size] = default (T);

            return returnValue;
        }

        internal void insertAt (int index, T value) {
            EnsureCapacity ();

            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }

            size++;
            array[index] = value;
        }

        private void EnsureCapacity () {
            if (capacity < size + 1) {
                capacity = capacity * 2;

                T[] new_array = new T[capacity];
                Array.Copy (array, 0, new_array, 0, array.Length);
                array = new_array;
            }
        }

        internal T[] getValues () {
            T[] returnValue = new T[size];
            Array.Copy (array, returnValue, size);
            return returnValue;
        }
    }
}