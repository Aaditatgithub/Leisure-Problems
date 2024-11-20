#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

// Function to convert a word to its numeric value based on the current letter-to-digit mapping
int wordToNumber(const string& word, const vector<int>& mapping) {
    int number = 0;
    for (char ch : word) {
        number = number * 10 + mapping[ch - 'A'];
    }
    return number;
}

// Function to solve the cryptarithm puzzle
void solveCryptarithm() {
    // Words involved in the equation: AKITA + KYOTO = SENDAI
    string word1 = "AKITA";
    string word2 = "KYOTO";
    string result = "SENDAI";

    // Unique letters in the equation
    string letters = "AKITOSENDY";

    // Ensure there are at most 10 unique letters (fits digits 0-9)
    if (letters.size() > 10) {
        cout << "Too many unique letters to solve!" << endl;
        return;
    }

    // Create a digit sequence (0 to 9)
    vector<int> digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    bool foundSolution = false;

    // Generate all permutations of the digits
    do {
        // Map the current permutation of digits to the letters
        vector<int> mapping(26, -1);
        for (size_t i = 0; i < letters.size(); i++) {
            mapping[letters[i] - 'A'] = digits[i];
        }

        // Convert words to numbers using the current mapping
        int number1 = wordToNumber(word1, mapping);
        int number2 = wordToNumber(word2, mapping);
        int numberResult = wordToNumber(result, mapping);

        // Check if the equation holds
        if (number1 + number2 == numberResult) {
            // Print the solution
            foundSolution = true;
            cout << "Solution found:" << endl;
            for (size_t i = 0; i < letters.size(); i++) {
                cout << letters[i] << " = " << digits[i] << endl;
            }
            cout << word1 << " + " << word2 << " = " << result << endl;
            cout << number1 << " + " << number2 << " = " << numberResult << endl;
            cout << "----------------------" << endl;
        }
    } while (next_permutation(digits.begin(), digits.begin() + letters.size()));

    if (!foundSolution) {
        cout << "No solution found!" << endl;
    }
}

int main() {
    solveCryptarithm();
    return 0;
}
