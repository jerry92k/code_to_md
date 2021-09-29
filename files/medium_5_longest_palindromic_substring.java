class Solution {
    public String longestPalindrome(String s) {

        String longgestStr = "";

        if (s.length() == 1) {
            return s;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            String tempLongOdd = findLongest(s, i, i);
            String tempLongEven = findLongest(s, i, i + 1);

            if (tempLongOdd.length() < tempLongEven.length() && longgestStr.length() < tempLongEven.length()) {
                longgestStr = tempLongEven;
            } else if (tempLongOdd.length() >= tempLongEven.length() && longgestStr.length() < tempLongOdd.length()) {
                longgestStr = tempLongOdd;
            }
        }
        return longgestStr;
    }

    public String findLongest(String s, int i, int j) {
        // StringBuffer를 굳이 쓰지 않고 인덱스만 기억해서 substring 가능함
        // StringBuffer strBuilder = new StringBuffer();
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                // i와 j가 이미 포함되지 않는 테두리 index이므로 i--, j++를 해주지 않는다.
                break;
            }
            if (i == j) {
                // strBuilder.append(s.charAt(i));
            } else {

                // strBuilder.insert(0, s.charAt(i));
                // strBuilder.append(s.charAt(j));
            }
            i--;
            j++;

        }
        // return strBuilder.toString();
        return s.substring(i + 1, j);
    }

}