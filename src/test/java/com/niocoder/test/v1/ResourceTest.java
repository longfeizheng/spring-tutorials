package com.niocoder.test.v1;

import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.io.FileSystemResource;
import com.niocoder.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception {
        Resource r = new ClassPathResource("niocoder-v1.xml");
        try(InputStream is = r.getInputStream()){
            Assert.assertNotNull(is);
        }
    }

    @Test
    public void testFileSystemResource() throws Exception {
        Resource r = new FileSystemResource("src/test/resources/niocoder-v1.xml");
        try(InputStream is = r.getInputStream()){
            Assert.assertNotNull(is);
        }
    }
}
