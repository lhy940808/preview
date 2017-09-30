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
}
