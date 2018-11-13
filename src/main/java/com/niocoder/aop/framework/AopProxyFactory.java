package com.niocoder.aop.framework;

/**
 * Created on 2018/11/13.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AopProxyFactory {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
