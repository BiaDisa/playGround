package pojo.innerStaticClassTest;

import lombok.Data;

public class StaticInnerClassTest {

    public void init(){
        InnerStaticTest.setI(10);
    }

    public void print(){
        System.out.println(InnerStaticTest.getI());
    }

    @Data
    private static class InnerStaticTest{
        private static int i ;

        public static int getI() {
            return i;
        }

        public static void setI(int i) {
            InnerStaticTest.i = i;
        }
    }

    public static void main(String[] args) {
        StaticInnerClassTest test1 = new StaticInnerClassTest();
        test1.init();
        test1.print();
        StaticInnerClassTest test2 = new StaticInnerClassTest();
        test2.print();
    }
}

