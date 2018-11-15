package com.niocoder.test.v5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 2018/11/10.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MethodLocatingFactoryTest.class,
        PointcutTest.class,
        BeanDefinitionTestV5.class,
        BeanFactoryTest5.class,
        CglibAopProxyTest.class,
        ReflectiveMethodInvocationTest.class,
        ApplicationContextTest5.class
})
public class V5AllTest {
}
