import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {

        int[] countForVal = new int[amount + 1];

        Arrays.sort(coins);

        for (int i = 1; i < countForVal.length; i++) {
            calculateCount(i, countForVal, coins);
        }
        return countForVal[amount];
    }

    public void calculateCount(int subAmount, int[] countForVal, int[] coins) {

        int coinCount = -1;
        for (int i = 0; i < coins.length; i++) {
            int remainVal = subAmount - coins[i];
            if (remainVal < 0) {
                continue;
            } else if (remainVal == 0) {
                countForVal[subAmount] = 1;
                return;
            } else {
                if (countForVal[remainVal] > 0 && (coinCount == -1 || coinCount > countForVal[remainVal])) {
                    coinCount = countForVal[remainVal];
                }
            }
        }
        countForVal[subAmount] = coinCount == -1 ? -1 : coinCount + 1;
    }
}