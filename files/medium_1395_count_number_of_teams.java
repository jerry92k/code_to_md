/*
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

 

Example 1:
Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 


Example 2:
Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.

Example 3:
Input: rating = [1,2,3,4]
Output: 4

*/
/*
[포인트]
1. 각 숫자는 unique하다
2. 각 중심값을 기준으로 중심값보다 작은 범위에 해당하는 값들은 중심값 보다 작으면서 숫자 간 상하관계가 존재하여 연속적인 증가, 연속적인 하락의 쌍이 된다.

*/

class Solution {
    public int numTeamsDP(int[] rating) {

        int inputLen = rating.length;
        int[] ratingAES = new int[inputLen];
        int[] ratingDESC = new int[inputLen];

        int count = 0;
        for (int i = 1; i < rating.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[i] > rating[j]) {
                    ratingAES[i]++;
                    // rating[i]가 rating[j] 보다 커서 if문 안으로 들어온 것이므로, j를 기준으로 j보다 작은 애들의 갯수 만큼 생성
                    count += ratingAES[j];
                }
                if (rating[i] < rating[j]) {
                    ratingDESC[i]++;
                    count += ratingDESC[j];
                }
            }
        }

        return count;
    }

    public int numTeamsBruteForce(int[] rating) {
        int count = 0;
        for (int i = 0; i < rating.length - 2; i++) {
            for (int j = i + 1; j < rating.length - 1; j++) {
                if (rating[j] <= rating[i]) {
                    continue;
                }
                for (int k = j + 1; k < rating.length; k++) {
                    if (rating[k] > rating[j]) {
                        count++;
                    }
                }
            }
        }

        for (int i = 0; i < rating.length - 2; i++) {
            for (int j = i + 1; j < rating.length - 1; j++) {
                if (rating[j] >= rating[i]) {
                    continue;
                }
                for (int k = j + 1; k < rating.length; k++) {
                    if (rating[k] < rating[j]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}