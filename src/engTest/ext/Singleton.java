package engTest.ext;


/**
 * 单例饿汉式构造方法，构造方法会先于静态变量的赋值前进行
 */
public class Singleton {



    public static int X = 0;
    public static int Y ;
    private static Singleton instance = new Singleton();

    private Singleton(){
        X++;
        Y++;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args){
        Singleton singleton = new Singleton();
        System.out.println(Singleton.X);
        System.out.println(Singleton.Y);
    }


}

class Test{

        }


