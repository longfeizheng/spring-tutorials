package com.niocoder.core.type;

import com.niocoder.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * Created on 2018/11/5.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface AnnotationMetadata extends ClassMetadata {

    Set<String> getAnnotationTypes();

    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
