package alogirithm.dataStructure.linkedLists;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * throws npe on next
     * @param next
     */
    public void insertAfter(ListNode next){
        ListNode cur = this.next;
        this.next = next;
        next.next = cur;
    }


    public void removeAfter(){
        if(null == this.next){
            return ;
        }
        this.next = this.next.next;
    }




}
