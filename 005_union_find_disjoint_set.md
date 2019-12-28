# Union Find
## WHat is a Union find?
- Is a data structure that keeps track of elements which are split into one or more disjoint sets.
- It has two primary operations find and union. 

## Where is Union find used?
- Kruskal's minimum spanning tree algorithm
- Grid percolation.
- Network connectivity.
- Least common ancestor in trees
- Image processing.

### Complexity
|  |  |
| - | - |
| Construction | O(n) |
| Union | a(n) |
| Find | a(n) |
| Get component size | a(n) |
| Check if connected | a(n) |
| Count components | O(1) |

a(n) - Amortized constant time.

### Kruskal's Minimum spanning Tree
- Given a graph G = (V, E) we want to find a Minimum Spanning Tree in the graph (it may not be unique).
- A minimum spanning tree is a subset og the edges which connect all vertices in the graph which the minimal total edge cost.
```
                 (A)-- 5 --(B)
                /   \      /
               /     9    2
              1       \  /
             /        (D)
            /        /
           (E) --- 2 
```
### Possible minimum spanning tree
```
                 (A)      (B)
                /         /
               /         2
              1         /
             /        (D)
            /        /
           (E) --- 2 
       
       weight = 5
```
- Sort by ascending edge weight.
- Walk through the sorted edges and look at the two nodes the edge belongs to, if the nodes are already unified we don't include the edge, otherwise we include it and unify the nodes.
- The algorithm terminates when every edge has been processed or all the vertices have been unified.

### Creating Union Find
- TO begin using union find, first construct a bijection (a mapping) between your objects and integers in the range [0,n).
- NOTE: This step is not necessary in general, but it will allow us to construct an array-based union find.

#### Design
- Randomly assign a mapping between the objects and the integers on the right.
- (A)...(L), 0...11
- Hash table
```
(E) --> 0
(F) --> 1
.
.
.
(H) --> 11
```
- Construct an array
    - Store union find information in an array.
    - Each index has an associated object (letter in this example) we can lookup through out mapping.
    ```
      E | F |... | H
     [0 | 1 |... | 11]
    ```
### Summary
#### Find operation
- To find which component a particular element belongs to find the root of that component by following the parent nodes until a self loop is reached.
#### Union operation 
- To unify two elements find which are the root nodes of each competent and if the root nodes are different make one of the root nodes be the parent of the other.

### Remarks
- In this data structure, we do not "un-union" elements. In general, this would be very inefficient to do since we would have to update all the children of a node.
- The number of components is equal to the number of roots remaining. Also, remark that the number of root nodes never increases.

### Complexity
- Our current version of Union find does not support the nice a(n) time complexity we want.
- Checking if A to H belong to the same group take multiple hops.