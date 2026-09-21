package KamaCoder.No612_615_pinduoduo;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class No614 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts;
        parts = br.readLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        int T = Integer.parseInt(parts[1]);
        int[] V = new int[N];
        for(int i=0; i<N; i++) {
            V[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(V);

        // f[i+1] 记录以 i 为右端点，最多能拿几件商品
        int[] f = new int[N+1];
        int ans = 0;
        // 双指针
        int l = 0;
        for(int r=0; r<N; r++) {
            while (V[r] - V[l] > T) {
                l++;
            }
            int len = r-l+1;
            f[r+1] = Math.max(f[r], len);
            ans = Math.max(ans, len+f[l]);
        }


        bw.write(Integer.toString(ans));
        bw.newLine();

        br.close();
        bw.close();
    }
}
