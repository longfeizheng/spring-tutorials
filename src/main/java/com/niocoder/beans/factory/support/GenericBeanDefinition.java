package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.ConstructorArgument;
import com.niocoder.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/10/29.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;
    private Class<?> beanClass;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;
    // 表明这个bean的定义是否是合成的
    private boolean isSynthetic = false;

    List<PropertyValue> propertyValues = new ArrayList<>();
    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public GenericBeanDefinition(Class<?> clz) {
        this.beanClass = clz;
        this.beanClassName = clz.getName();
    }

    public GenericBeanDefinition() {
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public void setBeanClassName(String className) {
        this.beanClassName = className;
    }

    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgument.isEmpty();
    }

    @Override
    public Class<?> getBeanClass() throws IllegalArgumentException {
        if (this.beanClass == null) {
            throw new IllegalStateException("Bean class name [" + this.getBeanClassName() + "] has not been resolve into an actual Class");
        }
        return this.beanClass;
    }

    @Override
    public boolean hasBeanClass() {
        return this.beanClass != null;
    }

    @Override
    public Class<?> resolveBeanClass(ClassLoader beanClassLoader) throws ClassNotFoundException {
        String className = getBeanClassName();
        if (className == null) {
            return null;
        }
        Class<?> resolvedClass = beanClassLoader.loadClass(className);
        this.beanClass = resolvedClass;
        return resolvedClass;
    }

    @Override
    public boolean isSynthetic() {
        return isSynthetic;
    }

    public void setSynthetic(boolean synthetic) {
        isSynthetic = synthetic;
    }
}
