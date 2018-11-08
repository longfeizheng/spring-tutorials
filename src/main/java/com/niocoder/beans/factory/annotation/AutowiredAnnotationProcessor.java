package com.niocoder.beans.factory.annotation;

import com.niocoder.beans.BeansException;
import com.niocoder.beans.factory.Autowired;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.config.AutowireCapableBeanFactory;
import com.niocoder.beans.factory.config.ConfigurableBeanFactory;
import com.niocoder.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.niocoder.core.annotation.AnnotationUtils;
import com.niocoder.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created on 2018/11/8.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;
    private String requiredParameterName = "required";
    private boolean requiredParameterValue = true;

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes =
            new LinkedHashSet<>();

    public AutowiredAnnotationProcessor() {
        this.autowiredAnnotationTypes.add(Autowired.class);
    }


    public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {
        List<InjectionElement> elements = new LinkedList<>();
        Class<?> targetClass = clazz;

        do {
            List<InjectionElement> currElements = new LinkedList<>();
            for (Field field : targetClass.getDeclaredFields()) {
                Annotation ann = findAutowiredAnnotation(field);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    boolean required = determineRequiredStatus(ann);
                    currElements.add(new AutowiredFieldElement(field, required, beanFactory));
                }
            }

            for (Method method : targetClass.getDeclaredMethods()) {
                //TODO
            }
            elements.addAll(0, currElements);
            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);

        return new InjectionMetadata(clazz, elements);
    }

    protected boolean determineRequiredStatus(Annotation ann) {
        try {
            Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
            if (method == null) {
                // Annotations like @Inject and @Value don't have a method (attribute) named "required"
                // -> default to required status
                return true;
            }
            return (this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann));
        } catch (Exception ex) {
            // An exception was thrown during reflective invocation of the required attribute
            // -> default to required status
            return true;
        }
    }

    private Annotation findAutowiredAnnotation(Field field) {
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            Annotation ann = AnnotationUtils.getAnnotation(field, type);
            if (ann != null) {
                return ann;
            }
        }
        return null;
    }

    public void setBeanFactory(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object beforeInitialization(Object bean, String beanName) throws BeansException {
        //do nothing
        return bean;
    }
    public Object afterInitialization(Object bean, String beanName) throws BeansException {
        // do nothing
        return bean;
    }
    public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
        // do nothing
        return true;
    }

    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
        try {
            metadata.inject(bean);
        }
        catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
        }
    }
}
