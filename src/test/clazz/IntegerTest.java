package test.clazz;


import java.util.concurrent.atomic.AtomicInteger;

public class IntegerTest {

    public static void main(String[] args){
        Integer a = new Integer(1000);
        Integer b = new Integer(1000);
        Integer c = 1000;
        Integer d = 1000;
        for(int i = 0;i<10;i++) {
            //even in loop,value not in init pool wouldn't put into constant cache in Integer class
            System.out.println((a == b ));
            System.out.println(c == d);
        }
        int ac = 1000;
        for(int i=0;i<10;i++){
            //obj can correctly compare with constant
            System.out.println("constant:"+(ac == a));
        }

        Integer e = Integer.valueOf(1000);
        Integer f = Integer.valueOf(1000);
        for (int i = 0; i < 10; i++) {
            //any type of init of Integer cannot
            System.out.println("valueOfType:"+(e == f));
        }
        AtomicInteger integer = new AtomicInteger(1);
    }

}
