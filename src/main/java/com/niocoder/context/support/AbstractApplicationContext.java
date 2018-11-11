package com.niocoder.context.support;

import com.niocoder.beans.factory.NoSuchBeanDefinitionException;
import com.niocoder.beans.factory.annotation.AutowiredAnnotationProcessor;
import com.niocoder.beans.factory.config.ConfigurableBeanFactory;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.context.ApplicationContext;
import com.niocoder.core.io.Resource;
import com.niocoder.util.ClassUtils;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile, ClassLoader cl) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
        factory.setBeanClassLoader(cl);
        registerBeanPostProcessors(factory);
    }

    public AbstractApplicationContext(String configFile) {
        this(configFile, ClassUtils.getDefaultClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    protected abstract Resource getResourceByPath(String path);

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    protected void registerBeanPostProcessors(ConfigurableBeanFactory beanFactory) {
        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
        processor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(processor);
    }

    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return this.factory.getType(name);
    }
}
