Reference: https://github.com/williamfiset/data-structures 

## What is DS?
- A data structure is a way of organizing data so that it can be used effectively.

## Why?
- They are essential ingredients in creating fast and powerful algorithms.
- They help to manage and organize data.
- They make code cleaner and easier to understand.


## ADT
- ADT is an abstraction of a DS which provides only the interface to which a DS must adhere to.
- The interface doesn't give any specific details about how something should be implemented.

| Abstract Data Types | Data Structures |
| --- | --- |
| LIST | Dynamic Array, Linked List |
| Queue | Linked List based Queue, Array based Queue, Stack based Queue |
| Map | Tree Map, Hash Map / Hash Table |
| Vehicle | Golf Cart, Bicycle, Smart Car |

## Complexity Analysis
- How much 'time' need to finish?
- How much 'space' need for it's compute?

### Big O Notation
- Gives the upper bound of the complexity in the worst case.
- n: size of input

| Complexities  | ordered from  | smallest to largest |
| - | - | - |
| Constant | Time | O(1) |
| Logarithmic | Time | O(log(n)) |
| Linear | Time | O(n) |
| Linearithmic | Time | O(nlog(n)) |
| Quadratic | Time | O(n ^ 2) |
| Qubic | Time | O(n ^ 3) |
| Exponential | Time | O(b ^ n), b > 1 |
| Factorial | Time | O(n!) |

#### Big properties
- `O(n + c)` = `O(n)`
- `O(nc)` = `O(n), c > 0`
- E.g., 
```
>>> f(n) = 7log(n) ^ 3 + 15n ^ 2 + 2n ^ 3 + 8
>>> O(f(n)) = O(n ^ 3)
```
```
for(i = 0; i < n; i = i +1)
    for(j = 0; j < n; j = j + 1)

f(n) = n * n = n ^ 2, O(f(n)) = O(n ^ 2)
```
```
for(i = 0; i < n; i = i +1)
    for(j = i; j < n; j = j + 1)

    - i goes from [0, n)
    - i = 0, n loop; i = 1, (n - 1) loop; i = 2, (n - 2) loop; etc
    - n + (n - 1) + (n - 2) ...
    - translates to n(n + 1 ) / 2

O(n(n + 1) / 2) = O((n ^ 2 / 2) + (n / 2)) = O(n ^ 2)
```

```
i = 0
while( i < n )

    j = 0
    while( j < 3 * n )
        j = j + 1

    j = 0
    while( j < 2 * n )
        j = j + 1

    i = i + 1

f(n) = n * (3n + 2n) = 5n ^ 2
O(f(n)) = O(n ^ 2)
```

```
i = 0
while( i < 3 * n )

    j = 10
    while( j <= 50 )
        j = j + 1

    j = 0
    while( j < n * n * n )
        j = j + 2

    i = i + 1

f(n) = 3n * (40 + (n ^ 3 / 2)) = (3n / 40) + (3n ^ 4 / 2)
O(f(n)) = O(n ^ 4)
```
```
- Finding all subsets of sets   O(2 ^ n)
- Finding all permutations of a string   O(n!)
- Sorting using merge sort  O(n log(n))
- Iterating over all the cells in a matrix of size n by m  O(nm)
```