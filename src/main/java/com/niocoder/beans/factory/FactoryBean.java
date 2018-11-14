package com.niocoder.beans.factory;

/**
 * Created on 2018/11/14.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();
}
