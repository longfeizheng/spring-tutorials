package com.niocoder.aop.aspectj;

import com.niocoder.aop.Advice;
import com.niocoder.aop.Pointcut;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created on 2018/11/12.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AspectJAfterReturningAdvice implements Advice {
    private Method adviceMethod;
    private Pointcut pointcut;
    private Object adviceObject;

    public AspectJAfterReturningAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object o = invocation.proceed();
        // 例如:调用TransactionManager的commit方法
        adviceMethod.invoke(adviceObject);
        return o;
    }
}
