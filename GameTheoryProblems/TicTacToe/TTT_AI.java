import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeSwing extends JFrame {
    private final char[][] board = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };
    private final JButton[][] buttons = new JButton[3][3];
    private boolean playerTurn = true;

    public TicTacToeSwing() {
        setTitle("Tic Tac Toe");
        setSize(600, 600);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard() {
        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                int row = Integer.parseInt(button.getName().split(",")[0]);
                int col = Integer.parseInt(button.getName().split(",")[1]);

                if (button.getText().equals("") && playerTurn) {
                    button.setText("X");
                    board[row][col] = 'X';
                    playerTurn = false;

                    if (!isGameFinished()) { //user won, ai won, boardFull
                        aiMove();
                        playerTurn = true;
                    }
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.setName(i + "," + j);
                button.setFont(new Font("Arial", Font.PLAIN, 60));
                button.addActionListener(buttonListener);
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    private void aiMove() {
        int[] move = minimax(2, 'O');
        int row = move[0];
        int col = move[1];
        board[row][col] = 'O';
        buttons[row][col].setText("O");
        if (isGameFinished()) {
            JOptionPane.showMessageDialog(this, "AI wins!");
        }
    }

    private int[] minimax(int depth, char player) {
        int[] bestMove = new int[]{-1, -1, player == 'O' ? Integer.MIN_VALUE : Integer.MAX_VALUE};

        if (hasContestantWon('O')) {
            return new int[]{-1, -1, 1};
        }
        if (hasContestantWon('X')) {
            return new int[]{-1, -1, -1};
        }
        if (isBoardFull()) {
            return new int[]{-1, -1, 0};
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = player;
                    int score = minimax(depth - 1, player == 'O' ? 'X' : 'O')[2];
                    board[i][j] = '_';
                    if (player == 'O') {
                        if (score > bestMove[2]) {
                            bestMove[0] = i;
                            bestMove[1] = j;
                            bestMove[2] = score;
                        }
                    } else {
                        if (score < bestMove[2]) {
                            bestMove[0] = i;
                            bestMove[1] = j;
                            bestMove[2] = score;
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    private boolean hasContestantWon(char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGameFinished() {
        if (hasContestantWon('X')) {
            JOptionPane.showMessageDialog(this, "Player wins!");
            return true;
        }
        if (hasContestantWon('O')) {
            JOptionPane.showMessageDialog(this, "AI wins!");
            return true;
        }
        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeSwing::new);
    }
}
