
class Solution {
    public int[][] merge(int[][] intervals) {
        

        //intervals를 sorting 하자 : 처음에 정렬을 해줘야 나중에 앞의 범위를 포괄하는 큰 케이스를 다루는 경우가 없음
        /*
        ex. [1,2][3,4][5,6],[7,8][1,10]
        */
        // Arrays.sort(intervals, new Comparator<int[]>(){
            
        //     @Override
        //     public int compare(int[] preArr, int[] nextArr){
        //         return preArr[0] - nextArr[0];
        //     }
        // });
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));

        ArrayList<int[]> mergeArray= new ArrayList<int[]>();

        
        mergeArray.add(intervals[0]);

        for(int i=1; i<intervals.length; i++){
       
            // 순서대로 정렬되어있기 때문에 마지막꺼랑만 비교하면됨
            int idx=mergeArray.size()-1;
            int[] mergeItem=mergeArray.get(idx);
            if(intervals[i][0]<=mergeItem[1] ){
                if(intervals[i][1]>mergeItem[1]){
                            mergeArray.get(idx)[1]=intervals[i][1];
                }   //같거나 작으면 수정할 필요 없음.
            }else{
                 mergeArray.add(intervals[i]);
            }

                // sorting을 먼저함으로써 필요없어진 아래 로직들
                //  int j=0;
                // int merLen=mergeArray.size();
                //for(; j<merLen; j++){ // 이미 들어가있는 arraylist를 참조함
                // 시작점보다 같거나 킅경우
                // if(mergeItem[0]==intervals[i][0] || (mergeItem[0]<intervals[i][0] && intervals[i][0]<=mergeItem[1]) ){
                //     if(intervals[i][1]>mergeItem[1]){
                //             mergeArray.get(j)[1]=intervals[i][1];
                //     }   //같거나 작으면 수정할 필요 없음.
                //     break;
                // }else if(mergeItem[0]<intervals[i][0] && intervals[i][0]<=mergeItem[1]){ // 
                //      if(intervals[i][1]>mergeItem[1]){
                //             mergeArray.get(j)[1]=intervals[i][1];
                //     }   //같거나 작으면 수정할 필요 없음.
                //     break;
                // }
                // else if(mergeItem[0]>intervals[i][0] && intervals[i][1]>=mergeItem[0]){ // 시작점보다 작은경우  
                //     mergeArray.get(j)[0]=intervals[i][0];
                //     if(intervals[i][1]>mergeItem[1]){
                //             mergeArray.get(j)[1]=intervals[i][1];
                //     }   //같거나 작으면 수정할 필요 없음.
                //     break;
                // }     
                //  }
                //     if(j>=merLen) // 겹치는 부분이 없었음
                //     {
                //         mergeArray.add(intervals[i]);
                //     }
        }
        // int[][] mergeArr=new int[mergeArray.size()][2];
        // for(int k=0; k<mergeArr.length; k++){
        //     mergeArr[k][0]=mergeArray.get(k)[0];
        //     mergeArr[k][1]=mergeArray.get(k)[1];
        // }

        int[][] mergeArr=mergeArray.stream()
                                    .map(l -> l)
                                    .toArray(int[][]::new);

        return mergeArr;
    }
}
/*
ㅇ Java8 ::(method reference) 표현식 의미
numbers.forEach(x -> System.out.println(x));
=> numbers.forEach(System.out::println);
*/