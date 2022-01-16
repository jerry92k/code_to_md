import java.util.*;

class Solution {

    public int knightDialer(int n) {

        if (n == 1) {
            return 10;
        }
        int[][] dials = { { 1, 2, 3 }, { 4, 5, 6, }, { 7, 8, 9 }, { -1, 0, -1 } };

        HashMap<Integer, ArrayList<Integer>> dialstoNextDial = new HashMap<>();
        Queue<Integer> nextDialqueue = new LinkedList<>();

        for (int i = 0; i < dials.length; i++) {
            for (int j = 0; j < dials[i].length; j++) {
                if (dials[i][j] < 0) {
                    continue;
                }

                ArrayList<Integer> nextDials = checkAllNextDialPossible(dials, i, j);

                nextDialqueue.addAll(nextDials);

                dialstoNextDial.put(dials[i][j], nextDials);

            }
        }

        for (int k = 3; k <= n; k++) {
            int queueSize = nextDialqueue.size();
            for (int i = 0; i < queueSize; i++) {
                nextDialqueue.addAll(dialstoNextDial.get(nextDialqueue.remove()));
            }
        }
        int divideVal = 1000000007;
        return nextDialqueue.size() % divideVal;
    }

    public ArrayList<Integer> checkAllNextDialPossible(int[][] dials, int i, int j) {

        ArrayList<Integer> nextDials = new ArrayList<Integer>();

        checkNextDialPossible(dials, i - 2, j + 1, nextDials);
        // -1 up + 2 right
        checkNextDialPossible(dials, i - 1, j + 2, nextDials);

        // 2 up + 1 right
        checkNextDialPossible(dials, i + 2, j + 1, nextDials);
        // 1 up + 2 right
        checkNextDialPossible(dials, i + 1, j + 2, nextDials);

        // -2 up - 1 right
        checkNextDialPossible(dials, i - 2, j - 1, nextDials);
        // -1 up - 2 right
        checkNextDialPossible(dials, i - 1, j - 2, nextDials);
        // 2 up - 1 right
        checkNextDialPossible(dials, i + 2, j - 1, nextDials);
        // 1 up - 2 right
        checkNextDialPossible(dials, i + 1, j - 2, nextDials);

        return nextDials;

    }

    public void checkNextDialPossible(int[][] dials, int horizonIdx, int verticalIdx,
            ArrayList<Integer> nextPossibleDials) {
        if (horizonIdx >= 0 && horizonIdx < dials.length && verticalIdx >= 0 && verticalIdx < dials[0].length) {
            int nextDial = dials[horizonIdx][verticalIdx];
            if (nextDial >= 0) {
                nextPossibleDials.add(nextDial);
            }
        }
    }

    public int knightDialer2(int n) {

        if (n == 1) {
            return 10;
        }
        int[][] dials = { { 1, 2, 3 }, { 4, 5, 6, }, { 7, 8, 9 }, { -1, 0, -1 } };

        // HashMap<Integer, ArrayList<Integer>> dialstoNextDial = new HashMap<>();
        // Queue<Integer> nextDialqueue = new LinkedList<>();

        int count = 0;
        int divideVal = 1000000007;
        for (int i = 0; i < dials.length; i++) {
            for (int j = 0; j < dials[i].length; j++) {
                if (dials[i][j] < 0) {
                    continue;
                }
                count += dfsNextDial(dials, i, j, n, 1) % divideVal;
            }
        }

        return count;
    }

    public int dfsNextDial(int[][] dials, int horizonIdx, int verticalIdx, int n, int curTime) {

        if (horizonIdx >= 0 && horizonIdx < dials.length && verticalIdx >= 0 && verticalIdx < dials[0].length
                && dials[horizonIdx][verticalIdx] >= 0) {
            if (curTime == n) {
                return 1;
            } else {
                return dfsNextDial(dials, horizonIdx - 2, verticalIdx + 1, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx - 1, verticalIdx + 2, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx + 2, verticalIdx + 1, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx + 1, verticalIdx + 2, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx - 2, verticalIdx - 1, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx - 1, verticalIdx - 2, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx + 2, verticalIdx - 1, n, curTime + 1)
                        + dfsNextDial(dials, horizonIdx + 1, verticalIdx - 2, n, curTime + 1);
            }
        }
        return 0;
    }

    public int knightDialer3(int n) {

        if (n == 1) {
            return 10;
        }
        int[][] dials = { { 1, 2, 3 }, { 4, 5, 6, }, { 7, 8, 9 }, { -1, 0, -1 } };

        HashMap<Integer, ArrayList<Integer>> dialstoNextDial = new HashMap<>();

        for (int i = 0; i < dials.length; i++) {
            for (int j = 0; j < dials[i].length; j++) {
                if (dials[i][j] < 0) {
                    continue;
                }
                ArrayList<Integer> nextDials = checkAllNextDialPossible(dials, i, j);
                dialstoNextDial.put(dials[i][j], nextDials);

            }
        }
        int divideVal = 1000000007;
        int[][] dp = new int[n][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else {
                    if (dp[i - 1][j] > 0) {
                        ArrayList<Integer> nextDials = dialstoNextDial.get(j);
                        for (int t = 0; t < nextDials.size(); t++) {
                            int prevDialTime = nextDials.get(t);
                            dp[i][prevDialTime] = (dp[i][prevDialTime] + dp[i - 1][j]) % divideVal;
                        }
                    }
                }
            }

        }
        int sum = 0;
        for (int j = 0; j < 10; j++) {
            sum = (sum + dp[n - 1][j]) % divideVal;
        }

        return sum;
    }

}
