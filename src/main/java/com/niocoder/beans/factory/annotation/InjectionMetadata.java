package com.niocoder.beans.factory.annotation;

import java.util.List;

/**
 * Created on 2018/11/7.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class InjectionMetadata {

    private final Class<?> targetClass;
    private List<InjectionElement> injectionElements;

    public InjectionMetadata(Class<?> targetClass, List<InjectionElement> injectionElements) {
        this.targetClass = targetClass;
        this.injectionElements = injectionElements;
    }

    public List<InjectionElement> getInjectionElements() {
        return injectionElements;
    }

    public void inject(Object taget){
        if(injectionElements==null || injectionElements.isEmpty()){
            return;
        }
        for(InjectionElement ele: injectionElements){
            ele.inject(taget);
        }
    }
}
