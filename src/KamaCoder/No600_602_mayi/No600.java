package KamaCoder.No600_602_mayi;

import java.io.*;
import java.util.HashMap;

public class No600 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T > 0){
            T--;
            int n = Integer.parseInt(br.readLine());
            int[] a = new int[n];
            int[] b = new int[n];
            String[] strings;
            strings = br.readLine().split(" ");
            for(int i=0; i<n; i++){
                a[i] = Integer.parseInt(strings[i]);
            }
            strings = br.readLine().split(" ");
            for(int i=0; i<n; i++){
                b[i] = Integer.parseInt(strings[i]);
            }

            int k = getK(a, b);
            bw.write(Integer.toString(k));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static int getK(int[] a, int[] b){
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = a.length;
        for(int i=0; i<n; i++){
            map.put(a[i], i);
        }
        int pre = -1;
        int k = 1;
        for(int x : b){
            int idx = map.get(x);
            if(idx < pre){
                k++;
            }
            pre = idx;
        }
        return k;
    }
}
