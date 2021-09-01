# Quicksort 
## What is Quicksort
Divide and conquer algorith. it picks an element as pivot and partitions the given array around the picked pivot. There are many different versions of quickSort that pick pivot in different ways.
1. Always pick first element as pivot
2. Always pick last element as pivot
3. Pick a random element as pivot
4. Pick middle elemnt as pivot

The key process in QuickSort is partition. Target of partitions is, given an array and an element x of array as pivot, put x at its correct position in sorted array and put all smaller elements (smaller than x) before x, and put all greater elements (greater than x) after x. All this should be done in linear time.

## Partition
1. Choosing a pivot: Picknig a random value, or pick a median form the array
2. Partition. Rearranging elements in an array where, all elements less than that of the pivot is mvmoved to the left. And elements greater than the pivot are moved to the right.
3. Repeat (1) and (2) until the array is sorted

##### Partition Algorithm
1. Variables "i" and "j" are declared as the first and lat index of the array
2. A pivot is chosen, either randomly, as the first element, as the last element or as the median
3. The method starts by comparing "i" to the pivot, if "i" is less than the pivot, increase "i" by 1, if "j" is bigger than the pivot, decrease the value of "j" by 1. If the conditions above are not met, "i" or 'j' will remain there until they are swapped.
4. If the following conditions are met, i <= pivot <= j, swap their corresponding elements. increase the "i" by 1 and decrease "j" by 1
5. The method stops when "i" is greater than or equal to "j"

## Partition Code
```java
static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high], i = (low-1); // Set the pivot as the arr[high] and i equal to low - 1
        for (int j = low; j < high; j++) { // Loop from low to high
            if (arr[j] < pivot) // If the jth element is smaller than the pivot element
            {
                i++; // Add 1 to i
                int temp = arr[i]; // Swap
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1]; // Swap
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
```    

## Quicksort Code
```java
static void quickSort(int arr[], int low, int high){
        if (low < high) // If high > low, break
        {
            int pi = partition(arr, low, high); // Partition and finds the pivot
            quickSort(arr, low, pi-1); // Quick sort first part
            quickSort(arr, pi+1, high); // Quick sort second part
        }
}
```

## Code Labeling
```java
public static void QuickSort(int list[], int left, int right){ // A
        int index = partition(list, left, right); // B
        
        if(left < index - 1) {
            QuickSort(list, left, index - 1); // D
        }
        if(index < right){
            QuickSort(list, index, right); // D
        }
    }
``` 
**Method Signature:** *A* <br/>
**A method returns an integer value:** *B* <br/>
**A recursive call:** *C, D* <br/>
**Invoking a method:** *E* 

## When / Why / Where to use Quicksort?
Merge sort is quicksort's only competition, because all the other sorting algorithm sucks. <br/>
**Auxillary Space:** Quicksort requires little space and has good cahce locality, requires less space than merge. Quick sort is an in-place sorting algorithm, meaning additional storage space is not required. Merge sort is not inplace, requires a temporary array to merge sorted arrays. <br/>
**Worst Cases:** Worst case of $`O(N^2)`$ can be avoided by using randomized quicksort. It can be easily avoided with high probability by choosing the last element or middle as pivot. With the average case, it can be just as efficient as merge sort <br/>
**Locality of Reference:** Quicksort's good cache locality makes it faster than merge sort in many cases like a virtual memory environemtn. <br/>
**Not as good for large data structures:** Unlike mergesort, quicksort is unstabe. Merge sort can be more easily adapted to operate on linked list, larger lists, lists sotred on slow-to-access media such as disk, storage, network attached storage.

**Conclusion:** Quicksort is generally more efficient than any other sorting algorithm. Quicksort should be used over merge if there is limited memory, but mergesort should be used over quicksort if there are more elements to be sorted.

## Pros & Cons:
**Pros:** 
- Easy to integrate into program
- Efficient because it is capable of dealing with a large quantity of data
- Extra sotrage isn't needed inorder to perform the sorting (In place sorting method)
- Uses recursion, which makes the program simple and clear

**Cons:**
- Time efficiency heavily relies on the choice of the pivot element
- Pooly chosen pivot can cause runtime errors or long runtimes
- If there happens to be a fialed pivot selection, can cause stack overflow error
- Worst case resembles average performance of bubble which is $`O(N^2)`$

## Summary
- It is very efficient sorting method, that requires a pivot and partition to sort list
- Several options for choosing a pivot (first element, last element, median, random element)
- THere must be a separate method for partition
- Incorporates recursion
- Can handle any lenth of list when sorting (Extra space isn't needed)
- The speed of of the program depends on the pivots

