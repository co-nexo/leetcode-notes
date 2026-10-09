package search;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class LC37 {
    private int[][] row = new int[9][9];
    private int[][] col = new int[9][9];
    private int[][] box = new int[9][9];
    public void solveSudoku(char[][] board) {

        // 初始化
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                int v = board[i][j] - '1';
                row[v][i] = 1;
                col[v][j] = 1;
                box[v][i/3*3+j/3] = 1;
            }
        }

        dfs(board,0, 0);
    }

    private boolean dfs(char[][] board, int i, int j){
        if(i == 9){
            // System.out.println(Arrays.deepToString(board));
            return true;
        }
        int ni = i;
        int nj = j+1;
        if(nj == 9){
            ni = ni+1;
            nj = 0;
        }
        if(board[i][j] != '.'){
            return dfs(board, ni, nj);
        }
        int pos = i/3*3+j/3;
        for(int v=0; v<9; v++){
            if(row[v][i] == 0 && col[v][j] == 0 && box[v][pos] == 0){
                row[v][i] = 1;
                col[v][j] = 1;
                box[v][pos] = 1;
                board[i][j] = (char)('1' + v);
                if(dfs(board, ni, nj)){
                    return true;
                }
                row[v][i] = 0;
                col[v][j] = 0;
                box[v][pos] = 0;
                board[i][j] = '.';
            }
        }
        return false;
    }
}
