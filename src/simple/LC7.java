package simple;

public class LC7 {
    public static void main(String[] args) {

    }
    public int reverse(int x) {
        int res = 0;
        int signed = x < 0 ? 1 : 0;
        x = Math.abs(x);
        while (x > 0){
            if((Integer.MAX_VALUE - x%10) / 10 < res){
                return 0;
            }
            res = res * 10 + x % 10;
            System.out.println(res);
            x /= 10;
        }

        return signed == 1 ? -res : res;
    }
}
