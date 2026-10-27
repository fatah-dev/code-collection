
package gamechess;
import java.util.Scanner;
public class GameUI {
    private Board board;
    private GameState gameState;
    private Scanner input;
    
    public GameUI() {
        board = new Board();
        gameState = new GameState(board);
        input = new Scanner(System.in);
    }
    
    public void printBoard() {
        board.printBoard(); 
    }
    
    public int getValidInput(Scanner input) {
    while (true) {
        if (input.hasNextInt()) {
            int value = input.nextInt();
            if (value >= 0 && value <= 7) {
                return value;
            } else {
                System.out.println("Number must be between 0 and 7. Try again:");
            }
        } else {
            System.out.println("Please enter a number, not text. Try again:");
            input.next();
        }
    }
}
    
    public void runGame( ){

        board.setupPieces();
        printBoard();
        
        
        
        while(true){
            System.out.println(gameState.getCurrentColor() + "'s turn.");
            System.out.println("Enter position:(row_1 , column_1 ,row_2 ,column_2)");
            int row_1 = getValidInput(input);
            int column_1 = getValidInput(input);
            int row_2 = getValidInput(input);
            int column_2 = getValidInput(input);
            String currentColor = gameState.getCurrentColor();
            String opponentColor = gameState.getOpponentColor();


             
            if (board.getPieceColor(row_1,column_1).equals( currentColor) && gameState.movePieces(row_1,column_1,row_2,column_2)==null){
                printBoard();
                if (gameState.isCheckMate(opponentColor)){
                    System.out.println("Checkmate! \n" + currentColor + " wins!");
                    break;
                }
                if (gameState.isStaleMate(opponentColor)) {
                    System.out.println("Stalemate!\n Game is draw.");
                    break;
                }
                if (gameState.isInCheck(opponentColor)) {
                    System.out.println(opponentColor + " king is in check!");
                }

                }
            }
            
    }
}