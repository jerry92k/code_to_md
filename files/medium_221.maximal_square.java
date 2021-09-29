

class MaximalSqaure {

    public int maximalSquare(char[][] matrix) {

        //Dynamic Programming으로 풀어보자

        int maxLen=0;
        int rowLen=matrix.length;
        int colLen=matrix[0].length;
        int[][] dp=new int[rowLen][colLen];

        boolean hasOne=false;

        //왼쪽과 위쪽 경계는 1이냐 0이냐에 따라서 초기 len값 설정을 해준다.
        for(int i=0; i<rowLen; i++){
                dp[i][0]=matrix[i][0]-'0';
                if(!hasOne&&dp[i][0]==1){
                    hasOne=true;
                }
        }
        for(int j=0; j<colLen; j++){
                dp[0][j]=matrix[0][j]-'0';

            if(!hasOne&&dp[0][j]==1){
                hasOne=true;
            }
        }
        if(hasOne){
            maxLen=1;
        }
        //탐색하는 원소가 정사각형의 오른쪽 아래 가장자리에 있다고 가정하고 DP를 계산해나가보자
        for(int i=1; i<rowLen; i++){
            for(int j=1; j<colLen; j++){
                if(matrix[i][j]=='0'){
                    //해당 값이 '0'인 경우, 이 원소를 포함해서 그리는 square가 0이므로 0 할당
                    dp[i][j]=0;
                }else {
                    // 왼쪽, 대각선(왼,위),위쪽 중 하나라도 0인 dp가 있으면 이어지지 않는 것이므로 1할당
//                    if(dp[i-1][j]==0 || dp[i][j-1]==0 || dp[i-1][j-1]==0){
//                        dp[i][j]=1;
//                    }
//                    else{
//                   이어지는 값중 가장 작은값+1 만큼 증가함
//                        dp[i][j]=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
//                    }

                    //처음엔 위와같이 if-else로 구분하였다가, 구분하는게 불필요하다는걸 깨달아서 아래와같이 수정함

                    dp[i][j]=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;

                    if(dp[i][j]>maxLen){
                        maxLen=dp[i][j];
                    }
                }
            }
        }
        return maxLen*maxLen;
    }
           /*
         1. 예시 input
        {{'1','1','1','1','1','1','1','1'},
         {'1','1','1','1','1','1','1','0'}
        ,{'1','1','1','1','1','1','1','0'}
        ,{'1','1','1','1','1','0','0','0'}
        ,{'0','1','1','1','1','0','0','0'}};

         2. 초기 dp 셋
         11111111
        10000000
        10000000
        10000000
        00000000

        3. 마지막 dp셋팅
        11111111
        12222220
        12333330
        12344000
        01234000
         */

    public void printDP(int[][] dp){
        for(int i=0; i<dp.length; i++)
        {
            for(int j=0; j<dp[0].length; j++){
                System.out.print(dp[i][j]);
            }
            System.out.println("");
        }
    }


}
