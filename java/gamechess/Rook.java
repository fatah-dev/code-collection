
package gamechess;

public class Rook extends Piece{
    
    public Rook(int row_1 , int column_1 , String pieceColor){
        super(row_1,column_1,pieceColor,pieceColor.equals("white")? '♖':'♜');
    }
    
    public int maxRow(int row_1,int row_2){
        int a=0;
        if(row_1>row_2){
            a=row_1;
        }else if(row_1<=row_2){
            a=row_2;
        }
        return a;
    }
    public int maxColumn(int column_1,int column_2){
        int a=0;
        if(column_1>column_2){
            a=column_1;
        }else if(column_1<=column_2){
            a=column_2;
        }
        return a;
    }
    public int minRow(int row_1,int row_2){
        int a=0;
        if(row_1>row_2){
            a=row_2;
        }else if(row_1<=row_2){
            a=row_1;
        }
        return a;
    }
    public int minColumn(int column_1,int column_2){
        int a=0;
        if(column_1>column_2){
            a=column_2;
        }else if(column_1<=column_2){
            a=column_1;
        }
        return a;
    }
    
    public boolean isRookPathClear(int row_1,int column_1, int row_2, int column_2 , Board board){
        int max_row=maxRow(row_1,row_2);
        int max_column=maxColumn(column_1,column_2);
        boolean a=true;
        if (column_1==column_2){
            for(int i=minRow(row_1,row_2)+1;i<max_row;i++){
            if(!board.isEmpty(i, column_2)){
                a=false;
                break;
            }
        }
        }
        if (row_1==row_2){
            for(int i=minColumn(column_1,column_2)+1;i<max_column;i++){
            if(!board.isEmpty(row_2, i)){
                a=false;
                break;
            }
        }
    }
        return a;
    }
    @Override
    public boolean isMoveValid(int row_2,int column_2,Board board){
        if((column_2 == this.getColumn_1() || row_2==this.getRow_1())
                && isRookPathClear(this.getRow_1(),this.getColumn_1(), row_2, column_2,board)){
            return true;
        }
        return false;
    }
    
    @Override 
    public String getType(){
        return "rook";
    }
}
