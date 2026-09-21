package BiShi.JD;

import java.util.ArrayList;
import java.util.Collections;

public class JD02 {
    public int minimumDifference(int[] nums) {
        // 折半搜索
        int n = nums.length / 2;
        ArrayList<ArrayList<Integer>> lSum = new ArrayList<>();
        for(int i=0; i<=n; i++){
            lSum.add(new ArrayList<>());
        }

        int len = 1 << n;
        for(int x=0; x<len; x++){
            // 为 1 则放在左侧，sum += v
            // 为 0 放在右侧，sum -= v
            int cnt = 0;
            int sum = 0;
            for(int i=0; i<n; i++){
                if((x & (1 << i)) == 0){
                    sum -= nums[i];
                }else{
                    sum += nums[i];
                    cnt ++;
                }
            }
            lSum.get(cnt).add(sum);
        }
        for(int i=0; i<=n; i++){
            Collections.sort(lSum.get(i));
        }



        int ans = Integer.MAX_VALUE;
        for(int x=0; x<len; x++){
            // 为 1 则放在右侧，sum += v
            // 为 0 放在左侧，sum -= v
            int cnt = 0;
            int sum = 0;
            for(int i=0; i<n; i++){
                if((x & (1 << i)) == 0){
                    sum -= nums[n+i];
                }else{
                    sum += nums[n+i];
                    cnt ++;
                }
            }
            // 选取 lSum[cnt] 时，左侧列表和右侧选取的数目刚好一致
            ArrayList<Integer> list = lSum.get(cnt);
            int pos = binarySearch(list, sum);
            if(pos < list.size()){
                ans = Math.min(ans, list.get(pos) - sum);
            }
            if(pos > 0){
                ans = Math.min(ans, sum - list.get(pos-1));
            }
        }

        return ans;


    }

    // 找第一个 >= target 的值
    private static int binarySearch(ArrayList<Integer> list, int target){
        int l = 0;
        int r = list.size();
        while (l < r){
            int mid = (l + r) / 2;
            int num = list.get(mid);
            if(num < target){
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        return l;
    }
}
