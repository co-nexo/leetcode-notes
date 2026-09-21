package KamaCoder.No616_618_ali;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class No618_sol1 {
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

            int[] depth = new int[n+1];
            int[] fa = new int[n+1];
            // 队列处理深度；
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            depth[1] = 1;
            queue.addLast(1);
            while (!queue.isEmpty()) {
                int u = queue.removeFirst();
                for(int v : edges.get(u)) {
                    if (depth[v] == 0) {
                        depth[v] = depth[u] + 1;
                        fa[v] = u;
                        queue.addLast(v);
                    }
                }
            }



            for(int i = 0; i< q; i++) {
                parts = br.readLine().split(" ");
                int r = Integer.parseInt(parts[0]);
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int a = LCA(r, u, depth, fa);
                int b = LCA(r, v, depth, fa);
                int c = LCA(u, v, depth, fa);

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

    static int LCA(int u, int v, int[] depth, int[] fa){
        if(depth[u] < depth[v]){
            int tmp = u;
            u = v;
            v = tmp;
        }

        while (depth[u] > depth[v]){
            u = fa[u];
        }

        while (u != v){
            u = fa[u];
            v = fa[v];
        }

        return u;
    }

}
