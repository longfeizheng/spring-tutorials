package com.niocoder.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created on 2018/11/12.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface Advice extends MethodInterceptor {
    Pointcut getPointcut();
}
