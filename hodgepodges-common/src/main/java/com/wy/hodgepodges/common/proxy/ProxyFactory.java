package com.wy.hodgepodges.common.proxy;


import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 23:37
 */
public class ProxyFactory implements InvocationHandler {

    private Class target;

    public <T>T getProxy(Class<T> c)  {
        this.target = c;
        return (T) Proxy.newProxyInstance(c.getClassLoader(),c.isInterface()?new Class[]{c}:c.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy , Method method , Object[] args) throws Throwable {
        System.out.println("代理执行执行");
        if ( !target.isInterface() ){
            method.invoke(target.newInstance(),args);
        }
        return "代理返回值";
    }

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyFactory proxyFactory = new ProxyFactory();
        IProx proxyImpl = proxyFactory.getProxy(ProxImpl.class);
        String result = proxyImpl.hello("hello word");
        System.out.println(result);
        System.out.println("---------");
        IProx proxy = proxyFactory.getProxy(IProx.class);
        result = proxy.hello("hello word");
        System.out.println(result);
    }


}

interface IProx{
    String hello(String id);
}

class ProxImpl implements IProx{

    @Override
    public String hello(String id) {
        System.out.println(id);
        return null;
    }
}