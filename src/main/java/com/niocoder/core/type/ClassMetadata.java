package com.niocoder.core.type;

/**
 * Created on 2018/11/5.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface ClassMetadata {

    String getClassName();

    boolean isInterface();

    boolean isAbstract();

    boolean isFinal();

    boolean hasSuperClass();

    String getSuperClassName();

    String[] getInterfaceNames();
}
