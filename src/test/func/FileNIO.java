package test.func;

import tools.HttpIOUtils;

import java.io.File;
import java.io.InputStream;

public class FileNIO {
    private static final String dir = "C:\\Users\\86134\\Desktop\\md\\note";

    public static void main(String[] args){
        File in = new File(dir + "\\后台篇.pdf");
        InputStream is = HttpIOUtils.getStreamFromFile(in);
        HttpIOUtils.downloadInputStreamVer2(is,"pdf");
    }

//    public static void testNIOFileTranslate(){
//        StringBuffer[] sb = new StringBuffer[];
//    }
}
