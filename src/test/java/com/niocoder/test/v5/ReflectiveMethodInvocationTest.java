package com.niocoder.test.v5;

import com.niocoder.aop.aspectj.AspectJAfterReturningAdvice;
import com.niocoder.aop.aspectj.AspectJAfterThrowingAdvice;
import com.niocoder.aop.aspectj.AspectJBeforeAdvice;
import com.niocoder.aop.aspectj.AspectJExpressionPointcut;
import com.niocoder.aop.config.AspectInstanceFactory;
import com.niocoder.aop.framework.ReflectiveMethodInvocation;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.service.v5.NioCoderService;
import com.niocoder.util.MessageTracker;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/11/12.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ReflectiveMethodInvocationTest extends AbstractV5Test{

    private  AspectJBeforeAdvice beforeAdvice = null;
    private  AspectJAfterReturningAdvice afterAdvice = null;
    private AspectJAfterThrowingAdvice afterThrowingAdvice;
    private AspectJExpressionPointcut pc = null;
    private BeanFactory beanFactory = null;
    private AspectInstanceFactory aspectInstanceFactory = null;
    private NioCoderService nioCoderService;

    @Before
    public  void setUp() throws Exception{

        MessageTracker.clearMsgs();
        nioCoderService = new NioCoderService();
        String expression = "execution(* com.niocoder.service.v5.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        beanFactory = this.getBeanFactory("niocoder-v5.xml");
        aspectInstanceFactory = this.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);

        beforeAdvice = new AspectJBeforeAdvice(
                getAdviceMethod("start"),
                pc,
                aspectInstanceFactory);

        afterAdvice = new AspectJAfterReturningAdvice(
                getAdviceMethod("commit"),
                pc,
                aspectInstanceFactory);
        afterThrowingAdvice = new AspectJAfterThrowingAdvice(
                getAdviceMethod("rollback"),
                pc,
                aspectInstanceFactory);

    }

    @Test
    public void testMethodInvocation() throws Throwable {

        Method targetMethod = NioCoderService.class.getMethod("placeOrder");

        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(beforeAdvice);
        interceptorList.add(afterAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(nioCoderService, targetMethod, new Object[0], interceptorList);

        mi.proceed();

        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }

    @Test
    public void testAfterThrowing() throws Throwable {
        Method targetMethod = NioCoderService.class.getMethod("placeOrderWithException");

        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(afterThrowingAdvice);
        interceptorList.add(beforeAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(nioCoderService, targetMethod, new Object[0], interceptorList);

        try {
            mi.proceed();
        } catch (Throwable t) {
            List<String> msgs = MessageTracker.getMsgs();

            Assert.assertEquals(2, msgs.size());
            Assert.assertEquals("start tx", msgs.get(0));
            Assert.assertEquals("rollback tx", msgs.get(1));
            return;
        }

        Assert.fail("No Exception thrown");
    }
}
