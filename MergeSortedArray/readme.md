# Merge Sorted Array (Using QuickSort)
This repository contains a Java implementation for merging two sorted arrays into a single sorted array. The approach taken here involves merging the elements first and then applying a QuickSort algorithm to sort the entire combined array.

## Problem Description
The common "Merge Sorted Array" problem typically asks you to merge nums2 into nums1 such that nums1 becomes a single sorted array. nums1 has enough space to hold elements from both nums1 (initial m elements) and nums2 (n elements).

Given:

nums1: An integer array with m initialized elements and n empty spaces at the end.

m: The number of initialized elements in nums1.

nums2: An integer array with n elements.

n: The number of elements in nums2.

The goal is to merge nums2 into nums1 and sort nums1 in non-decreasing order.

## Solution Approach
The provided merge method takes a two-step approach:

Append nums2 to nums1: It first copies the elements of nums2 into the available empty space at the end of nums1.

Sort the combined array: After all elements are in nums1, it uses a QuickSort algorithm to sort the entire nums1 array.

mergeArrays Method
This helper method handles the initial merging. It iterates from index m (the first empty slot in nums1) up to m + n - 1, copying elements from nums2 into nums1.
```
public static void mergeArrays(int[] nums1, int m, int[] nums2, int n) {
    for (int i = m; i < m + n; i ++) {
        nums1[i] = nums2[i - m]; // Copy nums2 elements into the end of nums1
    }
}
```

## Quicksort Method
This is a standard implementation of the QuickSort algorithm. It's a recursive, divide-and-conquer sorting algorithm.

Base Case: If low is greater than or equal to high, the subarray has 0 or 1 element and is already sorted, so the recursion stops.

Partitioning: It calls the partition method to rearrange the subarray such that elements smaller than the pivot are on its left, and elements larger than the pivot are on its right. The partition method returns the final index of the pivot.

Recursive Calls: It then recursively calls quickSort on the subarray to the left of the pivot and the subarray to the right of the pivot.

The quickSort method also includes extensive System.out.print statements for demonstrating its execution flow, which are useful for debugging and understanding but would typically be removed in production code for performance.

## Partition Method
This method is the core of the QuickSort algorithm. It selects the last element as the pivot.

It iterates through the array from low to high - 1.

If an element nums[j] is less than or equal to the pivot, it's swapped with the element at index i + 1 (where i tracks the boundary of elements smaller than the pivot). This ensures elements smaller than the pivot are moved to the left side of the "wall" represented by i.

Finally, the pivot (originally at nums[high]) is swapped with nums[i + 1], placing it in its correct sorted position.

The method returns i + 1, which is the pivot's final index.

Like quickSort, this method also contains numerous System.out.print statements for verbose output.

## Swap Method
A simple utility method to swap two elements in an array.
```
public static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```
## Merge (Main) Method
This is the entry point for merging. It orchestrates the process:

Calls mergeArrays to combine the arrays.

Calls quickSort to sort the combined array.

Formats and prints the final sorted array to the console. The string manipulation for printing is less efficient than Arrays.toString().


## Complexity Analysis
### Time Complexity

> mergeArrays:

This method iterates n times to copy elements. Its time complexity is O(n).

> quickSort (and partition):

Best and Average Case: QuickSort has an average time complexity of O((m+n) log(m+n)). This is because it divides the array roughly in half at each step.

Worst Case: In the worst-case scenario (e.g., if the array is already sorted or reverse-sorted and the pivot selection is always the smallest/largest element), QuickSort can degrade to O((m+n)^2).

> Main merge method:

The mergeArrays step is O(n).

The quickSort step dominates the time complexity, being O((m+n) log(m+n)) on average.

The final printing loop is O(m+n).

Therefore, the overall average time complexity of the merge method is O((m+n) log(m+n)).

### Space Complexity
> mergeArrays:

Uses constant extra space, O(1).

> quickSort (and partition):

QuickSort is an in-place sorting algorithm, meaning it doesn't require a large auxiliary data structure.

However, it uses space on the call stack for recursive calls. In the worst case, the recursion depth can be O(m+n), leading to O(m+n) space complexity for the call stack.

In the average case, the recursion depth is O(log(m+n)), so space complexity is O(log(m+n)).

> Main merge method:

The result string concatenation also consumes space proportional to m+n.

Overall, the dominant factor for space complexity is the QuickSort recursion stack, making the average space complexity O(log(m+n)) and worst-case O(m+n).


