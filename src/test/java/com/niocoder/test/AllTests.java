package com.niocoder.test;

import com.niocoder.test.v1.V1AllTest;
import com.niocoder.test.v2.V2AllTest;
import com.niocoder.test.v3.V3AllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 2018/11/3.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTest.class,
        V2AllTest.class,
        V3AllTest.class
//        V4AllTest.class
})
public class AllTests {
}
