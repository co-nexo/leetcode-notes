package KamaCoder.No622_623_huawei.java;

import java.io.*;

public class No622 {
    private static int ans;
    private static int n;
    private static int[] treeVal;
    private static final int NULL = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine().trim();
        String[] parts = line.substring(1, line.length()-1).split(", ");
        n = parts.length;
        treeVal = new int[n+1];
        for(int i=1; i<=n; i++){
            if(parts[i-1].trim().equals("null")){
                treeVal[i] = NULL;
            }else {
                treeVal[i] = Integer.parseInt(parts[i-1]);
            }

        }

        // 节点数为 1 <= n <= 1000
        // 层级为 1 <= layer(l) <= 11
        // 复杂度：n*2^(l-1)
        for(int i=1; i<=n; i++){
            // i 枚举起点
            dfs(i, treeVal[i], 1);
        }

        bw.write(Integer.toString(ans));
        bw.newLine();

        br.close();
        bw.close();
    }

    static void dfs(int cur, int sum, int cnt){
        if(cnt >= 2 && sum == 0){
            ans++;
        }
        int left = cur << 1;
        int right = (cur << 1) | 1;
        if(left <= n && treeVal[left] != NULL) dfs(left, sum+treeVal[left], cnt+1);
        if(right <= n && treeVal[right] != NULL) dfs(right, sum+treeVal[right], cnt+1);
    }
}
