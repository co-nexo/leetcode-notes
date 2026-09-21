package KamaCoder.No616_618_ali;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class No618_sol2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        String[] parts;
        while (T > 0) {
            T--;
            parts = br.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            int q = Integer.parseInt(parts[1]);
            ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                edges.add(new ArrayList<>());
            }
            for(int i=0; i<n-1; i++) {
                parts = br.readLine().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                edges.get(u).add(v);
                edges.get(v).add(u);
            }

            int LOG = 1;
            while ((1 << LOG) <= n) {
                LOG++;
            }

            int[] depth = new int[n+1];
            int[][] fa = new int[n+1][LOG];
            // 队列处理深度；
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            depth[1] = 1;
            queue.addLast(1);
            while (!queue.isEmpty()) {
                int u = queue.removeFirst();
                for(int v : edges.get(u)) {
                    if (depth[v] == 0) {
                        depth[v] = depth[u] + 1;
                        fa[v][0] = u;
                        queue.addLast(v);
                    }
                }
            }

            // 更新倍增数组，fa[u][k] 为 u 往上 2^k 的祖先节点
            for(int k=1; k<LOG; k++){
                for(int u=1; u<=n; u++){
                    fa[u][k] = fa[fa[u][k-1]][k-1];
                }
            }




            for(int i = 0; i< q; i++) {
                parts = br.readLine().split(" ");
                int r = Integer.parseInt(parts[0]);
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int a = LCA(r, u, depth, fa, LOG);
                int b = LCA(r, v, depth, fa, LOG);
                int c = LCA(u, v, depth, fa, LOG);

                int ans = a;
                if(depth[b] > depth[ans]) {
                    ans = b;
                }
                if(depth[c] > depth[ans]) {
                    ans = c;
                }
                bw.write(Integer.toString(ans));
                bw.newLine();
            }
        }


        br.close();
        bw.close();
    }

    static int LCA(int u, int v, int[] depth, int[][] fa, int LOG){
        if(depth[u] < depth[v]){
            int tmp = u;
            u = v;
            v = tmp;
        }

        int diff = depth[u] - depth[v];
        for(int k=0; k<LOG; k++){
            if(((diff >> k) & 1) == 1){
                u = fa[u][k];
            }
        }

        // 如果此时已经相遇，直接返回
        if (u == v) {
            return u;
        }

        // 两个人一起往上跳，从最大的步长开始尝试
        // 如果跳 2^k 之后还不相同，说明还没有跳过 LCA，可以放心跳
        for(int k=LOG-1; k>=0; k--){
            if(fa[u][k] != fa[v][k]){
                u = fa[u][k];
                v = fa[v][k];
            }
        }

        // 此时：u 和 v 已经位于 LCA 的下一层，所以它们的父亲就是 LCA
        return fa[u][0];
    }

}
