package com.niocoder.test.v4;

import com.niocoder.core.io.Resource;
import com.niocoder.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2018/11/4.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class PackageResourceLoaderTest {

    @Test
    public void testGetResources() throws Exception {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.niocoder.dao.v4");
        Assert.assertEquals(2, resources.length);
    }
}
