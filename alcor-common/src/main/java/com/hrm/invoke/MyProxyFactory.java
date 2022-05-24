package com.hrm.invoke;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/24 19:09
 */
public class MyProxyFactory<T> implements MethodInterceptor {
    private T target;

    public MyProxyFactory(T target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer en = new Enhancer();
        en.setSuperclass(this.target.getClass());
        en.setCallback(this);

        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("start proxy");
        Object result = method.invoke(target, objects);

        System.out.println("after proxy");

        return result;
    }
}
