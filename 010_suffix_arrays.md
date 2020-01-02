# Suffix Arrays.
## What is a suffix?
- A suffix is a substring at the end of a string characters. For our purposes suffixes are non empty.
- HORSE
    - E
    - SE
    - RSE
    - ORSE
    - HORSE
- is an array which contains all the sorted suffixes of a string.
    - where suffix started in a string ans sorted in lexical order
    ```
    [0 | Camel]   [1 |  amel]
    [1 |  amel]   [0 | Camel]
    [2 |   mel]   [3 |    el]
    [3 |    el]   [4 |     l]
    [4 |     l]   [2 |   mel]
    ```
- Actual suffix array is the array of sorted indices.
- This provides a compressed representation of the sorted suffix without actually neeting to store the suffixes.
- The suffix array provies a space efficient alternative to a suffix tree which itself is a compressed version of a trie.
    - suffix arrays can do everything suffix trees can, with some additional information such as a Longest Common Prefix (LCP) array.

# The Longest common prefix (LCP) array.
## What is LCP array.
- ABABBAB
- suffix array with sorted index
```
Sorted Index    |    LCP value  | Suffix
    5                   0           AB
    0                   2           ABABBAB
    2                   2           ABBAB
    6                   0           B
    4                   1           BAB
    1                   3           BABBAB
    3                   1           BBAB
```
- The LCP array is an array in which every index tracks how many characters tow sorted adjacent suffix have in common.
- LCP[0] is undefined, but for most purposes it's fine to set it to zero.
    - THere exisits many methods for efficiently constructing the LCP array in O(nlog(n)) and O(n).

### Using SA/LCP array to find unique substrings.
- The problem of finding/counting all the unique substrings of a string is a commonplace problem in computer science.
    - The naive algorithm generates all substrings and places them in a set resulting in a O(n^2) algorithm.
    - The better approach it to use the LCP array.
- AZAZA
    - All n(n+1) / 2 substrings
        - A, AZ, AZA, AZAZ, AZAZA, Z, ZA, ZAZ, ZAZA, A, AZ, AZA, Z, ZA, A
    - Number of unique substrings: 9
    ```
    [0 |     A]
    [1 |   AZA]
    [3 | AZAZA]
    [0 |    ZA]
    [0 |  ZAZA]
    ```
    - The number of unique substrings in a substring is given by:
    - ((n (n + 1)) / 2) -  ((n Σ i =1) LCP[i])
    - Number of strings - duplicates.
    - (5 (5 + 1) / 2) - (1 + 3 + 0 + 2 + 0) = 9