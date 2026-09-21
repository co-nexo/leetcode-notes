package BiShi.Shopee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Shopee01 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(";");
        int a = toInt(line[0].toCharArray());
        int b = toInt(line[1].toCharArray());
        // System.out.println(a + " " + b);
        // System.out.println(toBinary(a*b));
        bw.write(toBinary(a*b));
        bw.newLine();

        br.close();
        bw.close();
    }
    private static int toInt(char[] chars){
        int x = 0;
        int n = chars.length;
        for(int i=0; i<chars.length; i++){
            x |= (chars[n-i-1]-'0') << i;
        }
        return x;
    }
    private static String toBinary(int x){
        if(x == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while(x != 0){
            sb.append(x % 2);
            x = x >> 1;
        }
        return sb.reverse().toString();
    }
}
