#include<iostream>
#include<vector>
#include<cstdlib>
using namespace std;

void printBoard(vector<vector<char>>& board){
    for(vector<char>& row : board){
            for(char &ch : row){
                cout << ch << " ";
            }
            cout << endl;
    }
}

bool isBoardFull(vector<vector<char>>& board){
    for(vector<char>& row : board){
        for(char &ch : row){
            if(ch == '_')
                return false;
        }
    }
    return true;
}

bool checkWin(vector<vector<char>>& board, const char &player){
    // horizontal wins and vertical wins
    for(int i = 0; i < 3; i++){
        if(((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player)) || 
            ((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player))){
                return true;
        }
    }

    // diagonal wins
    if((board[0][0] == player && board[1][1] == player && board[2][2] == player) || 
       (board[0][2] == player && board[1][1] == player && board[2][0] == player)){
        return true;
    }

    return false;
}


void playerMove(vector<vector<char>>& board){
    int x = 0, y = 0;
    cout << "Enter the position you want to play: ";
    cin >> x >> y;
    do{
        if(board[x][y] == '_' && (x < 3 && x > -1) && (y > -1 && y < 3) ){
            board[x][y] = 'X';
        }else{
            cout << "Enter valid move: ";
            cin >> x >> y;
        }
    }while(board[x][y] == '_');
}

void computerMove(vector<vector<char>>& board, char& player){
    //check winning move
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            if(board[i][j] == '_'){
                board[i][j] = 'O';
                if(checkWin(board, 'O')){
                    board[i][j] = 'O';
                    return;
                }
                board[i][j] = '_';
            }
        }
    }

    //play random move
    int x = rand() % 3;
    int y = rand() % 3;

    if(board[x][y] == '_')
        board[x][y] = 'O';
    else{
        while(board[x][y] != '_'){
            x = rand() % 3;
            y = rand() % 3;
        }
        board[x][y] = 'O';
    }
    
}

void playGame(vector<vector<char>>& board){
    char player = 'X';

    while(true){
        if(player == 'X'){
            cout << "User plays" << endl;
            playerMove(board);
        }else{
            cout << "Computer plays" << endl;
            computerMove(board, player);
        }
        printBoard(board);

        if(checkWin(board, player)){
            cout << player << " wins." << endl;   
            exit(0);
        }else if(isBoardFull(board)){
            cout << "It's a draw." << endl;
            exit(0);
        }
        else{
            if(player == 'X')
                player = 'O';
            else player = 'X';
        }
    }
}

int main(){
    vector<vector<char>> board = vector<vector<char>>(3, vector<char>(3,'_'));
    playGame(board);
}