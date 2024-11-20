#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
using namespace std;

int wordToNumber(const string& word, const vector<int>& mapping){
    int number = 0;   
    for(const char& ch: word){
        number = number*10 + mapping[ch - 'A'];
    }
    return number;
}

int main(){
    
    string word1 = "AKITA";
    string word2 = "KYOTO";
    string result = "SENDAI";

    string letters = "AKITYOSEND";

    // max domain limit
    if(letters.size() > 10){
        cout << "words are greater than domain limit." << endl;
    }

    vector<int> digits = {0,1,2,3,4,5,6,7,8,9};
    bool solutionFound = false;

    do{
        vector<int> mapping(26,-1);
        for(size_t i = 0; i < letters.size(); i++){
            mapping[letters[i] - 'A'] = digits[i];
        }

        int number1 = wordToNumber(word1,mapping);
        int number2 = wordToNumber(word2,mapping);
        int res = wordToNumber(result,mapping);

        if(number1 + number2 == res){
            solutionFound = true;
            cout << "Solution found:" << endl;
            for (size_t i = 0; i < letters.size(); i++) {
                cout << letters[i] << " = " << digits[i] << endl;
            }
            cout << word1 << " + " << word2 << " = " << result << endl;
            cout << number1 << " + " << number2 << " = " << res << endl;
            cout << "----------------------" << endl;
        }

    }while(next_permutation(digits.begin(), digits.begin() + letters.size()));
}