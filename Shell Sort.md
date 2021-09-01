# Shell Sort Handout

## What is shell sort?
- Improvement to the original insertion sort
- "Diminishing Increment Sort", similar to comb sort
- Combination of sorting by insertion (insertion sort) and sorting by exchange (bubble sort)
- Starts off by comparing two elements that are far apart and gradually reduce the gap between them
- It works better than regular insertion sort as it moves certain out of place elements into place faster

## How does it work?
1. Start off my determining the gap sequence, which is the amount you will increment by each time (Note that each Gap Sequence has its own T/C)
2. For each gap sequence of length N, start at the Nth element and loop until the end of the array
3. Every increment, compare the Nth element with the N - gap element
4. If the Nth element is greater than the N - gap element, insert the N - gap element at the Nth index **(Insertion Sort)**
5. After the loop exits, re-insert the Nth element at the last index where an insertion happened (the last element of the second loop)

## Number of Passes (Increments):
For shell's original sequence, as it increments by a ratio of 2 each time, the number of increments would be $`log_2 N+1`$

## Example (Using Shell's Oringal Sequence):
`Array: 2, 5, 3, 4, 1, 8, 6, 7` 

0. 4, 2, 5, 1, 8, 3, 6, 7
1. 1, 5, 3, 4, 2, 8, 6, 7
2. 1, 4, 2, 5, 3, 7, 6, 8
3. 1, 2, 3, 4, 5, 6, 7, 8
4. 1, 2, 3, 4, 5, 6, 7, 8

## Time Complexity (Shell's Original Sequence):
**Best Case:** $`O(NLogN)`$ <br/>
**Average Case:** $`O(N^\frac{5}{4})`$ <br/>
**Worst Case:** $`O(N^2)`$

## Space Complexity:
The space complexity of shell sort will always be __O(1)__ as it does not create another array

## Stablility & Adaptability
**Stable:** `NO` (Equal elements will appear at different keys in the outputted array) <br/>
**Adaptable:** `YES` (When input is partially sorted)

## Pros & Cons:
|Pros|Cons|
|:---|:---|
|- Efficient sorting algorithm for medium sized arrays <br/> - Good when memory restraint is tight (Merge and Quicksort will sometimes MLE) <br/> - The fastest non-recursive sorting algorithm <br/> - Much faster than insertion with basically the same amount of code <br/> - Easy to get working and only requires knowledge of iterations and conditional statements|- Heap, merge and quick sort are both more efficient <br/> - Not widely used as it is difficult to understand and implement <br/> - Many people often use the built in version ```Arrays.sort()``` which uses quick sort <br/> - Unstable sorting algorithm (Equal elements will appear in different keys) <br/> - Not as effective for extremely large data sets|

## When to Use Shell Sort?
If possible, it is always best to use the built in sorting algorithm provided by your language. Java uses quick sort and merge sort for the built in ```Arrays.sort();```. However, if both of them cause MLE or your teacher doesn't allow for it, you can always use other sorting algorithms such as heap sort with a consistant $`O(NLogN)`$ time complexity. If all else fails, come to shell sort with best case of $`O(NLogN)`$ and no auxillary memory demand.

## Gap Sequences:
|General term $`(k \ge 1)`$|Concrete gaps|Worst-case time complexity|Author and year of publication|
|:------------------------:|:-----------:|:------------------------:|:----------------------------:|
|$`\Bigl\lfloor\dfrac{N}{2^K}\Bigr\rfloor`$|$`\dfrac{N}{2}`$, $`\dfrac{N}{4}`$, $`\dfrac{N}{2}`$|$`O(N^2)`$|Shell - 1959|
|$`2\Bigl\lfloor\dfrac{N}{2^{k+1}}\Bigr\rfloor+1`$|$`2\Bigl\lfloor\dfrac{N}{4}\Bigr\rfloor+1`$, $`3`$, $`1`$|$`O(N^\frac{3}{2})`$|Franz & Lazarus - 1960|
|$`2^k - 1`$|$`1, 3, 7, 15, 31, 63`$|$`O(N^\frac{3}{2})`$|Hibbard - 1963|
|$`2^k + 1`$|$`3, 5, 9, 17, 33, 65`$|$`O(N^\frac{3}{2})`$|Papernov & Stasevich - 1965|
|$`2^p`$$`3^q`$|$`1, 2, 3, 4, 6, 8, 9`$|$`O(NLog^{2}N)`$|Pratt - 1971|
|$`\dfrac{3^{k}-1}{2}`$|$`1, 4, 13, 40, 121`$|$`O(N^\frac{3}{2})`$|Pratt & Knuth - 1973|
|$`4^k+3\times2^{k-1}+1`$|$`1, 8, 23, 77, 281`$|$`O(N^\frac{4}{3})`$|Sedgewick - 1982|

