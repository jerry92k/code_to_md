/*

Given a characters array tasks,
representing the tasks a CPU needs to do,
where each letter represents a different task.
Tasks could be done in any order.

Each task is done in one unit of time.
For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown 
    period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.
Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

*/

import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {

        // [전략]
        // 1. 정렬해서 동일알파벳끼리 모은다
        // 2. n개 만큼의 배열을 만들어서, 동일 알파벳 중 제일 앞의 알파벳 index를 저장한다.

        Arrays.sort(tasks);

        ArrayList<Integer> alphabetIdxs = new ArrayList<>();

        char preTask = tasks[0];
        alphabetIdxs.add(0);
        for (int i = 0; i < tasks.length; i++) {

            if (preTask != tasks[i]) {
                alphabetIdxs.add(i);
                preTask = tasks[i];
            }
        }
    }
}