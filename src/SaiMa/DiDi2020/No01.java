package SaiMa.DiDi2020;

import java.io.*;
import java.util.Arrays;

public class No01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 只有交换后表达式值不变的相邻数字才能交换，意味着每次交换，都必须是同样的符号进行交换
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        char[] ops = new char[n];
        String[] parts = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            nums[i] = Integer.parseInt(parts[2*i]);
            if(i > 0){
                ops[i] = parts[2*i-1].charAt(0);
            }
        }
        ops[0] = '+';


        int l = 0;
        while (l < n){
            int r = l;
            // 找连续相同运算符的区间
            while (r+1 < n && ops[r] == ops[r+1]){
                r++;
            }

            char cur = ops[l];
            int left = l;
            int right = r;
            if(cur == '+' || cur == '-'){
                // + 5 + 2 + 3 + 7 * 8 中的 7 不能参与排序
                if(r+1 < n && (ops[r+1] == '*' || ops[r+1] == '/') ){
                    right = r-1;
                }
            }else if(cur == '*'){
                // 5 + 1 * 2 * 3，得把 1 也包括进来
                if(l-1 >= 0 && (ops[l-1] == '+' || ops[l-1] == '-')){
                    left = l-1;
                }
            }

            if(left < right){
                Arrays.sort(nums, left, right+1);
            }
            l = r+1;
        }

        bw.write(Integer.toString(nums[0]));
        for(int i=1; i<n; i++){
            bw.write(" " + ops[i] + " " + nums[i]);

        }


        br.close();
        bw.close();
    }


}
