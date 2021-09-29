class Solution {
    /*
     * [전략] 1. 앞자리에서부터 더 작은 알파벳으로 변경할수 있는지 확인 (홀수인 경우 중간 자리는 바뀌어봤자 palindrome이기 때문에
     * 제외) 2. String이 모두 a로 구성된 경우에만 for문을 빠져나오므로(a가 아니면 a로 변경해서 return 됨) 3. 제일
     * 마지막자리를 'b'로 변경해줌.
     * 
     */
    public String breakPalindrome(String palindrome) {

        char[] strArr = palindrome.toCharArray();
        int strLen = strArr.length;
        if (strLen < 2) {
            return "";
        }

        for (int i = 0; i < strLen; i++) {
            if ((strLen & 1) == 1 && i == strLen / 2) { // 홀수
                continue;
            }
            if (strArr[i] != 'a') {
                strArr[i] = 'a';
                return String.valueOf(strArr);
            }
        }

        strArr[strLen - 1] = 'b';
        return String.valueOf(strArr);
    }
}