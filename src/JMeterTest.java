import pojo.ExcelTur;

import java.util.*;

public class JMeterTest {

    public static HashMap vars;
    public static void main(String[] args) throws InterruptedException {
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
        /* String a = "{公司}{代理人姓名}";
        a=  a.replace("{公司}","12345");
        a = a.replace("{代理人姓名}","aaaaaaaaaaa");
        System.out.println(a);*/

/*        ExcelTur excelTur = ExcelTur.builder().itemId("123").itemName("456").itemType("类型").tenantId("ss").tenantName("go").build();
        CloneTestTur tur = (CloneTestTur) excelTur.clone();
        System.out.println(tur.equals(excelTur));
        WeakReferenceQueue<Object> weakReferenceQueue = new WeakReferenceQueue<>();
        weakReferenceQueue.add("123");
        WeakReference<Object> ref = new WeakReference(excelTur);
        System.out.println(((ExcelTur)ref.get()).getItemId());
        System.gc();
        Thread.sleep(10000l);
        System.out.println(((ExcelTur)ref.get()).getItemId());*/
        /*ExcelTur a = new ExcelTur();
        a.setTenantId("2");
        System.out.println(a.getTenantId());
        func();
        System.out.println(a.getTenantId());*/
        System.out.println(1<<30);
    }

    public static void func(){
        ExcelTur a = new ExcelTur();
        a.setTenantId("111");
        System.out.println(a.getTenantId());

    }
}
