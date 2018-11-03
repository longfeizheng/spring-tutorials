package com.niocoder.test.v2;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.dao.v2.AccountDao;
import com.niocoder.dao.v2.ItemDao;
import com.niocoder.service.v2.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created on 2018/11/2.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("niocoder-v2.xml");
        NioCoderService nioCoderService = (NioCoderService) ctx.getBean("nioCoder");

        Assert.assertNotNull(nioCoderService.getAccountDao());
        Assert.assertNotNull(nioCoderService.getItemDao());
        Assert.assertNotNull(nioCoderService.getOwner());

        assertTrue(nioCoderService.getItemDao() instanceof ItemDao);
        assertTrue(nioCoderService.getAccountDao() instanceof AccountDao);
        assertEquals(nioCoderService.getOwner(), "nioCoder");
        assertEquals(nioCoderService.getVersion(), 3);
        assertTrue(nioCoderService.isFlag());

    }

}
