class Solution {
    public int minIncrementForUnique(int[] nums) {

        /*
         * [전략] 1. 각 원소 값의 frequency를 계산하여, 값을 index로 frequency 값을 저장하는 배열을 생성하여 
         */

        if (nums.length < 2) {
            return 0;
        }
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxVal = Math.max(maxVal, nums[i]);
        }

        int[] freqNum = new int[maxVal + 2];

        for (int i = 0; i < nums.length; i++) {
            freqNum[nums[i]]++;
        }

        int increaseCount = 0;
        for (int i = 0; i <= maxVal; i++) {
            if (freqNum[i] > 1) {
                int increaseVal = freqNum[i] - 1;
                increaseCount += increaseVal;
                freqNum[i + 1] += increaseVal;
            }
        }
        for (int i = 1; i < freqNum[maxVal + 1]; i++) {
            increaseCount += i;
        }
        return increaseCount;
    }
}