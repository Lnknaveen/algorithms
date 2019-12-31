# Hash Table
## What is a Hash table?
- A Hash table (HT) is a data structure that provides a mapping from keys to values using a technique called hashing.
```
    Key ==> value
```
- We refer to these as key-value pairs.
- keys must be unique, but values can be repeated.
- You can store any type of data in HT, keys must be hashable.

### Hash Functions H(x)
- Maps key x to whole number in a fixed range.
    - All integer keys to the range [0,9]
```
    H(x) = (x^2 - 6x + 9) mod 10

    H(4) = (16 - 24 + 9) mod 10 = 1
    H(0) = (0 - 0 + 9) mod 10 = 9
```
- We can also define hash functions for arbitrary objects such as strings, lists, tuples, multi data objects, etc...
```
    ASCII('A') = 65
    ASCII('B') = 66
    ...
    ASCII('Z') = 90
```

### Properties of hash functions
- If H(x) = H(y) then objects x and y might be equal, but if H(x) != H(y) then x and y are certainly not equal.
- A hash function H(x) must be deterministic.
    - That means if H(x) = y then H(x) must always produce y and never another value.
- We try very hard to make them uniform hash functions to minimize the number of hash collisions.
- Use value which immutable.


### How does hash table work?
- Ideally we would like to have a very fast insertion, lookup and removal time for the data.
- We can achieve all this in O(1)
    - The constant time behaviour attributed to hash tables is only true if you have a good uniform hash function!
```
    [Index | Key | Value]
   ---------------
    [0 | 65 | "A"]
    [1 |    |    ]
    [2 |    |    ]
    [3 | 66 | "B"]
    [4 | 67 | "C"]
```
### What do we do if we have a hash collision?
- there are many hash collision resolution.
    - Separate chaining
        - Maintains a data structure (usually a linked list) to hold all the different values which hashed to a particular value. 
    - Open addressing.
        - Finding another place within the hash table for the object to go by offsetting it from the potion to which it hashed to.

### Complexity
| Operation | Average | Worst |
| - | - | - |
| Insert | O(1)* | O(n) |
| Remove | O(1)* | O(n) |
| Search | O(1)* | O(n) |
- * THe constant time behavior attributed to hash tables is only true if you have a good uniform hash function! 

## Separate chaining
- Maintains a data structure.
    - It can use array, binary tree, self balancing trees and etc...
 
 ```
[Name | Age | Hash]
---------------
[A | 10 | 2]
[B | 11 | 3]
[C | 12 | 2]
[D | 13 | 1]

Separate chaining
[1 = D]
[2 = A -> C]
[3 = B]
```
- Lookup
    - Obtain the index
    - Search chaining
#### How do i maintain O(1) insertion and lookup time complexity once my HT gets really full and I have long listed list chains?
- Once the HT contains a lot of elements you should create new HT with larger capacity and rehash all the items inside the old HT and disperse them throughout the new HT at different locations.
#### How do i remove key-value pairs from my HT?
- Apply the same procedure as doing a lookup for key, but this time instead of returning the value associated with the key remove the node in the linked list data structure.

## Open addressing
- When using open addressing as a collision resolution technique the key-value pairs are stored in the table (array) itself as opposed to data structure like separate chaining.
- Load factor = items in the table / size of table;
- Once a > threshold we need to grow the table size.
- If the position of our hash is occupied we try another position in the hash table by offsetting the current position subject to a probing sequence P(x). We keep doing this until an unoccupied slot is found.
- There are infinite number of probing sequence you can come up with.
    - Linear probing
        - P(x)  =ax + b where a,b are constants.
    - Quadratic probing
        - P(x) = ax^2 + bx + c where a,b,c are constants.
    - Double hashing
        - P(k,x) = x*H^^2(k) where H^^2(K) is a secondary hash function
    - Pseudo random number generator
        - P(k,x) = x*RNG(H(k),x) where RNG is a random number generator function seeded with H(k).

#### Chaos with cycles
- Most randomly selected probing sequences modulo N will produce a cycle shorter than table size.
- This becomes problematic when you are trying to insert a key-value pair and all the buckets on the cycle are occupied because you will get stuck in an infinite loop!
- How do we handle probing functions which produce cycles shorter than the table size?
    - we don't handle this issue, Instead we avoid it altogether by restricting our domain of probing functions to those which produce a cycle of exactly length N*
        - * There are few exceptions with special properties that can produce shorter cycles.

### Linear probing
#### Cycles
- Which values of the constant a in P(x) = ax produce a full cycle modulo N?
    - This happens when a and N are relatively prime. Two numbers are relatively prime if their Greatest Common Denominator (GCD) is equals to one. Hence, when GCD(a, N) = 1 the probing function P(x) be able to generate a complete cycle and we will always be able to find an empty bucket!
- Before we insert the next pair. if, we reached the threshold value.
    - we need to grow the table, this is done in some exponential fashion such as doubling the table size. Whatever you do make sure GCD(N,a) = 1 still holds.
- While inserting in new table re-probe all the keys and insert again.