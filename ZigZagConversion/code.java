class zigZagConv {
    public String convert(String s, int numRows) {
        // Handle edge cases: single row or empty string
        if (numRows == 1 || s.isEmpty()) {
            return s;
        }

        // Initialize an array of Strings, where each String represents a row
        String[] rows = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = ""; // Initialize each row as an empty string
        }

        int rowIndex = 0; // Current row index
        int directionNext = 1; // 1 for moving down, -1 for moving up

        // Iterate through each character of the input string
        for (char c : s.toCharArray()) {
            // Append the current character to the string at the current row
            rows[rowIndex] += c; // Note: String concatenation can be inefficient here.
                                 // Using StringBuilder for each row would be more optimal.

            // Determine the direction for the next character
            if (rowIndex == 0) {
                // If at the top row, switch to moving downwards
                directionNext = 1;
            } else if (rowIndex == numRows - 1) {
                // If at the bottom row, switch to moving upwards
                directionNext = -1;
            }

            // Move to the next row
            rowIndex += directionNext;
        }

        // Concatenate all rows into a single result string using StringBuilder
        StringBuilder result = new StringBuilder();
        for (String row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
