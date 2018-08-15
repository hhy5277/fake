/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.utils;

import com.github.houbb.fake.mvc.core.exception.MvcRuntimeException;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 2018/4/12
 * 反射工具类
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class ReflectionUtils {

    /**
     * 获取方法的参数名称列表
     * @param method 方法
     * @return 参数名称列表
     */
    public static List<String> getParamNames(Method method) {
        List<String> stringList = new LinkedList<>();

        try {
            Class clazz = method.getDeclaringClass();
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(new ClassClassPath(clazz));
            CtClass cc = pool.get(clazz.getName());
            CtMethod cm = cc.getDeclaredMethod(method.getName());
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr =
                    (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null) {
                return stringList;
            }

            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < cm.getParameterTypes().length; i++) {
                stringList.add(attr.variableName(i + pos));
            }
        } catch (NotFoundException e) {
            throw new MvcRuntimeException(e);
        }

        return stringList;
    }

}
