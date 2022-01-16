---
layout: post
title: LeetCode - 1647 Minimum Deletions To Make Character Frequencies Unique
date: 2021-06-28 00:00:00
categories: leetcode
---

```java
import java.util.HashMap;

class Solution {
    public int minDeletions(String s) {
        HashMap<Character, Integer> alphabetFreq = new HashMap<>();

        int maxFreq = 1;
        for (int i = 0; i < s.length(); i++) {
            alphabetFreq.compute(s.charAt(i), (key, value) -> value == null ? 1 : value + 1);
            maxFreq = Math.max(maxFreq, alphabetFreq.get(s.charAt(i)));
        }

        int[] freqArr = new int[maxFreq + 1];

        for (int freq : alphabetFreq.values()) {
            freqArr[freq]++;
        }

        int count = 0;
        for (int i = freqArr.length - 1; i >= 1; i--) {
            if (freqArr[i] > 1) {
                int deleteCount = freqArr[i] - 1;
                count += deleteCount;
                freqArr[i - 1] += deleteCount;
            }
        }
        return count;
    }
}
```

