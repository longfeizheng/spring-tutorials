package com.niocoder.test.v3;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
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
public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("niocoder-v3.xml");
        NioCoderService nioCoderService = (NioCoderService) ctx.getBean("nioCoder");

        Assert.assertNotNull(nioCoderService.getAccountDao());
        Assert.assertNotNull(nioCoderService.getItemDao());
        Assert.assertNotNull(nioCoderService.getVersion());

    }
}
