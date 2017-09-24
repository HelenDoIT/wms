package com._520it.wms.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Helen MM on 2017/8/22.
 */
@Target(ElementType.METHOD)//贴在方法上
@Retention(RetentionPolicy.RUNTIME)//可以存在于JVM中
public @interface RequiredPermission {
     String value();//权限的名字
}
