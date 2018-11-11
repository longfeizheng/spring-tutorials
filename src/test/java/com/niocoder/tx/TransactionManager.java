package com.niocoder.tx;

import com.niocoder.util.MessageTracker;
import org.junit.Before;

/**
 * 抽象类，直接使用，防止new
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class TransactionManager {

    @Before
    public void setUp() {
        MessageTracker.clearMsgs();
    }

    public void start() {
        System.out.println("start tx");
        MessageTracker.addMsg("start tx");
    }

    public void commit() {
        System.out.println("commit tx");
        MessageTracker.addMsg("commit tx");
    }

    public void rollback() {
        System.out.println("rollback tx");
    }
}
