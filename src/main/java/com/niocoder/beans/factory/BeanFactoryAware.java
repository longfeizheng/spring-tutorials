package com.niocoder.beans.factory;

import com.niocoder.beans.BeansException;

/**
 * Created on 2018/11/14.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
