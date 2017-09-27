package tree;

import java.util.Stack;

/**
 * Created by shixi_haiyan5 on 2017/9/11.
 */
public class postOrderTree {
    //采用非递归方式后序遍历树

    public void postOrderByStack(tree root) {
        if(root == null) {
            return;
        }
        Stack<tree> stack = new Stack<tree>();
        stack.push(root);
        tree pre = root;
        tree cur = null;
        while(!stack.isEmpty()){
            cur = stack.peek();
            if(cur.left != null && pre != cur.left && pre != cur.right) {
                stack.push(cur.left);
            }else (cur.right != null && pre != cur.right) {
                stack.push(cur.right);
            }else {
                System.out.println(stack.pop()+"  ");
                pre = cur;
            }
        }
    }

    //求二叉树中的最大搜索子树
    public tree biggestSubBST(tree root) {
        int[] record = new int[3];
        return postOrderT(root, record);
    }

    private tree postOrderT(tree root, int[] record) {
        if (root == null){
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = root.value;
        tree left = root.left;
        tree right = root.right;
        tree lBST = postOrderT(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        tree rBST = postOrderT(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(lMin, value);
        record[2] = Math.max(rMax, value);
        if(left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return root;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;

    }
}
