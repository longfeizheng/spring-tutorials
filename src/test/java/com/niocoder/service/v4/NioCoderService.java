package com.niocoder.service.v4;

import com.niocoder.beans.factory.Autowired;
import com.niocoder.dao.v3.ItemDao;
import com.niocoder.dao.v4.AccountDao;
import com.niocoder.stereotype.Component;

/**
 * Created on 2018/10/29.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Component(value = "nioCoder")
public class NioCoderService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;


    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
