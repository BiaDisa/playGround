package alogirithm;

public class IntegerMathTools {

    public static int pow(int num,int time){
        int res = 1;
        for (int i = 1; i <= time; i++) {
            res *= num;
        }
        return res;
    }



    public static void main(String[] args) {
        System.out.println(pow(2,3));
    }
}
