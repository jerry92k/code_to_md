/*
You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.

 Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
*/

import java.util.*;

class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        int moduloVal = 1000000007;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxHeight = h - horizontalCuts[horizontalCuts.length - 1];
        maxHeight = Math.max(maxHeight, horizontalCuts[0]);
        for (int i = 0; i < horizontalCuts.length - 1; i++) {
            int diff = horizontalCuts[i + 1] - horizontalCuts[i];
            maxHeight = Math.max(maxHeight, diff);
        }

        int maxWidth = w - verticalCuts[verticalCuts.length - 1];
        maxWidth = Math.max(maxWidth, verticalCuts[0]);
        for (int i = 0; i < verticalCuts.length - 1; i++) {
            int diff = verticalCuts[i + 1] - verticalCuts[i];
            maxWidth = Math.max(maxWidth, diff);
        }
        maxWidth = maxWidth % moduloVal;
        maxHeight = maxHeight % moduloVal;

        long product=((long) maxWidth*(long) maxHeight)%(long)moduloVal;
        return (int) product;
    }
}