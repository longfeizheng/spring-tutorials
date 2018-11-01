package com.niocoder.beans.factory;

import com.niocoder.beans.factory.support.BeanDefinitionRegistry;

/**
 * Created on 2018/10/29.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface BeanFactory extends BeanDefinitionRegistry {

    Object getBean(String beanId);
}
