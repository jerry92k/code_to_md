import java.util.*;

class Solution {
   
    public String reorganizeString(String s) {
 
        HashMap<Character, Integer> alphaMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            alphaMap.compute(s.charAt(i), (key, value) -> value == null ? 1 : value + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> sq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<Character, Integer> entry : alphaMap.entrySet()) {
            sq.add(entry);
        }

        StringBuilder sb = new StringBuilder();
        while (!sq.isEmpty()) {
            Map.Entry<Character, Integer> firstEntry = sq.poll();
            Map.Entry<Character, Integer> secondEntry = sq.poll();

            int firstEntryFreq = firstEntry.getValue();
            // 한가지 문자열이 남은 경우
            if (secondEntry == null) { // 한개이상 
                if (firstEntryFreq > 1) {
                    return "";
                }
                sb.append(firstEntry.getKey());
                break;
            }
            sb.append(firstEntry.getKey());
            sb.append(secondEntry.getKey());

            if (firstEntryFreq > 1) {
                firstEntry.setValue(firstEntryFreq - 1);
                sq.add(firstEntry);

                int secondEntryFreq = secondEntry.getValue();
                if (secondEntryFreq > 1) {
                    secondEntry.setValue(secondEntryFreq - 1);
                    sq.add(secondEntry);
                }
            }
        }
        return sb.toString();
    }
}



