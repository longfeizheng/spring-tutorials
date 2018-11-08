package com.niocoder.beans.factory.config;

import java.util.List;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();

    void addBeanPostProcessor(BeanPostProcessor postProcessor);

    List<BeanPostProcessor> getBeanPostProcessors();
}
