import java.util.Arrays;

/*
[전략]
각 보드를 시작으로 주어진 문자열이 될수있는 연속점이 있는지 확인
2번 방문하는 것을 방지하지 위해 방문 기록 관리.
*/
class Solution {
    public boolean exist(char[][] board, String word) {

        boolean[][] visits = new boolean[board.length][board[0].length];

        for (boolean[] visit : visits) {
            Arrays.fill(visit, false);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // System.out.println("i , j :"+ i+ " : "+j);
                if (backtracking(board, visits, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtracking(char[][] board, boolean[][] visits, int i, int j, String word, int len) {

        if (board[i][j] == word.charAt(len)) {

            if (word.length() == len + 1) {
                return true;
            }
            visits[i][j] = true;
            boolean up = i <= 0 || visits[i - 1][j] ? false : backtracking(board, visits, i - 1, j, word, len + 1);
            boolean down = i >= board.length - 1 || visits[i + 1][j] ? false
                    : backtracking(board, visits, i + 1, j, word, len + 1);
            boolean left = j <= 0 || visits[i][j - 1] ? false : backtracking(board, visits, i, j - 1, word, len + 1);
            boolean right = j >= board[0].length - 1 || visits[i][j + 1] ? false
                    : backtracking(board, visits, i, j + 1, word, len + 1);

            visits[i][j] = false;
            return up || down || left || right;
        }
        return false;
    }
}