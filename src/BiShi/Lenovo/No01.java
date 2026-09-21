package BiShi.Lenovo;

import java.io.*;

public class No01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T>0){
            T--;
            int n = Integer.parseInt(br.readLine());
            // 贪心策略，尽量让前面的数字小，后面的合并次数才可能更低
            int[] nums = new int[n];
            String[] parts = br.readLine().split(" ");
            for(int i=0; i<n; i++){
                nums[i] = Integer.parseInt(parts[i]);
            }
            int[][] dp = new int[n][2];
            int[] sum = new int[n];
            sum[0] = nums[0];
            dp[0][0] = nums[0];
            for(int i=1; i<n; i++){
                sum[i] = sum[i-1] + nums[i];
                for(int j=i-1; j>=0; j--){
                    // nums[j+1] + nums[j+2] + ... + nums[n]
                    int x = sum[i] - sum[j];
                    // System.out.println("枚举：" + i + " " + j + " " +x);
                    if(x >= dp[j][0] && (
                            dp[i][0] == 0 ||
                            dp[j][1] + (i-j-1) < dp[i][1]) ||
                            (dp[j][1] + (i-j-1) == dp[i][1] && dp[i][0] > x)
                    )
                    {
                        dp[i][0] = x;
                        dp[i][1] = dp[j][1] + (i-j-1);
                    }
                }
                // 没有结果
                if(dp[i][0] == 0){
                    dp[i][0] = sum[i];
                    dp[i][1] = i-1;
                }
                // System.out.println(i + " " + dp[i][0] + " " + dp[i][1]);


            }
            bw.write(Integer.toString(dp[n-1][1]));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
