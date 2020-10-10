package test.biz;

import java.util.ArrayList;
import java.util.List;

public class demo {


   /* public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<1000;i++){
            list.add(i);
        }
        list.add(1000001);
        List<List<Integer>> res = splitArr(list);
        System.out.println(res);
    }*/

    private static List<List<Integer>> splitArr(List<Integer> list){
        List<List<Integer>> res = new ArrayList<>();
        int spl = 0;
        while(spl<list.size()){
            int toIndex = Math.min(spl + 100, list.size());
            res.add(list.subList(spl,toIndex));
            spl = toIndex;
        }
        return res;
    }
}
