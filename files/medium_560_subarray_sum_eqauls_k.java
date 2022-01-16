/*
[전략]
dp 방법으로 모든 축적 값을 계산해봄
*/
class Solution {
    public int subarraySum(int[] nums, int k) {

        int count=0;
        for (int i = 0; i < nums.length; i++) {
            int accSum = 0;
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    accSum = nums[j];
                } else {
                    accSum = accSum + nums[j];
                }
                if (accSum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}

/*
[전략]
- 배열을 순회하며 축적값을 계산하고, HashMap에 저장
- 해당 index에서의 축적값 - k 한 값이 HashMap에 존재하면, 이전의 특정 idex 까지의 sum을 제외하면    
  k와 같은 연속적인 합이 되므로 count증가. 
- index t와 index t+q 까지의 sum이 같을수 있고 이 경우에 각각의 case로 count 해야하므로 HashMap에 저장할때 개별 카운팅해줌
*/
public class Solution2 {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

/*
 * value 1 2 1 2 1
 *   sum 1 3 4 6 7 
 *sum-k -2 0 1 3 4  
 */