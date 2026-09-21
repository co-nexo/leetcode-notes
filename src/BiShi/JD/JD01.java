package BiShi.JD;

public class JD01 {
    public static void main(String[] args) {

    }

    // 给定一个正整数 n，请判断是否存在 >1 的三个正整数 a,b,c，满足: n=a+b+c，且 a,b,c 互质
    // 1 < n < 1e9
    // 如果存在，请给出任意一组满足条件的 (a,b,c)；否则判断无解。
    public int[] getNums(int n){
        // n <= 9 无解
        // 10 = 2 + 3 + 5
        // 11 无解
        // 12 = 2 + 3 + 7
        // 13 无解
        // 14 = 2 + 5 + 7
        // 15 = 3 + 5 + 7
        // 16 = 2 + 3 + 11


        // 由于 1 < n < 1e9，想到需要 O(1) 的时间，构造三个整数，也即数论的内容
        // 如果尝试 2, 3, n - 5 的话， (n-5) % 2 == 0 和 (n-5) % 3 == 0 的情况太多了

        // 所以可以尝试 3, 5, n - 8
        // 1.1 如果 (n - 8) % 3 == 0，可以换成 3, 7, n-10；则 n=3k+2； (n-10) % 7 = (3k+2-10) % 7 = (3k-1) % 7
        // 1.2 如果 (n - 10) % 7 == 0，即 n=3k1+2 和 n=7k2+3 同时成立；则替换成 7, 9, n-16； (n-16) % 9 = (3k1+1) % 9 /= 0
        // 2 如果 (n - 8) % 5 == 0，且 (n - 8) % 3 != 0 可以换成 5, 9, n-14；且 n%3 != 2，则 (n-14) % 3 = (n+1) % 3 /= 0
        if(n <= 9 || n == 11 || n == 13 || n == 17){
            return new int[]{-1, -1, -1};
        }
        if(n == 10) return new int[]{2, 3, 5};
        if(n == 12) return new int[]{2, 3, 7};
        if(n == 14) return new int[]{2, 5, 7};
        if(n == 15) return new int[]{3, 5, 7};
        if(n == 16) return new int[]{2, 3, 11};

        // 为什么 n >= 18? 因为 n-16 >= 2
        if((n-8) % 3 == 0){
            if((n-10) % 7 == 0){
                return new int[]{7, 9, n-16};
            }else{
                return new int[]{3, 7, n-10};
            }
        }else {
            if((n-8) % 5 == 0){
                return new int[]{5, 9, n-14};
            }else{
                return new int[]{3, 5, n-8};
            }
        }

    }
}
