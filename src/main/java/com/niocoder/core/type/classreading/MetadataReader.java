package com.niocoder.core.type.classreading;

import com.niocoder.core.io.Resource;
import com.niocoder.core.type.AnnotationMetadata;
import com.niocoder.core.type.ClassMetadata;

/**
 * Created on 2018/11/5.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface MetadataReader {

    Resource getResource();

    ClassMetadata getClassMetadata();

    AnnotationMetadata getAnnotationMetadata();
}
