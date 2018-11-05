package com.niocoder.core.type.classreading;

import com.niocoder.core.type.ClassMetadata;
import com.niocoder.util.ClassUtils;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

/**
 * Created on 2018/11/4.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ClassMetadataReadingVisitor extends ClassVisitor implements ClassMetadata {

    private String className;

    private boolean isInterface;

    private boolean isAbstract;

    private boolean isFinal;

    private String superClassName;

    private String[] interfaces;

    public ClassMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
        this.className = ClassUtils.convertClassNameToResourcePath(name);
        this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        this.isFinal = ((access & Opcodes.ACC_FINAL) != 0);
        if(supername !=null){
            this.superClassName = ClassUtils.convertResourcePathToClassName(supername);
        }
        this.interfaces = new String [interfaces.length];
        for(int i=0;i<interfaces.length;i++){
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    public String getClassName() {
        return className;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean hasSuperClass() {
        return (this.superClassName != null);
    }

    public String getSuperClassName() {
        return superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        return this.interfaces;
    }

    public String[] getInterfaces() {
        return interfaces;
    }
}
