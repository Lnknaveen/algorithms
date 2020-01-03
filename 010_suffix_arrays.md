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
- This provides a compressed representation of the sorted suffix without actually needing to store the suffixes.
- The suffix array provides a space efficient alternative to a suffix tree which itself is a compressed version of a trie.
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


# Longest Common Substring (LCS) (K-Common Substring problem)
- Suppose we have n strings, how do we find the longest common substring that appears in at least 2 <= k <= n of the strings?
- Consider n = 3, k =2 with:
    - S1 = 'abca'
    - S2 = 'bcad'
    - S3 = 'daca'
- One approach is to use dynamic programming running in O(n^^1 * n^^2 * n^^3 * ... * n^^m), where n^^i is the length of the string s^^i. This may be ok for a few small strings,but rapidly gets unwieldy.
- An alternative method is to use a suffix array which can find the solution in O(n^^1 + n^^2 + ... + n^^m) time.
- Consider the same strings again.
    - TO find the LCS first create a new larger string T which is the concatenation of all the strings S^^i separated by unique sentinels.
    - T = S^^1 + '#' + S^^2 + '$' + S^^3 + '%' = abca#bcad$daca%
    - NOTE: THe sentinels must be unique and lexicographically less than any of the characters contained in any of the string S^^i. 
    - T = abca#bcad$daca% and Suffix array is
    ```
     [0 | #bcad$daca%       ] - Ignore which
     [0 | $daca%            ] - starts from 
     [0 | %                 ] - special chars

     LCP    Suffixes
     [0 | a#bcad$daca%      ]
     [1 | a%                ]
     [1 | abca#bcad$daca%   ]
     [1 | aca%              ]
     [1 | ad$daca%          ]
     [0 | bca#bcad$daca%    ]
     [3 | bcad$daca%        ]
     [0 | ca#bcad$daca%     ]
     [2 | ca%               ]
     [2 | cad%daca%         ]
     [0 | d%daca%           ]
     [1 | daca%             ]
    ```
    - Suppose k = 3 what is the LCS?
    - We can achieve a LCP value of two using these three strings.
- Things can get more messy when suffixes of different colours are not exactly adjacent.
- Use a sliding window to capture the correct amount of suffix colours. At each step advance the bottom endpoint or adjust the top endpoint such that window contains exactly K suffixes of different colours.
- The LCP between all strings in the window are 2
```
...
1 AAGT$CGAAGC%
5 AGAAGC#AGAAGT$CGAAGC%   --|  **
2 AGAAGT$CGAAGC%            |  ++
3 AGC#AGAAGT$CGAAGC%        |  **  AG 2 values
2 AGC%                    --|  --
0 AGT$CGAAGC%
...
```
### LCS Algorithm
- For each valid window perform a range query on the LCP array between the bottom and top endpoints. The LCS will be the maximum LCP value of all the possible windows.
- Lucky for us, the minimum sliding range query problem can be solved in a total of O(n) time for all windows.
- Alternatively, you can use min range query DS such as a segment tree to perform queries in log(n) time wihch may be easier but slightly slower running for total of O(nlog(n))
- Additionally, we will need a DS (hashtable) to track the colours in our sliding window.
- A valid window requires at least k or more of these to be greater than one!
```
0 AAGA      ++  --|     ** | ++ | --
3 AAGC      ++    |     ------------
4 AAGC%     **  --|     1  | 2  | 0
3 AAGT$     --
```

# Longest repeated string. 