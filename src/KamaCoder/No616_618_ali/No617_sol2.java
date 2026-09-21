package KamaCoder.No616_618_ali;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class No617_sol2 {
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
            Deque<Integer> lStack = new ArrayDeque<>();
            Deque<Integer> rStack = new ArrayDeque<>();

            for(int i = 0; i <n; i++){
                if(chars[i] == '('){
                    if(rStack.isEmpty() || rStack.getLast() < i){
                        lStack.addLast(i);
                    }else{
                        rStack.removeLast();
                    }
                }else{
                    if(lStack.isEmpty()){
                        rStack.addLast(i);
                    }else{
                        lStack.removeLast();
                    }
                }
            }


            int lSize = lStack.size();
            int rSize = rStack.size();
            ans = (lSize+1) / 2 + (rSize+1) / 2;
            // 前半部分，向上取整
            for(int i=0; i<(rSize+1)/2; i++){
                arr.add(rStack.removeFirst());
            }
            // 后半部分，向上取整
            for(int i=0; i<lSize/2; i++){
                lStack.removeFirst();
            }
            arr.addAll(lStack);

            bw.write(Integer.toString(ans));
            bw.newLine();
            for (int idx : arr) {
                bw.write((idx + 1) + " ");
            }
            bw.newLine();

        }

        br.close();
        bw.close();
    }
}
