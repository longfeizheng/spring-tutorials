package com.niocoder.beans;

/**
 * Created on 2018/11/2.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class PropertyValue {

    private final String name;
    private final Object value;

    private Boolean converted = false;

    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return this.converted;
    }


    public synchronized void setConvertedValue(Object value) {
        this.converted = true;
        this.convertedValue = value;
    }

    public synchronized Object getConvertedValue() {
        return this.convertedValue;
    }
}
