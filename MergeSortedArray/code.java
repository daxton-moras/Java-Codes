import java.util.Arrays;

class mergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step 1: Merge nums2 into the available space in nums1
        mergeArrays(nums1, m, nums2, n);

        // Calculate the total length of the merged array
        int k = m + n;

        // Step 2: Sort the entire combined array using QuickSort
        quickSort(nums1, 0, k - 1);

        // Step 3: Format and print the result (can be simplified using Arrays.toString)
        String result = "";
        for (int i = 0; i < k; i++) {
            result += nums1[i] + ", ";
        }

        if (result.length() > 0) {
            result = result.substring(0, result.length() - 2);
        }
        System.out.println("[" + result + "]");
    }

    // Helper method to merge nums2 into nums1's empty space
    public static void mergeArrays(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m; i < m + n; i ++) {
            nums1[i] = nums2[i - m];
        }
    }

    // QuickSort implementation
    public static void quickSort(int[] nums, int low, int high) {
        // Debugging prints - typically removed in production code
        System.out.print("start quickSort ");
        for (int i = low; i <= high; i++) {
            if (i == low) { System.out.print("["); }
            System.out.print(nums[i]);
            if (i == high) { System.out.print("]\n"); } else { System.out.print(", "); }
        }

        if (low < high) {
            int pivotIndex = partition(nums, low, high);
            // More debugging prints
            System.out.print("pivot Index: " + pivotIndex + "\n");
            System.out.print("quickSort left side " + "\n");
            quickSort(nums, low, pivotIndex - 1);
            System.out.print("end quickSort left side " + Arrays.toString(nums) + "\n");
            System.out.print("quickSort right side" + "\n");
            quickSort(nums, pivotIndex + 1, high);
            System.out.print("end quickSort right side " + Arrays.toString(nums) + "\n");
        }
    }

    // Partition method for QuickSort
    public static int partition(int[] nums, int low, int high) {
        int pivot = nums[high]; // Last element as pivot
        int i = low - 1; // Index of smaller element

        // Debugging print
        System.out.print("pivot value: " + pivot + "\n");
        for (int j = low; j < high; j++) {
            if (nums[j] <= pivot) {
                i++;
                // Debugging print
                System.out.print("nums[" + j + "] = " + nums[j] + " <= pivot transfer to the left side\n");
                if (i != j) { // Avoid unnecessary self-swaps
                    swap(nums, i, j);
                    System.out.print(Arrays.toString(nums) + "\n"); // Debugging print
                }
            } else {
                System.out.print("nums[" + j + "] = " + nums[j] + " > pivot transfer to the right side\n"); // Debugging print
            }
        }

        // Place the pivot in its correct sorted position
        System.out.print("swap pivot\n"); // Debugging print
        swap(nums, i + 1, high);
        System.out.print("end partition " + Arrays.toString(nums) + "\n"); // Debugging print
        return i + 1; // Return the pivot's final index
    }

    // Helper method to swap two elements
    public static void swap(int[] nums, int i, int j) {
        System.out.print("swap " + "nums[" + i + "] = " + nums[i] + " and " + "nums[" + j + "]" + " = " + nums[j] + "\n"); // Debugging print
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
