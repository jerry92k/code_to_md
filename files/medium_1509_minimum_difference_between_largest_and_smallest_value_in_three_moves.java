
/*
 [전략] 
 1. 배열의 원소 갯수가 4개 이하이면 하나의 값으로 통일할수 있으므로 항상 최소 차는 0
 2. 배열을 정렬하여 3개의 값을 변경하였을때 최소 차 후보들을 구함
 3. 최소 차가 되기 위해서는 배열의 작은 값들을 증가시키거나 큰값을 감소시켜야함. 
 [배열 앞 뒤 변경 경우의 수]
 0 3 / 1 2 / 2 1 / 3 0 
*/

import java.util.*;

class Solution {
    public int minDifference(int[] nums) {

        int numsLen = nums.length;
        if (numsLen <= 4) {
            return 0;
        }

        Arrays.sort(nums);

        int min = nums[numsLen - 1] - nums[0];

        for (int i = 0; i <= 3; i++) {
            min = Math.min(min, nums[numsLen - 4 + i] - nums[i]);
        }
        return min;
    }
}
