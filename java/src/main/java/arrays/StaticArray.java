package arrays;

/**
 * StaticArray
 */
public class StaticArray {
    int[] numbers;

    public StaticArray(int[] numbers) {
        this.numbers = numbers;
    }

    public boolean find(int num) {
        boolean found = false;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == num) {
                found = true;
                break;
            }
        }

        return found;
    }

    public void printElementsWithIndexes() {
        System.out.println(String.format("Array Size = %s", numbers.length));
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(String.format("numbers[%s]  = %s", i, numbers[i]));
        }
    }
}