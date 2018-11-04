package com.niocoder.service.v3;

import com.niocoder.dao.v3.AccountDao;
import com.niocoder.dao.v3.ItemDao;

/**
 * Created on 2018/10/29.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class NioCoderService {

    private AccountDao accountDao;
    private ItemDao itemDao;
    private int version;

    public NioCoderService() {
    }

    public NioCoderService(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = -1;
    }

    public NioCoderService(AccountDao accountDao, ItemDao itemDao, int version) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = version;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public int getVersion() {
        return version;
    }
}
