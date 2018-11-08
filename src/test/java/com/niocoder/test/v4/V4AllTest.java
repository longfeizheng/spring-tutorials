package com.niocoder.test.v4;

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
        ApplicationContextTestV4.class,
        ClassReaderTest.class,
        PackageResourceLoaderTest.class,
        MetadataReaderTest.class,
        AutowiredAnnotationProcessorTest.class,
        InjectionMetadataTest.class,
        ApplicationContextTestV4.class,
        XmlBeanDefinitionReaderTest.class
})
public class V4AllTest {
}
