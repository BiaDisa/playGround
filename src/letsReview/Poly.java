package letsReview;


import javax.annotation.Resource;

public class Poly {


    public static String testOnFinally(){
        String res = "try";
        try{
            System.out.println("on try");
            return res;
        }catch (Exception e){

        }finally {
            System.out.println("on finally");
            res += "1234";
        }
        return "common";
    }

    public static void main(String[] args) {

       /* Human human = new Fat();
        human.eat();
        if(human instanceof  Fat){
            System.out.println("yes");
        }
        human =  new Human();
        ((Fat)human).eat();*/

        /*Human human = new Human();
        human.eat();
        Fat fat = new Fat();
        human = fat;
        human.eat();
        ((Fat) human).go();
        fat.eat();*/

/*        MockAbsClazz impl = new AbsTestImpl();
        impl.innerAbsTest.rush();*/

//        String test = "A";
//        Fat testFinalOnMethodParam = new Fat();
//        testFinalOnMethodParam.setA(test);
//        test = "B";
//        testFinalOnMethodParam.setB(test);
//        test = "C";
//        System.out.println("A : "+testFinalOnMethodParam.getA() + ",B : " + testFinalOnMethodParam.getB());

        /**
         * test on final on param
         */
       /*
       String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = (d + 2).intern();
        System.out.println((a == c));
        System.out.println((a == e));*/

        /**
         * finally test
         */
        System.out.println(testOnFinally());
    }
}
class Human{
    public void eat(){
        System.out.println("I'm full");
    }
}

//------------poly
class Fat extends Human{
     private String a = "";
     String b = "";
    public void eat(){
        System.out.println("gain fat");
    }
    public void go(){
        System.out.println("just sit");
    }
    public void setA(final String a){
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b){
        this.b = b;
    }
}

//-----------inherited
interface MockInterfaceA{
    int a = 1;
    default void tell(){
        System.out.println("from interface");
    }
}

interface MockInterfaceB{
    int a = 1;
    default void tell(){
        System.out.println("from interface");
    }
}

abstract class MockAbsClazz implements MockInterfaceA,MockInterfaceB {

    InnerAbsTest innerAbsTest = new InnerAbsTest();

    @Override
    public void tell() {

    }

    public static class InnerAbsTest{
        int a = 10;

        public void rush(){
            System.out.println("good luck al");
        }
    }
}

class AbsTestImpl extends MockAbsClazz{

}
