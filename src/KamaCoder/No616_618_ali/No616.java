package KamaCoder.No616_618_ali;

import java.io.*;

public class No616 {
    static long gcd(long x, long y){
        if(y == 0){
            return x;
        }
        return gcd(y, x%y);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] parts;
        for(int i=0; i<n; i++){
            parts = br.readLine().split(" ");
            long a = Long.parseLong(parts[0]);
            long d = Long.parseLong(parts[1]);
            long m = Long.parseLong(parts[2]);

            long g = gcd(d, m);
            // (i*d) % m 的所有结果为 0, g, 2g, ..., m - g
            // + a%g 表示加上初始值
            long max = m - g + a % g;
            bw.write(Long.toString(max));
            bw.newLine();

        }

        br.close();
        bw.close();
    }
}
