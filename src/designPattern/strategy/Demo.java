package designPattern.strategy;


public class Demo {
    public static void main(String[] args) {
        Request request = new Request();
        request.setSubmitType("INSERT");
        StrategyFactory factory = new StrategyFactory();
        factory.process(request);
    }
}
