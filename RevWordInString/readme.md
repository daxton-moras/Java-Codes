# Problem Description
Given an input string s, reverse the order of the words.

### Constraints:

A word is defined as a sequence of non-space characters.
Words in s will be separated by at least one space.
The input string s may contain leading or trailing spaces.
Multiple spaces between two words should be reduced to a single space in the reversed string.
```
Example:
Input: s = "the sky is blue"
Output: "blue is sky the"

Input: s = "  hello world  "
Output: "world hello"
```

# Solution Approach
The provided reverseWords method efficiently solves this problem by leveraging Java's built-in string manipulation functionalities and a StringBuilder for efficient string construction.

Algorithm Steps:

### Trim Leading/Trailing Spaces:
The s.trim() method is used to remove any whitespace characters from the beginning and end of the input string s. This ensures that the final reversed string does not start or end with unnecessary spaces.

### Split String into Words:
trimmedString.split("\\s+") is used to split the trimmed string into an array of words.
\\s+ is a regular expression that matches one or more whitespace characters. This is crucial for handling cases with multiple spaces between words, treating them as a single delimiter and preventing empty strings in the words array.

### Reverse and Reconstruct Words:

A StringBuilder named reversedString is initialized. StringBuilder is preferred over direct String concatenation in a loop because String concatenation creates a new String object in memory with each operation, which is inefficient. StringBuilder allows for mutable string operations, making it much faster for building strings iteratively.
The code iterates through the words array from the last word (words.length - 1) down to the first word (0).

In each iteration:
The current word (words[i]) is appended to reversedString.
If it's not the very first word being appended (i.e., i > 0), a single space is appended after the word. This ensures that words are separated by exactly one space in the final output.

### Convert to String:

Finally, reversedString.toString() is called to convert the StringBuilder content back to an immutable String object, which is then returned.

# Example Walkthrough
Let's trace the execution with an example: s = "  hello   world  "
1. trimmedString = s.trim();
- trimmedString becomes "hello   world".
  
2. String[] words = trimmedString.split("\\s+");
- trimmedString.split("\\s+") will split by one or more spaces.
- words array will be: ["hello", "world"]. (Notice how \\s+ handles the multiple spaces between "hello" and "world" correctly).

3. StringBuilder reversedString = new StringBuilder();
- reversedString is empty.

4. for (int i = words.length - 1; i >= 0; i--) loop:
- Iteration 1: i = 1 (last word "world")
  - reversedString.append(words[1]); -> reversedString is "world".
  - if (i > 0) is true (1 > 0).
  - reversedString.append(" "); -> reversedString is "world ".
- Iteration 2: i = 0 (first word "hello")
  - reversedString.append(words[0]); -> reversedString is "world hello".
  - if (i > 0) is false (0 > 0 is false). So, no space is appended.

5. return reversedString.toString();
- The method returns "world hello".

# Complexity Analysis
### Time Complexity:

- s.trim(): In Java, trim() can take up to O(L) time, where L is the length of the string, as it might need to iterate through the string to find non-whitespace characters.
- trimmedString.split("\\s+"): The split method with a regular expression involves scanning the string and creating new string objects. In the worst case, if every character is a space, it could still iterate through the string once, making it O(L). The number of words can also impact this, but it's generally linear with the string length.
- StringBuilder loop: The loop iterates W times, where W is the number of words. Inside the loop, append operations are, on average, amortized O(word_length). In total, appending all words and spaces will take O(L) time, where L is the total length of the original string (sum of word lengths plus spaces).
- reversedString.toString(): Converting a StringBuilder to a String also takes O(L) time, as it creates a new String object and copies the characters.
- Overall Time Complexity: O(L), where L is the length of the input string. This is highly efficient as it's a single pass (or a few logical passes) over the input data.

### Space Complexity:

- trimmedString: Creates a new string, potentially O(L) space.
- words array: Stores references to substrings (words). In the worst case (e.g., "a b c d"), it could store W words, each of some average length. The total space for the characters in the words array is O(L).
- reversedString (StringBuilder): In the worst case, the StringBuilder will grow to hold the entire reversed string, taking O(L) space.
- Overall Space Complexity: O(L), where L is the length of the input string. This is necessary to store the processed words and the final result.
