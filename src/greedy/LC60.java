package greedy;

public class LC60 {
    public String getPermutation(int n, int k) {
        int[] fac = new int[n+1];
        fac[0] = 1;
        for(int i=1; i<n; i++){
            fac[i] = fac[i-1] * i;
        }

        int[] order = new int[n];
        k = k - 1;
        for(int i=n-1; i>=0; i--){
            order[n-1-i] = k / fac[i];
            k %= fac[i];
        }

        int[] used = new int[n];
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int cnt = -1;
            for(int j=0; j<n; j++){
                if(used[j] == 0){
                    cnt++;
                }
                if(cnt == order[i]){
                    used[j] = 1;
                    sb.append(j+1);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
