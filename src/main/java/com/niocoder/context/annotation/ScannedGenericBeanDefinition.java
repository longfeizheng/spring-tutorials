package com.niocoder.context.annotation;

import com.niocoder.beans.factory.annotation.AnnotatedBeanDefinition;
import com.niocoder.beans.factory.support.GenericBeanDefinition;
import com.niocoder.core.type.AnnotationMetadata;

/**
 * Created on 2018/11/6.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        this.metadata = metadata;
        setBeanClassName(this.metadata.getClassName());
    }


    public final AnnotationMetadata getMetadata() {
        return metadata;
    }
}
