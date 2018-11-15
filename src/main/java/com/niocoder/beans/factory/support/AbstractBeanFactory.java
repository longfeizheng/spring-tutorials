package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.config.ConfigurableBeanFactory;

/**
 * Created on 2018/11/15.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    protected abstract Object createBean(BeanDefinition bd) throws BeanCreationException;
}
