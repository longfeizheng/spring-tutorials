package com.niocoder.aop;

import java.lang.reflect.Method;

/**
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface MethodMatcher {

    boolean matches(Method method /*,Class<?> targetClass*/);
}
