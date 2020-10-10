import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMeterTest {

    public static HashMap vars;
    public static void main(String[] args){
//        String response_data = prev.getResponseDataAsString();
//        JSONObject jsonObject = JSONObject.parseObject(response_data);
//        JSONArray list = jsonObject.getJSONObject("data").getJSONArray("prologue");
//
//        if (list.size() == 0){
//            vars.put("tagId",0);
//        }else{
//            Map map = JSONObject.parseObject(JSONObject.toJSONString(list.get(0)), Map.class);
//            Object tagIdVal = map.get("tagId");
//            Object tagSort = map.get("tagSort");
//            int index = -1;
//            String[] tagArr = ((String)vars.get("tagSort")).split(",");
//            for(int i=0;i<tagArr.length;i++){
//                if(tagIdVal.equals(tagArr[i])){
//                    index = (i+1)%(tagArr.length-1);
//                }
//            }
//            vars.put("expectTagId",tagArr[index]);
//            vars.put("tagId",tagIdVal.toString());
//            vars.put("tagSort",tagSort.toString());
//        }

        List<Integer> a = new ArrayList<>();
        a.forEach(System.out::println);

    }
}
