package com.niocoder.context.annotation;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.annotation.AnnotatedBeanDefinition;
import com.niocoder.beans.factory.support.BeanDefinitionRegistry;
import com.niocoder.beans.factory.support.BeanNameGenerator;
import com.niocoder.core.annotation.AnnotationAttributes;
import com.niocoder.core.type.AnnotationMetadata;
import com.niocoder.util.ClassUtils;
import com.niocoder.util.StringUtils;

import java.beans.Introspector;
import java.util.Set;

/**
 * Created on 2018/11/6.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {


    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
            if (StringUtils.hasText(beanName)) {
                // Explicit bean name found.
                return beanName;
            }
        }
        return buildDefaultBeanName(definition, registry);
    }


    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
        AnnotationMetadata amd = annotatedDef.getMetadata();
        Set<String> types = amd.getAnnotationTypes();
        String beanName = null;
        for (String type : types) {
            AnnotationAttributes attributes = amd.getAnnotationAttributes(type);
            if (attributes.get("value") != null) {
                Object value = attributes.get("value");
                if (value instanceof String) {
                    String strVal = (String) value;
                    if (StringUtils.hasLength(strVal)) {
                        beanName = strVal;
                    }
                }
            }
        }
        return beanName;
    }


    protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return buildDefaultBeanName(definition);
    }


    protected String buildDefaultBeanName(BeanDefinition definition) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName);
    }
}