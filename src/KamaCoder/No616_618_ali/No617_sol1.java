package KamaCoder.No616_618_ali;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class No617_sol1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T > 0){
            T--;
            int n = Integer.parseInt(br.readLine());
            char[] chars = br.readLine().toCharArray();
            int ans = 0;
            ArrayList<Integer> arr = new ArrayList<>();
            int cnt;
            cnt = 0;
            for(int l=0; l<n; l++){
                if(chars[l] == '('){
                    cnt++;
                }else{
                    cnt--;
                }
                if(cnt < 0){
                    cnt += 2;
                    chars[l] = '(';
                    ans++;
                    arr.add(l);
                }
            }

            cnt = 0;
            for(int r=n-1; r>=0; r--){
                if(chars[r] == ')'){
                    cnt++;
                }else{
                    cnt--;
                }
                if(cnt < 0){
                    cnt += 2;
                    chars[r] = ')';
                    ans++;
                    arr.add(r);
                }
            }

            Collections.sort(arr);
            bw.write(Integer.toString(ans));
            bw.newLine();
            for (int x : arr) {
                bw.write((x + 1) + " ");
            }
            bw.newLine();

        }

        br.close();
        bw.close();
    }
}
