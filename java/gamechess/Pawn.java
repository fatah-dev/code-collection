
package gamechess;

public class Pawn extends Piece {
    public Pawn(int row_1 , int column_1 , String pieceColor){
        super(row_1,column_1,pieceColor,pieceColor.equals("white")? '♙':'♟');
    }
    
    @Override
    public boolean isMoveValid(int row_2,int column_2,Board board){
        if(this.getPieceColor().equals("white")
                && ((row_2==this.getRow_1() - 1 && column_2 == this.getColumn_1()&& board.isEmpty(row_2, column_2))
                ||( row_2 == this.getRow_1() - 2 && column_2 == this.getColumn_1()&& this.getRow_1()==6 && board.isEmpty(row_2, column_2) &&board.isEmpty(this.getRow_1()-1, this.getColumn_1())))){
            return true;
        }
        if(getPieceColor().equals("black") 
                && ((row_2==this.getRow_1() + 1 && column_2 == this.getColumn_1() && board.isEmpty(row_2, column_2))
                ||( row_2 == this.getRow_1() + 2 && column_2 == this.getColumn_1()&& this.getRow_1()==1 && board.isEmpty(row_2, column_2) &&board.isEmpty(this.getRow_1()+1, this.getColumn_1())))){
            return true;
        }
        if(getPieceColor().equals("white") && (column_2==this.getColumn_1()+1 || column_2==this.getColumn_1()-1)&& row_2==this.getRow_1()-1
                && !board.isEmpty(row_2, column_2) 
                && !board.getPieceColor(row_2, column_2).equals(this.getPieceColor())){
            return true;
        }
        if(getPieceColor().equals("black") && (column_2==this.getColumn_1()+1 || column_2==this.getColumn_1()-1)&& row_2==this.getRow_1()+1
                && !board.isEmpty(row_2, column_2) 
                && !board.getPieceColor(row_2, column_2).equals(this.getPieceColor())){
            return true;
        }
        return false;
    }
    
    @Override 
    public String getType(){
        return "pawn";
    }
}
