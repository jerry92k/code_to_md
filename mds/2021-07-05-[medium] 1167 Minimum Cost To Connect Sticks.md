---
layout: post
title: LeetCode - 1167 Minimum Cost To Connect Sticks
date: 2021-07-05 00:00:00
categories: leetcode
---

```java
import java.util.PriorityQueue;

class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> stickCosts = new PriorityQueue<>();

        for (int i = 0; i < sticks.length; i++) {
            stickCosts.add(sticks[i]);
        }

        int sum = 0;
        while (!stickCosts.isEmpty()) {
            int minVal = stickCosts.poll();

            if (stickCosts.isEmpty()) {
                return sum;
            }
            int secMinVal = stickCosts.poll();

            int subSum = minVal + secMinVal;
            sum += subSum;
            stickCosts.add(subSum);
        }
        return sum;
    }
}
```

