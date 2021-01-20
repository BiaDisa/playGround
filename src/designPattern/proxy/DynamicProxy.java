package designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args) {
        // 1. 创建目标对象(被代理对象)
        Teacher teacher = new Teacher();
        // 2. 创建代理对象,同时将被代理对象传递给代理对象
        ProxyFactory factory = new ProxyFactory(teacher, new PreHandler() {
            @Override
            public void invoke() {
                System.out.println("前置通知");
            }
        }, new PostHandler() {
            @Override
            public void invoke() {
                System.out.println("后置通知");
            }
        });
        // 返回的是一个接口实现层, 并非目标对象哦~~~
        ITeacher proxyInstance = (ITeacher) factory.newProxyInstance();
        proxyInstance.teach();
        /*
         * 前置通知
         * 老师正在上课...
         * 后置通知
         */
    }

    /**
     * 抽象接口
     */
    interface ITeacher {

        /**
         * 抽象方法
         */
        void teach();
    }

    /**
     * 具体实现层
     */
    static class Teacher implements ITeacher {

        @Override
        public void teach() {
            System.out.println("老师正在上课...");
        }
    }

    /**
     * 代理工厂
     */
    static class ProxyFactory {

        private Object target;
        private PreHandler preHandler;
        private PostHandler postHandler;

        public ProxyFactory(Object target, PreHandler preHandler, PostHandler postHandler) {
            this.target = target;
            this.preHandler = preHandler;
            this.postHandler = postHandler;
        }

        public Object newProxyInstance() {
            // ClassLoader loader,Class<?>[] interfaces,InvocationHandler h
            return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                    new InvocationHandler(){
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    preHandler.invoke();
                    Object result = null;
                    try {
                        // 执行实际的方法, 一定是使用目标对象执行(被代理对象), 也就是说代理对象调用被代理对象的方法
                        result = method.invoke(target, args);
                        return result;
                    } finally {
                        postHandler.invoke();
                    }
                }
            });
        }
    }

    /**
     * 前置通知
     */
    interface PreHandler {
        void invoke();
    }

    /**
     * 后置通知
     */
    interface PostHandler {
        void invoke();
    }

}
