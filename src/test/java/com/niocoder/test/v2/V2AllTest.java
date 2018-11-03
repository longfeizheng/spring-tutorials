package com.niocoder.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest.class,
        CustomNumberEditorTest.class,
        CustomBooleanEditorTest.class,
        TypeConverterTest.class
})
public class V2AllTest {
}
