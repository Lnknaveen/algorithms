# Union Find
- Consider Hypothetical case
```
E -> D -> C -> B -> A -> F <>
L -> K -> J -> I -> H -> G <>

<> self
```
- Union of E and L
- Start from E and L
    - Find root node of both
    - F is parent of E
        Now we have a reference of E
        ```
            A ---> 
            B ---> 
            C --->  F <>
            D ---> 
            E ---> 

            All points to F
        ```
        ```
            H ---> 
            I ---> 
            J --->  G <>
            K ---> 
            L ---> 

            All points to G
        ```
        - Now we can find the parent with constant time.
- Now merge to one group.