class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001];
        for (int x : nums)
            count[x]++;
        int avoid = 0;
        int using = 0;
        int prev = -1;

        for (int k = 0; k <= 10000; ++k) {
            if (count[k] > 0) {
                int m = Math.max(avoid, using);
                // k번째를 포함하는 경우
                if (k - 1 != prev) { // 인접하지 않은 경우, 이전까지 최대 합 + k번째값
                    using = k * count[k] + m;
                } else { // 인접한 경우, 이전까지 최대값 - 이전 인접값
                    using = k * count[k] + avoid; // 이번값 + 인접원소를 더하지 않은 최대값
                }
                // k번째를 포함하지 않는 경우 => avoid = k를 제외한 이전까지의 최대합
                avoid = m;
                prev = k;
            }
        }
        return Math.max(avoid, using);
    }
}