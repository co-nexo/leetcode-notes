package KamaCoder.No612_615_pinduoduo;

import java.io.*;
import java.util.Arrays;

public class No615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int t = Integer.parseInt(br.readLine());
        while (t > 0){
            t--;
            int n = Integer.parseInt(br.readLine());
            String[] parts;
            int[] a = new int[n-1];
            int[] b = new int[n-1];
            int[] c = new int[n];
            parts = br.readLine().split(" ");
            for(int i=0; i<n; i++){
                c[i] = Integer.parseInt(parts[i]);
            }
            parts = br.readLine().split(" ");
            for(int i=0; i<n-1; i++){
                a[i] = Integer.parseInt(parts[i]);
            }
            parts = br.readLine().split(" ");
            for(int i=0; i<n-1; i++){
                b[i] = Integer.parseInt(parts[i]);
            }

            int pre = -c[0];
            int ans = 0;
            for(int i=0; i<n-1; i++){
                int diff = Math.abs(a[i] - b[i]);
                if(diff != 0){
                    // 与前面的组成一个更大的环，还是自己做环的开端
                    pre = Math.max(pre+c[i]-diff+1, diff+1);
                }else{
                    pre = 1;
                }
                ans = Math.max(pre+c[i+1], ans);
            }


            bw.write(Integer.toString(ans));
            bw.newLine();


        }

        br.close();
        bw.close();
    }
}
