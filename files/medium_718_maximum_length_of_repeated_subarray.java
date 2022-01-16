import java.util.Arrays;

class Solution {

    // DP 이용
    public int findLength(int[] nums1, int[] nums2) {
        int maxSubLength = 0;

        int[][] dp = new int[nums1.length][nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = i>0 && j > 0 ? dp[i-1][j - 1] + 1 : 1;
                    maxSubLength = Math.max(maxSubLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxSubLength;
    }

    public int findLength2(int[] nums1, int[] nums2) {
        int maxSubLength = 0;

        boolean[][] dp = new boolean[nums1.length][nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (dp[i][j]) {
                    continue;
                }

                int subCount = 0;
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = true;

                    int trasalNum1Idx = i + 1;
                    int trasalNum2Idx = j + 1;
                    subCount = 1;
                    while (trasalNum1Idx < nums1.length && trasalNum2Idx < nums2.length
                            && nums1[trasalNum1Idx] == nums2[trasalNum2Idx]) {
                        dp[trasalNum1Idx][trasalNum2Idx] = true;
                        subCount++;

                        trasalNum1Idx++;
                        trasalNum2Idx++;
                    }
                    maxSubLength = Math.max(maxSubLength, subCount);
                }
            }
        }
        return maxSubLength;
    }
}