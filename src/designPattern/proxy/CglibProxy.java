package designPattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {
    public static void main(String[] args) {
        // 1. 创建目标对象(被代理对象)
        Teacher teacher = new Teacher();
        // 2. 创建代理对象,同时将被代理对象传递给代理对象
        ProxyFactory factory = new ProxyFactory(teacher, new PreHandler() {
            @Override
            public void invoke(Object proxy, Method method, Object[] args) {
                System.out.println("前置通知(cglib)");
            }
        }, new PostHandler() {
            @Override
            public void invoke(Object proxy, Method method, Object result, Object[] args) {
                System.out.println("后置通知(cglib)");
            }
        });
        // 返回的是一个接口实现层, 并非目标对象哦~~~
        Teacher proxyInstance = (Teacher) factory.newProxyInstance();
        proxyInstance.teach();
        /*
         * 前置通知
         * 老师正在上课...
         * 后置通知
         */
    }

    /**
     * 具体实现层
     */
    static class Teacher {

        public void teach() {
            System.out.println("老师正在上课...");
        }
    }

    /**
     * 代理工厂
     */
    static class ProxyFactory implements MethodInterceptor {

        private Object target;
        private PreHandler preHandler;
        private PostHandler postHandler;

        public ProxyFactory(Object target, PreHandler preHandler, PostHandler postHandler) {
            this.target = target;
            this.preHandler = preHandler;
            this.postHandler = postHandler;
        }

        public Object newProxyInstance() {
            // 1.创建一个工具类
            Enhancer enhancer = new Enhancer();
            // 2.设置父类
            enhancer.setSuperclass(target.getClass());
            // 3.设置回调函数
            enhancer.setCallback(this);
            // 4.创建子类对象，即代理对象
            return enhancer.create();
        }

        /**
         * @param object 被代理的对象
         * @param method 代理的方法
         * @param args 方法的参数
         * @param methodProxy CGLIB方法代理对象
         * @return  cglib生成用来代替Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
         * @throws Throwable
         */
        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            preHandler.invoke(object, method, args);
            Object result = null;
            try {
                result = methodProxy.invokeSuper(object, args);
                /* Object result = method.invoke(target, args); */
                return result;
            } finally {
                postHandler.invoke(object, method, result, args);
            }
        }
    }

    /**
     * 前置通知
     */
    static interface PreHandler {
        void invoke(Object proxy, Method method, Object[] args);
    }

    /**
     * 后置通知
     */
    static interface PostHandler {
        void invoke(Object proxy, Method method, Object result, Object[] args);
    }
}
