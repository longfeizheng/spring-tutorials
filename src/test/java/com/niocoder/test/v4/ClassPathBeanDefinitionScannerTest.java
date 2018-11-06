package com.niocoder.test.v4;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.context.annotation.ClassPathBeanDefinitionScanner;
import com.niocoder.context.annotation.ScannedGenericBeanDefinition;
import com.niocoder.core.annotation.AnnotationAttributes;
import com.niocoder.core.type.AnnotationMetadata;
import com.niocoder.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2018/11/5.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScanedBean(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackages = "com.niocoder.service.v4,com.niocoder.dao.v4";

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);

        String annotation = Component.class.getName();

        testAnnotationTest(factory, annotation);
    }

    public static void testAnnotationTest(DefaultBeanFactory factory, String annotation) {
        {
            BeanDefinition bd = factory.getBeanDefinition("nioCoder");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();

            Assert.assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            Assert.assertEquals("nioCoder",attributes.getString("value"));
        }

        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }

        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
