## Static Array
- Is a fixed length container containing n elements
- indexable from [0, n - 1], each slot/index is a number

### When is it used?
1. Sorting and accessing sequential data.
2. Temporarily storing objects.
3. Used by IO routines as buffers.
4. Lookup tables and inverse lookup tables.
5. Return multiple values from a function.
6. Used in dynamic programming to cache answers to sub problems

### Complexity
| | Static Array | Dynamic Array |
| - | - | - |
| Access | O(1) | O(1) |
| Search | O(n) | O(n) |
| Insertion | N/A | O(n) |
| Appending | N/A | O(1) |
| Deletion | N/A | O(n) |


## Dynamic Array
- Can grow and shrink in size.

## How do we implement a dynamic array?
### One way is to use static array.
- Create a static array with an initial capacity.
- Add elements to the static array.
- Keep track of the number of elements
- If adding element exceeds the cpability
- Then create a new static array with twice the capacity
- copy the original elements into it. 
