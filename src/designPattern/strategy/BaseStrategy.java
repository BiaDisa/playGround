package designPattern.strategy;

public interface BaseStrategy {

    //返回本策略方法的类型用于区分,也可以用枚举做进一步的信息深化
    String getType();

    //参数的处理,参数也可以使用继承等方式进行拓展
    Object process(Request request);


}
