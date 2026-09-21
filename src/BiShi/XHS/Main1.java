package BiShi.XHS;

import java.io.*;
import java.util.HashMap;

public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T > 0){
            T--;
            int n = Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(" ");
            int[] nums = new int[n];
            for(int i=0; i<n; i++){
                nums[i] = Integer.parseInt(parts[i]);
            }
            // 通过 HashMap 计算 0 的区间
            // 通过 i 表示下标， dp[i] 表示最大的折叠个数
            int[] dp = new int[n+1];
            HashMap<Integer, Integer> map =  new HashMap<>();
            map.put(0, -1);
            int sum = 0;
            for(int i=0; i<n; i++){
                dp[i+1] = dp[i];
                sum += nums[i];
                if(map.containsKey(sum)){
                    int j = map.get(sum);
                    int len = i - j;
                    if(j >= 0){
                        len += dp[j+1];
                    }
                    dp[i+1] = Math.max(dp[i+1], len);
                }
                map.put(sum, i);
            }
            bw.write(Integer.toString(dp[n]));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
