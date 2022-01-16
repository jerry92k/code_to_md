
import java.util.*;

class Solution {
    public int[] findOrderQueue(int numCourses, int[][] prerequisites) {

        HashMap<Integer, Set<Integer>> fromHash = new HashMap<>(); // key를 기준으로 다음 course
        HashMap<Integer, Set<Integer>> toHash = new HashMap<>(); // ket를 기준으로 이전 course

        for(int i = 0; i < prerequisites.length; i++) {
            Set<Integer> fromSet = toHash.get(prerequisites[i][0]);
            if (fromSet == null) {
                fromSet = new HashSet<>();
                toHash.put(prerequisites[i][0], fromSet);
            }
            fromSet.add(prerequisites[i][1]);
            
            Set<Integer> toSet = fromHash.get(prerequisites[i][1]);
            if (toSet == null) {
                toSet = new HashSet<>();
                fromHash.put(prerequisites[i][1], toSet);
            }
            toSet.add(prerequisites[i][0]);
        }

        Queue<Integer> courseCandidates = new LinkedList();
        for (int i = 0; i < numCourses; i++) {
            if (!toHash.containsKey(i)) {
                courseCandidates.add(i);
            }
        }

        if (courseCandidates.size() == 0) { // 시작점이 없이 순환됨
            return new int[0];
        }
        
        ArrayList<Integer> courseOrder = new ArrayList<>();

        Set<Integer> orderedSet = new HashSet<>();
        while (!courseCandidates.isEmpty()) {
            int orderCandidate = courseCandidates.poll();
            if (orderedSet.contains(orderCandidate)) {
                continue; // 이미 등록됨
            }
            if (toHash.containsKey(orderCandidate)) {
                //skip. 아직 선수과목이 남음
                continue;
            }
            courseOrder.add(orderCandidate);
            orderedSet.add(orderCandidate);

            if (fromHash.containsKey(orderCandidate)) {
                for (int nextVal : fromHash.get(orderCandidate)) {
                    Set<Integer> fromSet = toHash.get(nextVal);
                    if (fromSet == null || !fromSet.contains(orderCandidate)) {
                        // there is cycle;
                        return new int[0];
                    }
                    if (fromSet.size() == 1) {
                        toHash.remove(nextVal);
                    } else {
                        fromSet.remove(orderCandidate);
                    }
                    courseCandidates.add(nextVal);
                }
            }
        }
        if (courseOrder.size() < numCourses) {
            return new int[0];
        }
        return courseOrder.stream().mapToInt(Integer::intValue).toArray();
    }
}