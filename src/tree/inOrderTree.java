package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


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
                 .out.println(cur.value);
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
    //判断一棵树是否为二叉搜索树
    public boolean isBST(tree root){
        if (root == null) {
            return true;
        }
        boolean res = true;
        tree pre = null;
        tree cur1 = root;
        tree cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right == cur1;
                    cur1 = cur1.left;
                    continue;
                }
            }
        }
    }

    //判断一棵树是否为完全二叉树
    public boolean isCBT(tree  root) {

        if (tree == null) {
            return true;
        }
        Queue<tree>  queue = new LinkedList<tree>();
        boolean leaf = false;
        tree l = null;
        tree r = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            l = root.left;
            r = root.right;
            if ((leaf &&(l != null && r != null)) || (l == null && r != null)) {
                return false;
            }
            if(l != null) {
                queue.offer(1);
            }
            if(r != null) {
                queue.offer(r);
            }else {
                leaf = true;
            }
        }
        return true;
    }
}
