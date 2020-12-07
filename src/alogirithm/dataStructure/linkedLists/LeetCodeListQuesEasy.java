package alogirithm.dataStructure.linkedLists;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeListQuesEasy {

    /**
     * 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     *
     * 你能尝试使用一趟扫描实现吗？
     */

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = head;
        process(head,n);
        if(totalSize == n){
            res = res.next;
        }
        return res;
    }

    static int curListNum = 0;
    static int totalSize = 0;

    public static void process(ListNode cur,int tar){
        if(null != cur){
            totalSize++;
            process(cur.next,tar);
        }else{
            return;
        }
        if(curListNum == tar){
            ListNode next = cur.next;
            if(null == next){
                cur.next = null;
                return ;
            }
            next = next.next;
            cur.next = next;
        }
        curListNum++;
    }


    //---------------------

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     *
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode resHead = new ListNode(1);
        ListNode resCur = resHead;
        while(l1!=null && l2!=null){
            if(l1.val>=l2.val){
                resCur.next = l2;
                resCur = resCur.next;
                l2 = l2.next;
            }else{//l1.val<l2.val
                resCur.next = l1;
                resCur = resCur.next;
                l1 = l1.next;
            }
        }
        resCur.next = l1==null?l2:l1;
        return resHead.next;
    }

    //--------------------

    /**
     * 23. 合并K个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     *
     *
     * 示例 1：
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     *
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：lists = [[]]
     * 输出：[]
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(null == lists || lists.length == 0){
            return null;
        }
        //sol1
        ListNode resHead = new ListNode(0);
        resHead.next = lists[0];
        for (int i = 1; i < lists.length; i++) {
            resHead.next = mergeTwoLists(resHead.next,lists[i]);
        }
        return resHead.next;
    }

    //------------------

    /**
     * 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     *
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：[1]
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode headPre = new ListNode(0);
        headPre.next = head;
        ListNode pre = headPre;
        ListNode next ;
        while(null != head && (next = head.next)!=null){
            head.next = next.next;
            pre.next = next;
            next.next = head;
            //swap
            pre = head;
            head = head.next;
        }
        return headPre.next;
    }

    //--------------------------

    /**
     *实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
     *
     *  
     *
     * 示例：
     *
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-middle-node-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void deleteNode(ListNode node) {
        int nextVal = node.next.val;
        node.val = nextVal;
        node.next = node.next.next;
    }

    //------------------------

    /**
     * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
     *
     * 请你返回该链表所表示数字的 十进制值 。
     *
     *  
     *
     * 示例 1：
     *
     *
     *
     * 输入：head = [1,0,1]
     * 输出：5
     * 解释：二进制数 (101) 转化为十进制数 (5)
     * 示例 2：
     *
     * 输入：head = [0]
     * 输出：0
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int getDecimalValue(ListNode head) {
        int[] info = {0,0};
        tuple(head,info);
        return info[0];
    }

    //info:info[0]:curSum,info[1]:powNum
    public static void tuple(ListNode cur,int[] info){
        if(null == cur){
            return;
        }
        tuple(cur.next,info);
        info[0] += cur.val * pow(2,info[1]);
        info[1]++;
    }

    public static int pow(int num,int time){
        int res = 1;
        for (int i = 1; i <= time; i++) {
            res *= num;
        }
        return res;
    }

    //-------------------------

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    static ListNode getKthFromEndNode = new ListNode(0);

    public static ListNode getKthFromEnd(ListNode head, int k) {
            ListNode res = new ListNode(0);
            getKthFromEndTuple(head,k);
            return getKthFromEndNode;

    }

    public static int getKthFromEndTuple(ListNode cur, int num){
            if(null == cur)
            return 0;
            int length = getKthFromEndTuple(cur.next,num) + 1;
            if(length == num){
                getKthFromEndNode = cur;
            }
            return length;

    }

    //------------------

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     */
    static int[] reversePrintRes = {0};
    public static int[] reversePrint(ListNode head) {
            int[] info = {0,0};
            reversePrintTuple(head,info);
            return reversePrintRes;
    }

    //info[0]:total,info[1]:idx
    public static void reversePrintTuple(ListNode cur,int[] info){
        if(cur == null){
            reversePrintRes = new int[info[0]];
            return;
        }
        info[0]++;
        reversePrintTuple(cur.next,info);
        reversePrintRes[info[1]] = cur.val;
        info[1]++;
    }

    //-----------------------

    /**
     * 剑指 Offer 24. 反转链表
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 876. 链表的中间结点
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * 示例 2：
     *
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     *
     *
     * 提示：
     *
     * 给定链表的结点数介于 1 和 100 之间。
     * 通过次数85,207提交次数122,250

     */
    public static ListNode middleNode(ListNode head) {
        if(null == head || head.next == null){
            return head;
        }
        ListNode pre = head,next = head;
        while(null != next && null != next.next){
            pre = pre.next;
            next = next.next.next;
        }
        return pre;
    }

    /**
     * 面试题 02.01. 移除重复节点
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * 示例1:
     *
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     * 示例2:
     *
     *  输入：[1, 1, 1, 1, 2]
     *  输出：[1, 2]
     * 提示：
     *
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * 进阶：
     *
     * 如果不得使用临时缓冲区，该怎么解决？
     */
    public static ListNode removeDuplicateNodes(ListNode head) {
            ListNode cur = new ListNode(1);
            cur.next = head;
            ListNode res = cur;
            Set<Integer> distinct = new HashSet<>();
            while(cur.next!=null){
                if(distinct.contains(cur.next.val)){
                    ListNode next = cur.next;
                    if(null == next){
                        cur.next = null;
                    }
                    next = next.next;
                    cur.next = next;
                }else{
                    distinct.add(cur.next.val);
                    cur = cur.next;
                }
            }
            return res.next;
    }

    /**
     * 面试题 02.07. 链表相交
     * 给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
     *
     *
     * 示例 1：
     *
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * 示例 2：
     *
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *
     * 示例 3：
     *
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     * todo 红皮算法书中的算法
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) {
            //如果指针tempA不为空，tempA就往后移一步。
            //如果指针tempA为空，就让指针tempA指向headB（注意这里是headB不是tempB）
            tempA = tempA == null ? headB : tempA.next;
            //指针tempB同上
            tempB = tempB == null ? headA : tempB.next;
        }
        //tempA要么是空，要么是两链表的交点
        return tempA;
    }


    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     *
     * 返回删除后的链表的头节点。
     *
     * 注意：此题对比原题有改动
     *
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *  
     *
     * 说明：
     *
     * 题目保证链表中节点的值互不相同
     * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static ListNode deleteNode(ListNode head, int val) {
        ListNode resPre = new ListNode(1);
        resPre.next = head;
        ListNode tu = resPre;
        while(tu.next != null){
            if(tu.next.val == val){
                tu.next = tu.next.next;
                break;
            }
            tu = tu.next;
        }
        return resPre.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:
     *
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    public static ListNode deleteDuplicates(ListNode head) {

        if(null == head)
            return head;

        ListNode res = head;
        int val = head.val;
        while(null != head.next){
            if(head.next.val == val) {
                head.next = head.next.next;
            }else{
                val = head.next.val;
                head = head.next;
            }
        }
        return res;
    }

    /**
     * 141. 环形链表
     * 给定一个链表，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     *
     *
     * 进阶：
     *
     * 你能用 O(1)（即，常量）内存解决此问题吗
     */

    public static boolean hasCycle(ListNode head) {
        ListNode pre = head;
        while(pre != null && pre.next!=null){
            pre = pre.next.next;
            head = head.next;
            if(pre == head)
                return true;
        }
        return false;
    }

    ListNode isPalindromePre,isPalindromeNext;

    /**
     * 面试题 02.06. 回文链表
     * 编写一个函数，检查输入的链表是否是回文的。
     *todo  链表翻转
     * */
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head,slow = head;
        while(fast!=null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = isPalindromeRevert(slow);

        fast = head;

        while(slow!=null){
                if(fast.val != slow.val)
                    return false;
                fast = fast.next;
                slow = slow.next;
        }
        return true;
    }

    public static ListNode isPalindromeRevert(ListNode head){
        ListNode pre = null,next ;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 203. 移除链表元素
     * 删除链表中等于给定值 val 的所有节点。
     *
     * 示例:
     *
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode res = new ListNode(1);
        res.next = head;
        ListNode r = res;
        while(r.next!=null){
            if(r.next.val == val){
                r.next = r.next.next;
            }else{
                r = r.next;
            }
        }
        return res.next;
    }


    public static void main(String[] args){
        int[] start = {};
        int[] a = {1,2,3,4,5};
        int[] b = {3,6,7};
        int[] c = {8,9,10};
        int[] d = {1,1,1};
        int[] e = {1,2,3,4,5,6,7,8,9,10};
        int[] f = {1,2};
        int[] g = {1,2,3,3,2,1};
        ListNode npe = LinkedListHelper.convertIntArr(start);
        ListNode mockA = LinkedListHelper.convertIntArr(a);
        ListNode mockB = LinkedListHelper.convertIntArr(b);
        ListNode mockC = LinkedListHelper.convertIntArr(c);
        ListNode mockD = LinkedListHelper.convertIntArr(d);
        ListNode mockE = LinkedListHelper.convertIntArr(e);
        ListNode mockF = LinkedListHelper.convertIntArr(f);
        ListNode mockG = LinkedListHelper.convertIntArr(g);
        ListNode[] tmp = {npe,mockA,mockB,mockC};
        ListNode l = removeElements(mockG,1);
        LinkedListHelper.seeList(l);
    }

}
