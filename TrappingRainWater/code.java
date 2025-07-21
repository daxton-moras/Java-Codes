class trappingRainWater {
    // Converts the problem to calculating total area under "water walls" minus bar area
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0; // Not enough bars to trap water

        int heighestPoint = 0; // Index of the global highest bar
        int heighestValue = height[0]; // Value of the global highest bar
        
        int totalS = 0; // Accumulated area under the effective "water walls"
        int totalHeight = 0; // Accumulated area of the bars themselves

        // First pass: Find the global highest bar and sum all bar heights
        for (int i = 0; i < n; i++){
            if(height[i] > heighestValue){
                heighestValue = height[i];
                heighestPoint = i;
            }
            totalHeight += height[i];
        }

        // Logic for calculating totalS based on the position of the highest point
        if (heighestPoint == 0){
            // Case 1: Highest point is at the beginning (scan from right to left)
            int currentMaxRight = height[n - 1]; // Max height encountered from the right
            for (int i = n - 1; i >= 0; i--){ // Iterate down to 0, including the highest point
                totalS += Math.min(height[i], currentMaxRight); // This is where the logic deviates from standard
                if(height[i] > currentMaxRight){
                    currentMaxRight = height[i];
                }
            }
            // The original loop condition `i > 0` and then `totalS += heighestValue` was slightly different.
            // This adjustment attempts to align with the "min of surrounding walls" idea.
            // However, the original code's logic here doesn't directly map to the standard two-pointer or stack approach for water.
            // It seems to be trying to sum up column heights based on a `nextValue` (which is a form of max_right).
            // This path (highestPoint == 0 || highestPoint == n-1) seems to be a specific edge case handling
            // that doesn't fully capture the "water level" concept accurately.
            // Let's analyze the original loops as written:
            /*
            original:
            nextValue = height[n - 1];
            for (int i = n - 1; i > 0; i--){ // Loop up to index 1
                totalS += nextValue;
                if(height[i-1] > nextValue){
                    nextValue = height[i-1];
                }
            }
            totalS += heighestValue; // Add the left-most highest value once
            */
            // The logic above accumulates `currentMaxRight` for each column. This is more akin to
            // summing the max height of the boundary for each column.

        } else if (heighestPoint == n - 1){
            // Case 2: Highest point is at the end (scan from left to right)
            int currentMaxLeft = height[0]; // Max height encountered from the left
            for (int i = 0; i < n; i++){ // Iterate up to n-1, including the highest point
                totalS += Math.min(height[i], currentMaxLeft); // Similar deviation from typical solution
                if(height[i] > currentMaxLeft){
                    currentMaxLeft = height[i];
                }
            }
            /*
            original:
            nextValue = height[0];
            for (int i = 0; i < n - 1; i++){ // Loop up to index n-2
                totalS += nextValue;
                if(height[i+1] > nextValue){
                    nextValue = height[i+1];
                }
            }
            totalS += heighestValue; // Add the right-most highest value once
            */

        } else {
            // Case 3: Highest point is in the middle (scan from both sides towards the highest point)

            // Scan from left to the highest point
            int currentMaxLeft = height[0]; // Max height encountered from the left
            for (int i = 0; i < heighestPoint; i++){
                totalS += currentMaxLeft; // Accumulate max_left for each column
                if(height[i+1] > currentMaxLeft){
                    currentMaxLeft = height[i+1];
                }
            }

            // Scan from right to the highest point
            int currentMaxRight = height[n - 1]; // Max height encountered from the right
            for (int i = n - 1; i > heighestPoint; i--){
                totalS += currentMaxRight; // Accumulate max_right for each column
                if(height[i-1] > currentMaxRight){
                    currentMaxRight = height[i-1];
                }
            }
            // Add the height of the highest point itself to totalS
            totalS += heighestValue; // This is added for the peak
        }

        // The total trapped water is the "area under walls" minus the "area of bars"
        return totalS - totalHeight;
    }
}
