package com.niocoder.aop;

/**
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface Pointcut {
    MethodMatcher getMethodMatcher();

    String getExpression();
}
