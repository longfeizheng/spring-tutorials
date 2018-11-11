package com.niocoder.test.v5;

import com.niocoder.aop.aspectj.AspectJExpressionPointcut;
import com.niocoder.aop.MethodMatcher;
import com.niocoder.service.v5.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class PointcutTest {

    @Test
    public void testPointcut() throws Exception {
        String expression = "execution(* com.niocoder.service.v5.*.placeOrder(..))";
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        MethodMatcher mm = pc.getMethodMatcher();

        {
            Class<?> targetClass = NioCoderService.class;

            Method method1 = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(method1));

            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));
        }

        {
            Class<?> targetClass = com.niocoder.service.v4.NioCoderService.class;
            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));
        }
    }
}
