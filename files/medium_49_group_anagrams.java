import java.util.*;

/*
[전략]
- input String을 순회하며,
- HashMap을 이용해 정렬한 String을 key로 하고
- 정렬전 String들 key에 해당하는 List에 담아 value로 관리
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagGroups = new HashMap<>();

        for (String str : strs) {
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String sortedStr = String.valueOf(strArr);
            if(anagGroups.containsKey(sortedStr)){
                List<String> anags=anagGroups.get(sortedStr);
                anags.add(str);
            }
            else{
                List<String> anags=new ArrayList<>();
                anags.add(str);
                anagGroups.put(sortedStr, anags);
            }
        }
        
        List<List<String>> result = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : anagGroups.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}