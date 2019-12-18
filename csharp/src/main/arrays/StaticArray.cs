using System;

namespace arrays
{
    public class StaticArray
    {
        int[] numbers;

        public StaticArray(int[] numbers)
        {
            this.numbers = numbers;
        }

        public bool find(int num)
        {
            bool found = false;
            for (int i = 0; i < numbers.Length; i++)
            {
                if (numbers[i] == num)
                {
                    found = true;
                    break;
                }
            }

            return found;
        }

        internal void printElementsWithIndexes()
        {
            Console.WriteLine($"Array Size = {numbers.Length}");
            for (int i = 0; i < numbers.Length; i++)
            {
                Console.WriteLine($"numbers[{i}] = {numbers[i]}");
            }

        }
    }
}