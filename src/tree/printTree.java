package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by shixi_haiyan5 on 2017/9/11.
 */
public class printTree {
    public void printTreeMethod (tree root) {
        System.out.println("Binary tree:");
        printInOrder(root, 0, "H", 17);
        System.out.println();
    }

    private void printInOrder(tree root, int height, String to, int len) {
        if(root == null)
            return;
        printInOrder(root.right, height+1, "v", len);
        String val = to + root.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val +getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(root.left, height + 1, "^", len);
    }

    private String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    //在二叉树中找到符合搜索二叉树的最大拓扑结构
    public int bstTopoSize(tree root) {
        if (root == null)
            return 0;
        int max = maxTopo(root,root);
        max = Math.max(bstTopoSize(root.left, max));
        max = Math.max(bstTopoSize(root.right, max));
        return max;
    }

    private int maxTopo(tree h, tree n) {
        if(h != null && n != null && isBSTNode(h, n, n.value)) {
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    private boolean isBSTNode(tree h, tree n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value)
    }

    //按层打印节点
    public void prinByLevel(tree root) {
        if(root == null) {
            return;
        }
        tree last = root;
        tree nLast = null;//下一行最右的节点
        tree cur = null;
        Queue<tree> queue = new LinkedList<tree>();
        queue.add(root);
        int level = 1;
        System.out.print("level"+level+":")
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value+"  ");
            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left;
            }
            if (cur.right != null){
                queue.offer(cur.right);
                nLast = cur.right;
            }
            if (cur == last && !queue.isEmpty()) {
                System.out.print("level"+(level++)+":");
                last = nLast;
            }

        }

    }

    public void printByZigZag(tree root) {
        if (root == null) {
            return;
        }
        Deque<tree> dq = new LinkedList<tree>();
        int level = 1;
        boolean lr = true;
        tree last = root;
        tree nLast = null;
        dq.offerFirst(root);
        printLevelAndOrienation(level++, lr);
        while (!dq.isEmpty()){
            if (lr) {
                root = dq.pollFirst();
                if (root.left != null) {
                    nLast = nLast == null ? root.left : nLast;
                    dq.offerLast(root.left);
                }
                if (root.right != null){
                    nLast = nLast == null ? root.right : nLast;
                    dq.offerLast(root.right);
                }
            }else {
                root = dq.pollLast();
                if (root.left != null) {
                    nLast = nLast == null ? root.right : nLast;
                    dq.offerFirst(root.right);
                }
                if (root.right != null) {
                    nLast = nLast == null ?root.left : nLast;
                    dq.offerFirst(root.left);
                }
            }
            System.out.print(root.value+" ");
            if (root == last && !dq.isEmpty()) {
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                printLevelAndOrienation(level++, lr);
            }
        }
        System.out.println();
    }

    private void printLevelAndOrienation(int level, boolean lr) {
        System.out.print("Level"+ level + "from");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }

    //寻找搜索二叉树中错误节点
    public tree[] getTwoErrNodes(tree root) {
        tree errs = new tree[2];
        if (root == null) {
            return errs;
        }
        Stack<tree> stack = new Stack<tree>();
        tree pre = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                if (pre != null && pre.value > root.value) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = root;
                }
                pre = root;
                root = root.right;
            }
        }
        return errs;

    }
}
