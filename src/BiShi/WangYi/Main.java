package BiShi.WangYi;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int inf = 10_0000_0000;
    public static void main(String[] args) throws IOException {
        // 汇合点 -> 终点
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T > 0){
            T--;
            int n, m;
            String[] parts;
            parts = br.readLine().split(" ");
            n = Integer.parseInt(parts[0]);
            m = Integer.parseInt(parts[1]);
            char[][] matrix = new char[n][];
            for(int i=0; i<n; i++){
                matrix[i] = br.readLine().toCharArray();
            }
            int x1, y1, x2, y2, x3, y3;
            parts = br.readLine().split(" ");
            x1 = Integer.parseInt(parts[0]) - 1;
            y1 = Integer.parseInt(parts[1]) - 1;
            x2 = Integer.parseInt(parts[2]) - 1;
            y2 = Integer.parseInt(parts[3]) - 1;
            x3 = Integer.parseInt(parts[4]) - 1;
            y3 = Integer.parseInt(parts[5]) - 1;

            int[][][] dist = new int[3][n][m];
            bfs(dist[0], matrix, x1, y1);
            bfs(dist[1], matrix, x2, y2);
            bfs(dist[2], matrix, x3, y3);

            // 遍历汇合点
            int ans = inf;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(dist[0][i][j] == inf || dist[1][i][j] == inf || dist[2][i][j] == inf) {
                        continue;
                    }
                    ans = Math.min(Math.max(dist[0][i][j], dist[1][i][j]) + (dist[2][i][j]+1)/2, ans);

                }
            }
            if(ans == inf){
                bw.write("-1");
            }else{
                bw.write(Integer.toString(ans));
            }

            bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void bfs(int[][] dist, char[][] matrix, int x, int y){
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i=0; i<n ;i++){
            for(int j=0; j<m; j++){
                dist[i][j] = inf;
            }
        }
        Deque<Node> queue = new ArrayDeque<>();
        dist[x][y] = 0;
        queue.addLast(new Node(x, y,0));
        while (!queue.isEmpty()){
            Node cur = queue.pollFirst();
            int[][] direct = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
            for(int i=0; i<4; i++){
                int nx = cur.x + direct[i][0];
                int ny = cur.y + direct[i][1];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && dist[nx][ny] == inf && matrix[nx][ny] == '.'){
                    dist[nx][ny] = cur.step + 1;
                    queue.addLast(new Node(nx, ny, cur.step+1));
                }
            }
        }
    }

    static class Node{
        int x;
        int y;
        int step;

        public Node(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}