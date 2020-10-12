package tools;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import pojo.ExcelTur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelIntegration {


    public static void main(String[] args){
        simpleWrite();
    }

    public static void simpleWrite() {
        String fileName = "C:\\solidBackup\\rawCase\\template\\test.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        /*EasyExcel.write(fileName, ExcelTur.class).sheet(3).doWrite(data());
        */
        ExcelWriter excelWriter = EasyExcel.write(new File(fileName),ExcelTur.class).build();
        Sheet sheet = new Sheet(2);
        sheet.setStartRow(17);
        try {
            OutputStream outputStream = new FileOutputStream(new File(fileName));
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX,true);
            writer.write(data(),sheet);
            writer.finish();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        }

    public static List<ExcelTur> data(){
        List<ExcelTur> list = new ArrayList<ExcelTur>();
        for (int i = 0; i < 5; i++) {
            ExcelTur data = new ExcelTur();
            data.setItemName("test");
            data.setItemId("123456");
            data.setTenantName("集牛科技");
            data.setTenantId("00012314");
            list.add(data);
        }
        return list;
    }


    public static void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "C:\\solidBackup\\rawCase\\template\\test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelTur.class, new DemoDataListener()).sheet(2).doRead();
    }
}
