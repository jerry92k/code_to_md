import java.util.*;

/*
[전략]
1. 각 다이얼별로 가능한 문자 케이스를 관리
2. 스택 2가지 사용
 -  이전까지 조합의 케이스를 저장할 스택, 스택에서 하나씩 꺼내서 하나의 문자를 더 붙임으로서 발생하는 조합을 저장할 스택 
*/

class Solution {
    public List<String> letterCombinations(String digits) {

        char[][] dials = { { '-', '-', '-', '-' }, { '-', '-', '-', '-' }, { 'a', 'b', 'c', '-' },
                { 'd', 'e', 'f', '-' }, { 'g', 'h', 'i', '-' }, { 'j', 'k', 'l', '-' }, { 'm', 'n', 'o', '-' },
                { 'p', 'q', 'r', 's' }, { 't', 'u', 'v', '-' }, { 'w', 'x', 'y', 'z' } };

        if (digits.length() < 1) {
            return new ArrayList<>();
        }

        Stack<StringBuilder> dialCombinations = new Stack<>();
        Stack<StringBuilder> conveyStacks = new Stack<>();

        dialCombinations.add(new StringBuilder());
        // boolean[] visits = new boolean[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            int number = digits.charAt(i) - 48;
            while (!dialCombinations.empty()) {
                StringBuilder popSb = dialCombinations.pop();
                for (int j = 0; j < dials[0].length; j++) {
                    if (dials[number][j] == '-') {
                        break;
                    }
                    StringBuilder newSb = new StringBuilder(popSb);
                    newSb.append(dials[number][j]);
                    conveyStacks.push(newSb);
                }
            }
            dialCombinations = conveyStacks;
            conveyStacks.clear();
        }

        ArrayList<String> resultDigitsComb = new ArrayList<>();

        for (StringBuilder sb : dialCombinations) {
            resultDigitsComb.add(sb.toString());
        }

        return resultDigitsComb;
    }
}