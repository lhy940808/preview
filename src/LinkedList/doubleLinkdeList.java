package LinkedList;

/**
 * Created by shixi_haiyan5 on 2017/8/26.
 */
public class doubleLinkdeList {

    public DoubleNode removeLastKthNode(DoubleNode head,int lastKth){
        if(head == null || lastKth < 1){
            return head;
        }

        DoubleNode cur = head;
        while(cur!=null){
            lastKth--;
            cur = cur.next;
        }

        if(lastKth == 0){
            return head.next;
        }


        if(lastKth < 0){
            cur = head;
            while(++lastKth != 0){
                cur = cur.next;
            }
            DoubleNode next_node = cur.next.next;
            cur.next = next_node;
            if(next_node!=null){
                next_node.last = cur;
            }
        }


    }


    //翻转双向链表
    public DoubleNode reverseList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
    }
}

class DoubleNode{
    public int value;
    public DoubleNode last;
    public DoubleNode next;
    public DoubleNode(int data){
        this.value = data;
    }
}
