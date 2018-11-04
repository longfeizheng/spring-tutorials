package com.niocoder.test.v3;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.support.ConstructorResolver;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.service.v3.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2018/11/3.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ConstructorResolverTest {

    @Test
    public void testAutowireConstructor() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("niocoder-v3.xml"));

        BeanDefinition bd = factory.getBeanDefinition("nioCoder");

        ConstructorResolver resolver = new ConstructorResolver(factory);

        NioCoderService nioCoderService = (NioCoderService) resolver.autowireConstructor(bd);
        // 验证实通过构造函数初始化
        Assert.assertEquals(1, nioCoderService.getVersion());
        Assert.assertNotNull(nioCoderService.getAccountDao());
        Assert.assertNotNull(nioCoderService.getItemDao());
    }
}
