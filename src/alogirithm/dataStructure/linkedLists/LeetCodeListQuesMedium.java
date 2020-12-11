package alogirithm.dataStructure.linkedLists;

import alogirithm.dataStructure.trees.TreeNode;

public class LeetCodeListQuesMedium {
    /**
     * 1669. 合并两个链表
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     *
     * 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
     *
     * 下图中蓝色边和节点展示了操作后的结果：
     *
     *
     * 请你返回结果链表的头指针。
     *
     *
     *
     * 示例 1：
     * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
     * 输出：[0,1,2,1000000,1000001,1000002,5]
     * 解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-in-between-linked-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 3 <= list1.length <= 104
     * 1 <= a <= b < list1.length - 1
     * 1 <= list2.length <= 104
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-in-between-linked-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = new ListNode(1);
        head.next = list1;
        ListNode pre = new ListNode(1) ,next = new ListNode(2);
        int i = 0;
        while(true){
            if(i == a-1){
                pre = list1;
            }
            if(i == b+1){
                next = list1;
                break;
            }
            i++;
            list1 = list1.next;
        }
        pre.next = list2;
        while(list2.next!=null)
            list2 = list2.next;
        list2.next = next;
        return head.next;
    }


    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @param args
     *  todo - 二叉搜索树
     */
    public static TreeNode sortedListToBST(ListNode head) {
        TreeNode res = new TreeNode() ;
        return res;
    }

    public static void main(String[] args) {
        int[] a = {0,1,2,3,4,5};
        int[] b = {1000000,1000001,1000002};
        ListNode mockA = LinkedListHelper.convertIntArr(a);
        ListNode mockB = LinkedListHelper.convertIntArr(b);
        LinkedListHelper.seeList(mergeInBetween(mockA,3,4,mockB));
    }
}
