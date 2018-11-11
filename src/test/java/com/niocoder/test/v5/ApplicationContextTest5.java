package com.niocoder.test.v5;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.service.v5.NioCoderService;
import com.niocoder.util.MessageTracker;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ApplicationContextTest5 {

    @Test
    public void test() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("niocoder-v5.xml");
        NioCoderService nioCoder = (NioCoderService) ctx.getBean("nioCoder");

        Assert.assertNotNull(nioCoder.getAccountDao());
        Assert.assertNotNull(nioCoder.getItemDao());

        nioCoder.placeOrder();

        //
        List<String> msg = MessageTracker.getMsgs();

        Assert.assertEquals(3, msg.size());
        Assert.assertEquals("start tx", msg.get(0));
        Assert.assertEquals("place order", msg.get(1));
        Assert.assertEquals("commit tx", msg.get(2));
    }
}
