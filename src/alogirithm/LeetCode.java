package alogirithm;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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


    public static List<List<Integer>> combinationSum3(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,Integer> dic = new HashMap<>();
        for (int i : candidates) {
            int cnt = dic.getOrDefault(i,0);
            dic.put(i,cnt+1);
        }
        ArrayList<Integer> set = new ArrayList<>(dic.keySet());
        set.sort(Comparator.comparingInt(Integer::intValue));
        re3(res,dic,set,0,target,0,new ArrayList<>());
        return res;
    }

    public static void re3(List<List<Integer>> res, Map<Integer, Integer> dic, ArrayList<Integer> set, int idx, int target, int cur,List<Integer> such){
        if(idx == set.size()-1){
            return;
        }
        if(cur == target){
            res.add(new ArrayList<>(such));
            return;
        }
        int num = dic.get(set.get(idx));
        int t = set.get(idx);
        for(int i=0;i<num;i++){
            cur += i*t;
            if(cur >target){
                cur -= i*t;
                continue;
            }
            for(int j=0;j<i;j++)
                such.add(t);
            re3(res, dic, set, idx+1, target, cur, such);
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


    public static void main(String[] args){
        /*List<List<Integer>> s = new ArrayList<>();
        s.add(Arrays.asList(1,3));
        s.add(Arrays.asList(3,0,1));
        s.add(Arrays.asList(2));
        s.add(Arrays.asList(0));
        System.out.println(canVisitAllRooms(s));*/

        int[] a = {10,1,2,7,6,1,5};
        List<List<Integer>> res = combinationSum3(a,8);
        System.out.println(res);

    }
}
