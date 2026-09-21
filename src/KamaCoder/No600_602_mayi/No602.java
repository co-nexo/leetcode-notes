package KamaCoder.No600_602_mayi;

import java.io.*;

public class No602 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        String[] parts;
        while (T > 0){
            T--;
            parts = br.readLine().split(" ");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            long c = Long.parseLong(parts[2]);
            long ans = solve(a, b, c);
            bw.write(Long.toString(ans));
            bw.newLine();

        }


        br.close();
        bw.close();
    }

    private static long solve(long a, long b, long c){
        if(c == 0){
            return a^b;
        }
        // a,b,c <= 1e18 < 2^60
        // 直接考虑 x = b + c*k
        // 因为 a ^ (b + c*k) <= a^b，故答案的取值范围 < 2^60，枚举 0~59 即可
        // 因为要获得最小值，所以优先让最高位为 0，从最高位开始枚举
        long x = 0;
        for(int i = 59; i >= 0; i--){
            long aBit = (a >> i) & 1L;
            // 如果 aBit 为 1，那么 candidate 这 1 位为 1，否则为 0
            // 例如 x = 101_xxxxxxxxxx
            // 101 是前面确定的
            // _ 是现在确定的
            // xxxxxxxx 是接下来确定的
            long candidate = x | (aBit << i);
            // 判断 xxxxxxxx 中有没有满足的情况
            long right = candidate + (1L << i) - 1;
            if(check(candidate, right, b, c)){
                x |= (aBit << i);
            }else{
                // 做不到，只能取反
                // 由于之前保证了区间一定有解，不在那一半就只可能在这一半
                // 且至少有一个解为 x = b
                x |= ((aBit^1L) << i);
            }
        }

        return a^x;
    }

    // 判断 b + c*k 是否能组成 [left, right]
    private static boolean check(long left, long right, long b, long c){
        long delta = ((b - left) % c + c) % c;
        return left + delta <= right;
    }
}
