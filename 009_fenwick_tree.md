# Fenwick Tree (Binary Index Tree)


- Given an array of integers values compute the range sum between index [i, J).
```
A = [5 | -3 | 6 | 1 | 0 | -4 | 11 | 6 | 2 | 7]
```
- Let P be an array containing all the prefix sums of A.
```
A = [0 | 5 | 2 | 8 | 9 | 9 | 5 | 16 | 22 | 24 | 31]

 Sum of A from [2,7) = p[7] - p[2] = 16 -2 = 14
```
- What about if we update our initial array with some new value?
    - Re-Compute all the sums from index.
    - A prefix sum array is great for static arrays, but takes O(n) for updates.

## What is Fenwick Tree?
- A Fenwick Tree (also called Binary Index tree) is a data structure that supports sum range queries as well as setting values in a static array and getting the values in a static array and getting the vaue of the prefix sum up some index efficiently. 

### Complexity
| Operation | Complexity |
| - | - | 
| Construction | O(n) |
| Point Update | O(log(n)) |
| Range Sum | O(log(n)) |
| Range update | O(log(n)) |
| Adding Index | N/A |
| Removing Index | N/A |

### Range Queries
- unlike regular array, in a Fenwwick tree a specific cell is responsible for other cells as well.
- The position of the least significant bit(LSB) determines the range of responsibility that cell has to the cells below itself.
- Is 1 based.
```
=======================================
base 10 |   base 2
=======================================
16      |   10000                     |
15      |   01111     |               |
14      |   01110         |           |
13      |   01101     |   |           |
12      |   01100             |       |
11      |   01011     |       |       |
10      |   01010         |   |       |
9       |   01001     |   |   |       |
8       |   01000                 |   |
7       |   00111     |           |   |       
6       |   00110 --------|-------|---|-- Point update
5       |   00101     |   |       |   |
4       |   00100             |   |   |
3       |   00011     |       |   |   |
2       |   00010         |   |   |   |
1       |   00001     |   |   |   |   |
=======================================
```
- Index 12 in binary is: 1[1]00 
    - LSB is at position 3, so this index is responsible for 2 ^ (3 - 1) = 4 cells below itself.
- Index 10 in binary 10[1]0
    - LSB is at position 2, 2 ^ (2 -1) = 2
- Represent range responsibility
    - All the odd numbers have a their first least significant bit set in the ones position, do they are only responsible for themselves.
    - Numbers with their least significant bit in the second position have a range of two.
    - Numbers with their least significant bit in the third position have a range of four.
    - Number with their least significant bit in the fourth position have a range of eight. 
    - Number with their least significant bit in the fifth position have a range of sixteen. 
- In a Fenwick tree we may compute the prefix sum up to a certain index, which ultimately lets us perform range sum queries.
- Idea: Suppose you want to find the prefix sum of [1, i], then you start at i and cascade downwards until you reach zero adding the value at each of the indices you encounter.
- Find the prefix sum up to index 7.
    - sum = A[7] + A[6] + A[4]
- Find the prefix sum up to index 11.
    - sum = A[11] + A[10] + A[8]

- Let's  use the prefix sum to compute the interval sum between [i, j]
- compute the interval sum between [11, 15]
    - First we compute the prefix sum of [1, 15]
    - then, we will compute the prefix sum of [1, 11] and get the difference.
    - Not inclusive! we want the value at position 11
        - Sum of [1,15] = A[15] + A[14] + A[12] + a[8]
        - Sum of [1,11] = A[10] + A[8]
        - Range sum: (A[15] + A[14] + A[12] + a[8]) - (A[10] + A[8])
- In the worst case the cell we're quering has a binary representation of all ones (number of the form 2^n - 1)
- In the worst case a range query might make us have to do two queries that cost log^^2(n) operations.

### Range query algorithm
- To do a range query from [i,j] both inclusive a Fenwick tree of size N:
```
    function prefixSum(i):
        sum := 0
        while i != 0:
            sum = sum + tree[i]
            i = i - LSB(i)
        return sum 

    function rangeQuery(i, j):
        return prefixSum(j) - prefixSum(i-1)        
```
```
13 = 1101, 1101 - 0001 = 1100
12 = 1100, 1100 - 0100 = 1000
 8 = 1000, 1000 - 1000 = 0000
 0 = done
```

## Fenwick tree range queries
- Point update
    - Is opposite of this, we want to add the LSB to propagate the value up to the cells responsible for us.
```
 9 =  1001, 1001 + 0001 = 1010
10 =  1010, 1010 + 0010 = 1100
12 =  1100, 1100 + 0100 = 1000
16 = 10000
```
- If we add x to position 6 in the Fenwick tree which cells do we also need to modify?
```
 6 =  0110, 0110 + 0010 =  1000
 8 =  1000, 1000 + 1000 = 10000
16 = 10000,
```
- Required updates:
    A[6]  = A[6]  + x
    A[8]  = A[8]  + x
    A[16] = A[16] + x
- To update the cell at index i in the Fenwick tree of size N
```
    function add(i,x)
        while i < N
            tree[i] = tree[i] + x
            i = i + LSB(i)
```
- Where LSB returns the value of the least significant bit.
- LSB(12) = 4 because 12^^10 = 1100 is 100 or 4^^10


### Naive construction
- Let A be an array of values. For each element in A at index i do a point update on the Fenwick tree with a value of A[i]. There are n elements and each point update takes O(log(n)) for a total of O(nlog(n)), can we do better?
- Linear Construction.
    - Add value in the current cell to the immediate cell that is responsible for us.This resembles what we did for point updates but only one cell at a time.
    - This will make the cascading effect in range queries possible by propagating the value in each cell throughout the tree.
    - Let i be the current index
    - The immediate cell above us is at position j given by: j := i + LSB(i)
    ```
    i = 1 = 0001
    j = 0001 + 0001 = 0010 = 2

    i = 2 = 0010
    j = 0010 + 0010 = 0100 = 4
    ```
    - Ignore updating j if index is out of bounds.

### Construction algorithm
- Make sure values are 1-based.
```
    function construct(values):
        N := length(values)

        tree = deepCopy(values)

        for i = 1,2,3...N:
            j := i + LSB(i)
            if j < N:
                tree[j] = tree[j] + tree[i]
                
        return tree
```