package array;

import java.util.HashMap;

/**
 * Created by shixi_haiyan5 on 2017/9/12.
 */
public class arrayQuestion {

    //求未排序的数组中累加和为指定值得最大子数组问题
    public int maxLength (int[] array, int k) {
        if (array.length <= 0 || array == null) {
            return 0;
        }
        int sum = 0;
        int len = 0;
        //key位第一次出现的sum值，value为对应最早出现的位置
        HashMap<Integer, Integer>  map = new HashMap<Integer, Integer>();
        map.put(0,-1);
        for (int i = 0; i <  array.length;i++) {
            sum += array[i];
            if (map.containsKey(sum - k)){
                len = Math.max(len, i - map.get(sum - k));
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
    //未排序的数组中累加和小于等于某一个值得最长子数组
        public int maxLength2(int[] arr, int k) {
            int [] h = new int[arr.length];
            int sum = 0;
            h[0] = sum;
            for (int i = 0; i < arr.length; i++){
                sum += arr[i];
                h[i] = Math.max(h[i], sum);
            }
            sum = 0;
            int res = 0;
            int pre = 0;
            int len = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                pre = getLeastIndex(h, sum-k);
                len = pre == -1 ? 0 : i - pre + 1;
                res = Math.max(res, len);
            }
            return res;

        }

    private int getLeastIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid -1;
            }else {
                low = mid + 1;
            }
        }
        return mid;

    }
}
