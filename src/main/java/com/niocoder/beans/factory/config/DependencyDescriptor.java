package com.niocoder.beans.factory.config;

import java.lang.reflect.Field;

/**
 * Created on 2018/11/7.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class DependencyDescriptor {

    private Field field;
    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        this.field = field;
        this.required = required;
    }

    public Class<?> getDependencyType(){
        if(this.field!=null){
            return field.getType();
        }
        // TODO 构造器注入
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }
}
