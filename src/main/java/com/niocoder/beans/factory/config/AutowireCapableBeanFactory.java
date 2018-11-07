package com.niocoder.beans.factory.config;

import com.niocoder.beans.factory.BeanFactory;

/**
 * Created on 2018/11/7.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    Object resolveDependency(DependencyDescriptor descriptor);
}
