package slidingwindow;
import java.util.*;

public class LC30 {
    public List<Integer> findSubstring(String s, String[] words) {
        // 起点 0：0 → 3 → 6 → 9 ...
        // 起点 1：1 → 4 → 7 → 10 ...
        // 起点 2：2 → 5 → 8 → 11 ...
        int n = s.length();
        int len = words[0].length();
        int m = words.length;
        HashMap<String, Integer> wordMap = new HashMap<>();
        HashMap<String, Integer> wordCnt = new HashMap<>();
        for(int i=0; i<m; i++){
            wordMap.put(words[i], i);
            wordCnt.put(words[i], wordCnt.getOrDefault(words[i], 0) + 1);
        }


        List<Integer> ans = new ArrayList<>();
        // 枚举起点
        for(int start=0; start<len; start++){
            // 滑动数组
            Deque<Integer> deque = new ArrayDeque<>();
            int[] record = new int[m];
            // 按照词组长度跳转
            for(int i=start; i<=n-len; i+=len){

                String word = s.substring(i, i+len);
                // 1. 先判断是否存在
                if(!wordMap.containsKey(word)){
                    deque.clear();
                    Arrays.fill(record, 0);
                    continue;
                }
                // 2. 再判断是否超过限制数量
                int pos1 = wordMap.get(word);
                while(record[pos1] == wordCnt.get(word) && !deque.isEmpty()){
                    int j = deque.pollFirst();
                    int pos2 = wordMap.get(s.substring(j, j+len));
                    record[pos2]--;
                }

                // 3. 判断数量
                deque.addLast(i);
                record[pos1]++;
                if(deque.size() == m){
                    int j = deque.pollFirst();
                    int pos2 = wordMap.get(s.substring(j, j+len));
                    record[pos2]--;
                    ans.add(j);
                }

                // System.out.println(i + " " + deque);
            }
        }
        return ans;
    }
}
