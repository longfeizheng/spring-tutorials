package com.niocoder.service.v6;

import com.niocoder.stereotype.Component;
import com.niocoder.util.MessageTracker;

/**
 * Created on 2018/11/11.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Component(value = "nioCoder")
public class NioCoderService implements INioCoderService {

    public NioCoderService() {

    }


    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }

    public void placeOrderV2() {
        System.out.println("no interception");
    }
}
