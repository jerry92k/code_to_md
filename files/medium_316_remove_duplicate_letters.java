import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> alphaHash = new HashMap<>();
        Set<Character> posAlphas = new HashSet<Character>();
        Stack<Character> stacks = new Stack<>();

        
        for (int i = 0; i < s.length(); i++) {
            // 알파벳별로 빈도 저장
            // alphaHash.compute(s.charAt(i), (key, value) -> value == null ? 1 : value + 1);
            // 알파벳별로 빈도를 저장하지 않고, 마지막 index를 저장
            alphaHash.put(s.charAt(i), i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (!posAlphas.contains(ch)) {
                // alphaHash.get(stacks.peek()) >= 1 
                // -> alphaHash.get(stacks.peek()) > i. i 이후에 동일 알파벳이 뒤에 남아있는지 확인
                while (!stacks.empty() && stacks.peek() > ch && alphaHash.get(stacks.peek()) > i) {
                    char peekCh = stacks.pop();
                    posAlphas.remove(peekCh);
                }
                stacks.push(ch);
                posAlphas.add(ch);
            }
           // alphaHash.compute(ch, (key, value) -> value - 1);   
        }

        for (Character c : stacks) {
            sb.append(c.charValue());
        }
        return String.valueOf(sb);
    }
}