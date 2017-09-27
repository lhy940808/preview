package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by shixi_haiyan5 on 2017/9/8.
 */
public class preOrderTree {


    //递归实现先序遍历
    public void preOrderByRecursion (tree root) {
        if(root == null){
            return;
        }
        System.out.print(root.value);
        preOrderByRecursion(root.left);
        preOrderByRecursion(root.right);
    }

    public void preOrderByStack(tree root) {
        if (root == null) {
            return;
        }
        Stack<tree> stack = new Stack<tree>();
        stack.push(root.value);
        tree cur = null;
        while(!stack.isEmpty()) {
            cur = stack.pop();
            System.out.println(cur.value);
            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    //打印边界节点
    public void printEdgel(tree root) {
        if(head == null) {
            return;
        }
        int height = getHeight(root, 0);
        tree[][] edgeMap = new tree[height][2];
        setEdgeMap(root, 0, edgeMap);
        //打印左边界
        for(int i = 0; i != edgeMap.length; i++) {
            System.out.print(edgeMap[i][0].value + "  ");
        }
        //打印既不是左边界，也不是右边界的叶子结点
        printLeafNotInMap(root, 0 , edgeMap);
        //打印右边界，但不是左边界的节点
        for (int i = edgeMap.length-1; i != -1; i--) {
            if(edgeMap[i][0] != edgeMap[i][0]){
                System.out.print(edgeMap[i][1]);
            }
        }


    }

    private void setEdgeMap(tree root, int i, tree[][] edgeMap) {
        if(root == null)
            return ;
        edgeMap[i][0] = edgeMap[i][0] == null ? root :edgeMap[i][0];
        edgeMap[i][i] = root;
        setEdgeMap(root.left, i+1, edgeMap);
        setEdgeMap(root.right,i+1, edgeMap);

    }
    if(root.left == null  && root.right == null && root != edgeMap[])
    private int getHeight(tree root, int i) {
        if (head == null) {
            return i;
        }
        return Math.max(getHeight(root.left, i+1)，getHeight(root.right, i+1)),

    }

    //序列化一个二叉树
    public String serialTreeByPreOrder(tree root) {
        if (root == null) {
            return "#!";
        }
        String res = root.value + "!";
        res += serialTreeByPreOrder(root.left);
        res += serialTreeByPreOrder(root.right);
        return res;
    }

    //反序列化二叉树
    public tree reconByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i != values.length; i++)
            queue.offer(values[i]);
        }
        return reconbYPreOrder(queue);
    }
    public tree reconbYPreOrder(Queue<String> queue) {
        String value =  queue.poll();
        if (value.equals("#")){
            return null;
        }
        tree head = new tree(Integer.valueOf(value));
        head.left = reconByPreString(queue);
        head.right = reconByPreString(queue);
        return head;
    }
//用层次遍历来序列化二叉树
    public String serialByLevel(tree root) {
        if (root == null) {
            return "#!";
        }
        String  res = root.value + "!";
        Queue<tree> queue = new LinkedList<tree>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                res += root.left.value + "!";
                queue.offer(root.left);
            }else {
                res += "#!";
            }
            if (root.right != null) {
                res += root.right.value + "!";
                queue.offer(root.right);
            }else {
                res += "#!";
            }
        }
        return res;
    }

    //利用层次遍历进行反序列化
    public tree reconByLevelString (String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        tree head = generateNodeByString (values[index++]);
        Queue<tree> queue = new LinkedList<tree>();
        if (head != null) {
            queue.offer(head);
        }
        tree node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left ! = null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }

        return head;
    }

    private tree generateNodeByString(String value) {
        if (value.equals("#")) {
            return null;
        }
        return new tree(Integer.valueOf(value));


    }

    //在二叉树中求指定累加和最长的路径长度
    public int getMaxLength(tree root, int sum) {
        HashMap<Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, 0);
        return preOrder(root, sum, 0, 1, 0, sumMap);
    }

    private int preOrder(tree root, int sum, int preSum, int level, int maxLen, HashMap<Integer, Integer> sumMap) {
        if(root == null) {
            return maxLen;
        }
        int curSum = preSum + root.value;
        if(!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if(sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }

        maxLen = preOrder(root.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(root.right, sum, curSum, level + 1, maxLen, sumMap);
        if(level == sumMap.get(curSum)) {
            sumMap.remove(curSum);
        }
        return maxLen;


    }

    public boolean contains(tree t1, tree t2) {
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    private boolean check(tree h, tree t2) {
        if (t2 == null)
            return true;
        if (h == null || h.value != t2.value) {
            return false;
        }
        return check(h.left, t2.left) && check(h.right, t2.right);
    }

    public boolean isSubtree (tree t1, tree t2) {
        String t1Str = serialByPreOrder(t1);
        String t2Str = serialByPreOrder(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    //KMP
    private int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < 1 || m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while(si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            }else if(next[mi] == -1){
                si++;
            }else{
                m1 = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    private int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int en = 0;
        while(pos < next.length) {
            if (ms[pos-1]== ms[en]) {
                next[pos++] = ++en;
            }else if(en > 0) {
                en = next[en];
            }else {
                next[pos+1] = 0;
            }
        }
        return next;
    }

    private String serialByPreOrder(tree t) {
        if (t == null) {
            return "#!"
        }
        String res = t.value + "!";
        res += serialByPreOrder(t.left);
        res += serialByPreOrder(t.right);
        return res;
    }

}

