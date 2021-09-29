import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {

        int numOfIntervals = intervals.length;

        int[] startPoints = new int[numOfIntervals];
        int[] endPoints = new int[numOfIntervals];

        for (int i = 0; i < numOfIntervals; i++) {
            startPoints[i] = intervals[i][0];
            endPoints[i] = intervals[i][1];
        }

        Arrays.sort(startPoints);
        Arrays.sort(endPoints);

        int numOfRooms = 0;
        int endPointIdx = 0;
        for (int i = 0; i < numOfIntervals; i++) {

            if (startPoints[i] < endPoints[endPointIdx]) {
                numOfRooms++;
            } else {

                endPointIdx++;
            }
        }
        return numOfRooms;

    }
}