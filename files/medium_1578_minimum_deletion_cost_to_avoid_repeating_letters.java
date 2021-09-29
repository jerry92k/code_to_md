
// [전략] : 연속되는 같은 문자열에서 가장 큰 cost를 제외하고 나머지는 cost 계산에 더한다.
class Solution {
    public int minCost(String s, int[] cost) {

        int costSum = 0;
        char preCh = s.charAt(0);
        int subMaxVal = cost[0];
        for (int i = 1; i < s.length(); i++) {
            char curCh = s.charAt(i);
            if (curCh != preCh) {
                subMaxVal = cost[i];
                preCh = curCh;
            } else {
                if (subMaxVal < cost[i]) {
                    costSum += subMaxVal;
                    subMaxVal = cost[i];
                } else {
                    costSum += cost[i];
                }
            }

        }
        return costSum;
    }
}