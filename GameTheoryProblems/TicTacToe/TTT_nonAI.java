import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3];
    private boolean firstMove = true;
    private boolean playerTurn = true; // true for human, false for computer
    private Random random = new Random();
    private int firstMoveX, firstMoveY;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
                board[i][j] = ' ';
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        int x, y;

        ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[x][y].getText().equals("") && playerTurn) {
                buttons[x][y].setText("X");
                board[x][y] = 'X';
                playerTurn = false;
                if (!checkWin('X')) {
                    computerMove();
                }
            }
        }
    }

    private void computerMove() {
        if (firstMove) {
            int x, y;
            do {
                x = random.nextInt(2) * 2; // Ensure x is 0 or 2
                y = random.nextInt(2) * 2; // Ensure y is 0 or 2
            } while (board[x][y] != ' ');
            board[x][y] = 'O';
            buttons[x][y].setText("O");
            firstMoveX = x;
            firstMoveY = y;
            firstMove = false;

        } else {
            int[] move = findBestMove();
            if (move != null) {

                board[move[0]][move[1]] = 'O';
                buttons[move[0]][move[1]].setText("O");

            } else {

                int[] validMove = findValidMoveExcludingPattern();
                if (validMove != null) {
                    
                    board[validMove[0]][validMove[1]] = 'O';
                    buttons[validMove[0]][validMove[1]].setText("O");

                } else {
                    int x, y;
                    do {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
                    } while (board[x][y] != ' ');
                    board[x][y] = 'O';
                    buttons[x][y].setText("O");
                }
            }
        }
        playerTurn = true;
        checkWin('O');
    }

    private int[] findValidMoveExcludingPattern() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ' && isValidMovePattern(i, j)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private boolean isValidMovePattern(int x, int y) {
        // Avoid alternate column and next row or next column and alternate row
        if ((Math.abs(x - firstMoveX) == 1 && Math.abs(y - firstMoveY) == 2) || 
            (Math.abs(x - firstMoveX) == 2 && Math.abs(y - firstMoveY) == 1)) {
            return false;
        }
        return true;
    }

    private int[] findBestMove() {
        // Check for possible winning move for the computer
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    if (isWinning(board, 'O')) {
                        board[i][j] = ' ';
                        return new int[]{i, j};
                    }
                    board[i][j] = ' ';
                }
            }
        }
        return null;
    }

    private boolean checkWin(char player) {
        if (isWinning(board, player)) {
            String message = player == 'X' ? "You win!" : "Computer wins!";
            JOptionPane.showMessageDialog(this, message);
            resetBoard();
            return true;
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetBoard();
            return true;
        }
        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                board[i][j] = ' ';
            }
        }
        playerTurn = true;
    }

    private boolean isWinning(char[][] board, char player) {
        // horizontal wins
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        // vertical wins
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // diagonal wins
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
