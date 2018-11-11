package com.niocoder.beans.factory;

/**
 * Created on 2018/10/29.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface BeanFactory {

    Object getBean(String beanId);

    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
}
