package SaiMa.DiDi2020;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class No02 {
    static final int INF = 1 << 30;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts;
        parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int total = Integer.parseInt(parts[1]);
        int cost = Integer.parseInt(parts[2]);
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        parts = br.readLine().split(" ");
        for(int i=1; i<=n; i++){
            a[i] = Integer.parseInt(parts[i-1]);
        }
        parts = br.readLine().split(" ");
        for(int i=1; i<=n; i++){
            b[i] = Integer.parseInt(parts[i-1]);
        }


        // 记录位置序列，便于快速查找位置
        HashMap<Integer, ArrayList<Integer>> posMap = new HashMap<>();
        for(int i=1; i<=n; i++){
            posMap.computeIfAbsent(b[i], key -> new ArrayList<>()).add(i);
        }

        // 本质是匹配最长的子序列
        // 如何快速确定相同元素？如果使用 HashMap + List，可能在极端条件还是会退回到 n^2
        // 二维 dp[k][i]， maxK = total/cost <= 300
        // dp[k][i] = min(dp[k][i−1], next(A[i],dp[k−1][i−1]))
        int maxK = Math.min(n, total / cost);
        // 在 A 的前 i 个元素中，完成恰好 k 次匹配之后，B 最少走到哪个位置。
        int[][] dp = new int[maxK+1][n+1];

        int ans = 0;
        HashMap<Integer, Integer> ptr = new HashMap<>();
        for(int k=1; k<=maxK; k++){
            // A 一个元素都没参与，不可能完成 k 次匹配
            dp[k][0] = INF;
            // 固定 k 时，nextB 单调不增
            ptr.clear();
            for(int i=1; i<=n; i++){
                // 1. 不选择 a[i]
                dp[k][i] = dp[k][i-1];

                // 2. 选择 a[i]
                // 需要找到 > prevB 的 j，让 a[i] == b[j]
                int prevB = dp[k-1][i-1];
                ArrayList<Integer> pos = posMap.get(a[i]);
                if(prevB == INF || pos == null){
                    continue;
                }


                // 在 pos 中找最小的 nextB
                int p = ptr.getOrDefault(a[i], pos.size());
                while (p > 0 && pos.get(p-1) > prevB){
                    p--;
                }
                ptr.put(a[i], p);

                int nextB = (p == ptr.size()) ? INF : pos.get(p);
                if(nextB == INF){
                    continue;
                }
                dp[k][i] = Math.min(dp[k][i], nextB);


                // 如果在此时结束，则 B
                int lastB = pos.get(pos.size()-1);
                long spend = (long) k * cost + (n - i) + (n - lastB);
                if (spend <= total) {
                    ans = k;
                }
            }

        }

        bw.write(Long.toString(ans));
        bw.newLine();

        br.close();
        bw.close();
    }


}
