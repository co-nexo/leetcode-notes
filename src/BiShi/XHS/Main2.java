package BiShi.XHS;

import java.io.*;
import java.util.HashMap;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts;
        parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int q = Integer.parseInt(parts[1]);

        parts = br.readLine().split(" ");
        int[] nums = new int[n];
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        while (q > 0) {
            q--;
            parts = br.readLine().split(" ");
            int op = Integer.parseInt(parts[0]);
            if (op == 2) {
                int l = Integer.parseInt(parts[1]) - 1;
                int r = Integer.parseInt(parts[2]) - 1;
                // 计算相邻的 0，1
                int pre = -1;
                int ans = 0;
                for (int i = l; i <= r; i++) {
                    if (pre == -1) {
                        pre = nums[i];
                        continue;
                    }
                    if (pre != nums[i]) {
                        ans++;
                        pre = -1;
                    }
                }
                bw.write(Integer.toString(ans));
                bw.newLine();
            } else {
                int idx = Integer.parseInt(parts[1]) - 1;
                if (nums[idx] == 0) {
                    nums[idx] = 1;
                } else {
                    nums[idx] = 0;
                }
            }

        }

        br.close();
        bw.close();
    }
}
