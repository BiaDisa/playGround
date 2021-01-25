package designPattern.strategy.func;

import designPattern.strategy.BaseStrategy;
import designPattern.strategy.Request;
import org.springframework.stereotype.Component;

@Component
public class InsertStrategy implements BaseStrategy {

    private final String type = "INSERT";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Object process(Request request) {
        System.out.println(type);
        return type;
    }
}
