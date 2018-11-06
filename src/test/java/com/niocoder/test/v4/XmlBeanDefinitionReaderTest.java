package com.niocoder.test.v4;

import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.stereotype.Component;
import org.junit.Test;

/**
 * Created on 2018/11/6.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void testParseScanedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("niocoder-v4.xml"));

        String annotation = Component.class.getName();

        ClassPathBeanDefinitionScannerTest.testAnnotationTest(factory,annotation);
    }
}
