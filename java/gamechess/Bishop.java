
package gamechess;

public class Bishop extends Piece {
    
    public Bishop(int row_1 , int column_1 , String pieceColor){
        super(row_1,column_1,pieceColor,pieceColor.equals("white")? '♗':'♝');
    }
    
    public boolean isBishopPathClear(int row_1,int column_1, int row_2, int column_2,Board board){
        boolean a=true;
        int row_step=0;
        int column_step=0;
        if(row_2 - row_1 >0 && column_2 - column_1 > 0){
            row_step=1;
            column_step=1;
        }else if(row_2 - row_1 <0 && column_2 - column_1 <0){
            row_step=-1;
            column_step=-1;
        }else if(row_2 - row_1 > 0 && column_2 - column_1 <0){
            row_step=1;
            column_step=-1;
        }else if(row_2 - row_1 <0 && column_2 - column_1 >0){
            row_step=-1;
            column_step=1;
        }
        for(int i=1;i<Math.abs(row_2-row_1);i++){
            if(!board.isEmpty(row_1+(i*row_step), column_1+(i*column_step))){
                a=false;
                return a;
            }
        }
        return a;
    }
    
    
    
    @Override
    public boolean isMoveValid(int row_2,int column_2,Board board){
        if(Math.abs(row_2-this.getRow_1())==Math.abs(column_2-this.getColumn_1())
                && isBishopPathClear(this.getRow_1(),this.getColumn_1(),row_2,column_2,board)){
            return true;
        }
        return false;
    }
    
    @Override 
    public String getType(){
        return "bishop";
    }
}
