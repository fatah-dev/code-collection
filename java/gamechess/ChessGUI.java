package gamechess;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class ChessGUI {
    private Board board;
    private GameState gameState;
    private JButton[][] squares;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private Color lightColor = new Color(240, 217, 181);//کرمی
    private Color darkColor = new Color(181, 136, 99);//قهوره ای
    private Color highlightColor = new Color(152, 251, 152);//سبز
    private JLabel statusLabel;
    
    public ChessGUI() {
        board = new Board();
        gameState = new GameState(board);
        squares = new JButton[8][8];
        
        JFrame frame = new JFrame("Chess Game");
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // وضعیت نوبت
        statusLabel = new JLabel("White's turn", JLabel.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(statusLabel, BorderLayout.NORTH);
        
        // صفحه شطرنج
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        
        Font pieceFont = new Font("SansSerif", Font.BOLD, 32);
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton square = new JButton();
                
                if ((i + j) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }
                
                square.setOpaque(true);
                square.setBorderPainted(false);
                square.setFont(pieceFont);
                
                Piece piece = board.getPiece(i, j);
                if (piece != null) {
                    square.setText(piece.getPieceEmoji());
                }
                
                final int row = i;
                final int col = j;
                square.addActionListener(e -> {
                    handleSquareClick(row, col);
                });
                
                squares[i][j] = square;
                panel.add(square);
            }
        }
        
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            resetGame();
        });
        frame.add(newGameButton, BorderLayout.SOUTH);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private void resetGame() {
    board = new Board();
    gameState = new GameState(board);
    selectedRow = -1;
    selectedCol = -1;
    updateBoard();
    resetHighlight();
    statusLabel.setText("White's turn");
    
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            squares[i][j].setEnabled(true);
        }
    }
}
    
    private void handleSquareClick(int row, int col) {
        if (selectedRow == -1) {
            Piece piece = board.getPiece(row, col);
            if (piece == null) {
                statusLabel.setText("No piece selected. " + gameState.getCurrentColor() + "'s turn.");
                return;
            }
            if (!piece.getPieceColor().equals(gameState.getCurrentColor())) {
                statusLabel.setText("Wrong color! " + gameState.getCurrentColor() + "'s turn.");
                return;
            }
            selectedRow = row;
            selectedCol = col;
            squares[row][col].setBackground(highlightColor);
            statusLabel.setText(gameState.getCurrentColor() + " selected. Choose destination.");
        } else {
            String currentPlayer = gameState.getCurrentColor();
            String opponentPlayer = gameState.getOpponentColor();
            String result = gameState.movePieces(selectedRow, selectedCol, row, col);

            if (result == null) {
                updateBoard();

                if (gameState.isCheckMate(opponentPlayer)) {
                    updateBoard();
                    JOptionPane.showMessageDialog(null, "Checkmate! " + currentPlayer + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    statusLabel.setText("Checkmate! " + currentPlayer + " wins!");
                    disableAllSquares();
                    resetHighlight();
                    return;
                }
                if (gameState.isStaleMate(opponentPlayer)) {
                    updateBoard();
                    JOptionPane.showMessageDialog(null, "Stalemate! Game is draw.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    statusLabel.setText("Stalemate! Game is draw.");
                    disableAllSquares();
                    resetHighlight();
                    return;
                }
                if (gameState.isInCheck(opponentPlayer)) {
                    statusLabel.setText(opponentPlayer + " is in check! " + gameState.getCurrentColor() + "'s turn.");
                } else {
                    statusLabel.setText(gameState.getCurrentColor() + "'s turn.");
                }
            } else {
                statusLabel.setText(result + " Try again.");
            }

            resetHighlight();
            selectedRow = -1;
            selectedCol = -1;
        }

    }
    
    private void disableAllSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setEnabled(false);
            }
        }
    }
    
    private void resetHighlight() {
        if (selectedRow != -1 && selectedCol != -1) {
            if ((selectedRow + selectedCol) % 2 == 0) {
                squares[selectedRow][selectedCol].setBackground(lightColor);
            } else {
                squares[selectedRow][selectedCol].setBackground(darkColor);
            }
        }
    }
    
    private void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece != null) {
                    squares[i][j].setText(piece.getPieceEmoji());
                } else {
                    squares[i][j].setText("");
                }
            }
        }
    }
}