package alogirithm;

import alogirithm.dataStructure.TreeNode;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;

import java.util.*;

public class LeetCode {
    /**
     * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     *
     * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     *
     * 最初，除 0 号房间外的其余所有房间都被锁住。
     *
     * 你可以自由地在房间之间来回走动。
     *
     * 如果能进入每个房间返回 true，否则返回 false。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/keys-and-rooms
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param rooms
     * @return
     */



    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] access = new boolean[rooms.size()];
        boolean[] visit = new boolean[rooms.size()];
        recOfCanVisitAllRooms(access,visit,0,rooms);
        for (int i = 0; i < access.length; i++) {
            if(!access[i])
                return false;
        }
        return true;
    }

    public static void recOfCanVisitAllRooms(boolean[] access, boolean[] visit, int thisFloor, List<List<Integer>> rooms){
        access[thisFloor] = true;
        List<Integer> current = rooms.get(thisFloor);
        if(null == current || current.size() == 0 || visit[thisFloor]){
            return;
        }
        visit[thisFloor] = true;
        for (Integer i : current) {
            if(visit[i]) continue;
            recOfCanVisitAllRooms(access,visit,i,rooms);
        }
    }

    /**
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>res = new ArrayList();
        re(candidates,res,new ArrayList(),target,0,0);
        return res;
    }

    private static void re(int[] candidates,List<List<Integer>> res,List<Integer> thisF,int target,int cur,int idx){
        if(idx == candidates.length)
            return;

        if(cur == target){
            res.add(new ArrayList(thisF));
            return;
        }

        re(candidates, res, thisF, target, cur, idx+1);


            int tmpCur = cur;
            if(candidates[idx]+cur<=target){
                thisF.add(candidates[idx]);
                tmpCur += candidates[idx];
                re(candidates,res,thisF,target,tmpCur,idx);
                thisF.remove(thisF.size()-1);


        }
    }


    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     *
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> such = new ArrayList<>();
        re2(res,candidates,target,0,such,0);
        return res;
    }

    public static List<List<Integer>> combinationSumVer2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,Integer> dic = new HashMap<>();
        for (int i : candidates) {
            int cnt = dic.getOrDefault(i,0);
            dic.put(i,cnt+1);
        }
        ArrayList<Integer> set = new ArrayList<>(dic.keySet());
        set.sort(Comparator.comparingInt(Integer::intValue));
        re2Ver2(res,dic,set,0,target,0,new ArrayList<>());
        return res;
    }

    public static void re2Ver2(List<List<Integer>> res, Map<Integer, Integer> dic, ArrayList<Integer> set, int idx, int target, int cur, List<Integer> such){
        if(cur == target) {
            res.add(new ArrayList<>(such));
        }
        if(idx == set.size()){
            return;
        }
        int num = dic.get(set.get(idx));
        int t = set.get(idx);
        for(int i=0;i<=num;i++){
            cur += i*t;
            if(cur >target){
                cur-=i*t;
                break;
            }
            for(int j=0;j<i;j++)
                such.add(t);
                re2Ver2(res, dic, set, idx + 1, target, cur, such);

            cur-=i*t;
            for(int j=0;j<i;j++)
                such.remove(such.size()-1);
        }
    }

    private static void re2(List<List<Integer>> res,int[] candy,int target,int curSum,List<Integer> such,int idx){
        if(curSum>target){
            return;
        }

        if(curSum == target && verify(such,res)){
            res.add(new ArrayList<>(such));
            return;
        }



        for(int i =idx;i<candy.length;i++){
            such.add(candy[i]);
            curSum +=candy[i];

            such.remove(such.size()-1);
            curSum-=candy[i];
        }
    }

    private static boolean verify(List<Integer> ori,List<List<Integer>> res){
        for (List<Integer> re : res) {
            if(re.size() == ori.size()){
                boolean flag = false;
                for (int i = 0; i < ori.size(); i++) {
                    if(!ori.get(i).equals(re.get(i))){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    return false;
                }
            }
        }
        return true;
    }


    //------------------


    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * 说明：
     *
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     *
     *
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        re3(k,n,list,new ArrayList<>(),0,1);
        return list;
    }

    public static void re3(int k, int n,List<List<Integer>> res,List<Integer> curL,int curSum,int num){

        if(curSum > n || num >10 || (curL.size()>=k&&curSum < n))
            return;

        if(n == curSum){
            if(curL.size() == k){
                res.add(new ArrayList<>(curL));
            }
            return;
        }

        for(int i = num; i<=9; i++){
            curL.add(i);
            re3(k, n, res, curL, curSum+i, i+1);
            curL.remove(curL.size()-1);
        }


    }

    //----------------------------

    /**
     * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     *
     * 示例:
     *
     * nums = [1, 2, 3]
     * target = 4
     *
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     *
     * 请注意，顺序不同的序列被视作不同的组合。
     *
     * 因此输出为 7。
     */

    public int combinationSum4(int[] nums, int target) {
        return 0;
    }

    /**
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     *
     *  
     *
     * 例如：
     *
     * 输入: 原始二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     *
     * @param root
     * @return
     */

    public static TreeNode convertBST(TreeNode root) {
        re5(root,0);
        return root;
    }

    private static int re5(TreeNode node, int g){
        if(null == node){
            return g;
        }
        int ge = re5(node.right,g);

        node.val += ge;

        int res = re5(node.left,node.val);
        return res;
    }

    /**
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     */

    static int min = Integer.MAX_VALUE;

    public static int getMinimumDifference(TreeNode root) {

        return 0;

    }

    public static void re(TreeNode root,int min){

    }

    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        int[][] arr = new int[A.length][26];
        int[] tar = new int[26];
        int start = 'a';
        for(int i=0;i<A.length;i++){
            for (int j = 0; j < A[i].length(); j++) {
                arr[i][(A[i].charAt(j)-start)]++;
            }
        }

        for (int i = 0; i < tar.length; i++) {
            int min = arr[0][i];
            for(int j=0;j<A.length;j++){
                if(arr[j][i] == 0) {
                    min = 0;
                    break;
                }
                min = Math.min(arr[j][i],min);
            }
            tar[i] = min;
        }

        for (int i =0;i<tar.length;i++) {
                for(int j=0;j<tar[i];j++)
                    res.add(String.valueOf((char)('a'+i)));
        }
        return res;
    }

    /**
     * 字符串 S 由小写字母组成。
     * 我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
     * 返回一个表示每个字符串片段的长度的列表。
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-labels
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * S的长度在[1, 500]之间。
     * S只包含小写字母 'a' 到 'z' 。
     * @param args
     */

    /**
     * 1、实现一个方法，
     * 入参为一个字符串（只有字母不区分大小写），
     * 返回值为该字符串中每个字母出现的次数
     * @param
     */
    public static int[] test(String a){
        int[] res = new int[26];
        char[] source = a.toCharArray();
        int base = 'a';
        for (char c : source)
            res[Character.toLowerCase(c)-base]++;
        return res;
    }

    /**
     * 2、假设传入的字符串中包含字母的个数为N，其中不重复的字母数为M
     * 则该方法最少需要循环次数为几次？
     * @param args
     */


    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode head = new TreeNode(preorder[0]);

        return head;
    }


    public static void main(String[] args){
        /*List<List<Integer>> s = new ArrayList<>();
        s.add(Arrays.asList(1,3));
        s.add(Arrays.asList(3,0,1));
        s.add(Arrays.asList(2));
        s.add(Arrays.asList(0));
        System.out.println(canVisitAllRooms(s));

        int[] a = {10,1,2,7,6,1,5};
        List<List<Integer>> res = combinationSumVer2(a,8);
        System.out.println(res);*/

       /* TreeNode a = new TreeNode(1);
        a.left = null;
        a.right = new TreeNode(3);
        a.right.left = new TreeNode(2);
        getMinimumDifference(a);*/
        System.out.println(Arrays.toString(test("AsAAQWEQEZz")));
    }
}
