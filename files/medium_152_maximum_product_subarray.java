class Solution {

    // Brute force
    public int maxProduct(int[] nums) {

        int maxProd = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int preProd = 1;
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    preProd = nums[j];
                } else {
                    preProd = preProd * nums[j];
                }
                maxProd = Math.max(maxProd, preProd);
            }
        }
        return maxProd;
    }

    // DP 

    public int maxProduct2(int[] nums) {
        if (nums.length == 0)
            return 0;

        int maxAccProd=nums[0];
        int minAccProd = maxAccProd;
        int maxProd = maxAccProd;

        for (int i = 1; i < nums.length; i++) {
            
            int currVal = nums[i];

            int tempMax= Math.max(maxAccProd * currVal, Math.max(minAccProd * currVal, currVal));
            minAccProd = Math.min(maxAccProd * currVal, Math.min(minAccProd * currVal, currVal));
            maxAccProd = tempMax;

            maxProd = Math.max(maxAccProd, maxProd);

        }
    }
}