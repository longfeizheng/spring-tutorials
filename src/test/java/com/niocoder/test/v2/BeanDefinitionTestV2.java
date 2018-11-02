package com.niocoder.test.v2;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created on 2018/11/2.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition() throws Exception {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("niocoder-v2.xml"));

        BeanDefinition bd = factory.getBeanDefinition("nioCoder");

        List<PropertyValue> pvs = bd.getPropertyValues();
        Assert.assertTrue(pvs.size() == 2);
        {
            PropertyValue pv = this.getPropertyValues("accountDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValues("itemDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

    }

    private PropertyValue getPropertyValues(String name, List<PropertyValue> pvs) {
        for (PropertyValue pv : pvs) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }
        return null;
    }
}
