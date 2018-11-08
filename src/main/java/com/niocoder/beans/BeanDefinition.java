package com.niocoder.beans;

import java.util.List;

/**
 * Created on 2018/10/29.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface BeanDefinition {

    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";

    public boolean isSingleton();

    public boolean isPrototype();

    String getScope();

    void setScope(String scopt);

    String getBeanClassName();

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getID();

    boolean hasConstructorArgumentValues();

    Class<?> getBeanClass();

    boolean hasBeanClass();

    Class<?>  resolveBeanClass(ClassLoader beanClassLoader) throws ClassNotFoundException;
}
