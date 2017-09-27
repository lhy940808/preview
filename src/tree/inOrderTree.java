package tree;

import java.util.Stack;

/**
 * Created by shixi_haiyan5 on 2017/9/8.
 */
public class inOrderTree {
    //用非递归的方式完成中序遍历的过程
    public void  inOrderTreeByStack(tree root) {
        if(root == null) {
            return;
        }
        Stack<tree> stack = new Stack<tree>();
        tree cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                System.out.println(cur.value);
                stack.push(cur.right);
            }
        }
    }

    //Morris
    public void morrisIn(tree root) {
        if (tree == null) {
            return;
        }
        tree  cur1 = root;
        tree  cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else {

                    cur2.right = null;
                }
            }
            System.out.println(cur1.value + "  ");
            cur1 = cur1.right;
            System.out.println()

        }

    }
}
