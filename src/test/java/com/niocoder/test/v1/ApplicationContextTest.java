package com.niocoder.test.v1;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.context.support.FileSystemXmlApplicationContext;
import com.niocoder.service.v1.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("niocoder-v1.xml");
        NioCoderService nioCoderService = (NioCoderService) ctx.getBean("nioCoder");
        Assert.assertNotNull(nioCoderService);
    }

    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/resources/niocoder-v1.xml");
        NioCoderService nioCoderService = (NioCoderService) ctx.getBean("nioCoder");
        Assert.assertNotNull(nioCoderService);
    }
}
