package tools;

import java.io.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.ExcelTur;


/**
 * excel-parse-candidate
 */
public class PoiWriteExcel {


    public static void main(String[] args) throws Exception {


        FileInputStream fs = new FileInputStream("C:\\solidBackup\\rawCase\\template\\test.xlsx");  //获取d://test.xls
        //使用POI提供的方法得到excel的信息
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(2);  //获取到工作表，因为一个excel可能有多个工作表
        XSSFRow row = sheet.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
        System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格

        FileOutputStream out = new FileOutputStream("C:\\solidBackup\\rawCase\\template\\test.xlsx");  //向d://test.xls中写数据
        data().forEach(e->{
            XSSFRow r = sheet.createRow((short) (sheet.getLastRowNum() + 1)); //在现有行号后追加数据
            r.createCell(8).setCellValue(e.getItemName()); //设置第一个（有用数据）单元格的数据
            r.createCell(11).setCellValue(e.getTenantName());
            r.createCell(12).setCellValue(e.getTenantId());
        });

        out.flush();
        wb.write(out);
        out.close();
        System.out.println(row.getPhysicalNumberOfCells() + " " + row.getLastCellNum());
    }


    public static List<ExcelTur> data(){
        List<ExcelTur> list = new ArrayList<ExcelTur>();
        for (int i = 16; i < 100; i++) {
            ExcelTur data = new ExcelTur();
            data.setItemName("集牛礼品"+i);
            data.setTenantName("集牛科技");
            data.setTenantId("00010001");
            list.add(data);
        }
        return list;
    }
}










