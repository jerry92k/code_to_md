import java.util.HashMap;
import java.util.Map;

/*
Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

 

Example 1:


Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. 
graph[i][j] = 1 means person i knows person j, 
    otherwise graph[i][j] = 0 means person i does not know person j. 
The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.

*/

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {

    // brute force
    public int findCelebrity2(int n) {

        for (int i = 0; i < n; i++) {
            boolean isCelebrity = true;

            // i가 celebrity인지 확인
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                // i가 j를 알거나, j가 i를 모르면
                if (knows(i, j) || !knows(j, i)) {
                    isCelebrity = false;
                    break;
                }
            }
            if (isCelebrity) {
                return i;
            }
        }
        return -1;
    }

    // logical deduction
    public int findCelebrity(int n) {

        int celebrityCandi = 0;

        // 노드를 순회하며 celebrity 후보를 찾음.
        for (int i = 1; i < n; i++) {
            // celebrity 후보가 아는 사람이 생길 경우에는 더이상 후보가 될 수 없음.
            // celebrity 후보가 아는 사람은 새로운 celebrity 후보가 됨

            if (knows(celebrityCandi, i)) {
                celebrityCandi = i;
            }
            // celebrity 후보가 모르는 사람은, 최소 1명이상에게 모르는 사람이 되는 것이므로 celebrity 후보가 될 수 없음
        }
        // 1. 나머지 노드(celebrity 이전 + celebrity 이후)들이 celebrity를 모두 아는지는 모르니 다음 for문에서
        // 체크해봐야하고,
        // 2. celebrity 후보 이전 노드들을 celebrity가 아는지는 위에서 체크하지 못했으므로 다음 for문에서 체크해봐야함
        for (int i = 0; i < n; i++) {
            if (i == celebrityCandi) {
                continue;
            }
            if (knows(celebrityCandi, i) || !knows(i, celebrityCandi)) {
                return -1;
            }
        }
        return celebrityCandi;
    }
}