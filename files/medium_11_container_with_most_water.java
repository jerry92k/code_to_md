class Solution {

    public int maxArea(int[] height) {

        int inputLength = height.length;
        
        int maxProduct = 0;

        int i = 0;
        int j = inputLength - 1;
        while (i<j) {

           int productBetweenTwo = Math.min(height[i], height[j]) * (j - i);
           maxProduct = Math.max(maxProduct, productBetweenTwo);
           // 짧은 길이의 점을 그대로 두고 긴 길이의 점을 옮겨 더 긴 길이를 만나더라도 h의 기준은 짧은 길이이고 너비만 줄어들 뿐이므로
           // h가 커질수있는 후보를 찾기위해 짧은 길이의 점을 움직임
           if(height[i]<height[j]){
               i++;
           }else{
               j--;
           }
            
        }
        return maxProduct;
    }

    // brute force
    public int maxArea2(int[] height) {

        int inputLength=height.length;
        int maxProduct=0;

        for (int i = 0; i < inputLength; i++) {

            for (int j = i + 1; j < inputLength; j++) {
                int h = height[i] > height[j] ? height[j] : height[i];
                int subProduct = h * (j - i);
                if (subProduct > maxProduct) {
                    maxProduct = subProduct;
                }
            }
        }
        return maxProduct;
    }
}