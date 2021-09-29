import java.util.PriorityQueue;

/*
[전략]
1. 2n명의 사람이 모두 city B로 갔을때의 합을 구한다.
2. (A를 선택해서 추가되는 비용 - B를 선택하지 않아서 감소되는 비용)으로 priorityqueue를 구성한다.
3. 전체 합이 최소화 되는 방향으로 n명이 city A를 선택하도록 변경한다.
*/

class Solution {
    public int twoCitySchedCost(int[][] costs) {

        int minSumB = 0;
        for (int i = 0; i < costs.length; i++) {
            minSumB += costs[i][1];
        }

        PriorityQueue<Integer> abDiff = new PriorityQueue<>();

        for (int i = 0; i < costs.length; i++) {
            abDiff.add(costs[i][0] - costs[i][1]);
        }

        for (int i = 0; i < costs.length / 2; i++) {
            minSumB += abDiff.poll();
        }
        return minSumB;

    }

}