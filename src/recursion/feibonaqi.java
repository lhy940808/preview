package recursion;

//斐波那契数列系列问题
//例如：1 1 2 3 5 f(n)= f(n-1) + f(n-2)
public class feibonaqi {
    //递归解决
    public int f1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2 );
    }
    //按照顺序计算出每一项的值
    public int f2(int n) {
        if(n < 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }

        int pre = 1;
        int res = 1;
        int temp = 0;
        for (int i=3; i <= n; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }
        return res;
    }

}
