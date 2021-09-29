class Solution {
    public int countSubstrings(String s) {

        int paldromCount = s.length(); // 문자가 단독으로 사용된 경우 count
        for (int i = 0; i < s.length(); i++) {

            paldromCount = paldromCount + checkPalindrome(s, i, i + 1) + checkPalindrome(s, i - 1, i + 1); // substring이 짝수개, 홀수개인 경우 나눠서 계산
        }
        return paldromCount;
    }
    
    private int checkPalindrome(String s, int leftIdx, int rightIdx) {
        int count = 0;
        while (leftIdx >= 0 && rightIdx < s.length() && s.charAt(leftIdx) == s.charAt(rightIdx)) {
            count++;
            leftIdx--;
            rightIdx++;
        }
        return count;
    }
}