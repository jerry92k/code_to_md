class Solution {
    public int[] productExceptSelf(int[] nums) {
        int numsLen=nums.length;
        int[] preProduct=new int[numsLen];
        int[] postProduct=new int[numsLen];
        int[] arrProduct=new int[numsLen];
        
        int productVal=1;
        for(int i=0; i<numsLen; i++){
            productVal=productVal*nums[i];
            preProduct[i]=productVal;
        }
        productVal=1;
         for(int i=numsLen-1; i>=0; i--){
            productVal=productVal*nums[i];
            postProduct[i]=productVal;
        }

        for(int j=0; j<numsLen; j++){
            if(j==0){
                arrProduct[j]= postProduct[j+1];
            }
            else if(j==numsLen-1){
                arrProduct[j]= preProduct[j-1];
            }
            else{
            arrProduct[j]= preProduct[j-1]*postProduct[j+1];
            }
            
        }

        return arrProduct;
    }
}

/*

   1      2      3      4
   1     1X2   1X2X3 1X2X3X4
   1      2      6     24
1X2X3X4 2X3X4   3X4     4
  24      24     12     4 

*/