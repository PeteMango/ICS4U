# Sorting Algorithm Summary note

**Big O Notation:** In computer science, big O notation is used to classify algorithms according to how their time and space complexity increases as the size of the input increases. F

**Time Complexity of Sorting Algorithms:**
*B/C* (Best Case), *A/C* (Average Case), *W/C* (Worst Case) <br/>
$`O(N)`$: Insertion Sort (B/C), Improved Bubble Sort (B/C) <br/>
$`O(N^2)`$: Quick Sort (W/C), Insertion Sort (W/C & A/C), Selection Sort (B/C & W/C & A/C), Shell Sort (W/C) <br/>
$`O(N Log N)`$: Quick Sort (B/C & A/C), Merge Sort (B/C & A/C & W/C), Shell Sort (B/C) <br/>
$`O(N^{1.25})`$ or $`O(N^\frac{5}{4})`$: Shell Sort (A/C)

**Time Complexity:** <br/>
The analysis of the `time it takes for a program to finish execution` in relation to the number of operations it does. The time complexity of most algorithm increase as the size of input increases, however, for certain algorithms such as a sparsetable, the query does will have a constant $`O(1)`$ time. Time complexity can be shown with the big O notation followed by the time complexity inside of the brackets. For example, the best case time complexity of shell sort is $`O(NLogN)`$.

**Space Complexity:** <br/>
The analysis of the `memory a program occupies in the RAM (Randomly accessed memory) during execution`. Space complexity, like time complexity can be shown with the big O notation followed by the space complexity inside of the brackets. For example, the space complexity of merge sort is $`O(N)`$.

**Stability:** <br/>
A sorting algorithm is said to be stable if `two objects with equal keys appear in the same order in sorted output as they appear in the original array`. Such as the same keys keep their relative order as before being sorted. For example, if the array $`7_1, 7_2, 2, 12, 8`$ appear as $`2, 7_1, 7_2, 8, 12`$ in the sorted array, then the array is stable, but if the final array appears as $`2, 7_2, 7_1, 8, 12`$, then the sorting algorithm is not stable.

**Adapabillity:** <br/>
If the `order of the elements to be sorted affects the time complexity` of the sorting algorithm, the algorithm is said to be adaptable. For example, if the array is already sorted, improved bubble sort will have a time complexity of $`O(N)`$, however if the array is random, improved bubble sort can have a time complexity of up to $`O(N^2)`$

**Recursion:** <br/>
A method that calls itself is said to be recursive. It breaks down the problem into smaller and smaller pieces until the smaller problem can be solved in one step. It is an algorithmic technique where `a method, inorder to accomplish a task, calls itself with some part of the task`.

**Overhead:** <br/>
The `amount of memory and other resources required to set up an algorithm`. For example, inorder to set up merge sort, it requires arrays to temporarily hold the elements, this extra space required is known as the overhead.

**How to Measure Efficiency:** <br/> 
`Time Complexity:` The faster an algorithm runs, the more efficient it is (Doing the same amount of work in less time) <br/>
`Space Complexity:` The less memory it uses, the more efficient it is (Doing the same amount of work using the least amount of resources) <br/>
`Number of comparisons/swaps/insertions:` The less number of comparisons/swaps/insertions, the more efficient the algorithm (Doing less amount of work but accomplish the same task) <br/>
`Recursive or Iterative:` Recursive methods are slower as occupying memory then releasing them will cause delay. <br/>
`Adaptive:` Purpose for a specific task and can make them appealing in certain scenario <br/>
`Stable:` Adptive algorithms is important in databases as when you sort the data, you cannot change the order in which they are entered. <br/>
`Difficulty of implementation:` There will be no need to implement merge sort for an array of 10 elements as it is much easier and faster to implement selection sort.
