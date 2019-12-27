# Queue

## What is a Queue?
- A queue is a linear data structure which models real world queues having two primary operations. (ENQUEUE and DEQUEUE)
- FIFO (FIRST IN FIRST OUT)
- Enqueue = Adding = Offering
- Dequeue = removing in queue = polling.

## Where is a queue used?
- Any waiting line models a queue, for example a lineup at a movie theatre.
- Can be used to efficiently keep track of the x most recently added elements.
- Web server request management where you want first come first serve.
- Breadth first search (BFS) graph traversal.

### Complexity
| Operation | Complexity |
| - | - | 
| Enqueue | O(1) |
| Dequeue | O(1) |
| Peeking | O(1) |
| Searching | O(n) |
| Size | O(1) |

### Can be implemented using Arrays, Singly Linked List or Doubly Linked List

# Priority queues
## What is Priority Queue?
- A priority queue is an Abstract Data Type (ADT) that operates similar to a normal queue except that each element has a certain priority.
- The priority of the elements in the queue determine the order in which the elements are removed from the PQ.
- NOTE: PQ only supports comparable data.
- Priority queue used heap.

## WHat is a Heap?
- A heap is a tree based DS that satisfies the heap invariant (also called heap property): If A is a parent node of B then A is ordered with respect to B for all nodes A, B in the heap. 
- Max Heap: root is biggest.
- Min Heap: root is smallest.

## Where is a PQ used?
- Used in certain implementations of Dijkstra's Shortest path algorithm.
- Anytime you need the dynamically fetch the 'next best' or 'next worst' element.
- Used in the Huffman coding (which is often used for lossless data compression).
- BFS algorithms such as A* use PQs to continuously grab the next most promising node.
- Used by Minimum Spanning Tree (MST) algorithms.

### Complexity with binary heap
| Operation | Complexity |
| - | - | 
| Heap construction | O(n) |
| Pooling | O(log(n)) |
| Peeking | O(1) |
| Adding | O(log(n)) |
| Native removing | O(n) |
| Advanced removing with help from hash table * | O(log(n))|
| Native contains | O(n) |
| Contains check with help of a hash table *| O(1) |

* Using a hash table to help optimize these operations does take up linear space and also ads some overhead to the binary heap implementation.

# Turning Min PQ into Max PQ
- Standard libs of most programming languages only provide a min PQ. Sometimes we need a Max PQ.
- Since elements in a priority queue are comparable they implement some sort of comparable interface which we can simply negate to achieve a Max heap.
- For comparers x <= y negation y <= x
- Suppose 'lex' is a comparator for a string. (lexicographic)
    - lex(s1,s2) = -1; s1 < s2
    - lex(s1,s2) = 0; s1 = s2
    - lex(s1,s2) = 1; s1 > s2
- let nlex be negation lex
    - lex(s1,s2) = -(-1) = 1; s1 < s2
    - lex(s1,s2) = -(0) = 0; s1 = s2
    - lex(s1,s2) = -(1) = -1;  s1 > s2

## Ways of implementing a priority queue
- Priority queues usually implemented with heaps since this give them the best possible time complexity.
- The priority queue (PQ) is an Abstract Data Type (ADT), hence heaps are not the only way to implement PQs.
- We could use an unsorted list, (This would not give us the best possible time complexity)

### Priority queue with Binary Heap.
- There are many types of heaps we could use to implement a priority queue including:
    - Binary heap.
    - Fibonacci heap.
    - Binominal heap.
    - Pairing heap.

#### We make use of Binary tree
- A binary heap is a binary heap that supports the heap invariant.
- In a binary tree every node has exactly two children.
- A complete binary tree is a tree in which at every level, except possibly the last is completely filled and all the nodes are as far left as possible.
- Binary heap representation
    - (zero based)
    - Let i be the parent node index
    - Left child index: 2i + 1
    - Right child index 2i + 2
```
[ 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10| 11| 12 | 13 | 14 ]
            ---- 0 ----
           |   Index   |
         --1-- Tree  --2--
        |     |     |     | 
       -3-   -4-   -5-   -6- 
      |   | |   | |   | |   |
      7   8 9  10 11 12 13 14
```
```
[ 9 | 8 | 7 | 6 | 5 | 1 | 2 | 2 | 2 | 3 | 4| 0| 1 | 2 | 1 ]
            ---- 9 ----
           |           |
         --8--       --7--
        |     |     |     | 
       -6-   -5-   -1-   -2- 
      |   | |   | |   | |   |
      2   2 3   4 0   1 2   1
```
### Adding elements to binary heap.
- Add at last (Bubbling Up, Swimming, Sifting up)
- bubble up if parent is bigger
    - swap with bigger
    - repeat steps


### Removing elements from a binary heap.
- Remove root (polling)
- Swap last element to root. (heap invariant is not happy)
- bubble down
    - swap with smallest
    - repeat steps
----------
- Remove other element
    - Search for an element
    - swap with last node
    - remove element
    - Bubble up swapped node.
    If, heap variant satisfies bubble down. 

Complexity
```
Pooling             O(log(n))
Removing            O(n)
```
## Better way of doing this operation.
### Removing element from binary heap in O(log(n))
- Make use of `HashTable` than linear search.
- HashTable provides a constant time lookup and update for a mapping from a key to value. (node value to index)
- Caveat: What happens if there are two or more nodes with the same value?
    - Instead of mapping one value to one position we will map one value to multiple positions.
        - We can maintain a Set or Tree Set of indexes for which a particular node value (key) maps to.
```
            ---- 2 ----
           |           |
         --7--       --2--
        |     |     |     | 
       -11-  -7-   -13-  -2-

            ---- 0 ----
           |   Index   |
         --1-- Tree  --2--
        |     |     |     | 
       -3-   -4-   -5-   -6-        
```
| Node Value (Key) | Position (Value) |
| - | - |
| 2 | 0, 2, 6 |
| 7 | 1, 4 |
| 11 | 3 |
| 13 | 5 |

- Keep track of index when bubble up and bobble down
- If we remove a repeated node in our heap, which node do we remove and does it matter?
    - No it doesn't matter. If, we satisfy the heap invariant.

### Adding elements to binary heap.
- insert node at last and update index in table.
    - Bubble up and update table.
- remove multiple value

### Removing multiple entry elements from a binary heap.
- pick first index and update table
    - swap with last node and update table.
    - Bubble down and update table.
    - repeat and update table.
