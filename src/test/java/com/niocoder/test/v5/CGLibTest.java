package com.niocoder.test.v5;

import com.niocoder.service.v5.NioCoderService;
import com.niocoder.tx.TransactionManager;
import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created on 2018/11/13.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class CGLibTest {

    @Test
    public void testCallBack() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NioCoderService.class);
        enhancer.setCallback(new TransactionInterceptor());
        NioCoderService nioCoderService = (NioCoderService) enhancer.create();
        nioCoderService.placeOrder();
    }

    /**
     * MethodInterceptor
     */
    public static class TransactionInterceptor implements MethodInterceptor {

        TransactionManager txManager = new TransactionManager();

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            txManager.start();
            Object result = methodProxy.invokeSuper(o, objects);
            txManager.commit();

            return result;
        }
    }

    @Test
    public void testFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NioCoderService.class);

        enhancer.setInterceptDuringConstruction(false);

        Callback[] callbacks = new Callback[]{new TransactionInterceptor(), NoOp.INSTANCE};

        Class<?>[] types = new Class[callbacks.length];
        for (int x = 0; x < types.length; x++) {
            types[x] = callbacks[x].getClass();
        }

        enhancer.setCallbackFilter(new ProxyCallbackFilter());
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);

        NioCoderService nioCoderService = (NioCoderService) enhancer.create();
        nioCoderService.placeOrder();
        System.out.println(nioCoderService.toString());

    }

    private static class ProxyCallbackFilter implements CallbackFilter {

        public ProxyCallbackFilter() {

        }

        @Override
        public int accept(Method method) {
            if (method.getName().startsWith("place")) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
