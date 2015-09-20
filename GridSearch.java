package HackerRank_Algorithm;

import java.util.Scanner;

/**
 *
 * @author Rajiur
 */
public class GridSearch {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String totalTestCases_str = in.nextLine();
        int totalTestCases = Integer.parseInt(totalTestCases_str);
        for(int i=0; i<totalTestCases; i++){
            String RC = in.nextLine();
            String[] RCArray = RC.split(" ");
            int R = Integer.parseInt(RCArray[0]);
            int C = Integer.parseInt(RCArray[1]);
            int[][] grid = readData(in, R, C);

            String rc = in.nextLine();
            String[] rcArray = rc.split(" ");
            int r = Integer.parseInt(rcArray[0]);
            int c = Integer.parseInt(rcArray[1]);
            int[][] test = readData(in, r, c);
//            dumpMatrix(test, r, c);
            
            boolean res = checkSubMatrix(grid, R, C, test, r, c);
            if(res == true){System.out.println("YES");}
            else{System.out.println("NO");}
        }
    }
    
    private static boolean checkSubMatrix(int[][] grid, int R, int C, int[][] test, int r, int c) {
        //return true if test is a submatrix of grid, else false
        boolean res = false;
        for(int i=0; i<R-r+1; i++){
            for(int j=0; j<C-c+1; j++){
                if(grid[i][j] == test[0][0]){
                    boolean resTemp = checkBlockEquality(grid, test, r, c, i, j);
                    if(resTemp == true){
                        res = true;
                        break;
                    }
                }
            }
        }
        return res;
    }
    private static boolean checkBlockEquality(int[][] grid, int[][] test, int r, int c, int iCurrent, int jCurrent) {
        boolean res = true;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[iCurrent+i][jCurrent+j] == test[i][j]){
                    continue;
                }
                else{
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
    private static int[][] readData(Scanner in, int R, int C) {
        int[][] grid = new int[R][C];
        for(int j=0; j<R; j++){
            String str = in.nextLine();
            for(int k=0; k<C; k++){
                grid[j][k] = Character.getNumericValue(str.charAt(k));
            }
        }
        return grid;
    }   
    private static void dumpMatrix(int[][] grid, int row, int col) {
        System.out.println("\nRow: "+row+ " Col: "+col);
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}
