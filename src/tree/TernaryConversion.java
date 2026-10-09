package tree;

import java.io.*;
import java.util.*;

public class TernaryConversion {
    static class Node{
        int len; // 当前区间所有数字转成三进制并拼接后的总长度
        long val; // 当前区间经过：1.转换 2.翻转 后得到的三进制数字值
        public Node(int len, long val){
            this.len = len;
            this.val = val;
        }
    }

    static final long MOD = 998244353L;

    static int n, q;
    static long[] a;

    static Node[] tree;
    static long[] pow3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 读取 n、q
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // 下标从 1 开始，写线段树更方便
        a = new long[n + 1];

        // 读取初始数组
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        // 初始化 pow3
        pow3 = new long[40 * n + 1];
        pow3[0] = 1;
        for(int i=1; i<pow3.length; i++){
            pow3[i] = pow3[i-1] * 3 % MOD;
        }

        // 初始化 tree
        tree = new Node[4*n+5];
        build(1, 1, n);
        /*
         * TODO:
         *
         * 1.
         *
         * 2. 初始化 tree
         *
         * 3. build(1, 1, n);
         */


        // 处理 q 次操作
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                // 查询 [l, r]
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                Node ans = query(1, 1, n, l, r);
                bw.write(String.valueOf(ans.val));
                bw.newLine();
            } else if (op == 2) {
                // 修改 a[idx] = c
                int idx = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                a[idx] = c;
                update(1, 1, n, idx);
            }
        }

        bw.flush();

        br.close();
        bw.close();
    }

    private static Node query(int p, int l, int r, int ql, int qr) {
        if(ql <= l && r <= qr){
            return tree[p];
        }
        int mid = (l+r)>>1;
        Node left = null, right = null;
        if(mid >= ql){
            left = query(p<<1, l, mid, ql, qr);
        }
        if(qr >= mid+1){
            right = query(p<<1|1, mid+1, r, ql, qr);
        }
        return merge(left, right);
    }

    static void build(int p, int l, int r){
        if(l == r){
            tree[p] = createLeaf(a[l]);
            return;
        }

        int mid = (l+r)>>1;
        build(p<<1, l, mid);
        build(p<<1|1, mid+1, r);

        tree[p] = merge(tree[p<<1], tree[p<<1|1]);
    }

    private static Node merge(Node left, Node right) {
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        // F(A+B) = F(B)*3^len(A) + F(A)
        int len = left.len + right.len;
        long val = (right.val * pow3[left.len] + left.val) % MOD;
        return new Node(len, val);
    }

    private static Node createLeaf(long x) {
        if(x == 0){
            return new Node(1, 2);
        }
        long val = 0;
        int len = 0;
        while (x != 0){
            // 0->2，1->1，2->0
            val = (val * 3 + 2 - x%3) % MOD;
            x /= 3;
            len++;
        }
        return new Node(len, val);
    }

    private static void update(int p, int l, int r, int idx){
        if(l == r){
            tree[p] = createLeaf(a[idx]);
            return;
        }

        int mid = (l+r) >> 1;
        if(idx <= mid){
            update(p<<1, l, mid, idx);
        }else{
            update(p<<1|1, mid+1, r, idx);
        }

        tree[p] = merge(tree[p<<1], tree[p<<1|1]);
    }
}
