import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // HashMap<Integer, Integer> hashMap = new HashMap<>();

        Arrays.sort(nums);
        List<List<Integer>> sumList = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                // iterateSum(nums, i, sumList);
                // iterateHashSum
                iterateHashSum(nums, i, sumList);
            }
        }

        return sumList;
    }

    public void iterateSum(int[] nums, int i, List<List<Integer>> sumList) {

        int lo = i + 1;
        int hi = nums.length - 1;

        while (lo < hi) {
            if (lo > i + 1 && nums[lo] == nums[lo - 1]) {
                lo++;
            } else if (hi < nums.length - 1 && nums[hi] == nums[hi + 1]) {
                hi--;
            } else {
                int tempSum = nums[i] + nums[lo] + nums[hi];
                if (tempSum < 0) {
                    lo++;
                } else if (tempSum > 0) {
                    hi--;
                } else {
                    sumList.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                }
            }
        }
    }

    public void iterateHashSum(int[] nums, int i, List<List<Integer>> sumList) {

        HashSet<Integer> hashset = new HashSet<Integer>();

        // for (int j = i + 1; j < nums.length; j++) {
        int j = i + 1;
        while (j < nums.length) {

            int complement = -nums[i] - nums[j];
            if (hashset.contains(complement)) {
                sumList.add(Arrays.asList(nums[i], complement, nums[j]));
                hashset.add(nums[j++]);
                while (j < nums.length - 1 && nums[j - 1] == nums[j]) {
                    j++;
                }
            } else {
                hashset.add(nums[j++]);
            }

            // }
        }
    }
}