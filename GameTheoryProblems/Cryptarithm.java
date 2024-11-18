package GameTheoryProblems;
import java.util.HashSet;

public class Cryptarithm {
    public static void main(String[] args) {
        solveCryptarithm();
    }

    // Function to check if the current letter-to-digit mapping is valid
    // Ensures no two letters share the same digit
    public static boolean isValid(int[] letterToDigit) {
        HashSet<Integer> usedDigits = new HashSet<>();
        for (int digit : letterToDigit) {
            if (digit != -1) {  // If a digit is assigned to a letter
                if (usedDigits.contains(digit)) return false;  // Duplicate digit found
                usedDigits.add(digit);  // Mark this digit as used
            }
        }
        return true;  // All digits are unique
    }

    // Converts a given word into its corresponding numerical value
    // based on the current letter-to-digit mapping
    public static int wordToNumber(String word, int[] letterToDigit) {
        int number = 0;
        for (char ch : word.toCharArray()) {
            number = number * 10 + letterToDigit[ch - 'A'];  // Convert letters to digits
        }
        return number;
    }

    // Main function to attempt solving the cryptarithm puzzle
    public static void solveCryptarithm() {
        // Words involved in the equation: AKITA + KYOTO = SENDAI
        String word1 = "AKITA";
        String word2 = "KYOTO";
        String result = "SENDAI";
        
        // Initialize an array to map letters (A-Z) to digits (-1 means unassigned)
        int[] letterToDigit = new int[26];
        for (int i = 0; i < letterToDigit.length; i++) {
            letterToDigit[i] = -1;  // Mark all letters as unassigned
        }

        // Unique letters involved in the cryptarithm
        String letters = "AKITOSENDY";

        // Initialize an array for digits to be permuted (0-9)
        int[] digits = new int[letters.length()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = i;  // Initial digit sequence (0, 1, 2, ...)
        }
        
        // Start generating permutations of the digits
        permute(digits, 0, letterToDigit, word1, word2, result, letters);
    }

    // Recursive function to generate permutations of digits
    // and attempt to solve the cryptarithm for each permutation
    public static void permute(int[] digits, int index, int[] letterToDigit, String word1, String word2, String result, String letters) {
        // Base case: all digits have been assigned
        if (index == digits.length) {
            // Map each letter to the corresponding digit in the permutation
            for(int i = 0; i < letters.length(); i++) {
                letterToDigit[letters.charAt(i) - 'A'] = digits[i];
            }

            // Check if the current letter-to-digit mapping is valid (unique digits)
            if (isValid(letterToDigit)) {
                // Convert the words into numbers based on the current mapping
                int number1 = wordToNumber(word1, letterToDigit);
                int number2 = wordToNumber(word2, letterToDigit);
                int numberResult = wordToNumber(result, letterToDigit);
                
                // Check if the equation holds (word1 + word2 == result)
                if (number1 + number2 == numberResult) {
                    printSolution(word1, word2, result, letterToDigit, number1, number2, numberResult);
                }
            }
            return;  // Return to try a new permutation
        }
        
        // Recursive case: Generate all permutations of the remaining digits
        for (int i = index; i < digits.length; i++) {
            swap(digits, index, i);  // Swap the digits
            permute(digits, index + 1, letterToDigit, word1, word2, result, letters);  // Recursively permute the next digit
            swap(digits, index, i);  // Backtrack by swapping back
        }
    }

    // Function to print the solution once a valid mapping is found
    public static void printSolution(String word1, String word2, String result, int[] letterToDigit, int number1, int number2, int numberResult) {
        System.out.println("Solution found:");
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (letterToDigit[ch - 'A'] != -1) {  // Print only mapped letters
                System.out.println(ch + " = " + letterToDigit[ch - 'A']);
            }
        }
        // Print the equation in both word form and numeric form
        System.out.println(word1 + " + " + word2 + " = " + result);
        System.out.println(number1 + " + " + number2 + " = " + numberResult);
    }

    // Helper function to swap two elements in an array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
