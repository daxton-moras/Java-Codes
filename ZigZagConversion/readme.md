# Problem Description
The string s is written in a zigzag pattern on a given number of numRows like this: (you may want to display this pattern in a fixed font for better visualization)
```
P   A   H   N
A P L S I I G
Y   I   R
```
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows.

Example:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

# Solution Approach
The provided convert method simulates the zigzag pattern by distributing characters into an array of strings (representing rows). It then concatenates these rows to form the final zigzag-converted string.

### Intuition
The key insight is to realize that characters traverse down to the last row, then reverse direction and go up to the first row, and then repeat this cycle. We can maintain a rowIndex to track the current row and a directionNext variable to manage the up/down movement.

### Algorithm Steps

Handle Edge Cases:

- If numRows is 1 (single row) or the input string s is empty, no zigzag conversion is needed, so the original string s is returned directly.

Initialize Rows:

- An array of String objects named rows is created with a size equal to numRows.
- Each element in the rows array is initialized as an empty string (""). This is important because we will be appending characters to these strings.

Simulate Zigzag Traversal:

- rowIndex: An integer variable initialized to 0 to keep track of the current row where the character will be appended.
- directionNext: An integer variable initialized to 1 (representing moving downwards). This variable controls the change in rowIndex.
- The code iterates through each character c in the input string s (converted to a char array for easier iteration).
  - For each character c:
    - rows[rowIndex] += c;: The character c is appended to the string at the current rowIndex.
  - Change Direction Logic:
    - If rowIndex is 0 (top row), it means we just completed an upward stroke (or started a new cycle) and should now move downwards. So, directionNext is set to 1.
    - If rowIndex is numRows - 1 (bottom row), it means we just completed a downward stroke and should now move upwards. So, directionNext is set to -1.
    - rowIndex += directionNext;: The rowIndex is updated based on the current directionNext. This moves the next character to the appropriate row.

Concatenate Rows:

- A StringBuilder named result is initialized. Using StringBuilder for concatenation is crucial for performance, as direct String concatenation in a loop creates many intermediate String objects.
- The code iterates through each String in the rows array.
- Each row string is appended to the result StringBuilder.

Return Result:

- Finally, result.toString() is called to convert the StringBuilder content into an immutable String object, which is then returned.


# Complexity Analysis

### Time Complexity:

- Initialization of rows array: numRows operations to create empty strings. This is O(numRows).
- Character Traversal Loop: The loop iterates L times, where L is the length of the input string s (for s.toCharArray()).
  -  Inside the loop, rows[rowIndex] += c; involves String concatenation. In Java, String objects are immutable. Each += operation on a String actually creates a new String object. If k characters have already been appended to a row, appending the next character takes O(k) time to copy the existing k characters and the new one.
  -  In the worst case, if numRows is large (e.g., numRows = L), characters might be added to distinct rows, and this overhead is minimal. However, if numRows is small (e.g., numRows = 2), most characters will be appended to one of two rows, leading to repeated string copying.
  -  A more precise analysis of String concatenation is that for a total of L characters being appended across all numRows, the total cost would be proportional to L * (L / numRows) in the worst case (if all characters end up on a single row) or more generally, the sum of squares of lengths of each row. This approaches O(L^2 / numRows) in the worst case, or simply O(L^2) if numRows is constant.
- Concatenating result with StringBuilder: This loop iterates numRows times, and each append operation on StringBuilder is amortized O(length of appended string). The total length of all appended strings is L. So, this step is O(L).
- Overall Time Complexity: Due to the String concatenation in the main loop, the time complexity is not optimal. It's approximately O(L * (L / numRows)) or more simply O(L^2) in scenarios where many characters are appended to the same few strings.
   - To achieve optimal O(L) time complexity, rows should be an array of StringBuilder objects instead of String objects. This is a significant optimization. If rows were StringBuilder[], appending would be amortized O(1), making the loop O(L).

### Space Complexity:

- rows array: Stores numRows string objects. In the worst case, these strings will collectively store all L characters of the input string. This takes O(L) space.
- result StringBuilder: Stores the final concatenated string, taking O(L) space.
- s.toCharArray(): Creates a new char array, taking O(L) space.
- Overall Space Complexity: O(L), where L is the length of the input string. This is generally optimal as we need to store the output string, which can be of length L.
