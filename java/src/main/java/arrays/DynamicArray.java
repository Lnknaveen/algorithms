
package arrays;

public class DynamicArray<T> {

    private T[] array;
    private int capacity;
    private int size;

    public DynamicArray(T[] array) {
        this.array = array;
        this.capacity = array.length;
        this.size = array.length;
    }

    public void add(T data) {
        ensureCapacity();

        array[size++] = data;
    }

    private void ensureCapacity() {
        if (capacity < size + 1) {
            capacity = capacity * 2;

            T[] new_array = newArray(capacity);
            System.arraycopy(array, 0, new_array, 0, array.length);
            array = new_array;
        }
    }

    @SuppressWarnings("unchecked")
    private T[] newArray(int size) {
        T[] new_array = (T[]) new Object[size];
        return new_array;
    }

    public T removeAt(int index) {
        T returnValue = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[--size] = null;

        return returnValue;
    }

    public void insertAt(int index, T value) {
        ensureCapacity();

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        size++;
        array[index] = value;
    }

    public int getSize() {
        return size;
    }

    public T[] getValues() {
        T[] new_array = newArray(size);
        System.arraycopy(array, 0, new_array, 0, size);
        return new_array;
    }
}