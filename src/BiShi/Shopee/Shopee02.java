package BiShi.Shopee;

import java.util.ArrayList;

public class Shopee02 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     *
     *
     *
     * @param root TreeNode类
     * @return int整型二维数组
     */
    private static final ArrayList<ArrayList<Integer>> schema = new ArrayList<>();
    private static void dfs(TreeNode cur, int d){
        if(cur == null){
            return;
        }
        if(schema.size() < d+1){
            schema.add(new ArrayList<>());
        }
        schema.get(d).add(cur.val);
        dfs(cur.left, d+1);
        dfs(cur.right, d+1);
    }
    public int[][] levelOrder(TreeNode root) {
        // write code here
        dfs(root, 0);
        int[][] res = new int[schema.size()][];
        for(int i=0; i<schema.size(); i++){
            ArrayList<Integer> layer = schema.get(i);
            res[i] = new int[layer.size()];
            for(int j=0; j<res[i].length; j++){
                res[i][j] = layer.get(j);
            }
        }
        return res;
    }
}
