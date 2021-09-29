
class Solution {
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int twoPrevSum = nums[0];
        int prevSum = Math.max(nums[0], nums[1]);
        int sum = prevSum;

        for (int i = 2; i < nums.length; i++) {
            sum = Math.max(twoPrevSum + nums[i], prevSum);
            twoPrevSum = prevSum;
            prevSum = sum;
        }

        return sum;
    }
}