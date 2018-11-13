package com.niocoder.aop.framework;

import com.niocoder.aop.Advice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created on 2018/11/13.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AopConfig {

    Class<?> getTargetClass();

    Object getTargetObject();

    boolean isProxyTargetClass();


    Class<?>[] getProxiedInterfaces();


    boolean isInterfaceProxied(Class<?> intf);


    List<Advice> getAdvices();


    void addAdvice(Advice advice) ;

    List<Advice> getAdvices(Method method/*,Class<?> targetClass*/);

    void setTargetObject(Object obj);
}
