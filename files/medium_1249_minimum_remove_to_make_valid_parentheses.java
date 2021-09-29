import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> openBracketIdxs = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int sbIdx = 0;

        for(char ch : s.toCharArray()){
            if (ch == '(') {
                openBracketIdxs.push(sbIdx);
            } else if (ch == ')') {
                if (openBracketIdxs.isEmpty()) {
                    // 앞에 '(' 이 없어서 짝이 안맞는 ')'는 삭제해야하므로 sb에 append하지않고 sbIdx를 증가시키지도 않음
                    continue;
                } else {
                    openBracketIdxs.pop();
                }
            }
            sb.append(ch);
            sbIdx++;
        }
        while (!openBracketIdxs.isEmpty()) {
            int deleteIdx = openBracketIdxs.pop();
            sb.deleteCharAt(deleteIdx);
        }

        return sb.toString();
    }
    
}