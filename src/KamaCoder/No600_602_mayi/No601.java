package KamaCoder.No600_602_mayi;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class No601 {
    static class Pair{
        int x;
        int y;
        int step;
        public Pair(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts;
        parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        char[][] matrix = new char[n][];
        for(int i=0; i<n; i++){
            matrix[i] = br.readLine().toCharArray();
        }

        int[][] ans = new int[n][m];
        Deque<Pair> q = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == '0'){
                    q.addLast(new Pair(i, j, 0));
                }else{
                    ans[i][j] = -1;
                }
            }
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!q.isEmpty()){
            Pair pair = q.pollFirst();
            for(int[] dir : dirs){
                int nx = pair.x + dir[0];
                int ny = pair.y + dir[1];
                int step = pair.step;
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && ans[nx][ny] == -1){
                    ans[nx][ny] = step+1;
                    q.addLast(new Pair(nx, ny, step+1));
                }
            }
        }


        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                bw.write(Integer.toString(ans[i][j]));
                if(j != m-1){
                    bw.write(" ");
                }
            }
            bw.newLine();
        }

        br.close();
        bw.close();
    }


}
