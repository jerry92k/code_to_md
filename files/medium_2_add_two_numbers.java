class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode sumNode=new ListNode();
        ListNode head=null;

        int upper=0;
        int sum=0;
        while(l1!=null && l2!=null){
            //sumNode=new ListNode();
            if(head==null){
                head=sumNode;
            }else{
                sumNode.next=new ListNode();
                sumNode=sumNode.next;

                // 아래와 같이 사용하면, sumNode이 null 주소를 가리키게 되고, null 주소에 새로운 object를 할당시키는게 아니고
                // 새로 생성한(new ListNode())에 할당하게 되어 sumNode가 sumNode.next와 다른 주소를 가리키게됨. 기존 sumNode와 연결이 끊어진 새로운 object를 생성하게 되는 것.
                //  System.out.println("here head 2: " +System.identityHashCode(head)); 이용해서 메모리 주소 확인

                //  sumNode= sumNode.next
                // sumNode= new ListNode();
            }
            sum=l1.val+l2.val+upper;
            sumNode.val=sum%10;
            //System.out.println("here 1: " +sumNode.val);
            upper=sum /10; // 소수점 이하 절사
            l1=l1.next;
            l2=l2.next;
            
        }

        while(l1!=null){

            sumNode.next=new ListNode();
            sumNode=sumNode.next;
            
            sum=l1.val+upper;
            sumNode.val=sum%10;
            upper=sum /10; // 소수점 이하 절사
           
            l1=l1.next;
          
        }
        while(l2!=null){

            sumNode.next=new ListNode();
            sumNode=sumNode.next;

            sum=l2.val+upper;
            sumNode.val=sum%10;
            upper=sum /10; // 소수점 이하 절사
            l2=l2.next;
            
            //sumNode=new ListNode();
        }

        if(upper>0){
            sumNode.next=new ListNode();
            sumNode=sumNode.next;
            sumNode.val=upper;
        }
        return head;
    }
}