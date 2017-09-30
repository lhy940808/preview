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
}
