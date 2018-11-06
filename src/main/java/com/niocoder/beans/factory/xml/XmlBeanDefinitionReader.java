package com.niocoder.beans.factory.xml;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.ConstructorArgument;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.config.TypedStringValue;
import com.niocoder.beans.factory.support.BeanDefinitionRegistry;
import com.niocoder.beans.factory.support.GenericBeanDefinition;
import com.niocoder.context.annotation.ClassPathBeanDefinitionScanner;
import com.niocoder.core.io.Resource;
import com.niocoder.util.StringUtils;
import lombok.extern.java.Log;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Created on 2018/11/1.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Log
public class XmlBeanDefinitionReader {

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

    public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";

    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinition(Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> elementIterator = root.elementIterator();
            while (elementIterator.hasNext()) {
                Element ele = elementIterator.next();
                String namespaceUri = ele.getNamespaceURI();
                if(this.isDefaultNamespace(namespaceUri)){
                    parseDefaultElement(ele); //普通的bean
                }else if(this.isContextNamespace(namespaceUri)){
                    parseComponentElement(ele); // 例如<context:component-scan>
                }
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document", e);
        }
    }

    private void parseComponentElement(Element ele) {
        String basePackages = ele.attributeValue(BASE_PACKAGE_ATTRIBUTE);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.doScan(basePackages);

    }
    private void parseDefaultElement(Element ele) {
        String id = ele.attributeValue(ID_ATTRIBUTE);
        String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
        BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
        if (ele.attribute(SCOPE_ATTRIBUTE)!=null) {
            bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
        }
        parseConstructorArgElements(ele,bd);
        parsePropertyElement(ele,bd);
        this.registry.registerBeanDefinition(id, bd);

    }

    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        Iterator iterator = ele.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propElem = (Element) iterator.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                log.info("Tag 'property' must have a 'name' attribute");
                return;
            }

            Object val = parsePropertyValue(propElem, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);

            bd.getPropertyValues().add(pv);
        }
    }

    public boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }
    public boolean isContextNamespace(String namespaceUri){
        return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
    }

    public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";


        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                log.info(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));

            return valueHolder;
        } else {

            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }

    public void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
        Iterator iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while(iter.hasNext()){
            Element ele = (Element)iter.next();
            parseConstructorArgElement(ele, bd);
        }

    }
    public void parseConstructorArgElement(Element ele, BeanDefinition bd) {

        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        bd.getConstructorArgument().addArgumentValue(valueHolder);
    }

    public BeanDefinition getBeanDefinition(String beanId) {
        return this.registry.getBeanDefinition(beanId);
    }

}
