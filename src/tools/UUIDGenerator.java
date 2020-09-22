package tools;

import java.util.Random;
import java.util.UUID;

/**
 * @description:
 * @author: zexin.lin
 * @date: 2020/7/1 9:59
 */
public class UUIDGenerator {


    private static char[] dic = {
            '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
            'r','s','t','u','v','w','x','y','z'};


    public static String getUUID(){
        return UUID.randomUUID().toString();
    }



    public static String getShortenUUID(){
        return shorten(getUUID());
    }

    public static String shorten(String UUID){
        char[] res = UUID.replace("-","").toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<res.length;i+=2) {
            int pos = 0;
            for(int j=1;j>=0;j--) {
                if (res[i-j] > dic[9]) {
                    pos += res[i-j] + 10 - dic[10];
                }else{
                    pos += res[i-j]-dic[0];
                }
            }
            sb.append(dic[pos]);
        }
        return sb.toString();
    }


    /**
     * 生成 length 长度的随机字符串
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {

        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
                default:
            }


        }
        return sb.toString();
    }
}
