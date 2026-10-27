
package gamechess;

public abstract class Piece {
    private int row_1;
    private int column_1;
    private String pieceColor;
    private char piece;
    
    abstract boolean isMoveValid(int row_2, int column_2, Board board);
    abstract String getType();
    
    public Piece(int row_1 , int column_1 , String pieceColor , char piece){
         this.row_1=row_1;
         this.column_1=column_1;
         this.pieceColor=pieceColor;
         this.piece=piece;
    }
    
    public String getPieceColor(){
        return pieceColor;
    }
    public char getPiece(){
        return piece;
    }
    public int getRow_1(){
        return row_1;
    }
    public int getColumn_1(){
        return column_1;
    }
    public void setRow_1(int row_1){
        this.row_1=row_1;
    }
    public void setColumn_1(int column_1){
        this.column_1=column_1;
    }
    public void setPiece(char piece){
        this.piece=piece;
    }
    public String getPieceEmoji() {
        switch(pieceColor) {
            case "white":
                switch(getType()) {
                    case "king":   return "♔";
                    case "queen":  return "♕";
                    case "rook":   return "♖";
                    case "bishop": return "♗";
                    case "knight": return "♘";
                    case "pawn":   return "♙";
                }
            case "black":
                switch(getType()) {
                    case "king":   return "♚";
                    case "queen":  return "♛";
                    case "rook":   return "♜";
                    case "bishop": return "♝";
                    case "knight": return "♞";
                    case "pawn":   return "♟";
                }
        }
        return "";
    }
}
