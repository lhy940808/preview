package LinkedList;

/**
 * Created by shixi_haiyan5 on 2017/8/25.
 */
public class commonPart {

    public void printCommonPart(Node head1,Node head2){
        System.out.println("Common part:");
        while(head1 != null && head2 != null){
            if(head1.value <head2.value){
                head1 = head1.next;
            }else if(head1.value > head2.value){
                head2 = head2.next;
            }else{
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

}

class Node{
    public int value;
    public Node next;
    public Node(int data){
        this.value = data;
    }
}
