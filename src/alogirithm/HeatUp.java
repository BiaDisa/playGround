package alogirithm;

import alogirithm.dataStructure.ListNode;

public class HeatUp {

    public static ListNode reverseLinkedList(ListNode head){
        if(head == null)
            return null;
        ListNode cur = head,last = null;
        while(null != cur){
            ListNode next = cur.next;
            cur.next = last;

            last = cur;
            cur = next;
        }
        return last;
    }

    public static void  main(String[] args){
        ListNode<Integer> node = new ListNode<>(1);
        node.next = new ListNode<>(2);
        node.next.next = new ListNode<>(3);
        node = reverseLinkedList(node);
        while(node!=null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
