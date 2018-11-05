package com.niocoder.test.v4;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.service.v4.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2018/11/4.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */

public class ApplicationContextTestV4 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
        NioCoderService nioCoder = (NioCoderService) ctx.getBean("nioCoder");

        Assert.assertNotNull(nioCoder.getAccountDao());
        Assert.assertNotNull(nioCoder.getItemDao());
    }

}
