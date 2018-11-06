package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;

/**
 * Created on 2018/11/6.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);

}
