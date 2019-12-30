# Binary Tree
## What is Binary tree?
- A tree is an undirected graph which satisfies any of the following definitions.
    - An acyclic connected graph.
    - A connected graph with N nodes and N-1 edges.
    - A graph in which any two vertices are connected by exactly one path.
    ```
            ---- 9 ----   Root node 9
           |           |
         --8--       --7--
        |     |     |     | 
       -6-   -5-   -1-   -2- 
      |   | |   | |   | |   |
      2   2 3   4 0   1 2   1
    ```
    - Root node
        - If we have a rooted tree then we will want to have a reference to the root node of our tree.
        - It does not always matter which node is selected to be the root node because any node can root the tree!
    - A child is a node extending from another node.
    - A parent is the inverse of this.
    - What is the parent of the root node?
        - It has no parent, although it may be useful to assign the parent of the root node to be itself. (e.g., filesystem tree).
    - A leaf node is a node with no children.

    ### Subtree
    - A subtree is a tree entirely contained within another. They are usually denoted using triangles.
        - Subtree may consists of a single node!
    ```
         ○           
        / \
       △   △  ->   ○           
                  / \
                 ○   △ -> ○  
                         / \
                        ○   ○

    ○ -> Node, △ -> subtree
    ```

## what is a Binary Tree (BT)?
- A binary tree is a tree for which every node has at most two child nodes.
```
        ○  
       / \
      ○   ○

        ○  
       / \
      ○   ○
      |
      ○ 
```
## What is binary search tree (BST)?
- A binary search tree is a binary tree that satisfies the BST invariant: left subtree has smaller elements and right subtree has larger elements.
- BST can also have any data, its not limited to numbers. (Comparable and Ordered)

#### Is this a valid BST?
```
    3
   / \
  3   4
```
- It depends on whether you want to allow duplicate values in your tree. BST operations allow for duplicate values, but most of the time we are olny interested in having unique elements inside out tree.

## Where is BST used?
- BSTs
    - Implementation of some map and set ADTs
    - Red Black Trees.
    - AVL Trees.
    - Splay Trees
    - etc..
- Used in implementation of binary heaps.
- Syntax tree (used by compailer and calculators)
- Treap - a probabilistic DS (uses a randomized BST)

### Complexity
| Operation | Average | Worst |
| - | - | - |
| Insert | O(log(n)) | O(n) |
| Delete | O(log(n)) | O(n) |
| Remove | O(log(n)) | O(n) |
| Search | O(log(n)) | O(n) |

### Inserting elements to a BST
- Elements must be comparable.
    - Recurse down left subtree (< case)
    - Recurse down right subtree (> case)
    - handle finding a duplicate value (= case)
    - Create a new node (found a null leaf)

### Removing elements from a BST
- Removing elements from BST can be seen as a two step process.
    1. FInd the element we wish to remove (if exists)
    2. Replace the node we want to remove with its successor (if any) to maintain the BST invariant.

#### Finding phase
1. We hit a null node at which point we know the value doesn't exist within our BST.
2. Comparator value = 0 (found it!)
3. Comparator value < 0 (the value, if it exists, is in the left subtree)
4. Comparator value > 0 (the value, if it exists, is in the right subtree)

#### Remove phase
1. Node to remove is a leaf node.
```     
        |
        ○  
```      
    - Remove it immediately since it's a leaf node. 
2. Node to remove has a right subtree but no left subtree.
```
         |
         ○           
          \
           △ 
```
3. Node to remove has a left subtree but no right subtree
```
         |
         ○           
        / 
       △   
``` 
    - The successor of the node we are trying to remove in these cases will be the root node of the left/right subtree
    - It may be the case that you are removing the root node of the BST in which case its immediate child becomes the new root as you would expect.   
4. Node to remove has a both left subtree and a right subtree.
```
         |
         ○           
        / \
       △   △ 
```
    - Node has both a left subtree and a right subtree.
    - In which subtree will the successor of the nod we are trying to remove be?
        - The answer is both! The successor can either be the largest value in the left subtree or the smallest value in the right subtree.
    - A justification for why there could be more than one successor is:
        - The largest value in the left subtree satisfies the BST invariant since it:
            1. Is larger than everything in the left subtree.
            2. Is is smaller than everything in the right subtree.
        - The smallest value in the right subtree satisfies the BST invariant since it:
            1. Is smaller than everything is right subtree. This follows immediately from the definition of being the smallest.
            2. Is larger than everything in left subtree because it was found un the right subtree.

## Tree traversals
- Preorder
    - prints before the recursive calls
```
preorder(node):
    if(node == null): return
    print(node.value)
    preorder(node.left)
    preorder(node.right)
```

- Inorder
    - prints between the recursive calls
```
inorder(node):
    if(node == null): return
    inorder(node.left)
    print(node.value)
    inorder(node.right)
```

- Postorder
    - prints after the recursive calls
```
postorder(node):
    if(node == null): return
    postorder(node.left)
    postorder(node.right)
    print(node.value)
```

- Level order
    - we want to print the nodes as they appear one layer at a time.
    - To obtain this ordering we want to do a Breadth first search (BFS) from the root node to the leaf nodes.
        - We will need to maintain a Queue of the nodes left to explore.
        - Begin with the root inside of the queue and finish when the queue is empty.
            - At each iteration we add the left child and the right child of the current node to our queue.
