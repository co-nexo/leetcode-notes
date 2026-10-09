package tree;

import java.io.*;
import java.util.*;


public class AdjacentMatch {

    static class Node {
        int len;
        int pre; // 从最左边开始连续 1 的长度
        int suf; // 从最右边开始连续 1 的长度
        int ans;

        Node() {}

        Node(int v) {
            len = 1;
            pre = suf = v;
            ans = v;
        }
    }

    static char[] s;
    static Node[] tree;
    static int n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        s = br.readLine().toCharArray();

        // edge 有 n-1 个
        tree = new Node[n * 4];

        // 把原题转换成一个非常标准的问题。
        // 原问题：0 0 1 1 0 1
        // 转换：  0 1 0 1 1
        // 从这些 1 中尽量多选，但相邻的两个 1 不能同时选。

        if(n > 1){
            build(1, 0, n - 2);
        }
        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            String[] parts = br.readLine().split(" ");
            int p = Integer.parseInt(parts[0]);

            if (p == 0) {
                int i = Integer.parseInt(parts[1]) - 1;

                // 翻转
                s[i] = s[i] == '0' ? '1' : '0';

                // 只影响 i-1 和 i 两条边
                if (i - 1 >= 0)
                    update(1, 0, n - 2, i - 1);

                if (i < n - 1)
                    update(1, 0, n - 2, i);

            } else {
                int l = Integer.parseInt(parts[1]) - 1;
                int r = Integer.parseInt(parts[2]) - 1;

                if (l == r) {
                    System.out.println(0);
                } else {
                    // 字符 [l,r]
                    // 对应 edge [l,r-1]
                    int res = query(1, 0, n - 2, l, r - 1).ans;
                    bw.write(res + "\n");
                }
            }
        }
    }

    private static Node query(int p, int l, int r, int ql, int qr) {
        if(ql <= l && qr >= r){
            return tree[p];
        }

        int mid = (l+r) >> 1;
        Node left = null;
        Node right = null;
        if(ql <= mid){
            left = query(p<<1, l, mid, ql, qr);
        }
        if(qr >= mid+1){
            right = query(p<<1|1, mid+1, r, ql, qr);
        }
        return merge(left, right);
    }

    private static void update(int p, int l, int r, int x) {
        if(l == r){
            tree[p] = new Node(s[x] != s[x + 1] ? 1 : 0);
            return;
        }
        int mid = (l+r)>>1;
        if(x <= mid){
            update(p<<1, l, mid, x);
        }else{
            update(p<<1|1, mid+1, r, x);
        }

        tree[p] = merge(tree[p<<1], tree[p<<1|1]);
    }

    private static void build(int p, int l, int r) {
        // 终止条件
        if(l == r){
            tree[p] = new Node(s[l] != s[l+1] ? 1 : 0);
            return;
        }

        int mid = (l + r) >> 1;
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
        Node parent = new Node();
        parent.len = left.len + right.len;
        // 如果左侧都是 1，则结果为 left.len + right.pre
        // 否则为 left.pre
        parent.pre = left.len == left.pre ? left.len + right.pre : left.pre;

        // 如果右侧都是 1，则结果为 right.len + left.suf
        // 否则为 right.suf
        parent.suf = right.suf == right.len ? left.suf + right.len : right.suf;

        parent.ans = left.ans + right.ans;
        parent.ans -= (left.suf + 1) / 2;
        parent.ans -= (right.pre + 1) / 2;
        parent.ans += (left.suf + right.pre + 1) / 2;
        return parent;
    }
}