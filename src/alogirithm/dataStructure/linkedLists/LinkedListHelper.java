package alogirithm.dataStructure.linkedLists;

public class LinkedListHelper {

    public static ListNode convertIntArr(int[] arr){
        if(null == arr || arr.length == 0){
            return null;
        }
        ListNode mock = new ListNode(arr[0]);
        ListNode head = mock;
        for (int i = 1; i < arr.length; i++) {
            mock.next = new ListNode(arr[i]);
            mock = mock.next;
        }
        return head;
    }

    public static void seeList(ListNode head){
        while(null != head){
            System.out.print(head.val+"->");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public static int getLength(ListNode head){
        ListNode cur = head;
        int i=0;
        while(head!=null){
            head = head.next;
            i++;
        }
        return i;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        ListNode head = convertIntArr(arr);
        ListNode rec = head;
        while(head != null){
            if(head.val == 5){
                head.removeAfter();
            }
            head = head.next;
        }
        seeList(rec);
    }
}
