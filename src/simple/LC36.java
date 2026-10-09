package simple;

public class LC36 {
    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][] box = new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                int v = board[i][j] - '1';
                int pos = i/3*3 + j/3;
                if(row[v][i] == 1 || col[v][j] == 1 || box[v][pos] == 1){
                    return false;
                }
                row[v][i] = 1;
                col[v][j] = 1;
                box[v][pos] = 1;
            }
        }
        return true;
    }
}
