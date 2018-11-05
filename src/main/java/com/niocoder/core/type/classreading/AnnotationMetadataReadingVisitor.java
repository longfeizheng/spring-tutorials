package com.niocoder.core.type.classreading;

import com.niocoder.core.annotation.AnnotationAttributes;
import com.niocoder.core.type.AnnotationMetadata;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2018/11/5.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    private final Set<String> annotationSet = new LinkedHashSet<String>(4);
    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<String, AnnotationAttributes>(4);

    public AnnotationMetadataReadingVisitor() {
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
    }

    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }
}
