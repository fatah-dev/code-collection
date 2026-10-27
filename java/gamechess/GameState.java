package gamechess;
 
public class GameState {
    private boolean whiteTurn;
    private Board board;
    
    
    public GameState(Board board){
        whiteTurn=true;
        this.board=board;
    }
    public int[] findKingPosition(String color){
        
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                if(board.getPiece(i,j)!=null
                        &&board.getPiece(i, j).getType().equals("king")                         
                        &&board.getPieceColor(i, j).equals(color)){
                    return new int[]{i,j};
                }
                }
            }
        return null;
    }

    public boolean isSquareUnderAttack(int row ,int column , String attackerColor){
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                Piece piece=board.getPiece(i,j);
                if(piece!=null
                        &&piece.getPieceColor().equals(attackerColor)
                        &&piece.isMoveValid(row, column, board)){
                    return true;
                }
            }
        }
    return false;
    }
    
     public boolean isInCheck(String kingColor){
        int[] kingPosition=findKingPosition(kingColor);
        if(kingPosition==null){
            return false;
        }
        if(kingColor.equals("white")){
            if(isSquareUnderAttack(kingPosition[0] , kingPosition[1] , "black")){
            return true;
            }
        }
        if(kingColor.equals("black")){
            if(isSquareUnderAttack(kingPosition[0] , kingPosition[1] , "white")){
            return true;
        }
        }

        return false;
    }
     
     public boolean isMoveSafe(int row_1 , int column_1 , int row_2 , int column_2){
        String ownPiecesColor=board.getPieceColor(row_1,column_1);
        Piece piece_1=board.getPiece(row_1, column_1);
        Piece piece_2=board.getPiece(row_2, column_2);
        board.movePiece(row_1,column_1,row_2,column_2);
        if(isInCheck(ownPiecesColor)){
            board.movePiece(row_2,column_2,row_1,column_1);
            board.setPiece(row_2,column_2,piece_2);
            return false;
        }
        board.movePiece(row_2,column_2,row_1,column_1);
        board.setPiece(row_2,column_2,piece_2);
        return true;
    }
     
     public String isMoveLegal(int row_1 , int column_1 , int row_2 , int column_2){
         Piece piece_1 = board.getPiece(row_1, column_1);
         Piece piece_2 = board.getPiece(row_2, column_2);
        if(board.isEmpty(row_1, column_1)){
            return "No piece at origin.";
        }
        if (!board.isValidCoordinate(row_1,column_1 )
                || !board.isValidCoordinate(row_2,column_2)){
            return "Position is invalid.";
        }
        if(piece_2!=null
                &&piece_1.getPieceColor().equals(piece_2.getPieceColor())){
            return "You can't capture your own piece.";
        }
        if(!piece_1.isMoveValid(row_2, column_2, board)){
            return "Invalid move for this piece.";
        }
        if(!isMoveSafe(row_1,column_1,row_2,column_2)){
            return "Move puts your king in check.";
        }
        return null;
    }
    
    public boolean getwhiteTurn(){
        return whiteTurn;
    }
    public void setWhiteTurn(boolean whiteTurn){
        this.whiteTurn=whiteTurn;
    }
    
    public void switchTurn() {
        whiteTurn = !whiteTurn;
    }
    
    public String getCurrentColor() {
        if(whiteTurn){
            return "white";
        }
        return "black";
    }
    
    public String getOpponentColor() {
        if(whiteTurn){
            return "black";
        }
        return "white";
    }
    
   public String movePieces(int row_1, int column_1, int row_2, int column_2) {
    String error = isMoveLegal(row_1, column_1, row_2, column_2);
    if(error != null){
        return error;
    }
    board.movePiece(row_1, column_1, row_2, column_2);
    switchTurn();
    return null;
}
     
     public boolean isCheckMate(String kingColor){
        if(!isInCheck(kingColor)){
            return false;
        }
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                Piece piece=board.getPiece(i, j);
                if(piece != null
                        &&piece.getPieceColor().equals(kingColor)){
                    for(int x=0; x<8;x++){
                        for(int y=0; y<8;y++){
                            if(!(i == x && j == y) && isMoveLegal(i,j,x,y)==null){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isStaleMate(String kingColor){
        if(isInCheck(kingColor)){
            return false;
        }
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                Piece piece=board.getPiece(i, j);
                if(piece != null
                        &&piece.getPieceColor().equals(kingColor)){
                    for(int x=0; x<8;x++){
                        for(int y=0; y<8;y++){
                            if(!(i == x && j == y) && isMoveLegal(i,j,x,y)==null){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
