package KamaCoder.No612_615_pinduoduo;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class No613 {
    static class Node{
        int idx;
        int step;
        public Node(int idx, int step){
            this.idx = idx;
            this.step = step;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> nextCourses = new ArrayList<>();
        for(int i=0; i<n; i++){
            nextCourses.add(new ArrayList<>());
        }
        Deque<Node> queue = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            int preCourse = Integer.parseInt(br.readLine());
            if(preCourse == -1){
                queue.addLast(new Node(i, 1));
            }else{
                nextCourses.get(preCourse-1).add(i);
            }
        }


        int ans = 0;
        while (!queue.isEmpty()){
            Node cur = queue.pollFirst();
            ans = Math.max(ans, cur.step);
            for(int nx : nextCourses.get(cur.idx)){
                queue.addLast(new Node(nx, cur.step+1));
            }
        }


        bw.write(Integer.toString(ans));
        bw.newLine();

        br.close();
        bw.close();
    }
}
