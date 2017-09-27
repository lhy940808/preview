import java.util.LinkedList;

/**
 * Created by shixi_haiyan5 on 2017/8/19.
 */
public class maxsubmin {

    public int getNum(int[] arr,int num){
        if(arr == null || arr.length ==0){
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int i=0;
        int j=0;
        int res =0;
        while(i<arr.length){
            while(j<arr.length){
                while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]){
                    qmin.pollLast();
                }
                qmin.addLast(j);

                while(!qmax.isEmpty() && arr[qmax.peekLast() <= arr[j]]){
                    qmax.pollLast();
                }
                qmax.addLast();
                j++;
            }
            if(qmin.peekFirst() == i){
                qmin.pollFirst();
            }
            if(qmax.peekFirst() == i){
                qmax.pollFirst();
            }
            res += j-i;
            i++;
        }

    }


}
