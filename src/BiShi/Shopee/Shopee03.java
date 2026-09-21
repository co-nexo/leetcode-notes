package BiShi.Shopee;

public class Shopee03 {
    public static void main(String[] args) {
        System.out.println(reverses("Shopee is Our family 123"));
        System.out.println(reverses("1e3r"));
    }
    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     *
     *
     * 反转字符串中的小写字母，并且保证单个单词的顺序不变，其他字符的位子不变
     * @param original_str string字符串
     * @return string字符串
     */
    public static String reverses(String original_str) {
        // write code here
        int n = original_str.length() + 1;
        final char UNSET = '-';
        char[] chars = new char[n+1];
        System.arraycopy(original_str.toCharArray(), 0, chars, 0, n-1);
        chars[n-1] = ' ';

        StringBuilder sb = new StringBuilder();
        int preBlank = -1;
        for(int i=0; i<n; i++){
            if(chars[i] >= 'a' && chars[i] <= 'z'){
                sb.append(chars[i]);
                chars[i] = UNSET;
            }else if(chars[i] == ' '){
                // 从上一个空白符后面的位置开始
                int j = preBlank+1;
                // System.out.println(sb.toString());
                for(char c : sb.reverse().toString().toCharArray()){
                    while (j < i && chars[j] != UNSET){
                        j++;
                    }
                    if(j < i){
                        chars[j] = c;
                    }
                }
                // System.out.println(i + " " + n + " " +  new String(chars));
                // 重新记录
                sb = new StringBuilder();
                // 记录上一个空白符位置
                preBlank = i;
            }

        }
        
        return new String(chars, 0, n-1);

    }
}
