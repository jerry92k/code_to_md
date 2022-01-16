import java.util.*;

/**
[전략]
1. HashMap을 이용해서 문자가 나타날때마다 index 기록
2. HashMap에 있는 문자가 또 나타난 경우에는 해당 문자를 제외하고 연속적인 문자열로 인식
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {

        if (s.length() == 0) {
            return 0;
        }

        Map<Character,Integer> checkChars = new HashMap<>();
        
        int maxNonRepeatedLen = 0;

        int startIdx=0;
        int endIdx=0;
        for (int i=0; i<s.length(); i++) {
            char ch= s.charAt(i);
            if (checkChars.containsKey(ch)) {

                int preChIdx = checkChars.get(ch);
                if (preChIdx >= startIdx) {
                    startIdx = preChIdx + 1;    
                }
            }
            checkChars.put(ch,i);
            endIdx=i;   
            int nonRepeatedLen = endIdx - startIdx;
            maxNonRepeatedLen = Math.max(maxNonRepeatedLen, nonRepeatedLen);
        }
        maxNonRepeatedLen = Math.max(maxNonRepeatedLen, endIdx-startIdx);
        return maxNonRepeatedLen+1;
    }
}