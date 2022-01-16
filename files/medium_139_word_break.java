import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int wordLen = s.length();
        Set<String> wordDictionary = new HashSet<String>();
        Boolean[][] wordDictSubset = new Boolean[wordLen][wordLen]; // false로 초기화

        for (String word : wordDict) {
            wordDictionary.add(word);
        }

        for (int i = 0; i < wordLen; i++) {
            boolean isPromise = false;
            if (i == 0 || wordDictSubset[0][i - 1]) {
                isPromise = true;
            }

            for (int j = i; j < wordLen; j++) {
                if (isPromise) {
                    if (wordDictionary.contains(s.substring(i, j + 1))) {
                        if (j == wordLen - 1) {
                            return true;
                        }
                        wordDictSubset[0][j] = true;

                    } else {
                        wordDictSubset[i][j] = false;
                    }
                }
            }
        }
        return false;
    }
}
