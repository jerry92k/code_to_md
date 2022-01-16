import java.util.HashMap;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        /*
         * index 0 1 2 3 4 value 23, 2, 4, 6, 7 sum[i:j], i<j, 를 인덱스, i에서 j까지 해당하는
         * subarray의 합이라고 표현하면 모든 subarray 집합 sum[i:j]은 sum1[0:j1] or sum1[0:j1] -
         * sum2[0:j2] 로 표현할 수 있다. ( j1 -1 > j2 : subarray길이가 최소 2여야 하므로).
         * (sum1[0:j1]-sum2[0:j2])%k = 0 이 성립하는 sum1과 sum2가 존재하면 된다. 
         * sum1[i:j]%k = sum2[i2:j2]%k 
         */
        // 5, 0, 0, 0 , 3
         HashMap<Integer,Integer> subArrSum= new HashMap<>();

         int accmulSum=nums[0];
         subArrSum.put(accmulSum%k,0);
         for (int i = 1; i < nums.length; i++) {
             accmulSum=accmulSum+nums[i];
             int mod = accmulSum % k;
             if (mod == 0) {
                 return true;
             }
             if (subArrSum.containsKey(mod) && i - subArrSum.get(mod) >= 2) {
                 return true;
             }
             subArrSum.putIfAbsent(mod, i);
         }
         return false;

    }
    //시간초과
    public boolean checkSubarraySum2(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            int subArrSum = nums[i];
            
            for (int j = i+1; j < nums.length; j++) {
                subArrSum = subArrSum + nums[j];
                if (subArrSum==0 || subArrSum >= k && ((subArrSum % k) == 0)) {
                    return true;
                }
            }
        }
        return false;

    }
}