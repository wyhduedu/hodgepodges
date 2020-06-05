package com.wy.hodgepodges.common.proxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 23:35
 */
public class CgLib {

    public static void proxy(Class clz) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/wy/project/workspace/hodgepodges/hodgepodges-web/src/test/java/com/wy/hodgepodges/");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(clz);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyMethodInterceptor());
        // 创建代理对象
        HelloService proxy= (HelloService)enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();
    }

    public static void main(String[] args) {
        proxy(HelloService.class);
    }


}
