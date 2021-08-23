/*
 This class contains all the methods who are responsible for the logic of the game.
*/
public class Logic {

    private final int[][] A = new int[3][3];

    public Logic(){
        //nothing
    }

    /*
    This method checks if there is any row that is filled by the same player.
     */
    public  boolean checkRows(){
        for(int i=0; i<A.length; i++)
        {
            if( (A[i][0]==A[i][1]) && (A[i][1]==A[i][2]) && A[i][0] !=0)
                return true;
        }
        return false;
    }

    /*
    This method checks if there is any column that is filled by the same player.
     */
    public  boolean checkCols(){
        for(int i=0; i<A[0].length; i++)
        {
            if( (A[0][i]==A[1][i]) && (A[1][i]==A[2][i])&& A[0][i] !=0)
                return true;
        }
        return false;
    }

    /*
    This method checks if there is any diagonals that is filled by the same player.
     */
    public  boolean checkDiags(){
        if( (A[0][0]==A[1][1]) && (A[1][1]==A[2][2]) && A[0][0] !=0)
            return true;
        else if ((A[0][2]==A[1][1]) && (A[1][1]==A[2][0]) && A[1][1] !=0)
            return true;
        else
            return false;
    }

    /*
    This method checks if there is a winner or a draw in the game.
     */
    public  int  checkHit(String value) {

        if(checkRows() || checkCols() || checkDiags()){
            if(value.equals("X")){
                return 1;
            }
            else{
                return 2;
            }
        }
        else{
            if(isDraw())
                return -1;
            else
                return 0;
        }

    }

    /*
    This method checks if there is free the cell that the user is going to click
    to make his move.
     */
    public  boolean isFree (int row, int col) {
        if(A[row][col] == 0)
            return true;
        else
            return false;
    }


    /*
    This method fit the array(of Logic) in the row and column that is the cell in Gui.
     */
    public void filltable(int row,int col,String user){
        if(user.equals("X")){
           A[row][col]=1;
        }
        else {
           A[row][col]=2;
        }
    }


    /*
    This method checks which one of the players makes the latest move and return the value
    that is going to fit in the cell that is being selected.
     */
    public String fillTable(){
        int sum=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(A[i][j]==0){
                    sum++;
                }
            }
        }
        if(sum%2==0){
            return "O";
        }
        else{
            return "X";
        }

    }

    /*
    This method checks if all the cells have been fitted.
     */
    public boolean isDraw(){
        int sum=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(A[i][j]!=0){
                    sum++;
                }
            }
        }
        if(sum==9){
            return true;
        }
        else{
            return false;
        }
    }

}
