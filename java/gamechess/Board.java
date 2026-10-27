
package gamechess;


public class Board {
    Piece[][] board;  
    
    public Board(){
        board=new Piece[8][8];
        setupPieces();
    }
    
    public boolean isValidCoordinate(int row, int column){
        return row>=0 && row<8 && column>=0 && column<8;
    }

    public String getPieceColor(int row,int column){        
        if(board[row][column]==null){
            return "none";
        }
        return board [row][column].getPieceColor();
        }
    
    public Piece getPiece(int row, int column){
        return board[row][column];
    }
    
    public void setPiece(int row, int col, Piece piece) {
    board[row][col] = piece;
    if (piece != null) {
        piece.setRow_1(row);
        piece.setColumn_1(col);
    }
}
    
    public boolean isEmpty(int row, int column){
        if(board[row][column]==null){
            return true;
        }
        return false;
    }
    
    public void printBoard() {
    System.out.print("   ");
    for (int j = 0; j < 8; j++) {
        System.out.print(" " + j + "  ");
    }
    System.out.println();
    
    for (int i = 0; i < 8; i++) {
        System.out.print(" " + i + " ");
        for (int j = 0; j < 8; j++) {
            if (board[i][j] == null) {
                System.out.print(" . |");
            } else {
                System.out.print(board[i][j].getPiece() + " | ");
            }
        }
        System.out.println();
    }
}
    public  void setupPieces(){
        Class<?>[] blackPiece={Rook.class,Knight.class, Bishop.class,Queen.class,King.class,Bishop.class,Knight.class,Rook.class};
        Class<?>[] whitePiece={Rook.class,Knight.class, Bishop.class,Queen.class,King.class,Bishop.class,Knight.class,Rook.class};
        for(int i=0;i<8;i++){
            try{
                board[0][i]=(Piece)blackPiece[i].getConstructor(int.class,int.class,String.class).newInstance(0,i,"black");
            
            board[1][i]=new Pawn(1,i,"black");
            
            board[7][i]=(Piece)whitePiece[i].getConstructor(int.class,int.class,String.class).newInstance(7,i,"white");
            
            board[6][i]=new Pawn(6,i,"white");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
     public void movePiece(int row_1,int column_1, int row_2 , int column_2){
        board[row_2][column_2]=board[row_1][column_1];
        board[row_2][column_2].setRow_1(row_2);
        board[row_2][column_2].setColumn_1(column_2);
        board[row_1][column_1]=null;
    }
}
