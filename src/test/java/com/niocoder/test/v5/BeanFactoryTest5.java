package com.niocoder.test.v5;

import com.niocoder.aop.Advice;
import com.niocoder.aop.aspectj.AspectJAfterReturningAdvice;
import com.niocoder.aop.aspectj.AspectJAfterThrowingAdvice;
import com.niocoder.aop.aspectj.AspectJBeforeAdvice;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.tx.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created on 2018/11/15.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class BeanFactoryTest5 extends AbstractV5Test {

    static String expectedExpression = "execution(* com.niocoder.service.v5.*.placeOrder(..))";

    @Test
    public void testGetBeanByType() throws Exception {
        BeanFactory factory = super.getBeanFactory("niocoder-v5.xml");

        List<Object> advices = factory.getBeansByType(Advice.class);

        Assert.assertEquals(3, advices.size());

        {
            AspectJBeforeAdvice advice = (AspectJBeforeAdvice) this.getAdivce(AspectJBeforeAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("start"), advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression, advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
        }

        {
            AspectJAfterReturningAdvice advice = (AspectJAfterReturningAdvice) this.getAdivce(AspectJAfterReturningAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("commit"), advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression, advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
        }

        {
            AspectJAfterThrowingAdvice advice = (AspectJAfterThrowingAdvice) this.getAdivce(AspectJAfterThrowingAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("rollback"), advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression, advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
        }
    }

    private Object getAdivce(Class<?> type, List<Object> advices) {
        for(Object o : advices){
            if(o.getClass().equals(type)){
                return o;
            }
        }
        return null;
    }
}
