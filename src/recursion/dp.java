package recursion;

//运用动态规划解决问题
public class dp {
    //问题描述：给出一个矩阵，只能向右和向下走，求从左上角（0,0）到右下角中路径和累加和最小的值
    public int minPathSum1 (int [][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[m.length][m[0].length];
        int sum = 0;
        for (int i = 0; i < m[0].length; i++) {
            sum += m[0][i];
            dp[0][i] = sum;
        }
        sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i][0];
            dp[i][0] = sum;
        }
        //dp[i][j] = min(dp[i-1][j] + m[i][j], dp[i][j-1] + m[i][j])
        for (int i = 1; i < m.length; i++)
            for(int j = 1; j< m[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
            }

        return dp[m.length - 1][m[0].length  - 1];
    }

    //用压缩空间的方法解决上述问题
    public int minPathSum2(int[][] m) {
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 0;
        }

        int rows = m.length;
        int cols = m[0].length;
        int more = rows > cols ? rows : cols;
        int less = rows < cols ? rows : cols;
        boolean rowless = rows < cols ? true : false;
        int[] dp = new int[less];
        dp[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i-1] + rowless ? m[i][0] : m[0][i];
        }
        for (int i = 1; i < more; i++) {
            dp[0] += rowless ? m[0][i] : m[i][0];
            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j] + rowless ? m[j][i] : m[i][j], dp[j-1] + rowless ? m[j][i] : m[i][j]);
            }
        }
        return dp[less - 1];
    }

    //给定指定面额，给出一个数sum，算出组成sum的最少纸币数量、
    public int minMoneyNum(int[] money, int sum) {
        if (money == null || money.length == 0 || sum < 0){
            return -1;
        }
        int n = money.length;
        int max =   Integer.MAX_VALUE;
        int[][] dp = new int[money.length][sum + 1];
        for (int j = 1; j <= sum; j++) {
            dp[0][j]  = max;
            if (j - money[0] >= 0 && dp[0][j - money[0] != max]){
                dp[0][j] = dp[0][j] - money[0] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++){
                left = max;
                if (j - money[i] >= 0 && dp[i][j-money[i]] != max) {
                    left = dp[i][j-money[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[n - 1][sum] != max ?dp[n - 1][sum] : 1;
    }

    //用压缩空间的方法来解决上述问题
    public int minMoneyNum2(int[] money, int sum) {
        if(money == null || money.length == 0 || sum < 0){
            return -1;
        }
        int[] dp = new int[sum + 1];
        int max = Integer.MAX_VALUE;
        for (int i = 1; i <= sum; i++) {
            dp[i] = max;
            if(i - money[0] >= 0 && dp[i-money[0]] != max){
                dp[i] = dp[i - money[0]] + 1;
            }
        }
        int temp = 0;
        for (int i = 1; i < money.length)
            for(int j = 1; j <= sum; j++) {
                temp = max;
                if (j - money[i] >= 0 && dp[j - money[i] != max]){
                    temp = dp[j - money[i]] + 1;
                }
                dp[j] = Math.min(temp, dp[j]);
        }
        return dp[sum] == max ? -1 : dp[sum];
    }

    //上述问题的修改版本：数组中的值可以重复，代表纸币值
    public int minCoins3(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][sum + 1];
        for (int j = 1; j <= sum; j++){
           dp[0][j] = max;
        }
        if(arr[0] <= sum) {
            dp[arr[0]] = 1;
        }
        int leftup = 0;

        for (int i = 1; i < n; i++) {
            for(int j = 1; j <= sum; j++){
                leftup = max;
                if(j - arr[i] >= 0 && dp[i - 1][j - arr[i]] != max){
                    leftup = dp[i - ][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(leftup, dp[i-1][j]);
            }
        }
        return dp[n - 1][sum] == max ? -1 : dp[n - 1][sum];
    }
    //压缩空间
    public int minCoinS3(int[] arr, int sum){
        if (arr == null || arr.length == 0 || sum < 0)
            return -1;
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[sum + 1];
        for (int i = 0; i <= sum; i++)
            dp[i] = max;
        if(arr[0] <= sum) {
            dp[arr[0]] = 1;
        }
        int temp = 0;
        for (int i = 1; i < n; i++)
            for (int j = 1; j <= sum; j++){
                temp = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max){
                    temp = dp[j - arr[i]] +1;
                }
                dp[j] = Math.min(temp, dp[j]);
            }
    }

    //有一个数组组成的纸币面值，和一个指定的找钱数，求一共有多少种找钱的方法
    //方法一：暴力递归
    public int coinsl(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0)
            return 0;
        return process(arr, 0, sum);
    }

    private int process(int[] arr, int index, int sum) {
        int res = 0;
        if (index == arr.length) {
            res = sum == 0 ? 1 : 0;
        }else{
            for (int i = 0; arr[index] * i <= sum; i++) {
                res += process(arr, index + 1, sum - arr[index] *i);
            }
        }
        return res;
    }

    //方法二、利用记忆搜索法对上述递归方法进行优化
    public int coins2(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0)
            return 0;
        int[][] map = new int[arr.length + 1][sum + 1];
        return process2(arr, 0, sum, map);
    }

    private int process2(int[] arr, int index, int sum, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = sum == 0 ? 1 : 0;
        }else{
            int mapValue = 0;
            for(int i = 0; arr[index] * i <= sum; i++) {
                mapValue = map[index + 1][sum - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                }else {
                    res += process2(arr, index + 1, sum - arr[index] * i,map);
                }
            }
        }
        map[index][sum] = res == 0 ? -1 : res;
        return res;
    }

    //方法三、使用动态规划的方法来进行计算
    public int coins3(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][sum + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= sum; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++)
            for(int j = 1; j <= sum; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        return dp[arr.length - 1][sum];
    }
    //方法四、简化上述步骤
    public int coins4(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0)
            return 0;
        int[][] dp = new int[arr.length][sum + 1];
        for(int i = 0; i < arr.length; i++)
            dp[i][0] = 1;
        for(int j = 1; sum - arr[0] * j >= 0; j++)
            dp[0][arr[0] * j] = 1;
        int num = 0;
        for (int i = 1; i < arr.length; i++)
            for(int j = 1; j <= sum) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        return dp[arr.length - 1][sum];
    }
    //方法五，上述方法加上空间压缩
    public int coins5(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int[] dp = new int[sum + 1];
        for (int j = 1; arr[0] * j <= sum; j++)
            dp[j * arr[0]] = 1;
        for (int i = 1; i < arr.length; i++)
            for (int j = 1; j <= sum; j++){
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        return dp[sum];
    }

    //求最长递增子序列
    public int[] subQuen(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }
    //上述方法是求得dp数组，那么如何根据dp求得最长递增子序列呢
    public int[] generateLIS(int[] dp,int[] arr)  {
        int max = arr[0];
        int maxIndex = 0;
        for (int i = 0; i < dp.length; i++){
            if (dp[i] > max){
                max = dp[i];
                maxIndex = i;
            }
        }
        int[] lis = new int[max];
        lis[lis.length - 1] = arr[maxIndex];
        int len = lis.length - 2;
        //从maxindex位置开始逆序查找
        int next = maxIndex;
        for (int i = maxIndex - 1; i >=0; i--){
            if (arr[i] < arr[next] && dp[i] = dp[next] - 1) {
                next = i;
                lis[len--] = arr[i];
            }
        }
        return lis;
    }

    //用优化的方法来解决计算dp数组
    public int[] getDp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        int right = 0;
        int len = 1;
        ends[0] = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int index = getPosition(ends,right);
            if (index > -1) {
                dp[i] = index + 1;
                ends[index] = arr[i];
            }else {
                ends[++len] = arr[i];
                dp[i] = len;
            }
        }
        return dp;
    }

    private int getPosition(int[] ends, int right) {

    }
    public int[] getdp(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l < = r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                }else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
    }
}
