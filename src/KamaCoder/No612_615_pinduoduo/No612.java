package KamaCoder.No612_615_pinduoduo;

import java.io.*;

public class No612 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts;
        parts = br.readLine().split(" ");
        int initialWeight = Integer.parseInt(parts[0]);
        int maxSafeWeight = Integer.parseInt(parts[1]);
        int n = Integer.parseInt(parts[2]);

        parts = br.readLine().split(" ");
        int ans = -1;
        int count = 0;
        int curWeight = initialWeight;
        for(int i=0; i<n; i++){
            int weight = Integer.parseInt(parts[i]);
            curWeight = Math.max(0, curWeight + weight);
            if(curWeight <= maxSafeWeight){
                count++;
                ans = Math.max(count, ans);
            }else{
                count = 0;
            }
        }
        bw.write(Integer.toString(ans));
        bw.newLine();

        br.close();
        bw.close();
    }
}
