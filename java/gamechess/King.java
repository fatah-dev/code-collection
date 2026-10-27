
package gamechess;

public class King extends Piece{
    
    public King(int row_1 , int column_1 , String pieceColor){
        super(row_1,column_1,pieceColor,pieceColor.equals("white")? '♔':'♚');
    }
    
     @Override
    public boolean isMoveValid(int row_2,int column_2,Board board){
        if(Math.abs(row_2-this.getRow_1())<=1
                && Math.abs(column_2-this.getColumn_1())<=1){
            return true;
        }
        return false;
    }
    
    @Override 
    public String getType(){
        return "king";
    }
    
}
