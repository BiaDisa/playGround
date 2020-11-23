package tools;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import pojo.ExcelTur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelIntegration {


    public static void main(String[] args) throws FileNotFoundException {
        simpleWrite();
    }

    public static void simpleWrite() throws FileNotFoundException {
        String fileName = "C:\\solidBackup\\rawCase\\template\\testWritePlain.xlsx";
        InputStream fis = new FileInputStream(new File(fileName));
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        ExcelWriter excelWriter = null;
        try {
            // 这里 需要指定写用哪个class去写
            excelWriter = EasyExcel.write(fileName,ExcelTur.class).build();
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("新激活客户清单").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<ExcelTur> data = data();
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
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
