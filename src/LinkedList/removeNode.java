package LinkedList;

import java.util.*;

/**
 * Created by shixi_haiyan5 on 2017/8/25.
 */
public class removeNode {

    //删除单链表中的倒数第k个节点
    public Node removeLastKthNode(Node head,int lastKth){
        if(head == null || lastKth < 1){
            return head;
        }
        Node cur = head;
        while(cur!=null){
            cur = cur.next;
            lastKth--;
        }
        if(lastKth == 0){
            head = head.next;
        }
        if(lastKth < 0){
            cur = head;
            while(++lastKth != 0){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;

    }

    //删除链表中的中间节点
    public Node removeMiddleNode(Node head){
        if(head == null || head.next == null){
            return head;
        }

        if(head.next.next == null){
            return head.next;
        }

        Node pre = head;
        Node cur =head.next.next;
        while(cur.next != null && cur.next.next != null){
            pre = pre.next;
            cur =  cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }



    //删除弟a/b
    public Node removeByRatio(Node head,int a,int b){
        if(a < 1 || a >b ){
            return head;
        }

        int n=0;
        Node cur = head;
        while(cur != null){
            n++;
            cur = cur.next;
        }

        n =(int)Math.cell(((double)(a*b)) / (double)b);
        if(n == 1){
            head = head.next;
        }
        if(n>1){
            cur = head;
            while(--n != 1){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;

    }


    //翻转单向链表
    public Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next =pre;
            pre = head;
            head =next;
        }
        return pre;
    }


    //反转from-to部分的链表
    public Node reversePartList(Node head,int from,int to){
        int len = 0;
        Node node1 = head;
        Node tPre = null;
        Node tPos = null;
        while(node1 != null){
                len++;
                tPre = len == from -1 ?node1:tPre;
                tPos = len == to + 1 ? node1:tPos;
                node1 = node1.next;
        }
        if(from > to || from <1 || to > len){
            return head;
        }
        node1 = tPre == null ?head:tPre.next;
        Node node2 = node1.next;
        node1.next = tPos;
        Node next = null;
        while(node2 != tPos){
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 =next;
        }
        if(tPre != null){
            tPre.next = node1;
            return head;
        }
        return head;

    }

    //环形链表删除问题
    public Node josephusKill(Node head, int m){
        if(head == null || head.next == head || m < 1){
            return head;
        }

        Node last  = head;
        while(last.next != head){
            last = last.next;
        }

        int count = 0;
        while(head != last){
            if(++count == m){
                last.next = head.next;
                count = 0;
            }else{
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    public  Node josephusKill2(Node head, int m){
        if(head == null || head.next == null || m < 1){
            return head;
        }

        Node cur = head.next;
        int tmp = 1;//tmp -> list size
        while(cur != head){
            tmp++;
            cur = cur.next;
        }

        tmp = getLive(tmp,m);
        while(--tmp != 0){
            head = head.next;
        }
        head.next = head;
        return head;

    }

    public int getLive(int i, int m){
        if(i == 1){
            return 1;
        }
        return (getLive(i-1,m)+m-1)%i+1;
    }


    //判断一个链表是否是回文结构
        public boolean isPallndromel(Node head){
            Stack<Node> stack = new Stack<Node>();
            Node cur = head;
            while (cur != null){
                stack.push(cur);
                cur = cur.next;
            }
            while( head != null ){
                if(head.value != stack.pop().value){
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        //方法二：优化版的判断回文结构
    public boolean isPallndrome2(Node head){
            if(head == null || head.next == null){
                return true;
            }
            Node right = head.next;
            Node cur = head;
            while(cur.next != null && cur.next.next != null){
                right = right.next;
                cur = cur.next.next;
            }
            Stack<Node> stack = new Stack<Node>;
            while (right != null){
                stack.push(right);
                right = right.next;
            }
            while(!stack.isEmpty()){
                if(head.value != stack.pop().value){
                    return false;
                }
                head = head.next;
            }
            return true;
    }



    //方法三、判断是否是回文结构
    public boolean isPallndrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null ){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;

        while (n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean res = true;
        while(n1 != null && n2 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while(n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;


    }


    //使一个单向链表左边小于一个数，中间等于一个数 ，右边大于一个数
    public Node listPartition(Node head, int pivot){
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }

        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for(i = 0; i != nodeArr.length; i++){
                nodeArr[i] = cur;
                cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for(i = 1; i != nodeArr.length; i++){
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i-1].next = null;
        return nodeArr[0];
    }

    public void arrPartition(Node[] nodeArr, int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while(index != big) {
            if(nodeArr[index].value < pivot){
                swap(nodeArr, ++small, index++);
            }else if(nodeArr[index].value == pivot){
                index++;
            }else{
                swap(nodeArr, --big, index);
            }
        }
    }

    public void swap(Node[] nodeArr, int left, int right){
        Node temp = nodeArr[left];
        nodeArr[left] = nodeArr[right];
        nodeArr[right] = temp;
    }

    //优化的划分方法：实现空间复杂度为O(1)，也就是只用几个变量来解决划分问题
    public static Node listPartition2(Node head, int pivot){
        Node sH = null;//small head
        Node sT = null;//small tail
        Node eH = null;//equal head
        Node eT = null;//equal tail
        Node bH = null;//big head
        Node bT = null;//big tail
        Node next = null;//save next node

        while (head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else{
                if(bH == null){
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        //small and big reconnect

        if(sT != null){
            sT.next = eH;
            eT = eT == null ? sT : eT;

        }
        if(eT != null){
            eT.next = bH;
        }
        return sH != null ?sH : eH != null ? eH : bH;

    }

    public Node copyListWithRand1(Node head){
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while(cur != head){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);

    }

    public Node copyListWithRand2 (Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        while(cur != head){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        Node curCopy = null;
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ？next.next : null;
            cur = next;
        }
        return res;
    }


    //两个单链表相加
    public Node addList(Node head1, Node head2){
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        while (head1 != null){
            s1.push(head1.value);
            head1 = head1.next;
        }

        while (head2 != null){
            s2.push(head2.value);
            head2 = head2.next;
        }

        int ca = 0;//表示进位信息
        int n1 = 0;
        int n2 = 0;
        int n =0;
        Node node = null;
        Node pre = null;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }
        if(ca == 1){
            pre = node;\
            Node node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    public Node addList2(Node head1, Node head2) {
        //1、先将两个表逆序排列
        Node pre = null;
        Node next1 = null;
        while(head1 != null){
            next1 = head1.next;
            head1.next = pre;
            pre = head1;
            head1 = next1;
        }
        Node pre = null;
        Node next2 = null;
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        while(head2 != null){
            next2 = head2.next;
            head2.next = pre;
            pre = head2;
            head2 = next2;
        }
        Node head = null;
        pre = null;
        while(head1 != null || head2 != null){
            n1 = head1 == null ? 0 : head1.value;
            n2 = head2 == null ? 0 : head2.value;
            n = n1 + n2 + ca;
            pre = head;
            head = new Node(n % 10);
            head.next = pre;
            ca = n / 10;
            head1 = head1.next;
            head2 = head2.next;
        }
        if( ca == 1){
            pre = head;
            head = new Node(1);
            head.next = pre;
        }
        return head;
    }


    public Node getLoopNode (Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast ) {
            if(fast.next == null || fast.next.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow ! = fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }

    //判断两个无环的链表是否相交
    public  Node noLoop(Node head1, Node head2) {
        if(head1 == null || head2 == null)
            return null;
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0
        while(cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null) {
            n==;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }
        cur1 = n > 0 ?head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while ( n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while( cur1 != cur2 ) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //判断两个有环的链表是否相交
    public Node bothLoop (Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 - cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while( n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while(cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public Node getIntersectNode (Node head1 ,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null ) {
            return noLoop(head1, head2);
        }
        if(loop1 != null && loop2 !=null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    //将单链表每k个节点进行逆序
    public Node reverseKNode1 (Node head, int k) {
        if(k < 2) {
            return head;
        }
        Stack<Node> stack = new Stack<Node>();
        Node newHead = head;
        Node cur = head;
        Node next = null;
        Node pre = null;
        while(cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k){
                pre = resign(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    private Node resign(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if(left != null){
            left.next = cur;
        }
        Node next = null;
        while(!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    public Node reverseKnode2(Node head, int k) {
        if(k <2) {
            return head;
        }
        Node cur = head;
        Node start = null;
        Node next = null;
        Node pre = null;
        int count = 1;
        while(cur !=  null) {
            next = cur.next;
            if(count == k) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    private void resign2(Node left, Node start, Node cur, Node right) {
        Node next = null;
        Node pre = right;

        while(start != cur){
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        if(left != null){
            left.next = cur;
        }
    }
//删除单链表中的重复元素
    public void removeRep1 (Node head) {
        if( head == null )
             return;
        HashSet<Integer> set = new HashSet<Integer>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while (cur != null) {
            if(set.contains(cur.value)){
                pre.next = cur.next;
            }else {
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public void removeRep2 (Node head) {
        if(head == null) {
            return;
        }
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null){
                if(next.value == cur.value){
                    pre.next =  next.next;
                }else {
                    pre = next;
                }
                next = next.next;

            }
            cur = cur.next;
        }

    }

    //将一颗搜索二叉树zhua转换为双链表
    public DNode convert1 (DNode head) {
        Queue<DNode> queue = new LinkedList<DNode>();
        InOrderToQueue(head, queue);
        if (queue.isEmpty()){
            return head;
        }
        head = queue.poll();
        DNode pre = head;
        pre.left =  null;
        DNode cur = null;
        while ( !queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;

    }

    private void InOrderToQueue(DNode head, Queue<DNode> queue) {
        if(head == null){
            return;
            InOrderToQueue(head.left, queue);
            queue.offer(head);
            InOrderToQueue(head.right, queue);
        }
    }
    public DNode convert2 (DNode head) {
        if(head == null) {
            return null;
        }
        DNode last = process(head);
        head = last.right;
        last.right = null;
        returnr head;
    }

    private LinkedList.DNode process(DNode head) {
        if(head == null) {
            return  null;
        }
        DNode leftE = process(head.left);
        DNode rightE = process(head.right);
        DNode leftS = leftE != null ? leftE.right : null;
        DNode rightS = rightE != null ? rightE.right : null;
        if(leftE ! = null && rightE !=null) {
            leftE.right = head;
            head.left = leftE;
            head.right = rightS;
            rightS.left = head;
            rightE.right = leftS;
            return rightE;
        }else if( leftE != null) {
            leftE.right = head;
            head.left = leftE;
            head.right = leftS;
            return head;
        }else if(rightE != null ) {
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return  rightE;
        }else {
            head.right = head;
            return head;
        }
    }

    //使用选择排序给单链表排序
    public static Node selectionSort(Node head) {
        Node tail = null;
        Node cur = head;
        Node smallPre = null;
        Node small = null;
        while (cur != null) {
            small = cur;
            smallPre = getSmallPreNode(cur);
            if(smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            cur = cur == small ? cur.next : cur;
            if(tail == null) {
                head = small;
            }else {
                tail.next = small;
            }
            tail = small
        }
        return head;
    }

    private static LinkedList.Node getSmallPreNode(Node head) {
        Node smallPre = null;
        Node small = head;
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if(cur.value < small.value) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

//在不知道头结点的情况下，删除某个节点
    public void removeNodeWired (Node node) {
        if(node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("can not remove last node.");
        }
        node.value = node.next.value;
        node.next = node.next.next;
    }

    public Node InsertNum (Node head, int num) {
        Node node = new Node(num);
        if(head == null) {
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if(pre.value < node.value && cur.value > node.value){
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.value < num ? head : node;

    }
    public Node mergeList(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node head = null;
        Node cur1 = head;
        Node cur2 = head;
        if(head1.value > head2.value) {
                head = head2;
                cur2 = cur2.next;
        }else {
            head = head1;
            cur1 = cur1.next;
        }
        Node cur = head;
        while(cur1 !=null && cur2 != null){
            if(cur1.value > cur2.value) {
                cur.next = cur2;
                cur2 = cur2.next;
            }else {
                head.next = cur1;
                cur1 = cur1.next;
            }
            cur = cur.next;
        }

        if(cur1 != null){
            cur.next = cur1;
        }
        if(cur2 != null){
            cur.next = cur2;
        }
        return head;
    }
    //将链表的左右部分，交叉连接
    public Node relocate(Node head) {
        if(head == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node cur = head;
        Node right = slow.next;
        slow.next = null;
        Node next = null;
        while (cur.next != null) {
            next = right.next;
            right.next = cur.next;
            cur.next = right;
            cur = right.next;
            right = next;
        }
        slow.next = right;
    }
}
class DNode {
    public int value;
    public DNode left;
    public DNode right;
    public DNode(int data) {
        this.value = data;
    }
 }