## Sample Program
```java
public static void shellSort(int arr[]) // Method Header
    { 
        for(int gap = arr.length / 2; gap > 0; gap /= 2){ // Loop through the length of the gap sequences
            for(int i = gap; i < arr.length; i++){ // Loop through from the gap length to the length of the array
                int temp = arr[i], j; // Set the temp variable as the ith element in the array
                for(j = i; j >= gap && arr[j - gap] > temp; j -= gap){ // Loop through all the element to swap
                    arr[j] = arr[j - gap]; // Insert the jth element at the j - gap index
                }
                arr[j] = temp; // Insert temp at the jth element where the other element was swapped
            }
        }
    }
    
```

## Comparison to Other Sorting Algorithms:
### Shell vs Insertion
Shell sort can be seen as an improvement to insertion sort as it uses a variety of gap sequences and moves out of place elements into their correct positions faster whereas insertion sort will check each one by one. Even for partially sorted array, shell sort will still sort partially or nearly sorted arrays faster. Like selection sort, shell sort should be used for larger data sets.

### Shell vs Selection
Both insertion and shell sort can be used when the memory restraint is super tight and does not allow for auxillary memory. However, shell sort has a best case of $`O(NLogN)`$ while selection sort has a best time complexity of $`O(N^2)`$. For larger data sets, shell sort would obviously be more appealing than selection.

### Shell vs Quick
Shell sort and quick sort does not serve the same purpose, but many people tends to use quicksort as it is known as the "Queen of All Sorts" for its ability to sort randomized list in a short amount of time. However, shell sort is more favorable with strict space complexity as quick sort requires $`O(NLogN)`$ auxillary space complexity.

### Shell vs Merge
Merge sort is often regarded as one of the best sorting algorithms with consistent $`O(NLogN)`$ time complexity. However, like quick sort, it requires $`O(2N)`$ memory, which sometimes will result in MLE (Memory Limit Exceeded). For larger data sets, merge sort would, most of the time, be the better sorting algorithm.

# Enhanced Shell Sort
- A new gap sequence which can decrease the number of comparisons by up to 60%
- Published by Basit Shahzad and Muhammad Tanvir Afzal from the World Academy of Science
- The gap sequence would be h<sub>1</sub> and h<sub>n+1</sub> = 3h<sub>n</sub>+1
- For 100 elements, shell's original gap seuqence will make 170 comparisons but the Enhanced Shell Sort will make 85 comparisons

## Testing
`10 Elements: 100, 37, 12, 86, 2, 127, 62, 14, 3, 9` <br/>
Shell Sort: *30* <br/>
Enhanced Shell Sort: *12*

`25 Elements: 100, 37, 12, 86, 2, 127, 62, 14, 3, 9, 30, 14, 90, 1, 20, 30, 74, 48, 37, 40, 7, 101, 200` <br/>
Shell Sort: *47* <br/>
Ehanced Shell Sort: *31*

`35 Elements: 1, 2, 23, 12, 25, 7, 9, 5, 36, 1, 100, 37, 12, 86, 2, 127, 62, 14, 3, 9, 30, 14, 90, 1, 20, 30, 74, 48, 37, 40, 7, 101, 200, 4, 9` <br/>
Shell Sort: *202* <br/>
Enhanced Shell Sort: *105*

## Enhanced Merge Sort Code:
```java
public static void enhancedShellSort(int arr[])
    {
        int gap[] = new int[3]; // Initialize an array to store the gap sequence
        gap[0] = 1; // Set the first element as 1 
        for(int i = 1; i < gap.length; i++){ // Loop through the gap sequence array
            gap[i] = gap[i - 1] * 3 + 1; // Set the nth term as 3 * (n-1)th term + 1
        }
        for(int x = gap.length - 1; x >= 0; x--){
            for(int i = gap[x]; i < arr.length; i++){ // Loop through from the gap length to the length of the array
                int temp = arr[i], j; // Set the temp variable as the ith element in the array
                for(j = i; j >= gap[x] && arr[j - gap[x]] > temp; j -= gap[x]){ // Loop through all the element to swap
                    arr[j] = arr[j - gap[x]]; // Insert the jth element at the j - gap index
                }
                arr[j] = temp; // Insert temp at the jth element where the other element was swapped
            }
        }
    }
```

## Number of Comparisons to Array Size Chart
The chart below shows the increase in number of comparisons as the number of items in the array approaches infinity.
![alt text](https://i.imgur.com/SNfzwpU.png)
