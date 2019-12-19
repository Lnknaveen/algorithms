# Linked List
## What is a linked list?
- A linked list is a sequential list of nodes that hold data which point to other nodes also containing data.
```
Singly linked list,
(Data) -> (Data) -> (Data) -> NULL

Doubly linked list,
NULL <- (Data) <-> (Data) <-> (Data) -> NULL
```
### Where are the linked lists used?
- Used in List, Queue & Stacks.
- Great for creating a circular lists.
- Can easily model real world objects such as trains.
- Used in separate chaining, to deal with hash collisions.
- Often used in the implementation of adjacency lists for graphs.

### Terminology
- Head: THe first node in a linked list.
- Tail: The last node in a linked list.
- Pointer/Reference: Reference to another node.
- Node: An object containing data and pointer(s).
```
Head       Node           Pointer        Tail
 |          |               |             |  
(Data) -> (Data) -> (Data) -> (Data) -> (Data) -> NULL
```
#### Singly linked list
- holds only a reference to next node.
- Always maintain reference to head and tail.
---
- +++Uses less memory.
- +++Simpler implementation. 

- ---Can't easily access previous elements.
---

#### Doubly linked list
- Each node holds reference to next and previous node.
- Always maintain reference to head and tail.
---
- +++Can be traversed backwards.

- ---Takes 2x memory.
---

### Complexity
| | Sinlgy | Doubly |
| - | - | - |
| Search | O(n) | O(n) |
| Insert at head | O(1) | O(1) |
| Insert at tail | O(1) | O(1) |

