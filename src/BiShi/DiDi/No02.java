package BiShi.DiDi;

import java.io.*;

public class No02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] parts;
        parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int[][] dp = new int[n+1][m+1];
        for(int i=0; i<n; i++){
            parts = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                dp[i + 1][j + 1] = Integer.parseInt(parts[j]);
            }
        }


        // 计算 [r1, c1] -> [r2, c2] 的肥力之和（r2 >= r1，c2 >= c1）
        // dp[r2][c2] - dp[r1-1][c2] - dp[r2][c1-1] + dp[r1-1][c2-1]

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                dp[i][j] += dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1];
            }
        }


        long ans = Long.MIN_VALUE;
        // 枚举行
        for(int r1 = 1; r1 <=n;  r1++){
            // 固定高度
            for(int len=1; len<=Math.min(n-r1+1, m); len++){
                int minPre = 0;
                int r2 = r1 + len - 1;
                for(int c2 = len; c2<=m; c2++){
                    // O(1) 时间求前缀和 pre[c2];
                    // pre[c2] - Min(pre[c2 - len], ... , pre[0])
                    int rSum = dp[r2][c2] - dp[r1-1][c2];
                    int lSum = dp[r2][c2-len] - dp[r1-1][c2-len];
                    minPre = Math.min(lSum, minPre);
                    ans = Math.max(ans, (long)len * (rSum-minPre));
                }
            }
        }

        // 枚举列
        for(int c1 = 1; c1 <= m; c1++){
            // 固定宽度
            for(int len = 1; len<=Math.min(n, m-c1 +1); len++){
                int minPre = 0;
                int c2 = c1 + len - 1;
                for(int r2 = len; r2 <=n; r2++){
                    int rSum  = dp[r2][c2] - dp[r2][c1 -1];
                    int lSum =  dp[r2-len][c2] - dp[r2-len][c1 -1];
                    minPre = Math.min(lSum, minPre);
                    ans = Math.max(ans, (long)len * (rSum-minPre));
                }
            }
        }

        bw.write(Long.toString(ans));
        bw.newLine();

        br.close();
        bw.close();
    }

}
