package greedy;

public class LC44 {
    public boolean isMatch(String s, String p) {
        char[] str1 = s.toCharArray();
        char[] str2 = p.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int i = 0;
        int j = 0;
        int iStart = -1;
        int jStart = -1;
        while (i < n){
            if((j < m) && (str2[j] == '?' || str1[i] == str2[j])){
                i++;
                j++;
            }else if(j < m && str2[j] == '*'){
                iStart = i;
                jStart = ++j;
            }else {
                // 重新匹配
                if(iStart >= 0){
                    iStart++;
                    i = iStart;
                    j = jStart;
                }else{
                    return false;
                }
            }
        }
        while (j < m && str2[j] == '*') j++;
        return j == m;

    }
}
