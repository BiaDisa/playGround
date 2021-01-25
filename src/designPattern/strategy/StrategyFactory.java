package designPattern.strategy;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StrategyFactory implements InitializingBean, ApplicationContextAware {

    private HashMap<String,BaseStrategy> strategyDic = new HashMap<>();

    private ApplicationContext appContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        appContext.getBeansOfType(BaseStrategy.class)
                .values()
                .forEach(handler -> strategyDic.put(handler.getType(), handler));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public Object process(Request request){
        BaseStrategy cur = strategyDic.get(request.getSubmitType());
        if(null == cur){
            return "暂无法支持的类型";
        }
        return cur.process(request);
    }
}
