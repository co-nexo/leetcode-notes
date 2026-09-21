package KamaCoder.No622_623_huawei.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class No623 {
    static int N;
    static int distThreshold;
    static int weightThreshold;
    static class Node {
        int x, y, t, w, users;
        public Node(int x, int y, int t, int w, int users){
            this.x = x;
            this.y = y;
            this.t = t;
            this.w = w;
            this.users = users;
        }
        public boolean isDirectConnection(Node other){
            int dist = Math.abs(x-other.x) + Math.abs(y-other.y);
            return dist <= distThreshold;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts;
        parts = br.readLine().split(" ");
        N = Integer.parseInt(parts[0]);
        distThreshold = Integer.parseInt(parts[1]);
        weightThreshold = Integer.parseInt(parts[2]);


        Node[] nodes = new Node[N];
        for(int i=0; i<N; i++){
            parts = br.readLine().split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int t = Integer.parseInt(parts[2]);
            int w = Integer.parseInt(parts[3]);
            int users = Integer.parseInt(parts[4]);
            nodes[i] = new Node(x, y, t, w, users);
        }

        // 获取关键节点
        ArrayList<Node> keyNodes = getKeyNodes(nodes);

        // 根据时间戳排序
        Collections.sort(keyNodes, (n1, n2) -> n1.t - n2.t);

        // 找到关键节点传播链的最长路径
        HashMap<Node, Integer> map = new HashMap<>();
        int maxUsers = 0;
        for(int i=0; i<keyNodes.size(); i++){
            Node cur = keyNodes.get(i);
            map.put(cur, cur.users);
            for(int j=i-1; j>=0; j--){
                Node other = keyNodes.get(j);
                if(cur.isDirectConnection(other) && cur.t > other.t){
                    map.put(cur, Math.max(map.get(cur), map.get(other) + cur.users));
                }
            }
            maxUsers = Math.max(maxUsers, map.get(cur));
        }

        bw.write(Integer.toString(maxUsers));
        bw.newLine();

        br.close();
        bw.close();
    }

    static ArrayList<Node> getKeyNodes(Node[] nodes){
        ArrayList<Node> keyNodes = new ArrayList<>();
        for(int i=0; i<N; i++){
            int totalWeight = 0;
            for(int j=0; j<N; j++){
                if(nodes[i].isDirectConnection(nodes[j])){
                      totalWeight += nodes[j].w;
                }
            }
            if(totalWeight >= weightThreshold){
                keyNodes.add(nodes[i]);
            }
        }
        return keyNodes;
    }


}
