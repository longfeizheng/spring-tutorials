package com.niocoder.beans.factory.support;

import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.config.TypedStringValue;

/**
 * Created on 2018/11/2.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class BeanDefinitionValueResolver {

    private final BeanFactory beanFactory;

    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else {
            // TODO
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
