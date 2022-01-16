class Solution {

    // [전략] for문을 순회하면서,
    // 인덱스 0에서부터 시작하는 연속적인 마이너스 축적값을 계산하고
    // 연속적인 합이 마이너스가 되는 지점 이후 축적된 플러스 축적값을 계산하여
    // 순회가 끝나면 플러스 축적값 - 마이너스축적값을 뺀 값이 0이상이면 promise
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int stationLength = gas.length;
        int preMinusSum = 0;
        int plusSum = 0;
        int startPoint = 0;
        for (int i = 0; i < stationLength; i++) {
            int curDiff = gas[i] - cost[i];
            if (plusSum + curDiff >= 0) {
                plusSum += curDiff;
            } else {
                preMinusSum += plusSum + curDiff;

                plusSum = 0;
                startPoint = (i + 1) % stationLength;
            }
        }

        if (preMinusSum + plusSum >= 0) {
            return startPoint;
        }
        return -1;
    }

    // brute force
    public int canCompleteCircuit2(int[] gas, int[] cost) {

        int stationLength = gas.length;

        for (int i = 0; i < stationLength; i++) {
            int remainGas = gas[i] - cost[i];
            if (remainGas >= 0) {
                int j = (i + 1) % stationLength;
                while (j != i) {
                    remainGas = remainGas + gas[j] - cost[j];
                    if (remainGas < 0) {
                        break;
                    }
                    j = (j + 1) % stationLength;
                }
                if (i == j) {
                    return i;
                }
            }
        }
        return -1;
    }
}