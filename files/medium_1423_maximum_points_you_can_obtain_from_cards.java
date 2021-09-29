class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int cardPointsLen = cardPoints.length;
        
        int rightSum=cardPoints[cardPointsLen-1];

        // 오른쪽에서 k를 선택한 합을 구함
        for (int j = cardPointsLen - 2; j >= cardPoints.length - k; j--) {
            rightSum = rightSum + cardPoints[j];
        }
        if (cardPointsLen == k) {
            return rightSum;
        }

        int maxSum=rightSum;
        
        // 왼쪽에서 한개씩 선택을 늘여가고, 오른쪽에선 한개씩 선택을 빼면서 최대 합을 구함
        int leftSum = 0;
        for (int i = 0; i < k; i++) {
            leftSum = leftSum + cardPoints[i];
            rightSum = rightSum - cardPoints[cardPoints.length - k + i];
            int total = leftSum + rightSum;
            if (total > maxSum) {
                maxSum = total;
            }
        }
        return maxSum;
    }
}