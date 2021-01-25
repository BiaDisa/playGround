package designPattern.strategy.func;

import com.sun.xml.internal.rngom.parse.host.Base;
import designPattern.strategy.BaseStrategy;
import designPattern.strategy.Request;
import org.springframework.stereotype.Component;

@Component
public class UpdateStrategy implements BaseStrategy {

    private final static String type = "Update";

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
