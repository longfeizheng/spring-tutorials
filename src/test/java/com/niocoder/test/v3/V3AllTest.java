package com.niocoder.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 2018/11/3.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV3.class,
        BeanDefinitionTestV3.class,
        ConstructorResolverTest.class
})
public class V3AllTest {
}
