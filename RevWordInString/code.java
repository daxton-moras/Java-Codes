class revWordsInStr {
    public String reverseWords(String s) {
        // Step 1: Remove leading and trailing spaces from the input string.
        String trimmedString = s.trim();

        // Step 2: Split the trimmed string into an array of words.
        // "\\s+" regex matches one or more whitespace characters, effectively
        // handling multiple spaces between words and producing clean words.
        String[] words = trimmedString.split("\\s+");

        // Step 3: Use StringBuilder for efficient concatenation of the reversed words.
        StringBuilder reversedString = new StringBuilder();

        // Step 4: Iterate through the words array from end to beginning
        // to reverse their order.
        for (int i = words.length - 1; i >= 0; i--) {
            reversedString.append(words[i]); // Append the current word

            // Append a single space after the word, unless it's the last word
            // being appended (which will be the first word in the original string).
            if (i > 0) {
                reversedString.append(" ");
            }
        }

        // Step 5: Convert the StringBuilder content to a String and return.
        return reversedString.toString();
    }
}
