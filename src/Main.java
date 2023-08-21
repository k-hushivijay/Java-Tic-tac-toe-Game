import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private int currentPlayer = 1;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        initializeButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().isEmpty()) {
                if (currentPlayer == 1) {
                    buttons[row][col].setText("X");
                } else {
                    buttons[row][col].setText("O");
                }

                if (checkForWin(row, col)) {
                    JOptionPane.showMessageDialog(null, "Player " + (currentPlayer == 1 ? "X" : "O") + " wins!");
                    resetGame();
                } else {
                    currentPlayer = 3 - currentPlayer; // Switch player
                }
            }
        }
    }

    private boolean checkForWin(int row, int col) {
        // Check for a win by examining rows, columns, and diagonals
        if (checkLine(row, 0, row, 1, row, 2) ||
                checkLine(0, col, 1, col, 2, col) ||
                checkLine(0, 0, 1, 1, 2, 2) ||
                checkLine(0, 2, 1, 1, 2, 0)) {
            return true;
        }
        return false;
    }

    private boolean checkLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        String symbol1 = buttons[r1][c1].getText();
        String symbol2 = buttons[r2][c2].getText();
        String symbol3 = buttons[r3][c3].getText();
        return !symbol1.isEmpty() && symbol1.equals(symbol2) && symbol2.equals(symbol3);
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame());
    }
}
