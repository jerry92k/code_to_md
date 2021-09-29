// Brute Force 방법으로 풀었는데 TC가 오래걸려서 다른방법을 고안해보았다. 
// 첫번째 푼 방법과 두번째 푼 방법은 각각 하나의 인덱스에서 출발하여 범위를 넓혀가며 탐색하는 방법이지만,
// 첫번째꺼는 첫번째 인덱스부터 범위를 좁혀가는 방식이고, 두번째방법은 인덱스를 중심으로 범위를 넓혀가며 제일 긴 str를 구하는 방식이란 차이가 있다.

class Solution2 {

    public static void main(String[] args) {

        String s = "bb";
        String longestStr = s.substring(0, 1);
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; (j > i && j - i + 1 > maxLen); j--) {
                if (Palindrome.checkPalindrome(s, i, j)) {
                    if (maxLen < (j - i + 1)) {
                        longestStr = s.substring(i, j + 1);
                        maxLen = longestStr.length();
                    }
                    break;
                }
            }
            // longestPalindrome(s,i);
        }
        System.out.println(longestStr);
    }
}

class Palindrome {
    public static boolean checkPalindrome(String s, int startIdx, int endIdx) {
        boolean isSame = true;
        // for (int k = startIdx; k <= (endIdx + startIdx) / 2; k++) {
        while (startIdx < endIdx) {
            if (s.charAt(startIdx++) != s.charAt(endIdx--)) {
                isSame = false;
                break;
            }
            // check palindrome
        }
        return isSame;
    }
}

class Solution2 {

    public static void main(String[] args) {

        String s = "a";

        String longgestStr = "";

        if (s.length() == 1) {
            longgestStr = s;
        } else {

            for (int i = 0; i < s.length() - 1; i++) {
                String tempLongOdd = Palindrome.findLongest(s, i, i);
                System.out.println("tempLongOdd : " + tempLongOdd);
                String tempLongEven = Palindrome.findLongest(s, i, i + 1);
                System.out.println("tempLongEven : " + tempLongEven);

                if (tempLongOdd.length() < tempLongEven.length() && longgestStr.length() < tempLongEven.length()) {
                    longgestStr = tempLongEven;
                } else if (tempLongOdd.length() >= tempLongEven.length()
                        && longgestStr.length() < tempLongOdd.length()) {
                    longgestStr = tempLongOdd;
                }
            }
        }
        System.out.println(longgestStr);

    }
}

class Palindrome {

    public static String findLongest(String s, int i, int j) {
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
        return s.substring(i + 1, j);
    }

}

/*
 * a b b c b b a a t b t b t c t b b a
 * 
 * 
 */