/*
Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

 

Example 1:

Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.

*/

class Solution {
    public int integerBreak(int n) {

        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int count = 0;
        while (n > 3) {
            n = n - 2;
            count++;
        }

        return n * (int) Math.pow(2, count);
    }
}