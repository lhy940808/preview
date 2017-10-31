package tree;

import java.util.Stack;

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
    //判断一棵树是否为二叉树
    public boolean isBalance(tree root) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(root, 1, res);
        return res[0];
    }

    private int getHeight(tree root, int level, boolean[] res) {
        if (root == null) {
            return level;
        }
        int lH = getHeight(root.left, level + 1, res);
        if (!res[0]){
            return level;
        }
        int rH = getHeight(root.right, level + 1, res);
        if (!res[0]){
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }

    public boolean  isPostArray(int[] arr){
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    private boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }
        int less = -1;
        int more = end;
        for(int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;
            }else {
                more = more == end ? -1 : more;
            }
        }

        if(less == -1 || more == end) {
            return isPost(arr, start, end - 1);
        }
        if(less != more - 1) {
            return false;
        }
        return isPost(arr, start, less)  && isPost(arr, more, end - 1);
    }

}
