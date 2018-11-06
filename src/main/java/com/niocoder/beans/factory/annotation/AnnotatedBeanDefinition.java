package com.niocoder.beans.factory.annotation;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.core.type.AnnotationMetadata;

/**
 * Created on 2018/11/6.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
