public class SudokuProblem {
    public static boolean isSafe(int sudoku[][],int row,int col,int digit){
        // Check if 'digit' is already present in the row or column
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == digit || sudoku[i][col] == digit) {
                return false;
            }
        }
        // Check if 'digit' is already present in the 3x3 grid
        int sr=(row/3)*3;
        int sc=(col/3)*3;
        for(int i=sr;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(sudoku[i][j]==digit){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean SudokuSolver(int sudoku[][],int row,int col){
        //base case
        if(row==9 && col==0){
            return true;
        }
        //recursion
        int nextRow=row,nextCol=col+1;
        if(col+1==9){
            nextRow=row+1;
            nextCol=0;
        }
        if(sudoku[row][col]!=0){
            return SudokuSolver(sudoku,nextRow,nextCol);
        }
        // Try placing a number from 1 to 9 in the empty cell
        for(int digit=1;digit<=9;digit++){
            if(isSafe(sudoku,row,col,digit)){
                sudoku[row][col]=digit;
                if(SudokuSolver(sudoku,nextRow,nextCol)){
                    return true;
                }
                // If the current configuration doesn't lead to a solution, backtrack
                sudoku[row][col]=0; 
            }
        }
        return false;
    }
    public static void printSudoku(int sudoku[][]){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        // Test 1
        // int sudoku[][]={{8,5,0,3,6,0,0,0,2},
        //                 {6,0,0,0,8,5,7,0,4},
        //                 {0,0,9,0,2,0,0,5,8},
        //                 {5,0,0,0,1,0,2,0,0},
        //                 {0,7,6,0,0,0,9,8,0},
        //                 {0,0,8,0,7,0,0,0,5},
        //                 {3,2,0,0,4,0,8,0,0},
        //                 {9,0,4,7,3,0,0,0,6},
        //                 {7,0,0,0,9,8,0,1,3}};
        // Test 2
        int sudoku[][]={{0,9,4,0,0,0,0,6,0},
                        {7,0,0,8,0,0,5,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,6,0,0,0,0,0,9,2},
                        {8,0,0,7,0,0,0,0,0},
                        {0,4,0,0,0,0,0,0,0},
                        {0,0,0,0,9,2,0,0,0},
                        {3,0,0,0,0,0,8,0,0},
                        {0,0,0,0,6,0,0,0,0}};

        if(SudokuSolver(sudoku,0,0)){
            System.out.println("Solved Sudoku:");
            printSudoku(sudoku);
        }
        else{
            System.out.println("Solution does not exist");
        }
    }
}
