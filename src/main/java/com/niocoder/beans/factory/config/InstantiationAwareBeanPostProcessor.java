package com.niocoder.beans.factory.config;

import com.niocoder.beans.BeansException;

/**
 * Created on 2018/11/8.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    boolean afterInstantiation(Object bean, String beanName) throws BeansException;

    void postProcessPropertyValues(Object bean, String beanName) throws BeansException;
}
