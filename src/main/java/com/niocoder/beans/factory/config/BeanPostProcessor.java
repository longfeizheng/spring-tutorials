package com.niocoder.beans.factory.config;

import com.niocoder.beans.BeansException;

/**
 * Created on 2018/11/8.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface BeanPostProcessor {

    Object beforeInitialization(Object bean, String beanName) throws BeansException;

    Object afterInitialization(Object bean, String beanName) throws BeansException;
}
