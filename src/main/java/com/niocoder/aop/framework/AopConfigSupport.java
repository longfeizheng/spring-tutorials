package com.niocoder.aop.framework;

import com.niocoder.aop.Advice;
import com.niocoder.aop.Pointcut;
import com.niocoder.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/11/13.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AopConfigSupport implements AopConfig {

    private boolean proxyTargetClass = false;

    Object targetObject = null;

    List<Advice> advices = new ArrayList<>();

    private List<Class> interfaces = new ArrayList<>();

    public AopConfigSupport(){

    }

    public void setTargetObject(Object targetObject){
        this.targetObject = targetObject;
    }

    public Object getTargetObject(){
        return this.targetObject;
    }
    public Class<?> getTargetClass() {
        return this.targetObject.getClass();
    }



    public void addInterface(Class<?> intf) {
        Assert.notNull(intf, "Interface must not be null");
        if (!intf.isInterface()) {
            throw new IllegalArgumentException("[" + intf.getName() + "] is not an interface");
        }
        if (!this.interfaces.contains(intf)) {
            this.interfaces.add(intf);

        }
    }

    /**
     * Remove a proxied interface.
     * <p>Does nothing if the given interface isn't proxied.
     * @param intf the interface to remove from the proxy
     * @return {@code true} if the interface was removed; {@code false}
     * if the interface was not found and hence could not be removed
     */
	/*public boolean removeInterface(Class<?> intf) {
		return this.interfaces.remove(intf);
	}*/

    public Class<?>[] getProxiedInterfaces() {
        return this.interfaces.toArray(new Class[this.interfaces.size()]);
    }

    public boolean isInterfaceProxied(Class<?> intf) {
        for (Class proxyIntf : this.interfaces) {
            if (intf.isAssignableFrom(proxyIntf)) {
                return true;
            }
        }
        return false;
    }


    public void addAdvice(Advice advice)  {
        this.advices.add(advice);
    }


    public boolean isProxyTargetClass() {

        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public List<Advice> getAdvices() {

        return this.advices;
    }

    public List<Advice> getAdvices(Method method) {
        List<Advice> result = new ArrayList<Advice>();
        for(Advice advice : this.getAdvices()){
            Pointcut pc = advice.getPointcut();
            if(pc.getMethodMatcher().matches(method)){
                result.add(advice);
            }
        }
        return result;
    }
}
