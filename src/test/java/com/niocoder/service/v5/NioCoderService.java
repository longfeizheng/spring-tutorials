package com.niocoder.service.v5;

import com.niocoder.beans.factory.Autowired;
import com.niocoder.dao.v5.AccountDao;
import com.niocoder.dao.v5.ItemDao;
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
public class NioCoderService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    ItemDao itemDao;

    public NioCoderService() {

    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }
}
