package com.niocoder.aop.config;

import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.beans.factory.BeanFactoryAware;
import com.niocoder.util.StringUtils;

/**
 * Created on 2018/11/14.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AspectInstanceFactory implements BeanFactoryAware {

    private String aspectBeanName;

    private BeanFactory beanFactory;

    public void setAspectBeanName(String aspectBeanName) {
        this.aspectBeanName = aspectBeanName;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        if (!StringUtils.hasText(this.aspectBeanName)) {
            throw new IllegalArgumentException(" 'aspectBeanName' is required");
        }
    }

    public Object getAspectInstance() throws Exception {
        return this.beanFactory.getBean(this.aspectBeanName);
    }
}
