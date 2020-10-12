package tools;

import java.io.*;

public class SourceCodeCutter {
    public static void main(String[] args) {
        String path = "C:\\solidBackup\\project-aspect\\learning-admin\\src\\main\\java"; //要遍历的路径
        File file = new File(path); //获取其file对象
        StringBuilder data = new StringBuilder();
        getFileList(file,data);


        WriteStringToFile("C:\\solidBackup\\rawCase\\soft-auth\\培训管理系统\\learningAdmin.txt",data.toString());//遍历path下的文件和目录，放在File数组中
        System.out.println(data.length());
        System.out.println("finish");
    }


    private static void getFileList(File file,StringBuilder data)
    {
        File[] fs = file.listFiles();
        for(File f:fs){ //遍历File[]数组
            System.out.println(f.getName() + "|" +f.isDirectory());
            if(!f.isDirectory()) //若非目录(即文件)，则打印

                data.append(getFromFile(f));
            else
                getFileList(f,data);
        }
    }


    public static String getFromFile(File fileName) {
        if (fileName.getName().matches(".*\\.java$")) {

            return count(fileName);
        }
        else
        {
            return "";
        }
    }

    private static String count(File f) {
        BufferedReader br = null;
        StringBuilder data = new StringBuilder();
        boolean flag = false;
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = br.readLine()) != null) {
                // 除去注释前的空格
                line = line.trim();
                // 匹配空行
                if (line.matches("^[ ]*$")) {
                //blankLines++;
                } else if (line.startsWith("//")) {
                //commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                //commentLines++;
                    flag = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                //commentLines++;
                } else if (flag) {
                //commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                //codeLines++;

                    data.append(line+"\n");
                }
            }
                //files++;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }


    private static void WriteStringToFile(String filePath,String data) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));

            ps.append(data);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
