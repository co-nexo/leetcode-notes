package BiShi.DiDi;

import java.io.*;

public class No01 {
    static class Person{
        int W;
        int A;
        int B;
        public Person(int W, int A, int B){
            this.W = W;
            this.A = A;
            this.B = B;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Person[] persons = new Person[N];
        for(int i=0; i<N; i++){
            String[] parts = br.readLine().split(" ");
            int W = Integer.parseInt(parts[0]);
            int A = Integer.parseInt(parts[1]);
            int B = Integer.parseInt(parts[2]);
            persons[i] = new Person(W, A, B);
        }

        int Q = Integer.parseInt(br.readLine());
        int[] queries = new int[Q];
        for(int i=0; i<Q; i++){
            queries[i] = Integer.parseInt(br.readLine());
        }

        for(int query : queries){
            int money = query;
            for(Person person: persons){
                if(money > person.W){
                    money = Math.max(0, money-person.B);
                }else{
                    money += person.A;
                }
            }
            bw.write(Integer.toString(money));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
