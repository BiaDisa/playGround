import cacheAnnoDemo.item.CacheKeyStrategy;
import cacheAnnoDemo.item.CacheMethodEnum;
import com.jiniu.common.core.utils.DateUtils;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class main {

    private final static int BASE_INTERVAL = 5*60*1000;//单位：ms;

    public static void main(String[] args) throws Exception {
        /*String s = "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";
        File f  = new File("resources/tmp/a.jpeg");
        if(!f.exists()) f.createNewFile();
        byte[] res = Base64.getDecoder().decode(s);
        FileOutputStream outputStream = new FileOutputStream(f);
        outputStream.write(res);
        outputStream.close();*/

/*        Class clazz = main.class;
        Method[] methods = clazz.getMethods();
        HashMap<String,Method> method = new HashMap<>();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
            //bad call
            method.put(methods[i].getName(),clazz.getDeclaredMethod(methods[i].getName()));
        }
        System.out.println();*/

       /* Method method;
        String methodName = "concat";
        Class clazz = CacheKeyStrategy.class;
        try {
            method = clazz.getDeclaredMethod(methodName,Object[].class);
            System.out.println(method.getName());
            Object[] arg = {"s","go"};
            Object o = new Object[]{arg};
            Object res = method.invoke(clazz.newInstance(),o);
            System.out.println(res.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/

/*
        System.out.println(CacheMethodEnum.SIMPLE_CONCAT.generateKey(new Object[]{1,23}));
*/


    }
/*
    private static int getRandomInterval(){
        Random random = new Random();
        return BASE_INTERVAL+random.nextInt(BASE_INTERVAL);
    }*/
}
